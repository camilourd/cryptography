package cryptography.cryptosystem.ciphers;

import java.math.BigInteger;

import cryptography.cryptosystem.BlockCryptosystem;
import cryptography.cryptosystem.ciphers.rsa.NumberSubstitution;
import cryptography.cryptosystem.ciphers.rsa.RSA;
import cryptography.cryptosystem.ciphers.rsa.RSASubstitution;
import cryptography.cryptosystem.ciphers.rsa.key.RSAKey;
import unalcol.types.collection.vector.Vector;

public class RSACipher extends BlockCryptosystem<RSAKey, String, BigInteger> {

	public RSACipher() {
		super(new RSA(), new RSASubstitution(), new NumberSubstitution());
	}

	@Override
	public String complete(RSAKey key, String message) {
		return message;
	}

	@Override
	public Vector<BigInteger> divide(RSAKey key, BigInteger message) {
		BigInteger div = key.publicKey.n;
		Vector<BigInteger> blocks = new Vector<BigInteger>();
		while(message.signum() > 0) {
			blocks.add(message.mod(div));
			message = message.divide(div);
		}
		return blocks;
	}

	@Override
	public BigInteger merge(RSAKey key, Vector<BigInteger> blocks) {
		BigInteger mult = key.publicKey.n;
		BigInteger result = BigInteger.ZERO;
		for(BigInteger block: blocks)
			result = (result.multiply(mult)).add(block);
		return result;
	}

}
