package cryptography.signature;

public interface HashFunction<K, M> {
	
	public M hash(K key);
	public boolean verify(K key, M signed);

}
