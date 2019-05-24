package program.gui.addingViews;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import program.classes.Car;
import program.classes.Lorry;
import program.classes.MiniBus;
import program.controller.Controller;

/**
 * Class that validates every text field in the AddVehicleView. It also provides listeners for the option buttons of the same view.
 * @author Triantafyllidis Antonios 
 * 
 *
 */
public class AddVehicleValidator implements DocumentListener, ActionListener{

	private AddVehicleView addVehicleView;
	private Controller controller;

	private JTextField regNumberBox;
	private JTextField modelBox;
	private JTextField makeBox;
	private JTextField topSpeedBox;
	private JTextField dailyHireRateBox;

	//car
	private JTextField fuelTypeBox;
	private JTextField numberOfDoorsBox;
	//minibus
	private JTextField numberOfSeatsBox;
	//lorry
	private JTextField loadingCapacityBox;


	/**
	 * The constructor only takes an instance of the Controller
	 * @param controller
	 */
	public AddVehicleValidator(Controller controller) {

		this.controller = controller;
	}


	/**
	 * Adds a AddVehicleView to this validator.
	 * @param addVehicleView - The view to validate
	 */
	public void addView(AddVehicleView addVehicleView) {
		this.addVehicleView = addVehicleView;

		//Each field that is to be validated is returned from the view and saved in the validator
		regNumberBox = addVehicleView.getRegNumberField();
		modelBox = addVehicleView.getModelField();
		makeBox = addVehicleView.getMakeField();
		topSpeedBox = addVehicleView.getTopSpeedField();
		dailyHireRateBox = addVehicleView.getDailyHireRateField();
		fuelTypeBox = addVehicleView.getFuelTypeField();
		numberOfDoorsBox = addVehicleView.getNumberOfDoorsField();
		numberOfSeatsBox = addVehicleView.getNumberOfSeatsField();
		loadingCapacityBox = addVehicleView.getLoadingCapacityField();
	}


	//On update, insertion, deletion of characters inside a field's document, check which field was it
	@Override
	public void changedUpdate(DocumentEvent ev) {
		checkWhichField(ev);

	}


	@Override
	public void insertUpdate(DocumentEvent ev) {
		checkWhichField(ev);


	}


	@Override
	public void removeUpdate(DocumentEvent ev) {
		checkWhichField(ev);

	}


	/**
	 * Checks which field of all the fields in the view, triggered a Document Event.
	 * An appropriate function for the validation of each field is constructed, and invoked if that field caused the document event.
	 * @param ev - The event to check its source
	 */
	private void checkWhichField(DocumentEvent ev) {
		//The input is not prevented once the requirements are not met. 
		//This is because, if the user is looking down while typing he will not notice that nothing is actually written down.
		//This can be frustrating for the user, hence it is not happening. 
		//https://ux.stackexchange.com/questions/56290/text-field-validation-vs-prevention
		if (ev.getDocument().equals(regNumberBox.getDocument())) {
			validateRegNumber();
		}else if (ev.getDocument().equals(modelBox.getDocument())) {
			validateModel();
		}else if (ev.getDocument().equals(makeBox.getDocument())) {
			validateMake();
		}else if (ev.getDocument().equals(topSpeedBox.getDocument())) {
			validateTopSpeed();
		}else if (ev.getDocument().equals(dailyHireRateBox.getDocument())) {
			validateDailyHireRate();
		}else if (ev.getDocument().equals(fuelTypeBox.getDocument())) {
			validateFuelType();
		}else if (ev.getDocument().equals(numberOfDoorsBox.getDocument())) {
			validateNumOfDoors();
		}else if (ev.getDocument().equals(numberOfSeatsBox.getDocument())) {
			validateNumOfSeats();
		}else if (ev.getDocument().equals(loadingCapacityBox.getDocument())) {
			validatenLoadingCapacity();
		}

	}

