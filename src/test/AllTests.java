package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.alphabet.SimpleAlphabetTest;
import test.cryptosystem.ciphers.AffineCipherTest;
import test.cryptosystem.ciphers.CaesarCipherTest;
import test.cryptosystem.ciphers.HillCipherTest;
import test.cryptosystem.ciphers.RSATest;
import test.cryptosystem.ciphers.SimpleSubstitutionCipherTest;
import test.cryptosystem.ciphers.TripleDESTest;
import test.cryptosystem.ciphers.VigenereCipherTest;
import test.cryptosystem.ciphers.des.DESGeneratorTest;
import test.cryptosystem.ciphers.des.DESTest;
import test.math.ArithmeticTest;
import test.math.ModularArithmeticTest;
import test.math.XorArithmeticTest;

@RunWith(Suite.class)
@SuiteClasses({ AffineCipherTest.class, CaesarCipherTest.class, HillCipherTest.class, RSATest.class,
		SimpleSubstitutionCipherTest.class, VigenereCipherTest.class, SimpleAlphabetTest.class,
		ArithmeticTest.class, ModularArithmeticTest.class, DESGeneratorTest.class, DESTest.class,
		TripleDESTest.class, XorArithmeticTest.class})
public class AllTests {

}
