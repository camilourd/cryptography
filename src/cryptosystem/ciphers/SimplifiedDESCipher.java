package cryptosystem.ciphers;

import cryptosystem.ciphers.des.SimplifiedDES;

public class SimplifiedDESCipher extends DESCipher {

	public SimplifiedDESCipher() {
		super();
		des = new SimplifiedDES();
	}
	
}