	/**
	 * Validate the Registration Number field. <br>
	 * <strong>Constraints: </strong> <br>
	 * The Registration Number must be unique <br>
	 * The Registration Number must contain only letters and numbers, minimum 2 characters, maximum 13.<br>
	 * Uses red color on the Error label for invalid insertion and green for correct.
	 * @return True if the Registration Number successful passed the validation and false if not.
	 */
	private boolean validateRegNumber() {
		JLabel errorLabel = addVehicleView.getRegNumberErrorlbl();

		Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{2,11}+$");
		String text = regNumberBox.getText();
		Matcher m = pattern.matcher(text);
		if (!m.matches()) {
			if (controller.getModel().vehicleRegNumberExists(text)) {
				errorLabel.setForeground(Color.red);
				errorLabel.setText("<html>Registration number must be unique!</html>");
				regNumberBox.setBorder(new LineBorder(Color.red));
			}else {
				errorLabel.setForeground(Color.red);
				//Not limiting to British numbers. The vehicles might have different plates.
				errorLabel.setText("Minimum 2, Maximum 13, only letters and numbers.");
				regNumberBox.setBorder(new LineBorder(Color.red));
			}
			return false;
		}else {
			errorLabel.setForeground(Color.green);
			errorLabel.setText("Registration Number OK!");
			regNumberBox.setBorder(new LineBorder(Color.green));
			return true;
		}
	}




	/**
	 * Validate the Model field. <br>
	 * <strong>Constraints: </strong> <br>
	 * The Model must contain only letters and numbers, minimum 2 characters, maximum 18.<br>
	 * Uses red color on the Error label for invalid insertion and green for correct.
	 * @return True if the Model successful passed the validation and false if not.
	 */
	private boolean validateModel() {
		JLabel errorLabel = addVehicleView.getModelErrorlbl();

		Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{2,18}+$");
		String text = modelBox.getText();
		Matcher m = pattern.matcher(text);
		if (!m.matches()) {
			errorLabel.setForeground(Color.red);
			errorLabel.setText("Minimum 2, Maximum 18, letters and numbers allowed.");
			modelBox.setBorder(new LineBorder(Color.red));
			return false;
		}else {
			errorLabel.setForeground(Color.green);
			errorLabel.setText("Model OK!");
			modelBox.setBorder(new LineBorder(Color.green));
			return true;
		}
	}




	/**
	 * Validate the Make field. <br>
	 * <strong>Constraints: </strong> <br>
	 * The Make must contain only letters and numbers, minimum 2 characters, maximum 18.<br>
	 * Uses red color on the Error label for invalid insertion and green for correct.
	 * @return True if the Make successful passed the validation and false if not.
	 */
	private boolean validateMake() {
		JLabel errorLabel = addVehicleView.getMakeErrorlbl();

		Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{2,18}+$");
		String text = makeBox.getText();
		Matcher m = pattern.matcher(text);
		if (!m.matches()) {
			errorLabel.setForeground(Color.red);
			errorLabel.setText("Minimum 2, Maximum 18, letters and numbers allowed.");
			makeBox.setBorder(new LineBorder(Color.red));		
			return false;
		}else {
			errorLabel.setForeground(Color.green);
			errorLabel.setText("Make OK!");
			makeBox.setBorder(new LineBorder(Color.green));
			return true;
		}
	}





	/**
	 * Validate the Top Speed field. <br>
	 * <strong>Constraints: </strong> <br>
	 * The Top Speed must contain only numbers, minimum 2 digits, maximum 4 and after decimal point 2 mandatory digits.<br>
	 * Uses red color on the Error label for invalid insertion and green for correct.
	 * @return True if the Top Speed successful passed the validation and false if not.
	 */
	private boolean validateTopSpeed() {
		JLabel errorLabel = addVehicleView.getTopSpeedErrorlbl();

		Pattern pattern = Pattern.compile("^[0-9]{2,4}\\.[0-9]{2,4}+$");
		String text = topSpeedBox.getText();
		Matcher m = pattern.matcher(text);
		if (!m.matches()) {
			errorLabel.setForeground(Color.red);
			errorLabel.setText("Minimum 2, Maximum 4 numbers allowed, 2 mandatory after decimal place. Example: 190.32");
			topSpeedBox.setBorder(new LineBorder(Color.red));		
			return false;
		}else {
			errorLabel.setForeground(Color.green);
			errorLabel.setText("Top Speed OK!");
			topSpeedBox.setBorder(new LineBorder(Color.green));
			return true;
		}
	}





