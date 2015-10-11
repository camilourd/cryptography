package cryptosystem.ciphers.aes;

import math.XorArithmetic;
import tools.BitArrayTools;
import unalcol.types.collection.bitarray.BitArray;

public class StateArray {
	
	protected BitArray[][] matrix;
	protected int nb = 4, l = 8;
	protected int polynomial;
	
	public static int LEFT_SHIFT = 0;
	public static int RIGHT_SHIFT = 1;
	
	public StateArray(BitArray input, int polynomial) {
		this.polynomial = polynomial;
		matrix = new BitArray[nb][nb];
		for(int i = 0; i < nb; ++i)
			for(int j = 0; j < nb; ++j) {
				int idx = l * ((i * nb) + j);
				matrix[j][i] = input.subBitArray(idx, idx + l);
			}
	}
	
	public BitArray getState(int i, int j) {
		return matrix[i][j];
	}
	
	public void setState(int i, int j, BitArray value) {
		matrix[i][j] = value;
	}
	
	public BitArray getOutput() {
		BitArray output = new BitArray("");
		for(int i = 0; i < nb; ++i)
			for(int j = 0; j < nb; ++j)
				output.add(matrix[j][i]);
		return output;
	}
	
	public void shift(int row, int dir) {
		if(dir == LEFT_SHIFT)
			leftShift(row);
		else
			rightShift(row);
	}
	
	private void leftShift(int row) {
		BitArray aux = matrix[row][0];
		for(int k = 0; k < (nb - 1); ++k)
			matrix[row][k] = matrix[row][k + 1];
		matrix[row][nb - 1] = aux;
	}
	
	private void rightShift(int row) {
		BitArray aux = matrix[row][nb - 1];
		for(int k = nb - 1; k > 0; --k)
			matrix[row][k] = matrix[row][k - 1];
		matrix[row][0] = aux;
	}
	
	public void multiplyColumns(int[][] matrixMultiplication, int polynomial) {
		for(int c = 0; c < nb; ++c)
			multiplyColumn(matrixMultiplication, c, polynomial);
	}

	public void multiplyColumn(int[][] matrixMultiplication, int col, int polynomial) {
		int[] values = getColumnIntValues(col);
		for(int row = 0; row < nb; ++row) {
			int res = 0;
			for(int i = 0; i < nb; ++i)
				res = XorArithmetic.mod(res ^ XorArithmetic.multiply(matrixMultiplication[row][i], values[i], polynomial), polynomial);
			matrix[row][col] = BitArrayTools.parseBitArray(res, l);
		}
	}
	
	public int[] getColumnIntValues(int col) {
		int[] values = new int[nb];
		for(int row = 0; row < nb; ++row)
			values[row] = BitArrayTools.parseInt(matrix[row][col]);
		return values;
	}
	
	public void multiplyColumn(BitArray bits, int col) {
		BitArray[] values = BitArrayTools.divide(nb, bits);
		for(int row = 0; row < nb; ++row)
			matrix[row][col].xor(values[row]);
	}

}
