package cryptography.cryptosystem.ciphers.aes;

import cryptography.alphabet.alphabets.BinaryAlphabet;
import cryptography.cryptosystem.Cryptosystem;
import cryptography.tools.BitArrayTools;
import unalcol.types.collection.bitarray.BitArray;;

public class AES extends Cryptosystem<BitArray, BitArray> {
	
	protected int blockSize;
	protected int nk, nr, nb = 4;
	protected int polynomial;
	protected StateSubstitution substitution;
	protected int[][] matrixMultiplication = {
		{2, 3, 1, 1},
		{1, 2, 3, 1},
		{1, 1, 2, 3},
		{3, 1, 1, 2}
	};
	protected int[][] invMatrixMultiplication = {
		{14, 11, 13, 9},
		{9, 14, 11, 13},
		{13, 9, 14, 11},
		{11, 13, 9, 14}
	};

	public AES(int keyLenght, int polynomial) {
		super(new BinaryAlphabet());
		this.blockSize = keyLenght;
		this.nk = keyLenght >> 5;
		this.nr = this.nk + 6;
		this.polynomial = polynomial;
		this.substitution = new StateSubstitution(99, polynomial);
	}

	public StateSubstitution getSubstitution() {
		return substitution;
	}

	@Override
	public BitArray encode(BitArray key, BitArray input) {
		BitArray[] keys = KeyGenerator.generate(key, substitution);
		StateArray state = new StateArray(input, polynomial);
		addRoundKey(state, keys, 0);
		for(int round = 1; round < nr; ++round) {
			subBytes(state, StateSubstitution.SUBSTITUTE);
			shiftRows(state, StateArray.LEFT_SHIFT);
			state.multiplyColumns(matrixMultiplication, polynomial);
			addRoundKey(state, keys, round * nb);
		}
		subBytes(state, StateSubstitution.SUBSTITUTE);
		shiftRows(state, StateArray.LEFT_SHIFT);
		addRoundKey(state, keys, nr * nb);
		return state.getOutput();
	}

	public void addRoundKey(StateArray state, BitArray[] key, int i) {
		for(int col = 0; col < nb; ++col)
			state.multiplyColumn(key[i + col], col);
	}

	public void subBytes(StateArray state, int dir) {
		for(int i = 0; i < nb; ++i)
			for(int j = 0; j < nb; ++j)
				if(dir == StateSubstitution.SUBSTITUTE)
					state.setState(i, j, substitution.substitute(state.getState(i, j)));
				else
					state.setState(i, j, substitution.restore(state.getState(i, j)));
	}
	
	public void shiftRows(StateArray state, int dir) {
		for(int i = 0; i < nb; ++i)
			for(int j = 0; j < i; ++j)
				state.shift(i, dir);
	}

	@Override
	public BitArray decode(BitArray key, BitArray input) {
		BitArray[] keys = KeyGenerator.generate(key, substitution);
		StateArray state = new StateArray(input, polynomial);
		addRoundKey(state, keys, nr * nb);
		for(int round = nr - 1; round > 0; --round) {
			shiftRows(state, StateArray.RIGHT_SHIFT);
			subBytes(state, StateSubstitution.RESTORE);
			addRoundKey(state, keys, round * nb);
			state.multiplyColumns(invMatrixMultiplication, polynomial);
		}
		shiftRows(state, StateArray.RIGHT_SHIFT);
		subBytes(state, StateSubstitution.RESTORE);
		addRoundKey(state, keys, 0);
		return state.getOutput();
	}

	@Override
	public boolean isValidKey(BitArray key) {
		return key.size() == nk << 5;
	}

	@Override
	public BitArray generateKey() {
		return BitArrayTools.generate(nk << 5);
	}

}