	/**
	 * Validate the Daily Hire Rate field. <br>
	 * <strong>Constraints: </strong> <br>
	 * The Daily Hire Rate must contain only numbers, minimum 1 digits, maximum 4 and after decimal point 2 mandatory digits.<br>
	 * Uses red color on the Error label for invalid insertion and green for correct.
	 * @return True if the Daily Hire Rate successful passed the validation and false if not.
	 */
	private boolean validateDailyHireRate() {

		JLabel errorLabel = addVehicleView.getDailyHireRateErrorlbl();

		Pattern pattern = Pattern.compile("^[0-9]{1,4}\\.[0-9]{1,4}+$");
		String text = dailyHireRateBox.getText();
		Matcher m = pattern.matcher(text);
		if (!m.matches()) {
			errorLabel.setForeground(Color.red);
			errorLabel.setText("Minimum 1, Maximum 4 numbers allowed, 2 mandatory after decimal place. Example: 190.32");
			dailyHireRateBox.setBorder(new LineBorder(Color.red));	
			return false;
		}else {
			errorLabel.setForeground(Color.green);
			errorLabel.setText("Daily Hire Rate OK!");
			dailyHireRateBox.setBorder(new LineBorder(Color.green));
			return true;
		}
	}




	/**
	 * Validate the Fuel Type field. <br>
	 * <strong>Constraints: </strong> <br>
	 * The Fuel Type must contain only letters and numbers, minimum 2 characters, maximum 18.<br>
	 * Uses red color on the Error label for invalid insertion and green for correct.
	 * @return True if the Fuel Type successful passed the validation and false if not.
	 */
	private boolean validateFuelType() {

		JLabel errorLabel = addVehicleView.getFuelTypeErrorlbl();

		Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{2,18}+$");
		String text = fuelTypeBox.getText();
		Matcher m = pattern.matcher(text);
		if (!m.matches()) {
			errorLabel.setForeground(Color.red);
			errorLabel.setText("Minimum 2, Maximum 18, letters and numbers allowed.");
			fuelTypeBox.setBorder(new LineBorder(Color.red));		
			return false;
		}else {
			errorLabel.setForeground(Color.green);
			errorLabel.setText("Fuel Type OK!");
			fuelTypeBox.setBorder(new LineBorder(Color.green));
			return true;
		}
	}





	/**
	 * Validate the Number of Doors field. <br>
	 * <strong>Constraints: </strong> <br>
	 * The Number of Doors must contain only numbers, minimum 1 digits, maximum 2<br>
	 * Uses red color on the Error label for invalid insertion and green for correct.
	 * @return True if the Number of Doors successful passed the validation and false if not.
	 */
	private boolean validateNumOfDoors() {

		JLabel errorLabel = addVehicleView.getNumberOfDoorsErrorlbl();

		Pattern pattern = Pattern.compile("^[0-9]{1,2}+$");
		String text = numberOfDoorsBox.getText();
		Matcher m = pattern.matcher(text);
		if (!m.matches()) {
			errorLabel.setForeground(Color.red);
			errorLabel.setText("Minimum 1, Maximum 2 numbers allowed.");
			numberOfDoorsBox.setBorder(new LineBorder(Color.red));
			return false;
		}else {
			errorLabel.setForeground(Color.green);
			errorLabel.setText("Number of Doors OK!");
			numberOfDoorsBox.setBorder(new LineBorder(Color.green));
			return true;
		}
	}




