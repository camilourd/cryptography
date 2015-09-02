package alphabet.alphabets;

import java.util.Arrays;

import alphabet.Alphabet;

public class SimpleAlphabet implements Alphabet {
	
	protected char[] cars;

	public SimpleAlphabet(String cars) {
		this.cars = cars.toCharArray();
		Arrays.sort(this.cars);
	}

	@Override
	public int getIndex(char car) {
		int low = 0, high = cars.length, mid;
		while(low < high) {
			mid = (low + high) >> 1;
			if(cars[mid] < car)
				low = mid + 1;
			else
				high = mid;
		}
		return (cars[low] == car)? low : -1;
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
