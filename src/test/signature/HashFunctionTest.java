package test.signature;

import org.junit.Test;

import cryptography.signature.HashFunction;

public abstract class HashFunctionTest<K, M> {

	protected HashFunction<K, M> function;
	
	public HashFunctionTest(HashFunction<K, M> function) {
		this.function = function;
	}

	@Test
	public abstract void hashtest();

}
