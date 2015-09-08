package cryptosystem;

import java.lang.reflect.Array;

import alphabet.Alphabet;
import tools.ModularArithmetic;

public class Caesar<P> extends Cryptosystem<P, P, Integer> {

	@SuppressWarnings("rawtypes")
	private Class Pclass;
	private int modulus;
	
	public Caesar(Alphabet<P> alpha) {
		super(alpha, alpha);
		
		Pclass = alpha.getValue(0).getClass();
		modulus = alpha.getSize();
	}

	@Override
	public P[] encrypt(Integer key, P[] input) {
		
		if (!isValidKey(key)) {
			throw new IllegalArgumentException("Invalid Caesar Key");
		}
		key = ModularArithmetic.modulo(key, modulus);
		
		@SuppressWarnings("unchecked")
		P[] output = (P[])Array.newInstance(Pclass, input.length);
		
		for(int i = 0; i < input.length; ++i) {
			int idx = (inAlphabet.getIndex(input[i])+key)%modulus;
			assert(idx >= 0);
			output[i] = inAlphabet.getValue(idx);
		}
		return output;
	}

	@Override
	public P[] decrypt(Integer key, P[] input) {
		
		if (!isValidKey(key)) {
			throw new IllegalArgumentException("Invalid Caesar Key");
		}
		key = ModularArithmetic.modulo(key, modulus);
		
		@SuppressWarnings("unchecked")
		P[] output = (P[])Array.newInstance(Pclass, input.length);
		
		for(int i = 0; i < input.length; ++i) {
			int idx = (outAlphabet.getIndex(input[i])-key+modulus)%modulus;
			output[i] = inAlphabet.getValue(idx);
		}
		return output;
	}
	
	@Override
	public boolean isValidKey(Integer key) {
		return true;
	}
	
	@Override
	public Integer generateKey(Object seed) {
		return null;
	}
}
