package cryptosystem.types;

public interface Function<X, Y> {

	public void set(X x, Y y);
	public boolean mapped(X x);
	public Y eval(X x);
	public X inverse(Y y);
}
