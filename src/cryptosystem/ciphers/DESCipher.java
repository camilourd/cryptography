package cryptosystem.ciphers;

import alphabet.alphabets.SimpleAlphabet;
import cryptosystem.Cryptosystem;
import unalcol.types.collection.bitarray.BitArray;
import cryptosystem.ciphers.des.DES;

public class DESCipher extends Cryptosystem<BitArray, String> {

	public final static int ENCRYPTION_MODE = 1;
	public final static int DECRYPTION_MODE = 2;
	
	public DESCipher() {
		super(new SimpleAlphabet("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ+-*/=<>: .,;"));
	}

	@Override
	public String encode(BitArray key, String message) {
		if(isValidKey(key)) {
			while((message.length() * 6) % 64 > 0)
				message += alphabet.getCharacter((int)(Math.random() * alphabet.size()));
			return runDES(ENCRYPTION_MODE, key, message);
		}
		return message;
	}

	private String runDES(int mode, BitArray key, String message) {
		char[] result = message.toCharArray();
		BitArray code = new BitArray(0, false), save = new BitArray(0, false);
		for(int i = 0, j = 0; i < result.length; ++i) {
			code.add(ParseBitArray(result[i]));
			if(code.size() >= 64) {
				save.add(runMode(mode, key, code.subBitArray(0, 64)));
				
				int n = save.size() / 6;
				for(int k = 0; k < n; ++k)
					result[j++] = ParseCharacter(save, k * 6);
				
				code = code.subBitArray(64);
				save = save.subBitArray(n * 6);
			}
		}
		return new String(result);
	}

	private BitArray runMode(int mode, BitArray key, BitArray code) {
		if(mode == ENCRYPTION_MODE)
			return DES.encrypt(key, code);
		return DES.decrypt(key, code);
	}

	public BitArray ParseBitArray(char c) {
		int val = alphabet.getIndex(c);
		BitArray result = new BitArray(6, false);
		for(int i = 0; i < 6; ++i)
			result.set(5 - i, (val & (1<<i)) > 0);
		return result;
	}
	
	private char ParseCharacter(BitArray save, int idx) {
		int val = 0;
		for(int i = 0; i < 6; ++i)
			val = (val<<1) + ((save.get(idx + i))? 1:0);
		return alphabet.getCharacter(val);
	}

	@Override
	public String decode(BitArray key, String message) {
		if(isValidKey(key))
			return runDES(DECRYPTION_MODE ,key, message);
		return message;
	}

	@Override
	public boolean isValidKey(BitArray key) {
		return key.size() == 64;
	}
	
	

}
