package cryptosystem.ciphers.aes;

import math.XorArithmetic;
import substitution.SubstitutionFunction;
import tools.BitArrayTools;
import unalcol.types.collection.bitarray.BitArray;

public class StateSubstitution implements SubstitutionFunction<BitArray, BitArray> {

	protected BitArray[][] substitution;
	protected BitArray[][] inverse;
	
	public static int SUBSTITUTE = 0;
	public static int RESTORE = 1;
	
	public StateSubstitution(int c, int polynomial) {
		substitution = new BitArray[16][16];
		inverse = new BitArray[16][16];
		
		BitArray bc = BitArrayTools.parseBitArray(c, 8);
		for(int i = 0; i < 16; ++i)
			for(int j = 0; j < 16; ++j) {
				int n = (i * 16) + j;				
				if(i + j == 0)
					substitution[i][j] = (BitArray) bc.clone();
				else {
					BitArray num = BitArrayTools.parseBitArray(XorArithmetic.multiplicativeInverse(n, polynomial), 8);
					substitution[i][j] = calculateSubstitution(num, bc);
				}
				int row = BitArrayTools.parseInt(substitution[i][j].subBitArray(0, 4));
				int col = BitArrayTools.parseInt(substitution[i][j].subBitArray(4));
				inverse[row][col] = BitArrayTools.parseBitArray(n, 8);
			}
	}
	
	private BitArray calculateSubstitution(BitArray b, BitArray c) {
		BitArray result = new BitArray(b.size(), false);
		for(int i = 0; i < b.size(); ++i) {
			boolean res = b.get(7 - i);
			for(int j = 4; j < 8; ++j)
				res ^= b.get(7 - ((i + j) % 8));
			result.set(7 - i, res ^ c.get(7 - i));
		}
		return result;
	}

	@Override
	public BitArray substitute(BitArray value) {
		int row = BitArrayTools.parseInt(value.subBitArray(0, 4));
		int col = BitArrayTools.parseInt(value.subBitArray(4));
		return (BitArray) substitution[row][col].clone();
	}

	@Override
	public BitArray restore(BitArray value) {
		int row = BitArrayTools.parseInt(value.subBitArray(0, 4));
		int col = BitArrayTools.parseInt(value.subBitArray(4));
		return (BitArray) inverse[row][col].clone();
	}

}
