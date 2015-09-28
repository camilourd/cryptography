package cryptosystem.ciphers.des;

import unalcol.types.collection.bitarray.BitArray;

public class SimplifiedDES extends DES {
	
	// Expansion permutation
	public final static DESSubstitution EP = new DESSubstitution(new byte[] {
			0, 1,  3,  2,  3,  2, 4, 5
		});
	
	// Boxes
		public final static DESBox[] S = new DESBox[]{
			new DESBox(new byte[][] {
				{5, 2, 1, 6, 3, 4, 7, 0},
		        {1, 4, 6, 2, 0, 7, 5, 3},
			}, 3),
			new DESBox(new byte[][] {
				{4, 0, 6, 5, 7, 1, 3, 2},
		        {5, 3, 0, 7, 6, 2, 1, 4},
			}, 3)
		};
	
	public static BitArray encrypt(BitArray key, BitArray message) {
		BitArray output = (BitArray) message.clone();
		if(isFeasible(key, message)) {
			BitArray[] subkeys = getEncryptKeys(key);
			BitArray[] parts = DESGenerator.divide(2, output);
			for(int i = 0; i < subkeys.length; ++i)
				calculateNextIteration(subkeys[i], parts);
			swap(parts);
			output = join(parts[0], parts[1]);
		}
		return output;
	}

	private static void calculateNextIteration(BitArray subkey, BitArray[] parts) {
		BitArray result = expand(parts[1], EP);
		result.xor(subkey);
		BitArray[] inputs = DESGenerator.divide(S.length, result);
		result = S[0].getEcoding((inputs[0].get(0))? 1:0, getInt(inputs[0].subBitArray(1)));
		result.add(S[1].getEcoding((inputs[1].get(0))? 1:0, getInt(inputs[1].subBitArray(1))));
		result.xor(parts[0]);
		parts[0] = parts[1];
		parts[1] = result;
	}
	
	private static int getInt(BitArray bits) {
		int val = 0;
		for(int i = 0; i < bits.size(); ++i)
			val = (val<<1) + (bits.get(i)? 1:0);
		return val;
	}

	private static BitArray[] getEncryptKeys(BitArray key) {
		BitArray[] subkeys = new BitArray[2];
		subkeys[0] = key.subBitArray(0, 8);
		subkeys[1] = (BitArray) subkeys[0].clone();
		DESGenerator.leftRotated(subkeys[1]);
		return subkeys;
	}

	public static boolean isFeasible(BitArray key, BitArray message) {
		return key.size() == 9 && message.size() == 12;
	}
	
	public static BitArray decrypt(BitArray key, BitArray message) {
		BitArray output = (BitArray) message.clone();
		if(isFeasible(key, message)) {
			BitArray[] subkeys = getEncryptKeys(key);
			BitArray[] parts = DESGenerator.divide(2, output);
			for(int i = subkeys.length - 1; i >= 0 ; --i)
				calculateNextIteration(subkeys[i], parts);
			swap(parts);
			output = join(parts[0], parts[1]);
		}
		return output;
	}

}

