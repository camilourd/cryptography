package cryptography.substitution;

public interface SubstitutionFunction<V, S> {
	
	public S substitute(V value);
	public V restore(S value);
	
}
