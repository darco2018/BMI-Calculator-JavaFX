package application;


import java.math.RoundingMode;
import java.text.DecimalFormat;

import application.BMICalculator.Gender;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

public class BMICalculatorController {
	
		private BMICalculator calculator; 
		
		@FXML
	    private RadioButton femaleRadioButton;
	    
	    @FXML
	    private RadioButton maleRadioButton;
	    
	    @FXML
	    private Label referenceTable;
	    
	    @FXML
	    private Label weightLabel;
	    
	    @FXML
	    private Label heightLabel;	    
	    
	    @FXML
	    private Slider weightSlider;

	    @FXML
	    private Slider heightSlider;

	    @FXML
	    private TextField resultTextField;
	    
	    @FXML
	    private TextField interpretationTextField;
	    

	    @FXML
	    void initialize() {
	    	// group RadioButtons and select one of them
	    	final ToggleGroup group = new ToggleGroup();
	    	maleRadioButton.setToggleGroup(group);
	    	femaleRadioButton.setToggleGroup(group);
	    	femaleRadioButton.setSelected(true);
	    	
	    	// set sliders' initial values and display them in the labels
	    	weightSlider.setValue(50);
	    	weightLabel.setText(String.valueOf((int)weightSlider.getValue()));
	    	heightSlider.setValue(140);
	    	heightLabel.setText(String.valueOf((int)heightSlider.getValue()));
	    	
	    	// create BMICalculator object with sliders' and radiobutton's initial values
	    	calculator = new BMICalculator(weightSlider.getValue(), heightSlider.getValue(), 
	    					femaleRadioButton.isSelected() ? Gender.FEMALE : Gender.MALE);
	    	
	    	//display the initial BMI with interpretation and the BMI table for adults	    	
	    	printResult();
	    	
	    	// add listener to RadioButtons
	    	group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
	    		
	    		@Override
	    		public void changed(ObservableValue<? extends Toggle> ov,  Toggle old_toggle, Toggle new_toggle) {
	    			
	    			if (femaleRadioButton.isSelected())
	    				calculator.setGender(Gender.FEMALE);	
	    			else
	    				calculator.setGender(Gender.MALE);
	    			
	    			printResult();
	    	    }
	    	});
	    	
	    	// add listener to weightSlider
	    	weightSlider.valueProperty().addListener(new ChangeListener<Number>() {

				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
					
					int weight = newValue.intValue(); 
					weightLabel.setText(String.valueOf(weight));
					calculator.setWeightInKilograms(weight);
					printResult();
				}
			});
	    	
	    	// add listener to heightSlider
	    	heightSlider.valueProperty().addListener(new ChangeListener<Number>() {

				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
					
					int height = newValue.intValue(); 
					heightLabel.setText(String.valueOf(height));
					calculator.setHeightInCentimeters(height);
					printResult();
				}
			});
	    } 
	    
	    
	    private void printResult(){
	    	
	    	DecimalFormat decimalFormat = new DecimalFormat("#.##");
	    	decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
	    	double bmi = calculator.calculateBMI();
	    	resultTextField.setText("Your BMI is " + decimalFormat.format(bmi));
	    	
	    	referenceTable.setText(calculator.bmiTableForAdults());
	    	
	    	interpretationTextField.setText( BMICalculator.bmiInterpretation(bmi, calculator.getGender()) );
	    }
}
