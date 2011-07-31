// DE3302 (2010-2011)
// 
// RSA key, which can be private or public key.

import java.math.*;

public class RsaKey {

    // This can be i or j in a private or public key.
    private BigInteger first;
    // This is the common part n in a private or public key.
    private BigInteger second;

    // Construct key.
    public RsaKey(BigInteger first, BigInteger second) {
	this.first = first;
	this.second = second;
    }

    // Get i or j.
    public BigInteger getFirst() {
	return first;
    }

    // Get n.
    public BigInteger getSecond() {
	return second;
    }

}
