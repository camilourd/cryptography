package cryptography.cryptosystem.ciphers.des;

import cryptography.tools.BitArrayTools;
import unalcol.types.collection.bitarray.BitArray;
import unalcol.types.collection.vector.Vector;

public class SimplifiedDES extends DES {
	
	public SimplifiedDES() {
		super();
		this.keySize = 9;
		this.blockSize = 12;
	}
	
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
	
	@Override
	public BitArray encode(BitArray key, BitArray message) {
		BitArray output = (BitArray) message.clone();
		if(isFeasible(key, message)) {
			BitArray[] subkeys = getEncryptKeys(key);
			Vector<BitArray> parts = BitArrayTools.divide(2, output);
			for(int i = 0; i < subkeys.length; ++i)
				calculateNextIteration(subkeys[i], parts);
			swap(parts);
			output = join(parts.get(0), parts.get(1));
		}
		return output;
	}

	private void calculateNextIteration(BitArray subkey, Vector<BitArray> parts) {
		BitArray result = expand(parts.get(1), EP);
		result.xor(subkey);
		Vector<BitArray> inputs = BitArrayTools.divide(S.length, result);
		result = S[0].getEcoding((inputs.get(0).get(0))? 1:0, getInt(inputs.get(0).subBitArray(1)));
		result.add(S[1].getEcoding((inputs.get(1).get(0))? 1:0, getInt(inputs.get(1).subBitArray(1))));
		result.xor(parts.get(0));
		parts.set(0, parts.get(1));
		parts.set(1, result);
	}
	
	private int getInt(BitArray bits) {
		int val = 0;
		for(int i = 0; i < bits.size(); ++i)
			val = (val<<1) + (bits.get(i)? 1:0);
		return val;
	}

	private BitArray[] getEncryptKeys(BitArray key) {
		BitArray[] subkeys = new BitArray[2];
		subkeys[0] = key.subBitArray(0, 8);
		subkeys[1] = (BitArray) subkeys[0].clone();
		DESGenerator.leftRotated(subkeys[1]);
		return subkeys;
	}
	
	@Override
	public BitArray decode(BitArray key, BitArray message) {
		BitArray output = (BitArray) message.clone();
		if(isFeasible(key, message)) {
			BitArray[] subkeys = getEncryptKeys(key);
			Vector<BitArray> parts = BitArrayTools.divide(2, output);
			for(int i = subkeys.length - 1; i >= 0 ; --i)
				calculateNextIteration(subkeys[i], parts);
			swap(parts);
			output = join(parts.get(0), parts.get(1));
		}
		return output;
	}

}

