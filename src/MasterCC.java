/**
 * Master Card
 * @author Nghi Nguyen
 *
 */
public class MasterCC extends CreditCard {


	/**
	 * constructor use superclass'constructor
	 * @param cardNumber
	 */
	public MasterCC(Long cardNumber) {
		super(cardNumber);
	}
	
	public String toString() {
		 String str ="Master Card";
		 return str;
	}

}