	/**
	 * Validate the Number of Seats field. <br>
	 * <strong>Constraints: </strong> <br>
	 * The Number of Seats must contain only numbers, minimum 1 digits, maximum 3<br>
	 * Uses red color on the Error label for invalid insertion and green for correct.
	 * @return True if the Number of Seats successful passed the validation and false if not.
	 */
	private boolean validateNumOfSeats() {

		JLabel errorLabel = addVehicleView.getNumberOfSeatsErrorlbl();

		Pattern pattern = Pattern.compile("^[0-9]{1,3}+$");
		String text = numberOfSeatsBox.getText();
		Matcher m = pattern.matcher(text);
		if (!m.matches()) {
			errorLabel.setForeground(Color.red);
			errorLabel.setText("Minimum 1, Maximum 3 numbers allowed.");
			numberOfSeatsBox.setBorder(new LineBorder(Color.red));	
			return false;
		}else {
			errorLabel.setForeground(Color.green);
			errorLabel.setText("Number of Seats OK!");
			numberOfSeatsBox.setBorder(new LineBorder(Color.green));
			return true;
		}
	}


	/**
	 * Validate the Number of Seats field. <br>
	 * <strong>Constraints: </strong> <br>
	 * The Number of Seats must contain only numbers, minimum 1 digits, maximum 3<br>
	 * Uses red color on the Error label for invalid insertion and green for correct.
	 * @return True if the Number of Seats successful passed the validation and false if not.
	 */
	private boolean validatenLoadingCapacity() {

		JLabel errorLabel = addVehicleView.getLoadingCapacityErrorlbl();

		Pattern pattern = Pattern.compile("^[0-9]{1,3}+$");
		String text = loadingCapacityBox.getText();
		Matcher m = pattern.matcher(text);
		if (!m.matches()) {
			errorLabel.setForeground(Color.red);
			errorLabel.setText("Minimum 1, Maximum 3 numbers allowed. Loading capacity is measured in tons.");
			loadingCapacityBox.setBorder(new LineBorder(Color.red));	
			return false;
		}else {
			errorLabel.setForeground(Color.green);
			errorLabel.setText("Loading Capacity OK!");
			loadingCapacityBox.setBorder(new LineBorder(Color.green));
			return true;
		}
	}
	
	
	
	

	/**
	 * Is invoked when 'Finish' button is pressed and ensures that all the fields are validated before creating a new instance of the requested Vehicle to the model through the controller. 
	 * Relevant success MessageDialogs are being displayed. 
	 * The view is then disposed. 
	 */
	private void finishValidation(){

		if (validateRegNumber() && validateModel() && validateMake() && validateTopSpeed() && validateDailyHireRate()) {
			if (addVehicleView.getComboBox().getSelectedItem().toString().equals("Car")) {
				if (validateNumOfDoors() && validateFuelType()) {
					controller.getModel().addNewVehicle(new Car(modelBox.getText(), makeBox.getText(), regNumberBox.getText(), Double.parseDouble(topSpeedBox.getText()),Double.parseDouble(dailyHireRateBox.getText()), fuelTypeBox.getText(), Integer.parseInt(numberOfDoorsBox.getText()), false));
					JOptionPane.showMessageDialog(addVehicleView, "Car with registration number " + regNumberBox.getText() + " is added!");
					controller.serializeModel();
					controller.removeView(addVehicleView);
					addVehicleView.dispose();
				}
			}else if(addVehicleView.getComboBox().getSelectedItem().toString().equals("Mini-bus")) {
				if (validateNumOfSeats()) {
					controller.getModel().addNewVehicle(new MiniBus(modelBox.getText(), makeBox.getText(), regNumberBox.getText(), Double.parseDouble(topSpeedBox.getText()), Double.parseDouble(dailyHireRateBox.getText()), Integer.parseInt(numberOfSeatsBox.getText()), false));
					JOptionPane.showMessageDialog(addVehicleView, "Mini bus with registration number " + regNumberBox.getText() + " is added!");
					controller.serializeModel();
					controller.removeView(addVehicleView);
					addVehicleView.dispose();
				}

			}else if(addVehicleView.getComboBox().getSelectedItem().toString().equals("Lorry")) {
				if (validatenLoadingCapacity()) {
					controller.getModel().addNewVehicle(new Lorry(modelBox.getText(), makeBox.getText(), regNumberBox.getText(), Double.parseDouble(topSpeedBox.getText()), Double.parseDouble(dailyHireRateBox.getText()), Double.parseDouble(loadingCapacityBox.getText()), false));
					JOptionPane.showMessageDialog(addVehicleView, "Lorry with registration number " + regNumberBox.getText() + " is added!");
					controller.serializeModel();
					controller.removeView(addVehicleView);
					addVehicleView.dispose();
				}

			}
		}
	}

