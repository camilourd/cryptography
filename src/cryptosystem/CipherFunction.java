package cryptosystem;

public interface CipherFunction<K, M> {

	public String encode(K key, M message);
	public String decode(K key, M message);
	
}
