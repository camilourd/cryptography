package cryptosystem.ciphers;

import cryptosystem.ciphers.des.SimplifiedDES;
import unalcol.types.collection.bitarray.BitArray;

public class SimplifiedDESCipher extends DESCipher {

	public SimplifiedDESCipher() {
		super();
		super.keySize = 9;
		super.blockSize = 12;
	}

	@Override
	protected BitArray runMethod(int method, BitArray key, BitArray code) {
		if(method == ENCRYPT)
			return SimplifiedDES.encrypt(key, code);
		return SimplifiedDES.decrypt(key, code);
	}
	
}
