package test.alphabet;

import static org.junit.Assert.*;

import org.junit.Test;
import alphabet.alphabets.StringAlphabet;

public class SimpleAlphabetTest {

	StringAlphabet alphabet;
	char[] cars;
	
	public SimpleAlphabetTest() {
		alphabet = new StringAlphabet("aby574ok-,;");
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
			assertEquals((char)alphabet.getElement(i), cars[i]);
			assertEquals((char)alphabet.getElement(i + 10), cars[(i + 10) % cars.length]);
			assertEquals((char)alphabet.getElement(-(i + 1)), cars[cars.length - 1 - (i % cars.length)]);
		}
	}

}
