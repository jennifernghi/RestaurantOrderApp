/**
 * Visa Card
 * @author Nghi Nguyen
 *
 */
public class VisaCC extends CreditCard {

	
	/**
	 * constructor use superclass'constructor
	 * @param cardNumber
	 */
	public VisaCC(Long cardNumber) {
		super(cardNumber);
		
	}
	
	public String toString() {
		 String str ="Visa Card";
		 return str;
	}

}
