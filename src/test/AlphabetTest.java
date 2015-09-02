package test;

import static org.junit.Assert.*;

import org.junit.Test;

import alphabet.Alphabet;
import alphabet.alphabets.EnglishAlphabet;

public class AlphabetTest {

	Alphabet alphabet = new EnglishAlphabet();
	
	@Test
	public void getIndexTest() {
		for(char car = 'a'; car <= 'z'; ++car)
			assertEquals(alphabet.getIndex(car), (int)(car - 'a'));
	}
	
	@Test
	public void getCharacterTest() {
		for(int i = 0; i < 26; ++i) {
			assertEquals(alphabet.getCharacter(i), 'a' + i);
			assertEquals(alphabet.getCharacter(i + 10), 'a' + ((i + 10) % 26));
			assertEquals(alphabet.getCharacter(-i - 1), 'z' - i);
		}
	}

}
