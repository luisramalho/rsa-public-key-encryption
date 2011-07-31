// DE3302 (2010-2011)
//
// RSA key pair, consisting of private and public key.

import java.math.*;
import java.util.*;

public class RsaKeyPair {

    // Generates random number
	private Random random = new Random();
	// private long randomSeed = 123456789;
    // private Random random = new Random(randomSeed);

    // Values for the public key (i,n) and private key (j,n).
    // Bit lengths.
    private int pLen = 100;
    private int qLen = 90;
    private int iLen = 110; // must be > pLen, > qLen, < pLen + qLen
    private BigInteger p = BigInteger.probablePrime(pLen, random);
    private BigInteger q = BigInteger.probablePrime(qLen, random);
    private BigInteger i = BigInteger.probablePrime(iLen, random);
    private BigInteger n = p.multiply(q);
    private BigInteger phi = totient(p, q);
    private BigInteger j = privateKeyComponent(i, phi);

    // Access methods.
    public RsaKey getPublicKey() {
	return new RsaKey(i, n);
    }
    public RsaKey getPrivateKey() {
	return new RsaKey(j, n);
    }

    ///////////////////////////////////////////////////////
    // Auxiliary methods on keys.

    // Compute totient function, phi(p * q) = (p-1)(q-1),
    // assuming p and q are both prime.
    private static BigInteger totient(BigInteger p, BigInteger q) {
        BigInteger pMinusOne = p.subtract(BigInteger.ONE);
        BigInteger qMinusOne = q.subtract(BigInteger.ONE);
        return pMinusOne.multiply(qMinusOne);
    }

    // Compute the private key from i and totient(n).
    private static BigInteger privateKeyComponent(BigInteger i, BigInteger phi) {
    	BigInteger h = i;
    	BigInteger z = phi;
    	BigInteger sOld = new BigInteger("1");
    	BigInteger tOld = new BigInteger("0");
    	BigInteger s = new BigInteger("0");
    	BigInteger t = new BigInteger("1");
    	BigInteger sNew, tNew, q, r;
    	BigInteger zero = new BigInteger("0");

    	// Extended Euclidean algorithm
    	while(!z.equals(zero)) {
    		q = h.divide(z);
    		r = h.mod(z);
    		sNew = sOld.subtract(q.multiply(s));
    		tNew = tOld.subtract(q.multiply(t));
    		h = z;
    		z = r;
    		sOld = s;
    		tOld = t;
    		s = sNew;
    		t = tNew;
    	}
    	// We want j to be positive
    	// if sOld is less than zero we add it to s
    	// else, we just return it
    	if(sOld.compareTo(zero) == -1) {
    		return sOld.add(s);
    	}
    	return sOld;
    }
}
