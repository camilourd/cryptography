package test.alphabet;

import static org.junit.Assert.*;

import org.junit.Test;

import alphabet.Alphabet;
import alphabet.alphabets.EnglishAlphabet;

public class SimpleAlphabetTest {

	Alphabet alphabet;
	char[] cars;
	
	public SimpleAlphabetTest() {
		alphabet = new EnglishAlphabet();
		cars = alphabet.getCharacters();
	}
	
	@Test
	public void getIndexTest() {
		for(int i = 0; i < cars.length; ++i)
			assertEquals(alphabet.getIndex(cars[i]), i);
		assertEquals(alphabet.getIndex('.'), -1);
	}
	
	@Test
	public void getCharacterTest() {
		for(int i = 0; i < cars.length; ++i) {
			assertEquals(alphabet.getCharacter(i), cars[i]);
			assertEquals(alphabet.getCharacter(i + 10), cars[(i + 10) % cars.length]);
			assertEquals(alphabet.getCharacter(-(i + 1)), cars[cars.length - 1 - (i % cars.length)]);
		}
	}

}
