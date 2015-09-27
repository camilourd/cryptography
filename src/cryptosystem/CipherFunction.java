package cryptosystem;

public interface CipherFunction<K, M> {

	public M encode(K key, M message);
	public M decode(K key, M message);
	
}
