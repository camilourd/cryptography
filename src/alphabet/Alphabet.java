package alphabet;

public interface Alphabet<M> {
	
	public int getIndex(M car);
	public M getElement(int index);
	public int size();
	
}
