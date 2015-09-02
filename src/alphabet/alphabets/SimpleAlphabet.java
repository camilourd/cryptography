package alphabet.alphabets;

import alphabet.Alphabet;

public class SimpleAlphabet implements Alphabet {
	
	protected char[] cars;

	public SimpleAlphabet(String cars) {
		this.cars = cars.toCharArray();
	}

	@Override
	public int getIndex(char car) {
		for(int i = 0; i < cars.length; ++i)
			if(car == cars[i])
				return i;
		return -1;
	}

	@Override
	public char getCharacter(int index) {
		int idx = index % cars.length;
		return cars[(idx < 0)? cars.length + idx : idx];
	}
	
	@Override
	public char[] getCharacters() {
		return this.cars;
	}
	
}
