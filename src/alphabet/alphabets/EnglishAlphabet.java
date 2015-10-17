package alphabet.alphabets;

public class EnglishAlphabet extends StringAlphabet {

	public EnglishAlphabet() {
		super("abcdefghijklmnopqrstuvwxyz");
	}
	
	@Override
	public int getIndex(char car) {
		int index = car - 'a';
		return (index < 0)? -1 : index;
	}

}
