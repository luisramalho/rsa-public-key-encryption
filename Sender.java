import java.math.BigInteger;
import java.util.Random;

public class Sender {

	public BigInteger sendTo(Receiver receiver) {
		Random random = new Random();
		// Constructs a randomly generated BigInteger
		// uniformly distributed over the range 0 to (2^100 - 1), inclusive.
		BigInteger message = new BigInteger(100, random);
			
		// Output message before encryption, in order to test the correctness
		System.out.println("Message before encryption: " + message);
		
		// This message is then encrypted by the public key obtained from the receiver
		RsaCoder rc = new RsaCoder(receiver.receiverPublicKey());
		System.out.println("Encrypted Message: " + rc.code(message));
		return rc.code(message);
	}
	
	public static void main(String[] args) {
		// One sender and one receiver are created
		Sender sender = new Sender();
		Receiver receiver = new Receiver();
		
		// sendTo method of the sender is called.
		receiver.receive(sender.sendTo(receiver));
	}
}