	/**
	 * Hides and disables Minibus and Lorry related fields and labels in the addVehicleView. Enables and shows only components related to Car data insertion.
	 */
	public void showOnlyCarFields(){


		hideMiniBusFields();
		hideLorryFields();


		addVehicleView.getNumberOfDoorsField().setEnabled(true);
		addVehicleView.getLblNumberOfDoors().setEnabled(true);
		addVehicleView.getNumberOfDoorsErrorlbl().setVisible(true);

		addVehicleView.getLblFuelType().setEnabled(true);
		addVehicleView.getFuelTypeField().setEnabled(true);
		addVehicleView.getFuelTypeErrorlbl().setVisible(true);
	}

	
	/**
	 * Hides and disables Car and Lorry related fields and labels in the addVehicleView. Enables and shows only components related to Minibus data insertion.
	 */
	public void showOnlyMinibusFields(){

		hideCarFields();
		hideLorryFields();


		addVehicleView.getLblNumberOfSeats().setEnabled(true);
		addVehicleView.getNumberOfSeatsErrorlbl().setVisible(true);
		addVehicleView.getNumberOfSeatsField().setEnabled(true);
	}

	
	/**
	 * Hides and disables Car and Minibus related fields and labels in the addVehicleView. Enables and shows only components related to Lorry data insertion.
	 */
	public void showOnlyLorryFields(){

		hideMiniBusFields();
		hideCarFields();

		addVehicleView.getLblCargoCapacitytons().setEnabled(true);
		addVehicleView.getLoadingCapacityErrorlbl().setVisible(true);
		addVehicleView.getLoadingCapacityField().setEnabled(true);
	}







	/**
	 * Hides and disables Car related Fields and labels from addVehicleView
	 */
	public void hideCarFields(){
		addVehicleView.getNumberOfDoorsField().setEnabled(false);
		addVehicleView.getLblNumberOfDoors().setEnabled(false);
		addVehicleView.getNumberOfDoorsErrorlbl().setVisible(false);

		addVehicleView.getLblFuelType().setEnabled(false);
		addVehicleView.getFuelTypeField().setEnabled(false);
		addVehicleView.getFuelTypeErrorlbl().setVisible(false);
	}

	/**
	 * Hides and disables Minibus related Fields and labels from addVehicleView
	 */
	public void hideMiniBusFields(){
		addVehicleView.getLblNumberOfSeats().setEnabled(false);
		addVehicleView.getNumberOfSeatsErrorlbl().setVisible(false);
		addVehicleView.getNumberOfSeatsField().setEnabled(false);
	}
	
	
	/**
	 * Hides and disables Lorry related Fields and labels from addVehicleView
	 */
	public void hideLorryFields(){
		addVehicleView.getLblCargoCapacitytons().setEnabled(false);
		addVehicleView.getLoadingCapacityErrorlbl().setVisible(false);
		addVehicleView.getLoadingCapacityField().setEnabled(false);
	}	


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(addVehicleView.getFinishInsertButton())) {

			finishValidation();

		}else if(e.getSource().equals(addVehicleView.getCancelInsertButton())){
			controller.removeView(addVehicleView);
			addVehicleView.dispose();
		}else if (e.getSource().equals(addVehicleView.getComboBox())) {
			if (addVehicleView.getComboBox().getSelectedItem().equals("Car")) {

				showOnlyCarFields();

				addVehicleView.repaint();
				addVehicleView.revalidate();


			}else if (addVehicleView.getComboBox().getSelectedItem().equals("Mini-bus")) {

				showOnlyMinibusFields();

				addVehicleView.repaint();
				addVehicleView.revalidate();


			}else if (addVehicleView.getComboBox().getSelectedItem().equals("Lorry")) {

				showOnlyLorryFields();

				addVehicleView.repaint();
				addVehicleView.revalidate();
			}
		}

	}

}


