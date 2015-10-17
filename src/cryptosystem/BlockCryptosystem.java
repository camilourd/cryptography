package cryptosystem;

import substitution.AlphabetSubstitution;

public abstract class BlockCryptosystem<K, M, C, B> implements CipherFunction<K, M> {
	
	protected Cryptosystem<K, B, C> cryptosystem;
	protected AlphabetSubstitution<M, B, C> encodingSubstitution;
	protected AlphabetSubstitution<M, B, C> decodingSubstitution;
	
	public BlockCryptosystem(Cryptosystem<K, B, C> cryptosystem,
			AlphabetSubstitution<M, B, C> encodingSubstitution,
			AlphabetSubstitution<M, B, C> decodingSubstitution) {
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
