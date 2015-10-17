package test.cryptosystem;

import org.junit.Test;

import cryptosystem.BlockCryptosystem;

public abstract class BlockCryptosystemTest <K, M, B> {

	protected BlockCryptosystem<K, M, B> cryptosystem;

	public BlockCryptosystemTest(BlockCryptosystem<K, M, B> cryptosystem) {
		this.cryptosystem = cryptosystem;
	}

	@Test
	public abstract void encodeDecodeTest();

}
