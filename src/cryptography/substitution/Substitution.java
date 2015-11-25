package cryptography.substitution;

import java.util.TreeMap;

public class Substitution<V, S> implements SubstitutionFunction<V, S> {
	
	protected TreeMap<V, S> substitution;
	protected TreeMap<S, V> inverse;
	
	public Substitution() {
		this.substitution = new TreeMap<V, S>();
		this.inverse = new TreeMap<S, V>();
	}
	
	public void add(V value, S substitution) {
		this.substitution.put(value, substitution);
		this.inverse.put(substitution, value);
	}
	
	public S substitute(V value) {
		return substitution.get(value);
	}
	
	public V restore(S value) {
		return inverse.get(value);
	}
	
}
