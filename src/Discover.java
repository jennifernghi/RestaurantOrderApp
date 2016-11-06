/**
 * Discover Card
 * @author Nghi Nguyen
 *
 */
public class Discover extends CreditCard {


	/**
	 * constructor use superclass'constructor
	 * @param cardNumber
	 */
	public Discover(Long cardNumber) {
		super(cardNumber);
		
	}
	
	public String toString() {
		 String str ="Discover Card";
		 return str;
	}

}
