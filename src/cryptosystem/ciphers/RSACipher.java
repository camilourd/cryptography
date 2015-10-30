package cryptosystem.ciphers;

import java.math.BigInteger;
import java.util.ArrayList;

import cryptosystem.BlockCryptosystem;
import cryptosystem.ciphers.rsa.NumberSubstitution;
import cryptosystem.ciphers.rsa.RSA;
import cryptosystem.ciphers.rsa.RSASubstitution;
import cryptosystem.ciphers.rsa.key.RSAKey;

public class RSACipher extends BlockCryptosystem<RSAKey, String, BigInteger> {

	public RSACipher() {
		super(new RSA(), new RSASubstitution(), new NumberSubstitution());
	}

	@Override
	public String complete(RSAKey key, String message) {
		return message;
	}

	@Override
	public BigInteger[] divide(RSAKey key, BigInteger message) {
		BigInteger div = BigInteger.TEN.pow(key.publicKey.n.toString().length() - 1);
		ArrayList<BigInteger> nums = new ArrayList<BigInteger>();
		while(!message.equals(BigInteger.ZERO)) {
			nums.add(message.mod(div));
			message = message.divide(div);
		}
		BigInteger[] result = new BigInteger[nums.size()];
		for(int i = 0; i < nums.size(); ++i)
			result[i] = nums.get(i);
		return result;
	}

	@Override
	public BigInteger merge(RSAKey key, BigInteger[] blocks) {
		int blockSize = key.publicKey.n.toString().length() - 1;
		String result = "";
		for(BigInteger block: blocks) {
			String aux = block.toString();
			while(aux.length() < blockSize)
				aux = "0" + aux;
			result = aux + result;
		}
		return new BigInteger(result);
	}
	
	

}
