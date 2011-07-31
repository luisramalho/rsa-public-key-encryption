import java.math.BigInteger;

public class Receiver {
	// Generates a key pair
	RsaKeyPair rkp = new RsaKeyPair();

	// Public key is made available
	public RsaKey receiverPublicKey() {
		return rkp.getPublicKey();
	}
	
	// Takes cipher text, decrypts it and outputs it
	public void receive(BigInteger cipherText) {
		RsaCoder rc = new RsaCoder(rkp.getPrivateKey());
		System.out.println("Decrypted cipher text: " + rc.code(cipherText));
	}
}