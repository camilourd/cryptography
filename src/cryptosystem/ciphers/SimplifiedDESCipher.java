package cryptosystem.ciphers;

import cryptosystem.ciphers.des.SimplifiedDES;

public class SimplifiedDESCipher extends DESCipher {

	public SimplifiedDESCipher() {
		super();
		cryptosystem = new SimplifiedDES();
		this.blockSize = 12;
	}
	
}
