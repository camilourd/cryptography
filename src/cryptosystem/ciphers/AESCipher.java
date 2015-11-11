package cryptosystem.ciphers;

import alphabet.alphabets.StringAlphabet;
import alphabet.alphabets.ExtendedAlphabet;
import cryptosystem.BlockCryptosystem;
import cryptosystem.ciphers.aes.AES;
import cryptosystem.ciphers.des.DESDecodingSubstitution;
import cryptosystem.ciphers.des.DESEncodingSubstitution;
import tools.BitArrayTools;
import unalcol.types.collection.bitarray.BitArray;
import unalcol.types.collection.vector.Vector;

public class AESCipher extends BlockCryptosystem<BitArray, String, BitArray> {

	protected int keyLenght;
	protected int polynomial;
	protected int blockSize;
	protected AES aes;
	
	public AESCipher(int keyLenght, int polynomial) {
		super(new AES(keyLenght, polynomial),new DESEncodingSubstitution(new ExtendedAlphabet()),
				new DESDecodingSubstitution(new ExtendedAlphabet()));
		this.keyLenght = keyLenght;
		this.polynomial = polynomial;
		aes = new AES(keyLenght, polynomial);
		this.blockSize = 128;
	}

	@Override
	public String complete(BitArray key, String message) {
		StringAlphabet alphabet = (StringAlphabet) ((DESEncodingSubstitution)encodingSubstitution).getAlphabet();
		while((message.length() * 7) % blockSize > 0)
			message += alphabet.getElement((int)(Math.random() * alphabet.size()));
		return message;
	}

	@Override
	public Vector<BitArray> divide(BitArray key, BitArray message) {
		return BitArrayTools.divide(message.size() / blockSize, message);
	}

	@Override
	public BitArray merge(BitArray key, Vector<BitArray> blocks) {
		BitArray result = new BitArray("");
		for(BitArray block: blocks)
			result.add(block);
		return result;
	}

}
