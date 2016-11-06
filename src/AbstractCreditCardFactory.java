/**
 * 
 * @author Nghi Nguyen
 *
 */
public abstract class AbstractCreditCardFactory {

	/**
	 * hold credit card Type, is set by isValidCreditCard()
	 */
	private String creditCardType;
	/**
	 * hold status of checking valid credit card, is set by isValidCreditCard()
	 */
	private boolean status;
	/**
	 * initialize the fields
	 */
	public AbstractCreditCardFactory() {
		this.creditCardType="";
		this.status=false;
	}
	/**
	 * setter
	 * @param creditCardType - set creditCardType
	 */
	public void setCreditCardType(String creditCardType) {
		this.creditCardType=creditCardType;
	}
	/**
	 * getter
	 * @return creditCardType
	 */
	public String getCreditCardType() {
		return this.creditCardType;
	}
	/**
	 * setter
	 * @param status - set status
	 */
	public void setStatus(boolean status) {
		this.status=status;
	}
	/**
	 * getter
	 * @return status
	 */
	public boolean getStatus() {
		return this.status;
	}
	
	/**
	 * check a credit card number is valid
	 * @param num - credit card number
	 * @return boolean value, true if valid
	 */
	public abstract boolean isValidCreditCard(String num);
	/**
	 * create credit card if it is valid
	 * @param creditCardType 
	 * @param cardNumber
	 * @return credit card with correct type
	 */
	public abstract CreditCard creditCardFactory(String creditCardType, Long cardNumber);
}
