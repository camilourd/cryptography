package test.cryptosystem.ciphers;

import org.junit.Test;

import alphabet.alphabets.EnglishAlphabet;
import cryptosystem.ciphers.HillCipher;
import cryptosystem.ciphers.des.DESGenerator;
import test.cryptosystem.CryptosystemTest;
import unalcol.types.collection.bitarray.BitArray;

public class DESTest extends CryptosystemTest {

	public DESTest() {
		super(new HillCipher(new EnglishAlphabet()), new EnglishAlphabet());
	}

	@Override
	public void encodeDecodeTest() {
		// TODO Auto-generated method stub
		
	}

}
