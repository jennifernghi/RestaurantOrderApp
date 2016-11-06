/**
 * Credit Card superclass
 * @author Nghi Nguyen
 *
 */
public class CreditCard {
	//holds cardNumber
	private Long cardNumber;
	

	/**
	 * constructor
	 * @param cardNumber
	 */
	public CreditCard(Long cardNumber){
		this.cardNumber=cardNumber;
	}
	/**
	 * setter
	 * @param cardNumber
	 */
	public void setCardNumber(Long cardNumber) {
		this.cardNumber=cardNumber;
	}
	/**
	 * getter
	 * @return cardNumber
	 */
	public Long getCardNumber(){
		return this.cardNumber;
	}
}
