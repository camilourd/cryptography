package cryptography.cryptosystem.ciphers;

import java.math.BigInteger;

import cryptography.alphabet.alphabets.ExtendedAlphabet;
import cryptography.cryptosystem.BlockCryptosystem;
import cryptography.cryptosystem.ciphers.elgamal.ElGamal;
import cryptography.cryptosystem.ciphers.elgamal.ElGamalDecodingSubstitution;
import cryptography.cryptosystem.ciphers.elgamal.ElGamalEncodingSubstitution;
import cryptography.cryptosystem.ciphers.elgamal.key.ElGamalKey;
import cryptography.types.Pair;
import unalcol.types.collection.vector.Vector;

public class ElGamalCipher extends BlockCryptosystem<ElGamalKey, String, Pair<BigInteger, BigInteger>> {
	
	public ElGamalCipher() {
		super(new ElGamal(), new ElGamalEncodingSubstitution(new ExtendedAlphabet()),
				new ElGamalDecodingSubstitution(new ExtendedAlphabet()));
	}

	@Override
	public String complete(ElGamalKey key, String message) {
		return message;
	}

	@Override
	public Vector<Pair<BigInteger, BigInteger>> divide(ElGamalKey key, Pair<BigInteger, BigInteger> message) {
		Vector<Pair<BigInteger, BigInteger>> blocks = new Vector<Pair<BigInteger, BigInteger>>();
		BigInteger div = key.publicKey.p;
		while(message.second.signum() > 0) {
			blocks.add(new Pair<BigInteger, BigInteger>(
					message.first.mod(div),
					message.second.mod(div)
					));
			message = new Pair<BigInteger, BigInteger>(
					message.first.divide(div),
					message.second.divide(div)
					);
		}
		return blocks;
	}

	@Override
	public Pair<BigInteger, BigInteger> merge(ElGamalKey key, Vector<Pair<BigInteger, BigInteger>> blocks) {
		Pair<BigInteger, BigInteger> result = new Pair<BigInteger, BigInteger>(BigInteger.ZERO, BigInteger.ZERO);
		BigInteger mult = key.publicKey.p;
		for(Pair<BigInteger, BigInteger> block: blocks)
			result = new Pair<BigInteger, BigInteger>(
					(result.first.multiply(mult)).add(block.first),
					(result.second.multiply(mult)).add(block.second)
					);
		return result;
	}

}
