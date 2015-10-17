package alphabet.alphabets;

import java.util.Arrays;

import alphabet.Alphabet;
import math.ModularArithmetic;

public class CharacterAlphabet implements Alphabet<Character> {
	
	protected char[] cars;

	public CharacterAlphabet(String cars) {
		this.cars = cars.toCharArray();
		Arrays.sort(this.cars);
	}

	@Override
	public int getIndex(Character car) {
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
	public Character getElement(int index) {
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
