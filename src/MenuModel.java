import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * MenuModel
 * @author Nghi Nguyen
 *
 */
public class MenuModel {
	
	private StringProperty menuItem;//food name
	private DoubleProperty price;
	/**
	 * constructor
	 */
	public MenuModel(){
		this("N/A",0.0);
	}
	/**
	 * constructor
	 * @param menuItem
	 * @param price
	 */
	public MenuModel(String menuItem, Double price){
		this.menuItem = new SimpleStringProperty(menuItem);
		this.price = new SimpleDoubleProperty(price);
	}
	
	/**
	 * setter
	 * @param menuItem
	 */
	public void setMenuItem(String menuItem){
		this.menuItem.set(menuItem);
	}
	/**
	 * getter
	 * @return a StringProperty
	 */
	public StringProperty getMenuItemProperty(){
		return this.menuItem;
	}
	/**
	 * getter
	 * @return a String
	 */
	public String getMenuItem(){
		return this.menuItem.get();
	}
	/**
	 * setter
	 * @param price
	 */
	public void setPrice(Double price){
		this.price.set(price);
	}
	/**
	 * getter
	 * @return a DoubleProperty
	 */
	public DoubleProperty getPriceProperty(){
		return this.price;
	}
	/**
	 * getter
	 * @return a Double
	 */
	public Double getPrice() {
		return this.price.get();
	}
}
