package cryptography.cryptosystem.ciphers;

import cryptography.cryptosystem.ciphers.des.SimplifiedDES;

public class SimplifiedDESCipher extends DESCipher {

	public SimplifiedDESCipher() {
		super();
		cryptosystem = new SimplifiedDES();
		this.blockSize = 12;
	}
	
}
