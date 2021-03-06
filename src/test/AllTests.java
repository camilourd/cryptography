package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.alphabet.SimpleAlphabetTest;
import test.cryptosystem.ciphers.AESCipherTest;
import test.cryptosystem.ciphers.AffineCipherTest;
import test.cryptosystem.ciphers.CaesarCipherTest;
import test.cryptosystem.ciphers.ElGamalCipherTest;
import test.cryptosystem.ciphers.HillCipherTest;
import test.cryptosystem.ciphers.RSACipherTest;
import test.cryptosystem.ciphers.SimpleSubstitutionCipherTest;
import test.cryptosystem.ciphers.TripleDESCipherTest;
import test.cryptosystem.ciphers.VigenereCipherTest;
import test.cryptosystem.ciphers.aes.AESTest;
import test.cryptosystem.ciphers.des.DESGeneratorTest;
import test.cryptosystem.ciphers.des.DESTest;
import test.cryptosystem.ciphers.elgamal.ElGamalTest;
import test.math.ArithmeticTest;
import test.math.ModularArithmeticTest;
import test.math.XorArithmeticTest;
import test.signature.function.sha.SHA256Test;

@RunWith(Suite.class)
@SuiteClasses({ AffineCipherTest.class, CaesarCipherTest.class, HillCipherTest.class,
		SimpleSubstitutionCipherTest.class, VigenereCipherTest.class, SimpleAlphabetTest.class,
		ArithmeticTest.class, ModularArithmeticTest.class, DESGeneratorTest.class, DESTest.class,
		TripleDESCipherTest.class, XorArithmeticTest.class, AESTest.class, AESCipherTest.class,
		RSACipherTest.class, ElGamalTest.class, ElGamalCipherTest.class, SHA256Test.class})
public class AllTests {

}
