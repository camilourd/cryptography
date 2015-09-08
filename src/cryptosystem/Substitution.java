package cryptosystem;

import java.lang.reflect.Array;
import java.util.TreeMap;

import alphabet.Alphabet;
import cryptosystem.types.Function;

public class Substitution<P, C> extends Cryptosystem<P, C, Substitution.Key<P, C>> {

	@SuppressWarnings("rawtypes")
	private Class Cclass;
	@SuppressWarnings("rawtypes")
	private Class Pclass;
	
	public Substitution(Alphabet<P> inAlpha, Alphabet<C> outAlpha) {
		super(inAlpha, outAlpha);
		
		Pclass = inAlpha.getValue(0).getClass();
		Cclass = outAlpha.getValue(0).getClass();
	}

	@Override
	public C[] encrypt(Key<P, C> key, P[] input) {
		
		if (!isValidKey(key)) {
			throw new IllegalArgumentException("Invalid Subtitution Key");
		}
		@SuppressWarnings("unchecked")
		C[] output = (C[])Array.newInstance(Cclass, input.length);
		for(int i = 0; i < input.length; ++i) {
			output[i] = key.eval(input[i]);
		}
		return output;
	}

	@Override
	public P[] decrypt(Key<P, C> key, C[] input) {
		
		if (!isValidKey(key)) {
			throw new IllegalArgumentException("Invalid Subtitution Key");
		}
		@SuppressWarnings("unchecked")
		P[] output = (P[])Array.newInstance(Pclass, input.length);
		for(int i = 0; i < input.length; ++i) {
			output[i] = key.inverse(input[i]);
		}
		return output;
	}

	@Override
	public Key<P, C> generateKey(Object seed) {
		return null;
	}
	
	@Override
	public boolean isValidKey(Key<P, C> key) {
		
		for(int i = 0; i < inAlphabet.getSize(); ++i) {
			if(!key.mapped(inAlphabet.getValue(i))) {
				return false;
			}
		}
		return true;
	}
	
	public static class Key<P, C> implements Function<P, C> {
		
		private TreeMap<P, C> func;
		private TreeMap<C, P> inve;
		
		public Key() {
			func = new TreeMap<P, C>();
			inve = new TreeMap<C, P>();
		}
		
		@Override
		public void set(P x, C y) {
			
			func.put(x, y);
			inve.put(y, x);
		}

		@Override
		public boolean mapped(P x) {
			return func.containsKey(x);
		}
		
		@Override
		public C eval(P x) {
			return func.get(x);
		}

		@Override
		public P inverse(C y) {
			return inve.get(y);
		}
	}
}
