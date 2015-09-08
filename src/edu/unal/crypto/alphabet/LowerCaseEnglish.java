package edu.unal.crypto.alphabet;

public class LowerCaseEnglish extends Alphabet<Character> {

	public static final int SIZE = 26;
	public static final LowerCaseEnglish defaultInstance;
	private static final Character[] alpha;
	
	static {
		alpha = new Character[SIZE];
		for (char c = 'a'; c <= 'z'; c++) {
			alpha[c-'a'] = c;
		}
		defaultInstance = new LowerCaseEnglish();
	}
	
	@Override
	public int getIndex(Character c) {
		return c.charValue()-'a';
	}

	@Override
	public Character getValue(int idx) {
		return alpha[idx];
	}

	@Override
	public Character[] getValues() {
		return alpha;
	}

	@Override
	public int getSize() {
		return SIZE;
	}

}
