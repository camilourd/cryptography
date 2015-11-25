package cryptography.cryptosystem;

import cryptography.substitution.SubstitutionFunction;
import unalcol.types.collection.vector.Vector;

public abstract class BlockCryptosystem<K, M, B> implements CipherFunction<K, M> {
	
	protected Cryptosystem<K, B> cryptosystem;
	protected SubstitutionFunction<M, B> encodingSubstitution;
	protected SubstitutionFunction<M, B> decodingSubstitution;
	
	public BlockCryptosystem(Cryptosystem<K, B> cryptosystem,
			SubstitutionFunction<M, B> encodingSubstitution,
			SubstitutionFunction<M, B> decodingSubstitution) {
		this.cryptosystem = cryptosystem;
		this.encodingSubstitution = encodingSubstitution;
		this.decodingSubstitution = decodingSubstitution;
	}

	@Override
	public M encode(K key, M message) {
		if(isValidKey(key)) {
			Vector<B> blocks = divide(key, encodingSubstitution.substitute(complete(key, message)));
			for(int i = 0; i < blocks.size(); ++i)
				blocks.set(i, cryptosystem.encode(key, blocks.get(i)));
			return encodingSubstitution.restore(merge(key, blocks));
		}
		return message;
	}
	
	public abstract M complete(K key, M message);
	public abstract Vector<B> divide(K key, B message);
	public abstract B merge(K key, Vector<B> blocks);

	@Override
	public M decode(K key, M message) {
		if(isValidKey(key)) {
			Vector<B> blocks = divide(key, decodingSubstitution.substitute(message));
			for(int i = 0; i < blocks.size(); ++i)
				blocks.set(i, cryptosystem.decode(key, blocks.get(i)));
			return decodingSubstitution.restore(merge(key, blocks));
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
