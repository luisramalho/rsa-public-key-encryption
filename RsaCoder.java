// DE3302 (2010-2011)
//
// Coder of RSA. Can do encryption or decryption.

import java.math.*;

class RsaCoder {

	// The key (private or public).
	private RsaKey key;

	// Construct coder.
	public RsaCoder(RsaKey key) {
		this.key = key;
	}

	// Can be used for encryption (with public key) or 
	// decryption (with private key).
	public BigInteger code(BigInteger message) {
		return modpow(message, key.getFirst(), key.getSecond());
	}

	// Do modular exponentiation for the expression b^e mod m
	// (b to the power e, modulo m).
	private BigInteger modpow(BigInteger b, BigInteger e, BigInteger m) {
		// prints the calculations
		// System.out.println(b + " " + e + " " + m);
		BigInteger zero = new BigInteger("0");
		BigInteger one = new BigInteger("1");
		BigInteger two = one.add(one);

		// Base Case
		if (e.equals(zero))
			return one;
		if (e.equals(one))
			return b.mod(m);

		if (e.mod(two).equals(zero)) {
			// Calculates the square root of the answer
			BigInteger answer = modpow(b, e.divide(two), m);
			// Reuses the result of the square root
			return (answer.multiply(answer)).mod(m);
		}	

		return (b.multiply(modpow(b,e.subtract(one),m))).mod(m);
	}
}