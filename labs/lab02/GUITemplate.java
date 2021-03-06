package labs.lab2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
/**
 * This is a template for a JavaFX Application
 * To use it, extend GUITemplate, create an ArrayList of Strings
 * for the labels of the radio buttons you need, and call the set() 
 * method of this class in the constructor.  In your main method,
 * call the main method of this class with your args as the parameter.
 * 
 * @author Bruce Martin
 * @version January 18, 2016
 */

public class GUITemplate extends Application implements EventHandler<ActionEvent> {
	/** the instructions */
	private Label lbl; 
	/** the answer label */
	private Label answer; 
	/** the Button to execute your instructions */
	private Button btnGo; 
	/** the Button to exit the Application */
	private Button btnQuit; 
	/** a list of TextField controls */
	private ArrayList<TextField> input; 
	/** a list of RadioButton controls */
	private ArrayList<RadioButton> rb; 
	/** the ToggleGroup for the RadioButtons */
	private ToggleGroup tg; 
	
	/**
	 * The set method sets up the GUI.  It should be called from
	 * the constructor of your derived class.
	 * @param instruct is a String for your instructions label at the top
	 * @param numText is an int representing the number of input TextFields
	 * @param rbLabel is an ArrayList of Strings, one for each RadioButton you want
	 * @param myHandle is the EventHandler class, which should be a reference to an object of the derived class, which should override public void handle(ActionEvent arg0) 
	 */
	public void set(String instruct,int numText, ArrayList<String> rbLabel, EventHandler<ActionEvent> myHandle){
		lbl = new Label(instruct);
		input = new ArrayList<TextField>();
		for (int i=0; i<numText; i++) input.add(new TextField());
		rb = new ArrayList<RadioButton>();
		tg = new ToggleGroup();
		for (String rbs: rbLabel){
			RadioButton newButton = new RadioButton(rbs);
			newButton.setToggleGroup(tg);
			rb.add(newButton);
		}
	}
	/**
	 * The default constructor sets up a dummy GUI with one TextField and two RadioButtons
	 */
	public GUITemplate(){
		String[] labels = {"First","Second"};
		lbl = new Label("instructions");
		input = new ArrayList<TextField>();
		for (int i=0; i<1; i++) input.add(new TextField());
		rb = new ArrayList<RadioButton>();
		tg = new ToggleGroup();
		for (String rbs: labels){
			RadioButton newButton = new RadioButton(rbs);
			newButton.setToggleGroup(tg);
			rb.add(newButton);
		}
	}
	/**
	 * getAsDouble will attempt to parse the contents of the TextField
	 * with the given index as a double.
	 * @param inx is an int which represents the index of the TextField
	 * @return The value as a double
	 * @throws ArrayIndexOutOfBoundsException if inx is out of bounds
	 * @throws IllegalArgumentException if the TextField is not a number.
	 */
	public double getAsDouble(int inx){
		if (inx<0 || inx>=input.size())
			throw new ArrayIndexOutOfBoundsException("getAsDouble illegal index: " + inx);
		if (!input.get(inx).getText().matches("^[\\d\\-\\.]+$")){
			throw new IllegalArgumentException(input.get(inx).getText() + " is not a number");	
		}
		return Double.parseDouble(input.get(inx).getText());
	}
	/**
	 * setAnswer sets the answer Label to the passed String
	 * @param s is the String to set the answer Label's text to
	 */
	public void setAnswer(String s){
		this.answer.setText(s);
	}
	/**
	 * getSelectedRBIndex is used to determing which RadioButton is selected
	 * @return the selected index, or -1 if none are selected
	 */
	public int getSelectedRBIndex() {
		for (int i=0; i<this.rb.size(); i++)
			if (rb.get(i).isSelected()) return i;
		return -1;
	}
	@Override
	public void start(Stage stage) throws Exception {
		HBox hb1 = new HBox();
		HBox hb2 = new HBox();
		VBox vb = new VBox();
		hb2.setSpacing(5);
		btnGo = new Button(" Go ");
		btnGo.setOnAction(this);
		answer = new Label("answer here");
		btnQuit = new Button(" Quit ");
		btnQuit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				stage.close();				
			}});
		hb1.setSpacing(20);
		for (TextField tf: this.input){
			hb1.getChildren().add(tf);
		}
		for (RadioButton b: this.rb)
			hb2.getChildren().add(b);
		this.rb.get(0).setSelected(true);
		vb.getChildren().add(lbl);
		vb.getChildren().add(hb1);
		vb.getChildren().add(hb2);
		vb.getChildren().add(btnGo);
		vb.getChildren().add(answer);
		vb.getChildren().add(btnQuit);
		Scene sc = new Scene(vb,800,400);
		stage.setScene(sc);
		sc.getStylesheets().add("labs/lab2/myStyle.css");
		stage.show();
	}
	public static void main(String[] args){
		launch(args);
	}
	/**
	 * handle should be overridden in the derived class to set
	 * what happens when the Go button is clicked.
	 */
	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
