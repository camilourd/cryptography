package cryptosystem.ciphers.aes;

import alphabet.alphabets.AESAlphabet;
import cryptosystem.Cryptosystem;
import tools.BitArrayTools;
import unalcol.types.collection.bitarray.BitArray;;

public class AES extends Cryptosystem<BitArray[], StateArray> {
	
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
	
	public static int ENCODE = 0;
	public static int DECODE = 1;

	public AES(int keyLenght, int polynomial) {
		super(new AESAlphabet());
		this.blockSize = keyLenght;
		this.nk = keyLenght / 32;
		this.nr = this.nk + 6;
		this.polynomial = polynomial;
		this.substitution = new StateSubstitution(99, polynomial);
	}

	public StateSubstitution getSubstitution() {
		return substitution;
	}

	@Override
	public StateArray encode(BitArray key[], StateArray state) {
		addRoundKey(state, key, 0);
		for(int round = 1; round < nr; ++round) {
			substitute(state, ENCODE);
			mixColumns(state, ENCODE);
			addRoundKey(state, key, round * nb);
		}
		substitute(state, ENCODE);
		addRoundKey(state, key, nr * nb);
		return state;
	}

	public void addRoundKey(StateArray state, BitArray[] key, int i) {
		for(int col = 0; col < nb; ++col)
			state.multiplyColumn(key[i + col], col);
	}
	
	public void substitute(StateArray state, int op) {
		if(op == ENCODE) {
			subBytes(state, StateSubstitution.SUBSTITUTE);
			shiftRows(state, StateArray.LEFT_SHIFT);
		} else {
			shiftRows(state, StateArray.RIGHT_SHIFT);
			subBytes(state, StateSubstitution.RESTORE);
		}
	}

	private void mixColumns(StateArray state, int op) {
		if(op == ENCODE)
			state.multiplyColumns(matrixMultiplication, polynomial);
		else
			state.multiplyColumns(invMatrixMultiplication, polynomial);
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
	public StateArray decode(BitArray key[], StateArray state) {
		addRoundKey(state, key, nr * nb);
		for(int round = nr - 1; round > 0; --round) {
			substitute(state, DECODE);
			addRoundKey(state, key, round * nb);
			mixColumns(state, DECODE);
		}
		substitute(state, DECODE);
		addRoundKey(state, key, 0);
		return state;
	}

	@Override
	public boolean isValidKey(BitArray key[]) {
		if(key.length != nb*(nr + 1))
			return false;
		for(int i = 0; i < key.length; ++i)
			if(key[i].size() != 32)
				return false;
		return true;
	}

	@Override
	public BitArray[] generateKey() {
		return KeyGenerator.generate(BitArrayTools.generate(nk * 32), substitution);
	}

}
