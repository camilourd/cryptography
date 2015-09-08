package test.cryptosystem;

import static org.junit.Assert.*;

import java.util.ArrayList;

import edu.unal.crypto.alphabet.*;
import edu.unal.crypto.cryptosystem.*;
import edu.unal.crypto.tools.CharStream;

public class CaesarCipherTest extends CryptosystemTest {
	
	ArrayList<Integer> shifts;
	
	public CaesarCipherTest() {
		super(new Caesar<Character>(new LowerCaseEnglish()));
		shifts = new ArrayList<Integer>();
		shifts.add(0); shifts.add(-1); shifts.add(2);
		shifts.add(10); shifts.add(-38); shifts.add(107);
	}

	@Override
	public void encodeDecodeTest() {
		
		String message = "thisisatest";
		Character[] input = CharStream.fromString(message);
		@SuppressWarnings("unchecked")
		Caesar<Character> algo = ((Caesar<Character>)cryptosystem);
		
		for(Integer shift: shifts) {
			Character[] output = algo.encrypt(shift, input);
			Character[] secret = algo.decrypt(shift, output);
			assertEquals(CharStream.equals(secret, input), true);
		}
	}
}
