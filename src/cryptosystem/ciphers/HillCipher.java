package cryptosystem.ciphers;

import alphabet.Alphabet;
import cryptosystem.Cryptosystem;
import flanagan.math.Matrix;
import tools.Arithmetic;
import tools.ModularArithmetic;

public class HillCipher extends Cryptosystem<Matrix, String> {

	public HillCipher(Alphabet alphabet) {
		super(alphabet);
	}

	@Override
	public String encode(Matrix key, String message) {
		char[] result = message.toCharArray();
		if(isValidKey(key))
			matrixEncode(key, result);
		return new String(result);
	}
	
	@Override
	public String decode(Matrix key, String message) {
		char[] result = message.toCharArray();
		if(isValidKey(key)) {
			int inv = ModularArithmetic.modInverse((int) key.determinant(), alphabet.size());
			matrixEncode(key.cofactor().times((double) inv).transpose(), result);
		}
		return new String(result);
	}

	private void matrixEncode(Matrix key, char[] result) {
		modMatrix(key);
		int size = key.getNcol();
		double input[][] = new double[1][size];
		
		for(int i = 0; i + size <= result.length; i += size) {
			for(int j = 0; j < size; ++j)
				input[0][j] = alphabet.getIndex(result[i + j]);
			Matrix aux = (new Matrix(input)).times(key);
			for(int j = 0; j < size; ++j)
				result[i + j] = alphabet.getCharacter((int)aux.getElement(0, j));
		}
	}

	private void modMatrix(Matrix key) {
		for(int i = 0; i < key.getNrow(); ++i)
			for(int j = 0; j < key.getNcol(); ++j)
				key.setElement(i, j, ModularArithmetic.modulo((int) key.getElement(i, j), alphabet.size()));
	}

	@Override
	public boolean isValidKey(Matrix key) {
		modMatrix(key);
		int det = (int) key.determinant();
		return (det != 0) && Arithmetic.areCoprimes(det, alphabet.size());
	}

}
