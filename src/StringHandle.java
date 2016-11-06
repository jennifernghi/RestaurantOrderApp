/**
 * utility class to handle string
 * @author Nghi Nguyen
 *
 */
public class StringHandle {
	/**
	 * check input credit card number contains all number
	 * @param input - cardNumber from text field
	 * @return boolean true if input contains all number
	 */
	public static boolean isNumericInput(String input){
		boolean status = false;
		for(int i=0; i<input.length(); i++){
			if(input.charAt(i) >= 48 && input.charAt(i) <=57){
				status = true;
			}else{
				status = false;
				break;
			}
		}
		return status;
	}
	/**
	 * show only last 4 digit of a credit card number
	 * @param input - cardNumber
	 * @return String - modified cardNumber
	 */
	public static String lastFourDigit(String input){
		String result ="";
		
		int lengthBeforeLastFourDight = input.length()-4;
		for(int i= 0; i<input.trim().length() -4; i++){
			//replace the rest with X
			result += "X";
		}
		for(int i= lengthBeforeLastFourDight; i<input.trim().length(); i++){
			result += input.charAt(i);
		}
		return result;
	}
}
