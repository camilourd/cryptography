package edu.unal.crypto.alphabet;

public abstract class Alphabet<T> {
	
	public abstract int getIndex(T val);
	public abstract T getValue(int idx);
	public abstract T[] getValues();
	public abstract int getSize();
}
