package test.cryptosystem;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

import alphabet.LowerCaseEnglish;
import cryptosystem.Affine;
import cryptosystem.types.Pair;
import tools.Arithmetic;
import tools.CharStream;

public class AffineCipherTest extends CryptosystemTest {

	public AffineCipherTest() {
		super(new Affine<Character>(new LowerCaseEnglish()));
	}

	@Override
	public void encodeDecodeTest() {
		
		String message = "thisisasimpletest";
		Character[] input = CharStream.fromString(message);
		@SuppressWarnings("unchecked")
		Affine<Character> algo = (Affine<Character>)cryptosystem;
		
		for(int i = 0; i < algo.getInAlphabet().getSize(); ++i) {
			Pair<Integer, Integer> key = new Pair<Integer, Integer>((int) (Math.random() * 100), i);
			if (algo.isValidKey(key)) {
				Character[] output = algo.encrypt(key, input);
				Character[] secret = algo.decrypt(key, output);				
				assertEquals(CharStream.equals(input, secret), true);
			}
		}
	}
}
