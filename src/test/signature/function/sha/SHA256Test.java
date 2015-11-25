package test.signature.function.sha;

import static org.junit.Assert.*;

import cryptography.signature.function.sha.SHA256;
import test.signature.HashFunctionTest;

public class SHA256Test extends HashFunctionTest<String, String> {

	protected String[] inputs;
	protected String[] hashes;
	
	public SHA256Test() {
		super(new SHA256());
		inputs = new String[]{
			"hola",
			"The quick brown fox jumps over the lazy dog"
		};
		hashes = new String[]{
			"b221d9dbb083a7f33428d7c2a3c3198ae925614d70210e28716ccaa7cd4ddb79",
			"d7a8fbb307d7809469ca9abcb0082e4f8d5651e46d3cdb762d02d0bf37c9e592",
		};
	}

	@Override
	public void hashtest() {
		System.out.println("SHA-2 256 tests");
		for(int i = 0; i < inputs.length; ++i) {
			System.out.println("------------test:" + i + "------------");
			System.out.println("input: " + inputs[i]);
			String output = function.hash(inputs[i]);
			System.out.println("output: " + output);
			assertEquals(hashes[i], output);
		}
	}

}
