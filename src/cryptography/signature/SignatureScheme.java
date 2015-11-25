package cryptography.signature;

public abstract class SignatureScheme<K, M, FK, FM> {

	protected HashFunction<FK, FM> function;

	public SignatureScheme(HashFunction<FK, FM> function) {
		this.function = function;
	}
	
	public abstract M sign(K key, FK password);
	public abstract boolean verify(K key, M hash);
	
}
