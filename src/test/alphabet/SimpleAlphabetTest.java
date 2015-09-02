package test.alphabet;

import static org.junit.Assert.*;

import org.junit.Test;

import alphabet.Alphabet;
import alphabet.alphabets.EnglishAlphabet;

public class SimpleAlphabetTest {

	Alphabet alphabet;
	String cars;
	
	public SimpleAlphabetTest() {
		alphabet = new EnglishAlphabet();
		cars = alphabet.getCharacters();
	}
	
	@Test
	public void getIndexTest() {
		for(int i = 0; i < cars.length(); ++i)
			assertEquals(alphabet.getIndex(cars.charAt(i)), i);
	}
	
	@Test
	public void getCharacterTest() {
		for(int i = 0; i < cars.length(); ++i) {
			assertEquals(alphabet.getCharacter(i), cars.charAt(i));
			assertEquals(alphabet.getCharacter(i + 10), cars.charAt((i + 10) % cars.length()));
			assertEquals(alphabet.getCharacter(-(i + 1)), cars.charAt(cars.length() - 1 - (i % cars.length())));
		}
	}

}
