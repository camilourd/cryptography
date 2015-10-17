package alphabet.alphabets;

public class EnglishAlphabet extends CharacterAlphabet {

	public EnglishAlphabet() {
		super("abcdefghijklmnopqrstuvwxyz");
	}
	
	@Override
	public int getIndex(Character car) {
		int index = car - 'a';
		return (index < 0)? -1 : index;
	}

}
