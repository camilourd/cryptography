package alphabet.alphabets;

public class EnglishAlphabet extends SimpleAlphabet {

	public EnglishAlphabet() {
		super("abcdefghijklmnopqrstuvwxyz");
	}
	
	@Override
	public int getIndex(char car) {
		return car - 'a';
	}

}
