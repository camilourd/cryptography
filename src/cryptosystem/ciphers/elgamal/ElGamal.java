package cryptosystem.ciphers.elgamal;

import alphabet.alphabets.NaturalAlphabet;
import cryptosystem.Cryptosystem;
import cryptosystem.ciphers.elgamal.key.ElGamalKey;
import tools.MathTools;
import types.Pair;

import java.math.BigInteger;

public class ElGamal extends Cryptosystem<ElGamalKey, Pair<BigInteger, BigInteger>> {

	public ElGamal() {
		super(new NaturalAlphabet());
	}

	@Override
	public Pair<BigInteger, BigInteger> encode(ElGamalKey key, Pair<BigInteger, BigInteger> message) {
		BigInteger k = MathTools.generateBigInteger(MathTools.random(key.publicKey.p.toString().length()) + 1);
		BigInteger r = key.publicKey.gToTheb.modPow(k, key.publicKey.p);
		return new Pair<BigInteger, BigInteger>(
				key.publicKey.g.modPow(k, key.publicKey.p)
				, (message.second.multiply(r)).mod(key.publicKey.p));
	}

	@Override
	public Pair<BigInteger, BigInteger> decode(ElGamalKey key, Pair<BigInteger, BigInteger> message) {
		//BigInteger r = Arithmetic.pow(message.first, key.privateKey.b);
		BigInteger r = (message.first.modInverse(key.publicKey.p)).modPow(key.privateKey.b, key.publicKey.p);
		return new Pair<BigInteger, BigInteger>(
				message.first,
				(message.second.multiply(r)).mod(key.publicKey.p));
	}

	@Override
	public boolean isValidKey(ElGamalKey key) {
		return true;
	}

	@Override
	public ElGamalKey generateKey() {
		return null;
	}
	
}
