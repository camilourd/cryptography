package cryptosystem.ciphers.des;

import unalcol.types.collection.bitarray.BitArray;

public class DESBox {
	
	protected byte[][] encodings;

	public DESBox(byte[][] encodings) {
		this.encodings = encodings;
	}
	
	public BitArray getEcoding(int f, int c) {
		byte encoding = encodings[f][c];
		BitArray result = new BitArray(4, false);
		for(int i = 0; i < result.size(); ++i)
			result.set(result.size() - 1 - i, (encoding & (1<<i)) > 0);
		return result;
	}

}