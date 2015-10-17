package cryptosystem;

import substitution.AlphabetSubstitution;

public abstract class BlockCryptosystem<K, M, B> implements CipherFunction<K, M> {
	
	protected Cryptosystem<K, B> cryptosystem;
	protected AlphabetSubstitution<M, B> encodingSubstitution;
	protected AlphabetSubstitution<M, B> decodingSubstitution;
	
	public BlockCryptosystem(Cryptosystem<K, B> cryptosystem,
			AlphabetSubstitution<M, B> encodingSubstitution,
			AlphabetSubstitution<M, B> decodingSubstitution) {
		this.cryptosystem = cryptosystem;
		this.encodingSubstitution = encodingSubstitution;
		this.decodingSubstitution = decodingSubstitution;
	}

	@Override
	public M encode(K key, M message) {
		if(isValidKey(key)) {
			B[] blocks = divide(encodingSubstitution.substitute(complete(message)));
			for(B block: blocks)
				block = cryptosystem.encode(key, block);
			return decodingSubstitution.restore(merge(blocks));
		}
		return message;
	}
	
	public abstract M complete(M message);
	public abstract B[] divide(B message);
	public abstract B merge(B[] blocks);

	@Override
	public M decode(K key, M message) {
		if(isValidKey(key)) {
			B[] blocks = divide(decodingSubstitution.substitute(message));
			for(B block: blocks)
				block = cryptosystem.decode(key, block);
			return encodingSubstitution.restore(merge(blocks));
		}
		return message;
	}
	
	public boolean isValidKey(K key) {
		return cryptosystem.isValidKey(key);
	}
	
	public K generateKey() {
		return cryptosystem.generateKey();
	}

}
