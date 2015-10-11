package cryptosystem.ciphers;

import alphabet.alphabets.ExtendedAlphabet;
import cryptosystem.Cryptosystem;
import unalcol.types.collection.bitarray.BitArray;
import cryptosystem.ciphers.des.DES;
import tools.BitArrayTools;

public class DESCipher extends Cryptosystem<BitArray, String> {

	public final static int ENCODE = 1;
	public final static int DECODE = 2;
	
	protected DES des;
	
	public DESCipher() {
		super(new ExtendedAlphabet());
		des = new DES();
	}

	@Override
	public String encode(BitArray key, String message) {
		if(isValidKey(key)) {
			while((message.length() * 6) % des.getBlockSize() > 0)
				message += alphabet.getCharacter((int)(Math.random() * alphabet.size()));
			return runDES(ENCODE, key, message);
		}
		return message;
	}

	private String runDES(int method, BitArray key, String message) {
		char[] result = message.toCharArray();
		BitArray code = new BitArray(0, false), save = new BitArray(0, false);
		for(int i = 0, j = 0; i < result.length; ++i) {
			code.add(BitArrayTools.parseBitArray(alphabet.getIndex(result[i]), 6));
			if(code.size() >= des.getBlockSize()) {
				save.add(runMethod(method, key, code.subBitArray(0, des.getBlockSize())));
				int n = save.size() / 6;
				for(int k = 0; k < n; ++k)
					result[j++] = alphabet.getCharacter(BitArrayTools.parseInt(save.subBitArray(k * 6, (k + 1) * 6)));
				code = code.subBitArray(des.getBlockSize());
				save = save.subBitArray(n * 6);
			}
		}
		return new String(result);
	}

	protected BitArray runMethod(int method, BitArray key, BitArray code) {
		if(method == ENCODE)
			return des.encode(key, code);
		return des.decode(key, code);
	}

	@Override
	public String decode(BitArray key, String message) {
		if(isValidKey(key))
			return runDES(DECODE ,key, message);
		return message;
	}

	@Override
	public boolean isValidKey(BitArray key) {
		return des.isValidKey(key);
	}

	@Override
	public BitArray generateKey() {
		return des.generateKey();
	}

}
