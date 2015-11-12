package cryptosystem.ciphers.des;

import alphabet.alphabets.BinaryAlphabet;
import cryptosystem.Cryptosystem;
import types.Pair;
import unalcol.types.collection.bitarray.BitArray;

public class TripleDES extends Cryptosystem<Pair<BitArray, BitArray>, BitArray> {

	protected DES cipher;
	
	public TripleDES() {
		super(new BinaryAlphabet());
		cipher = new DES();
	}
	
	@Override
	public BitArray encode(Pair<BitArray, BitArray> key, BitArray message) {
		if(isValidKey(key))
			return cipher.encode(key.first, cipher.decode(key.second, cipher.encode(key.first, message)));
		return message;
	}

	@Override
	public BitArray decode(Pair<BitArray, BitArray> key, BitArray message) {
		if(isValidKey(key))
			return cipher.decode(key.first, cipher.encode(key.second, cipher.decode(key.first, message)));
		return message;
	}

	@Override
	public boolean isValidKey(Pair<BitArray, BitArray> key) {
		return cipher.isValidKey(key.first) && cipher.isValidKey(key.second);
	}

	@Override
	public Pair<BitArray, BitArray> generateKey() {
		Pair<BitArray, BitArray> key = new Pair<BitArray, BitArray>(cipher.generateKey(), cipher.generateKey());
		return key;
	}
	
}
