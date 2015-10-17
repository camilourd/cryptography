package cryptosystem.ciphers;

import java.util.ArrayList;

import alphabet.Alphabet;
import cryptosystem.Cryptosystem;
import math.Arithmetic;
import math.ModularArithmetic;
import tools.MathTools;
import types.Pair;

public class AffineCipher extends Cryptosystem<Pair<Integer, Integer>, String> {

	public AffineCipher(Alphabet alphabet) {
		super(alphabet);
	}

	@Override
	public String encode(Pair<Integer, Integer> key, String message) {
		char[] result = message.toCharArray();
		if(isValidKey(key))
			for(int i = 0; i < result.length; ++i)
				result[i] = alphabet.getElement((key.first * alphabet.getIndex(result[i])) + key.second);
		return new String(result);
	}

	@Override
	public String decode(Pair<Integer, Integer> key, String message) {
		char[] result = message.toCharArray();
		if(isValidKey(key)) {
			int inverse = ModularArithmetic.multiplicativeInverse(key.first, alphabet.size());
			for(int i = 0; i < result.length; ++i)
				result[i] = alphabet.getElement(inverse * (alphabet.getIndex(result[i]) - key.second));
		}
		return new String(result);
	}
	
	public boolean isValidKey(Pair<Integer, Integer> key) {
		return Arithmetic.areCoprimes(key.first, alphabet.size());
	}

	@Override
	public Pair<Integer, Integer> generateKey() {
		ArrayList<Integer> coprimes = MathTools.calculateCoprimes(alphabet.size());
		return new Pair<Integer, Integer>(coprimes.get((int)(Math.random() * coprimes.size())), (int)(Math.random() * 100));
	}

}
