/**
 * concrete implementation of factory method
 * @author Nghi Nguyen
 *
 */
public class CreditCardFactory  extends AbstractCreditCardFactory{
	
	/**
	 * constructor, use superclass' constructor
	 */
	public CreditCardFactory() {
		super();
	}
	/**
	 * check a credit card number is valid
	 * @param num - credit card number
	 * @return boolean value, true if valid
	 */
	public boolean isValidCreditCard(String num){
		
		if(num.trim().length()==16){
			//check master card: length 16, prefix from 51 to 55
			if(Integer.parseInt(num.trim().substring(0,2))>=51 && Integer.parseInt(num.trim().substring(0,2))<=55){
				super.setCreditCardType("master");
				super.setStatus(true);
				
			}
			//check discover card: length 16, prefix 6011
			if(num.trim().substring(0,4).equals("6011"))
			{
				super.setCreditCardType("Discover");
				super.setStatus(true);
				
			}
		}
		//check American express card: length 15, prefix 34 or 37
		if(num.trim().length()==15 && ((Integer.parseInt(num.trim().substring(0,2))==34)|| (Integer.parseInt(num.trim().substring(0,2))==37))){
			super.setCreditCardType("America Express");
			super.setStatus(true);
			
		}
		//check Visa card: length 13 or 16, prefix 4
		if(Integer.parseInt(num.trim().substring(0, 1))==4 && ((num.trim().length()==13)||(num.trim().length()==16))){
			super.setCreditCardType("Visa");
			super.setStatus(true);
			
		}
		
		return getStatus();
		
	}
	/**
	 * create credit card if it is valid
	 * @param creditCardType 
	 * @param cardNumber
	 * @return credit card with correct type
	 */
	public CreditCard creditCardFactory(String creditCardType, Long cardNumber) {
		CreditCard instance = null;
			//create MasterCard
			if(creditCardType.equals("master")){
				instance = new MasterCC(cardNumber);
			}
			//create DiscoverCard
			if(creditCardType.equals("Discover")){
				instance = new Discover(cardNumber);
			}
			//create AmericanExpressCard
			if(creditCardType.equals("America Express")){
				instance = new AmExCC(cardNumber);	
			}
			//create VisaCard
			if(creditCardType.equals("Visa")){
				instance = new VisaCC(cardNumber);
			}
		return instance;
	}
}
