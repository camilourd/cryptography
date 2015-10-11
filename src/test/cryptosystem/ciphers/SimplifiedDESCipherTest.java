package test.cryptosystem.ciphers;

import static org.junit.Assert.*;

import alphabet.alphabets.ExtendedAlphabet;
import cryptosystem.ciphers.SimplifiedDESCipher;
import test.cryptosystem.CryptosystemTest;
import unalcol.types.collection.bitarray.BitArray;

public class SimplifiedDESCipherTest extends CryptosystemTest {

BitArray key;
	
	public SimplifiedDESCipherTest() {
		super(new SimplifiedDESCipher(), new ExtendedAlphabet());
		key = new BitArray("111000111");
	}

	@Override
	public void encodeDecodeTest() {
		String message = "thisisasimpletest";
		String output = ((SimplifiedDESCipher)cryptosystem).encode(key, message);
		output = ((SimplifiedDESCipher)cryptosystem).decode(key, output);
		assertEquals(message, output.substring(0, message.length()));
	}

}
