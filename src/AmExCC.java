/**
 * American Express Card
 * @author jennifernghinguyen
 *
 */
public class AmExCC extends CreditCard {
	/**
	 * constructor use superclass'constructor
	 * @param cardNumber
	 */
	public AmExCC(Long cardNumber) {
		super(cardNumber);
		
	}

	public String toString() {
		 String str ="American Express Card";
		 return str;
	}

}
