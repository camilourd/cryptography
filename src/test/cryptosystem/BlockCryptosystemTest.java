package test.cryptosystem;

import org.junit.Test;

import cryptosystem.BlockCryptosystem;

public abstract class BlockCryptosystemTest <K, M, C, B> {

	protected BlockCryptosystem<K, M, C, B> cryptosystem;

	public BlockCryptosystemTest(BlockCryptosystem<K, M, C, B> cryptosystem) {
		this.cryptosystem = cryptosystem;
	}

	@Test
	public abstract void encodeDecodeTest();

}
