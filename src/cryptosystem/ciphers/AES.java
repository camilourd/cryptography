package cryptosystem.ciphers;

import alphabet.alphabets.SimpleAlphabet;
import cryptosystem.Cryptosystem;
import math.XorArithmetic;

public class AES extends Cryptosystem<int[], String> {
	
	protected int keylenght = 32;
	protected int m = 283;
	
	public AES() {
		super(new SimpleAlphabet(" !�\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~����������������������������"));
	}
	
	@Override
	public String encode(int[] key, String message) {
		
		return null;
	}
	
	protected int[] encrypt(int[] key, int[] input) {
		int[] state = input;
		
		return new int[4];
	}

	@Override
	public String decode(int[] key, String message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isValidKey(int[] key) {
		if(key.length == keylenght) {
			for(int i = 0; i < key.length; ++i)
				if(key[i] < 0 && key[i] > 255)
					return false;
			return true;
		}
		return false;
	}
	
	public int[] generateKey() {
		int[] key = new int[keylenght];
		for(int i = 0; i < key.length; ++i)
			key[i] = (int)(Math.random() * 256);
		return key;
	}
	
	public int xtimes(int a, int x) {
		return XorArithmetic.mod(XorArithmetic.multiply(a, x), m);
	}

}
