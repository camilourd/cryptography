package cryptosystem;

import java.lang.reflect.Array;

import alphabet.Alphabet;
import tools.ModularArithmetic;

public class Vigenere<P> extends Cryptosystem<P, P, P[]> {

	@SuppressWarnings("rawtypes")
	private Class Pclass;
	private int modulus;
	
	public Vigenere(Alphabet<P> alpha) {
		super(alpha, alpha);
		
		Pclass = alpha.getValue(0).getClass();
		modulus = alpha.getSize();
	}

	@Override
	public P[] encrypt(P[] key, P[] input) {
		
		if (!isValidKey(key)) {
			throw new IllegalArgumentException("Invalid Vigenere Key");
		}
		@SuppressWarnings("unchecked")
		P[] output = (P[])Array.newInstance(Pclass, input.length);
		
		for (int i = 0; i < input.length; i++) {
			int idx = inAlphabet.getIndex(input[i]);
			int kdx = inAlphabet.getIndex(key[i%key.length]);
			output[i] = inAlphabet.getValue(ModularArithmetic.modulo(idx+kdx, modulus));
		}
		return output;
	}

	@Override
	public P[] decrypt(P[] key, P[] input) {
		
		if (!isValidKey(key)) {
			throw new IllegalArgumentException("Invalid Vigenere Key");
		}
		@SuppressWarnings("unchecked")
		P[] output = (P[])Array.newInstance(Pclass, input.length);
		
		for (int i = 0; i < input.length; i++) {
			int idx = inAlphabet.getIndex(input[i]);
			int kdx = inAlphabet.getIndex(key[i%key.length]);
			output[i] = inAlphabet.getValue(ModularArithmetic.modulo(idx-kdx, modulus));
		}
		return output;
	}

	@Override
	public P[] generateKey(Object seed) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isValidKey(P[] key) {
		return key.length > 0;
	}

}
