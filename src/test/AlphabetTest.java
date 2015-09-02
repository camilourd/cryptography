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
			assert alphabet.getIndex(car) == (int)(car - 'a');
	}
	
	@Test
	public void getCharacterTest() {
		for(int i = 0; i < 26; ++i)
			assert alphabet.getCharacter(i) == ('a' + i);
	}

}
