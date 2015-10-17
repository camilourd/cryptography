package alphabet.alphabets;

import alphabet.Alphabet;

public class BinaryAlphabet implements Alphabet<Boolean> {

	@Override
	public int getIndex(Boolean car) {
		return (car)? 1:0;
	}

	@Override
	public Boolean getElement(int index) {
		return index != 0;
	}

	@Override
	public int size() {
		return 2;
	}

}
