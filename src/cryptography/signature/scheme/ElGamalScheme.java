package cryptography.signature.scheme;

import java.math.BigInteger;

import cryptography.cryptosystem.ciphers.elgamal.ElGamal;
import cryptography.cryptosystem.ciphers.elgamal.key.*;
import cryptography.signature.HashFunction;
import cryptography.signature.SignatureScheme;
import cryptography.tools.MathTools;
import cryptography.types.Pair;

public class ElGamalScheme extends SignatureScheme<ElGamalKey, Pair<Pair<BigInteger, BigInteger>, BigInteger>, String, String> {

	protected ElGamal cipher;
	protected BigInteger k = null;
	
	public ElGamalScheme(HashFunction<String, String> function) {
		super(function);
		cipher = new ElGamal();
	}
	
	public void generateK(ElGamalKey key) {
		k = BigInteger.ONE;
		BigInteger p = key.publicKey.p.subtract(BigInteger.ONE);
		do {
			k = MathTools.generateBigInteger(MathTools.random(p.toString().length()) + 1).mod(p);
		} while(k.gcd(p).compareTo(BigInteger.ONE) != 0);
	}

	@Override
	public Pair<Pair<BigInteger, BigInteger>, BigInteger> sign(ElGamalKey key, String password) {
		if(k == null)
			generateK(key);
		BigInteger p = key.publicKey.p.subtract(BigInteger.ONE);
		BigInteger r = key.publicKey.g.modPow(k, key.publicKey.p);
		BigInteger hash = new BigInteger(function.hash(password), 16);
		BigInteger s = (hash.subtract(r.multiply(key.privateKey.b)).multiply(k.modInverse(p))).mod(p);
		return new Pair<Pair<BigInteger, BigInteger>, BigInteger>(
				new Pair<BigInteger, BigInteger>(r, s),
				key.publicKey.g.modPow(hash, key.publicKey.p));
	}

	@Override
	public boolean verify(ElGamalKey key, Pair<Pair<BigInteger, BigInteger>, BigInteger> hash) {
		BigInteger left = key.publicKey.gToTheb.modPow(hash.first.first, key.publicKey.p);
		BigInteger right = hash.first.first.modPow(hash.first.second, key.publicKey.p);
		BigInteger result = (left.multiply(right)).mod(key.publicKey.p);
		return result.compareTo(hash.second) == 0;
	}

}
