import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * 
 * @author Nghi Nguyen
 *
 */
public class McPatternController {
	
	public final FileHandler FILEHANDLER = new FileHandler();
	
	//fields
	private MenuModel menuModel;
	private McPatternGUI view;
	//holds all menuItems
	private ObservableList<MenuModel> menuData = FXCollections.observableArrayList();
	//holds all selected menuItems
	private ObservableList<MenuModel> orderedItemData = FXCollections.observableArrayList();
	
	//global variables
	private CreditCard creditCardModel;
	
	private Double totalPrice;
	
	//holds ordered Items String property
	private PropertyValueFactory<MenuModel, String> orderedItemProperty;
	//holds prices ordered Items Double property
	private PropertyValueFactory<MenuModel, Double> orderedPriceProperty;
	

	
	private Map<String, Double> map = new HashMap<>();
	/**
	 * constructor
	 * this class will be loaded before gui constructor
	 * initialize data here
	 */
	public McPatternController() {
		setMenuModel(new MenuModel());
		setTotalPrice(0.0);
		loadMenuItemFromFile();//extract menu.txt and add menuItems to the map
		for (Map.Entry<String, Double> entry : map.entrySet()) {
			//add menu items to menuData
			getMenuData().add(new MenuModel(entry.getKey().toString(), Double.valueOf(entry.getValue().toString())));
		}
		map.clear();//clear the map
		//initialize data of ordered table
		setOrderedItemProperty(new PropertyValueFactory<MenuModel, String>("menuItem"));
		setOrderedPriceProperty(new PropertyValueFactory<MenuModel, Double>("price"));
	}
	/**
	 * extract menu.txt and add menuItems to the map
	 * @return map with menu items as key and price as value
	 */
	public Map<String, Double> loadMenuItemFromFile(){
		
		return map = FILEHANDLER.fileToMap();
	}
	/**
	 * setter
	 * @param menuModel
	 */
	public void setMenuModel(MenuModel menuModel){
		this.menuModel = menuModel;
	}
	/**
	 * getter
	 * @return menuModel
	 */
	public MenuModel getMenuModel(){
		return this.menuModel;
	}
	/**
	 * setter
	 * @param view
	 */
	public void setView(McPatternGUI view){
		this.view = view;
	}
	/**
	 * getter
	 * @return view
	 */
	public McPatternGUI getView(){
		return this.view;
	}
	/**
	 * setter
	 * @param menuData
	 */
	public void setMenuData(ObservableList<MenuModel> menuData){
		this.menuData = menuData;
	}
	/**
	 * getter
	 * @return menuData
	 */
	public ObservableList<MenuModel> getMenuData(){
		return this.menuData;
	}
	/**
	 * setter
	 * @param orderedItemData
	 */
	public void setOrderedItemData(ObservableList<MenuModel> orderedItemData){
		this.orderedItemData = orderedItemData;
	}
	/**
	 * getter
	 * @return orderedItemData
	 */
	public ObservableList<MenuModel> getOrderedItemData(){
		return this.orderedItemData;
	}
	/**
	 * setter
	 * @param totalPrice
	 */
	public void setTotalPrice(Double totalPrice){
		this.totalPrice = totalPrice;
	}
	/**
	 * getter
	 * @return
	 */
	public Double getTotalPrice(){
		return this.totalPrice;
	}
	/**
	 * getter
	 * @return orderedItemProperty - ordered items data shown in ordered table
	 */
	public PropertyValueFactory<MenuModel, String> getOrderedItemProperty() {
		return this.orderedItemProperty;
	}
	/**
	 * setter - set ordered items data for ordered table
	 * @param orderedItemProperty - string property
	 */
	public void setOrderedItemProperty (PropertyValueFactory<MenuModel, String> orderedItemProperty) {
		this.orderedItemProperty = orderedItemProperty;
	}
	/**
	 * getter
	 * @return orderedPriceProperty - price of ordered items data shown in ordered table
	 */
	public PropertyValueFactory<MenuModel, Double> getOrderedPriceProperty() {
		return this.orderedPriceProperty;
	}
	/**
	 * setter - set price of ordered items data for ordered table
	 * @param orderedPriceProperty
	 */
	public void setOrderedPriceProperty (PropertyValueFactory<MenuModel, Double> orderedPriceProperty) {
		this.orderedPriceProperty = orderedPriceProperty;
	}
	
	/**
	 * called when a menu button is clicked
	 * add this selected item to orderedData and orderedTable
     * update total price
	 * @param menuModel
	 */
	public void putInOrder(MenuModel menuModel){
		//add to observablelist menuData
		getOrderedItemData().add(menuModel);
		
		//enable disabled ui components
		view.getCreditCardTextField().setDisable(false);
		view.getPlaceOrderButton().setDisable(false);
		view.getClearOrderButton().setDisable(false);
		
		//update the totalPrice and show on the screen
		totalPrice += menuModel.getPrice();
		view.getTotalPriceLabel().setText(this.getTotalPrice().toString());
		
	}
	/**
	 * called when clearButton is click or okButton in confirmation alert is clicked
	 */
	@SuppressWarnings("unchecked")
	public void clearOrder(){
		//clear orderedItemData
		getOrderedItemData().clear();
		
		//update on the screen
		view.getorderedTable().setItems(orderedItemData);
		
		//reset price
		setTotalPrice(0.0);
		
		//update on the screen
		view.getTotalPriceLabel().setText(this.getTotalPrice().toString());
		
		view.getCreditCardTextField().setText("Enter credit card #");
		view.getCreditCardTextField().setDisable(true);
		view.getPlaceOrderButton().setDisable(true);
		view.getClearOrderButton().setDisable(true);
	}
	/**
	 * called when placeOrderButton is clicked
	 */
	public void placeOrder(){
		//use creditCardFactory method
		AbstractCreditCardFactory factory = new CreditCardFactory();
		
		if(StringHandle.isNumericInput(view.getCreditCardTextField().getText().trim())){//if input contains all number
			
			if(factory.isValidCreditCard(view.getCreditCardTextField().getText().trim())){//if input is a valid credit card
				//create correct credit card
				creditCardModel = factory.creditCardFactory(factory.getCreditCardType(), Long.parseLong(view.getCreditCardTextField().getText().trim()));
				
				//show confirmation with credit card information
				showConfirmation();
				
			}else{
				
				//if input is not a valid credit card
				showAlert("Wrong Credit Card Type!", "Only Master, Visa, American Express and Discover cards excepted.");
			}
		}else{
			
			//if input either empty or contains any character that is not a number
			showAlert("Invalid Card Number!", "Credit card contains only number.");
		}
		
		
	}
	/**
	 * show error alert
	 * @param headerMessage
	 * @param message 
	 */
	
	public void showAlert(String headerMessage, String message){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText(headerMessage);
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	
	/**
	 * show information with credit card information
	 */
	public void showConfirmation() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Success! Order is being prepared.");
		
		//show card number with last 4 digits visible
		String cardNum = "Credit Card: "+String.valueOf(StringHandle.lastFourDigit(creditCardModel.getCardNumber().toString()));
		
		//show card type
		String cardType = "Card Type: "+ creditCardModel.toString();
		
		//show total paid price
		String totalPaidPrice = "Total Paid: $"+String.valueOf(totalPrice);
		alert.setContentText(cardNum+"\n" + cardType+"\n"+totalPaidPrice);
		
		Optional<ButtonType> option = alert.showAndWait();
		//assign events when ok is clicked
		if(option.get() == ButtonType.OK){
			alert.close();//close alert
			clearOrder();//call clearOrder();
		}
	}
	
	
}
