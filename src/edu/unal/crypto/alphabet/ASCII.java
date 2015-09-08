package edu.unal.crypto.alphabet;

public class ASCII extends Alphabet<Character> {

	public static final int SIZE = 26;
	public static final ASCII defaultInstance;
	private static final Character[] alpha = new Character[SIZE];
	
	static {
		for (char i = 0; i < 256; i++) {
			alpha[i] = i;
		}
		defaultInstance = new ASCII();
	}
	
	@Override
	public int getIndex(Character c) {
		return c;
	}

	@Override
	public Character getValue(int i) {
		return alpha[i];
	}

	public Character[] getValues() {
		return alpha;
	}
	
	@Override
	public int getSize() {
		return SIZE;
	}
}
