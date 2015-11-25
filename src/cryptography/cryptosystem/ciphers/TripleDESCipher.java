package cryptography.cryptosystem.ciphers;

import cryptography.alphabet.alphabets.ExtendedAlphabet;
import cryptography.alphabet.alphabets.StringAlphabet;
import cryptography.cryptosystem.BlockCryptosystem;
import cryptography.cryptosystem.ciphers.des.DESDecodingSubstitution;
import cryptography.cryptosystem.ciphers.des.DESEncodingSubstitution;
import cryptography.cryptosystem.ciphers.des.TripleDES;
import cryptography.tools.BitArrayTools;
import cryptography.types.Pair;
import unalcol.types.collection.bitarray.BitArray;
import unalcol.types.collection.vector.Vector;

public class TripleDESCipher extends BlockCryptosystem<Pair<BitArray, BitArray>, String, BitArray> {

	protected int blockSize;
	
	public TripleDESCipher() {
		super(new TripleDES(), new DESEncodingSubstitution(new ExtendedAlphabet()),
				new DESDecodingSubstitution(new ExtendedAlphabet()));
		this.blockSize = 64;
	}
	
	@Override
	public String complete(Pair<BitArray, BitArray> key, String message) {
		StringAlphabet alphabet = (StringAlphabet) ((DESEncodingSubstitution)encodingSubstitution).getAlphabet();
		while((message.length() * 7) % blockSize > 0)
			message += alphabet.getElement((int)(Math.random() * alphabet.size()));
		return message;
	}

	@Override
	public Vector<BitArray> divide(Pair<BitArray, BitArray> key, BitArray message) {
		return BitArrayTools.divide(message.size() / blockSize, message);
	}

	@Override
	public BitArray merge(Pair<BitArray, BitArray> key, Vector<BitArray> blocks) {
		BitArray result = new BitArray("");
		for(BitArray block: blocks)
			result.add(block);
		return result;
	}

}
