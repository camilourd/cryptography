package cryptosystem.ciphers;

import alphabet.alphabets.DESAlphabet;
import cryptosystem.Cryptosystem;
import unalcol.types.collection.bitarray.BitArray;

public class TripleDES extends Cryptosystem<BitArray[], String> {

	public DESCipher cipher;
	
	public TripleDES() {
		super(new DESAlphabet());
		cipher = new DESCipher();
	}

	@Override
	public String encode(BitArray[] key, String message) {
		if(isValidKey(key))
			return cipher.encode(key[0], cipher.decode(key[1], cipher.encode(key[0], message)));
		return message;
	}

	@Override
	public String decode(BitArray[] key, String message) {
		if(isValidKey(key))
			return cipher.decode(key[0], cipher.encode(key[1], cipher.decode(key[0], message)));
		return message;
	}

	@Override
	public boolean isValidKey(BitArray[] key) {
		return key.length == 2 && cipher.isValidKey(key[0]) && cipher.isValidKey(key[1]);
	}

}
