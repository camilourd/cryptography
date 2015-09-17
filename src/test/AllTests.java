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
import test.cryptosystem.ciphers.VigenereCipherTest;
import test.tools.ArithmeticTest;
import test.tools.ModularArithmeticTest;

@RunWith(Suite.class)
@SuiteClasses({ AffineCipherTest.class, CaesarCipherTest.class, HillCipherTest.class, RSATest.class,
		SimpleSubstitutionCipherTest.class, VigenereCipherTest.class, SimpleAlphabetTest.class,
		ArithmeticTest.class, ModularArithmeticTest.class})
public class AllTests {

}
