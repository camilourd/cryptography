package test.cryptosystem.ciphers.aes;

import static org.junit.Assert.*;

import cryptosystem.ciphers.aes.AES;
import test.cryptosystem.CryptosystemTest;
import tools.BitArrayTools;
import unalcol.types.collection.bitarray.BitArray;

public class AESTest extends CryptosystemTest<BitArray, BitArray, Character> {

	public static int polynomial = 283;
	
	public AESTest() {
		super(new AES(128, polynomial));
	}
	
	@Override
	public void encodeDecodeTest() {
		BitArray input = new BitArray("00110010010000111111011010101000100010000101101000110000100011010011000100110001100110001010001011100000001101110000011100110100");
		System.out.println("input:    " + BitArrayTools.toHexString(input));
		BitArray key = new BitArray("00101011011111100001010100010110001010001010111011010010101001101010101111110111000101011000100000001001110011110100111100111100");
		System.out.println("key:      " + BitArrayTools.toHexString(key));
		
		BitArray result = cryptosystem.encode(key, input);
		System.out.println("encoding: " + BitArrayTools.toHexString(result));
		assertNotEquals(result, input);
		result = cryptosystem.decode(key, result);
		System.out.println("decoding: " + BitArrayTools.toHexString(result) + "\n");
		assertEquals(result, input);
		
		input = new BitArray("00000000000100010010001000110011010001000101010101100110011101111000100010011001101010101011101111001100110111011110111011111111");
		System.out.println("input:    " + BitArrayTools.toHexString(input));
		key =   new BitArray("00000000000000010000001000000011000001000000010100000110000001110000100000001001000010100000101100001100000011010000111000001111");
		System.out.println("key:      " + BitArrayTools.toHexString(key));
		
		result = cryptosystem.encode(key, input);
		System.out.println("encoding: " + BitArrayTools.toHexString(result));
		assertNotEquals(result, input);
		result = cryptosystem.decode(key, result);
		System.out.println("decoding: " + BitArrayTools.toHexString(result) + "\n");
		assertEquals(result, input);
	}
	

}
