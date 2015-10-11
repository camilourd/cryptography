package cryptosystem.ciphers;

import alphabet.alphabets.ExtendedAlphabet;
import cryptosystem.Cryptosystem;
import cryptosystem.ciphers.aes.AES;
import cryptosystem.ciphers.aes.KeyGenerator;
import cryptosystem.ciphers.aes.StateArray;
import tools.BitArrayTools;
import unalcol.types.collection.bitarray.BitArray;

public class AESCipher extends Cryptosystem<BitArray, String> {

	protected int keyLenght;
	protected int polynomial;
	protected AES aes;
	
	public final static int ENCODE = 1;
	public final static int DECODE = 2;
	
	public AESCipher(int keyLenght, int polynomial) {
		super(new ExtendedAlphabet());
		this.keyLenght = keyLenght;
		this.polynomial = polynomial;
		aes = new AES(keyLenght, polynomial);
	}

	@Override
	public String encode(BitArray key, String message) {
		if(isValidKey(key)) {
			while((message.length() * 6) % 128 > 0)
				message += alphabet.getCharacter((int)(Math.random() * alphabet.size()));
			BitArray[] keys = KeyGenerator.generate(key, aes.getSubstitution());
			return runAES(ENCODE, keys, message);
		}
		return message;
	}
	
	private String runAES(int method, BitArray[] key, String message) {
		char[] result = message.toCharArray();
		BitArray code = new BitArray(0, false), save = new BitArray(0, false);
		for(int i = 0, j = 0; i < result.length; ++i) {
			code.add(BitArrayTools.parseBitArray(alphabet.getIndex(result[i]), 6));
			if(code.size() >= 128) {
				save.add(runMethod(method, key, code.subBitArray(0, 128)));
				int n = save.size() / 6;
				for(int k = 0; k < n; ++k)
					result[j++] = alphabet.getCharacter(BitArrayTools.parseInt(save.subBitArray(k * 6, (k + 1) * 6)));
				code = code.subBitArray(128);
				save = save.subBitArray(n * 6);
			}
		}
		return new String(result);
	}
	
	protected BitArray runMethod(int method, BitArray[] key, BitArray code) {
		StateArray state = new StateArray(code, polynomial);
		if(method == ENCODE)
			return aes.encode(key, state).getOutput();
		return aes.decode(key, state).getOutput();
	}

	@Override
	public String decode(BitArray key, String message) {
		if(isValidKey(key)) {
			BitArray[] keys = KeyGenerator.generate(key, aes.getSubstitution());
			return runAES(DECODE ,keys, message);
		}
		return message;
	}

	@Override
	public boolean isValidKey(BitArray key) {
		return key.size() == keyLenght;
	}

	@Override
	public BitArray generateKey() {
		return BitArrayTools.generate(keyLenght);
	}

}
