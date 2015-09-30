package alphabet.alphabets;

import java.util.Arrays;

import alphabet.Alphabet;
import math.ModularArithmetic;

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
		return cars[ModularArithmetic.modulo(index, cars.length)];
	}
	
	@Override
	public char[] getCharacters() {
		return this.cars;
	}

	@Override
	public int size() {
		return cars.length;
	}
	
}
