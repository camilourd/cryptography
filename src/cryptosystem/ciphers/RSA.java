package cryptosystem.ciphers;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

import alphabet.Alphabet;
import alphabet.alphabets.EnglishAlphabet;
import cryptosystem.Cryptosystem;

public class RSA extends Cryptosystem<RSA.Key, String> {

	public RSA(Alphabet alphabet) {
		super(alphabet);
	}

	@Override
	public String encode(Key key, String message) {
		
		if (!isValidKey(key)) {
			throw new IllegalArgumentException("Invalid RSA key");
		}

		BigInteger n = key.p.multiply(key.q);
		BigInteger e = key.e;
		
		int msgLength = message.length();
		int characterLength = (alphabet.size()+"").length();
		int blockSize = getBlockSize(n, characterLength);
		int r = msgLength%blockSize;
		int dummy = r == 0 ? 0 : blockSize-r;
		
		char[] input = Arrays.copyOf(message.toCharArray(), msgLength+dummy);
		for (int i = input.length-1; dummy > 0; i--, dummy--) {
			input[i] = alphabet.getCharacter(0);
		}
		String output = "";
		
		for (int i = 0; i < input.length; i += blockSize) {
			String block = "";
			for (int j = 0; j < blockSize; j++) {
				block += pad(alphabet.getIndex(input[i+j])+"", characterLength);
			}
			block = new BigInteger(block).modPow(e, n).toString();
			output += block+" ";
		}
		return output;
	}

	@Override
	public String decode(Key key, String message) {
		
		if (!isValidKey(key)) {
			throw new IllegalArgumentException("Invalid RSA key");
		}

		BigInteger phi = key.p.subtract(BigInteger.ONE);
		phi = phi.multiply(key.q.subtract(BigInteger.ONE));
		BigInteger n = key.p.multiply(key.q);
		BigInteger d = key.e.modInverse(phi);
		
		int characterLength = (alphabet.size()+"").length();
		String output = "";
		
		StringTokenizer tokenizer = new StringTokenizer(message);
		while (tokenizer.hasMoreTokens()) {
			String block = new BigInteger(tokenizer.nextToken()).modPow(d, n).toString();
			block = pad(block, characterLength);
			for (int i = 0; i < block.length(); i += characterLength) {
				int idx = 0;
				for (int k = 0; k < characterLength; k++) {
					idx *= 10;
					idx += block.charAt(i+k)-'0';
				}
				output += alphabet.getCharacter(idx);
			}
		}
		return output;
	}
	
	@Override
	public boolean isValidKey(Key key) {
		
		if (!key.p.isProbablePrime(98) || !key.q.isProbablePrime(98)) {
			return false;
		}
		BigInteger phi = key.p.subtract(BigInteger.ONE);
		phi = phi.multiply(key.q.subtract(BigInteger.ONE));
		return phi.gcd(key.e).equals(BigInteger.ONE);
	}

	private String pad(String str, int len) {
		
		int r = str.length()%len;
		for (; r-- > 0; ) {
			str = "0"+str;
		}
		return str;
	}
	
	private int getBlockSize(BigInteger n, int characterLength) {
		
		BigInteger pow = BigInteger.TEN.pow(characterLength);
		BigInteger val = BigInteger.valueOf(alphabet.size()-1);
		BigInteger bound = BigInteger.valueOf(0);
		
		while (bound.compareTo(n) < 0) {
			bound = bound.multiply(pow).add(val);
		}
		return bound.toString().length()/characterLength-1;
	}
	
	public static class Key {
		BigInteger p;
		BigInteger q;
		BigInteger e;
		
		public Key(BigInteger p, BigInteger q, BigInteger e) {
			this.p = p;
			this.q = q;
			this.e = e;
		}
	}
}
 