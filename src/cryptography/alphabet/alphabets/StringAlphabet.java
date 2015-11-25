package cryptography.alphabet.alphabets;

import java.util.Arrays;

import cryptography.alphabet.Alphabet;
import cryptography.math.ModularArithmetic;

public class StringAlphabet implements Alphabet {
	
	protected char[] cars;

	public StringAlphabet(String cars) {
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
	public char getElement(int index) {
		return cars[ModularArithmetic.modulo(index, cars.length)];
	}
	
	public char[] getCharacters() {
		return this.cars;
	}

	@Override
	public int size() {
		return cars.length;
	}
	
}
