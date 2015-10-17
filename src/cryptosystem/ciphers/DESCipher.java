package cryptosystem.ciphers;

import alphabet.alphabets.StringAlphabet;
import alphabet.alphabets.ExtendedAlphabet;
import alphabet.alphabets.HexadecimalAlphabet;
import cryptosystem.BlockCryptosystem;
import cryptosystem.ciphers.des.DES;
import cryptosystem.ciphers.des.StringBitArraySubstitution;
import unalcol.types.collection.bitarray.BitArray;
import tools.BitArrayTools;

public class DESCipher extends BlockCryptosystem<BitArray, String, BitArray> {
	
	protected int blockSize;
	
	public DESCipher() {
		super(new DES(), new StringBitArraySubstitution(new ExtendedAlphabet()),
				new StringBitArraySubstitution(new HexadecimalAlphabet()));
		this.blockSize = 64;
	}

	@Override
	public String complete(String message) {
		StringAlphabet alphabet = (StringAlphabet) encodingSubstitution.getAlphabet();
		while((message.length() * 6) % blockSize > 0)
			message += alphabet.getElement((int)(Math.random() * alphabet.size()));
		return message;
	}

	@Override
	public BitArray[] divide(BitArray message) {
		return BitArrayTools.divide(message.size() / blockSize, message);
	}

	@Override
	public BitArray merge(BitArray[] blocks) {
		BitArray result = new BitArray("");
		for(BitArray block: blocks)
			result.add(block);
		return result;
	}

}
