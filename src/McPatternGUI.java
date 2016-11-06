

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * The main view
 * @author Nghi Nguyen
 *
 */
public class McPatternGUI extends Application {
	//fields
	private McPatternController controller;// holds controller
	
	//global variable
	private BorderPane root= new BorderPane();//holds the root pane
	private Label totalPriceLabel;
	
	
	@SuppressWarnings("rawtypes")
	private TableView orderedTable;
	
	@SuppressWarnings("rawtypes")
	private TableColumn orderedColumn;
	
	@SuppressWarnings("rawtypes")
	private TableColumn orderedPriceColumn;
	
	@SuppressWarnings({ "unused", "rawtypes" })
	private PropertyValueFactory orderedItemProperty;
	
	@SuppressWarnings({ "rawtypes", "unused" })
	private PropertyValueFactory orderedPriceProperty;
	

	
	private javafx.scene.control.TextField creditCardTextField;
	
	private Button placeOrderButton;
	private Button clearOrderButton;		
	
	/**
	 * controller
	 * this class loaded after main
	 */
	public McPatternGUI() {
		
		//set controller for this view
		setController(new McPatternController());
		//set this view for controller
		controller.setView(this);
	}

	/**
	 * setter
	 * @param controller
	 */
	public void setController(McPatternController controller){
		this.controller = controller;
	}

	/**
	 * getter
	 * @return controller
	 */
	public McPatternController getController(){
		return this.controller;
	}

	public Label getTotalPriceLabel() {
		return this.totalPriceLabel;
	}
	
	public javafx.scene.control.TextField getCreditCardTextField() {
		return this.creditCardTextField;
	}
	public Button getPlaceOrderButton() {
		return this.placeOrderButton;
	}
	public Button getClearOrderButton() {
		return this.clearOrderButton;
	}
	@SuppressWarnings("rawtypes")
	public TableView getorderedTable() {
		return this.orderedTable;
	}
	/**
	 * create menu GUI
	 * @return a FlowPane 
	 */
	public Node loadMenuGUI(){
		
		FlowPane menuPane = new FlowPane();
		menuPane.setVgap(20);
		menuPane.setHgap(20);
		menuPane.setAlignment(Pos.CENTER);
		//for each element in menuData
		for(MenuModel items: controller.getMenuData()){
			//create a button with a text of menuItem
			Button button = new Button(items.getMenuItem());
			//when user click on the button, call putInOrder()
			button.setOnAction(e ->controller.putInOrder(items));
			//min height for the button
			button.setMinHeight(50);
			//add button to flow pane
			menuPane.getChildren().add(button);
			
		}
		return menuPane;
		
	}
	/**
	 * create the gui for selected items
	 * @return TableView orderedTable
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Node loadOrderedGUI(){
		 orderedTable = new TableView();
		 
		//first column with title Ordered
		 orderedColumn = new TableColumn("Ordered");
		orderedColumn.setMinWidth(170);
		
		//show the menuItem data 
		orderedColumn.setCellValueFactory(controller.getOrderedItemProperty());
		
		//second column with title price
		 orderedPriceColumn = new TableColumn("Price");
		orderedPriceColumn.setMinWidth(70);
		
		//show the price data 
		orderedPriceColumn.setCellValueFactory(controller.getOrderedPriceProperty());
		
		orderedTable.setItems(controller.getOrderedItemData());
		orderedTable.getColumns().addAll(orderedColumn, orderedPriceColumn);
		
		return orderedTable;
	}
	/**
	 * create gui to show total price
	 * @return HBox pricePane
	 */
	public Node loadPricePaneGUI(){
		HBox pricePane = new HBox(5);
		Label label = new Label("Total: $ ");
		
		//set the totalPriceLabel with the text of totalPrice
		totalPriceLabel = new Label(controller.getTotalPrice().toString());
		totalPriceLabel.setText(controller.getTotalPrice().toString());
		
		pricePane.setAlignment(Pos.CENTER_RIGHT);
		
		pricePane.getChildren().addAll(label, totalPriceLabel);
		return pricePane;
	}
	/**
	 * create gui to place or clear order
	 * @return VBox checkoutPane
	 */
	public Node loadCheckOutGUI(){
		VBox checkoutPane = new VBox(10);
		
		 creditCardTextField = new javafx.scene.control.TextField("Enter credit card #");
		 
		 //when no item selected all UI components in this gui is disabled
		 creditCardTextField.setDisable(true);
		 
		 //mouse click event
		creditCardTextField.setOnMouseClicked(e -> {
			if(creditCardTextField.getText().equals("Enter credit card #")){
				//the text field now is empty
				creditCardTextField.setText("");
			
			}
			
		});
		
		//when ENTER is hit
		creditCardTextField.setOnKeyPressed(e -> {
			if(e.getCode()==KeyCode.ENTER){
				//call
				controller.placeOrder();
			}
		});
		
		 HBox buttonPane = new HBox(10);// hold 2 buttons
		 
		 placeOrderButton = new Button("PLACE ORDER");
		 clearOrderButton = new Button("CLEAR");
		 // set disabled
		 placeOrderButton.setDisable(true);
		 clearOrderButton.setDisable(true);
		 
		 placeOrderButton.setOnAction(e -> controller.placeOrder());//assign event for placeOrderButton
		 clearOrderButton.setOnAction(e -> controller.clearOrder());//assign event for clearOrderButton
		 
		 buttonPane.getChildren().addAll(placeOrderButton, clearOrderButton);
		 buttonPane.setAlignment(Pos.CENTER);
		 checkoutPane.getChildren().addAll(creditCardTextField, buttonPane);
		return checkoutPane;
		
	}
	
	/**
	 * this class is loaded last
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		 //"receipt" gui
		VBox rightPane = new VBox(5);
		rightPane.getChildren().addAll(loadOrderedGUI(), loadPricePaneGUI(),loadCheckOutGUI());
		
		//set main menu gui at the center of the root pane
		root.setCenter(loadMenuGUI());
		
		//set "receipt" gui at the right of the root pane
		root.setRight(rightPane);

		Scene scene = new Scene(root, 900,700);
		//link to the css file
		scene.getStylesheets().add(getClass().getResource("css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("McPattern");
		primaryStage.show();
	}
	
	

}
