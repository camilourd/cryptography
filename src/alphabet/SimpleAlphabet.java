package alphabet;

import java.util.Arrays;

public class SimpleAlphabet implements Alphabet {
	
	protected String cars;

	public SimpleAlphabet(String cars) {
		char[] aux = cars.toCharArray();
		Arrays.sort(aux);
		this.cars = new String(aux);
	}

	@Override
	public int getIndex(char car) {
		int low = 0, high = cars.length(), mid;
		while(low < high) {
			mid = (low + high) >> 1;
			if(cars.charAt(mid) < car)
				low = mid + 1;
			else
				high = mid;
		}
		return low;
	}

	@Override
	public char getCharacter(int index) {
		return cars.charAt(index);
	}
	
}
