package cryptosystem.ciphers;

import alphabet.alphabets.StringAlphabet;
import alphabet.alphabets.ExtendedAlphabet;
import alphabet.alphabets.HexadecimalAlphabet;
import cryptosystem.BlockCryptosystem;
import cryptosystem.ciphers.aes.AES;
import cryptosystem.ciphers.des.StringBitArraySubstitution;
import tools.BitArrayTools;
import unalcol.types.collection.bitarray.BitArray;

public class AESCipher extends BlockCryptosystem<BitArray, String, BitArray> {

	protected int keyLenght;
	protected int polynomial;
	protected int blockSize;
	protected AES aes;
	
	public AESCipher(int keyLenght, int polynomial) {
		super(new AES(keyLenght, polynomial), new StringBitArraySubstitution(new ExtendedAlphabet()),
				new StringBitArraySubstitution(new HexadecimalAlphabet()));
		this.keyLenght = keyLenght;
		this.polynomial = polynomial;
		aes = new AES(keyLenght, polynomial);
		this.blockSize = 128;
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
