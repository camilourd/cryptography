package test.cryptosystem.ciphers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import alphabet.alphabets.SimpleAlphabet;
import cryptosystem.ciphers.AES;
import test.cryptosystem.CryptosystemTest;

public class AESTest extends CryptosystemTest {

	public AESTest() {
		super(new AES(), new SimpleAlphabet("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ."));
	}
	
	@Override
	public void encodeDecodeTest() {
		String message = "thisisasimpletest";
		int[] key = ((AES)cryptosystem).generateKey();
	}
	

}
