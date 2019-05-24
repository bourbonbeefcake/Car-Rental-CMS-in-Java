package program.gui.addingViews;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import com.github.lgooddatepicker.components.DatePickerSettings;

import program.controller.Controller;

/**
 * View that provides the GUI for user input in order a new Vehicle be added into the Model if all input is validated. 
 * @author Triantafyllidis Antonios 
 *
 */
public class AddVehicleView extends JFrame{

	
	private Controller controller;
	private AddVehicleValidator validator;
	private int viewID;
	

	
	private JButton finishInsertButton;
	private JButton cancelInsertButton;
	
	private JComboBox<String> comboBox;
	
	private JFormattedTextField regNumberField;
	private JFormattedTextField modelField;
	private JFormattedTextField makeField;
	private JFormattedTextField topSpeedField;
	private JFormattedTextField dailyHireRateField;
	
	private JLabel regNumberErrorlbl;
	private JLabel modelErrorlbl;
	private JLabel makeErrorlbl;
	private JLabel topSpeedErrorlbl;
	private JLabel dailyHireRateErrorlbl;
	private JLabel lblFuelType;
	private JFormattedTextField fuelTypeField;
	private JLabel fuelTypeErrorlbl;
	private JLabel lblNumberOfDoors;
	private JFormattedTextField numberOfDoorsField;
	private JLabel numberOfDoorsErrorlbl;
	private JLabel lblNumberOfSeats;
	private JFormattedTextField numberOfSeatsField;
	private JLabel numberOfSeatsErrorlbl;
	private JLabel lblCargoCapacitytons;
	private JTextField loadingCapacityField;
	private JLabel loadingCapacityErrorlbl;
	private Component rigidArea;
	


	/**
	 * Initializes the AddVehicleView
	 * @param controller - The instance of the controller
	 * @param validator - The instance of the validator specific to that class
	 */
	public AddVehicleView(Controller controller, AddVehicleValidator validator) {
		this.controller = controller;
		this.validator = validator;
		initialize();
		
		//Adding the validator as document listener to the documents of all the text fields 
		regNumberField.getDocument().addDocumentListener(validator);
		modelField.getDocument().addDocumentListener(validator);
		makeField.getDocument().addDocumentListener(validator);
		topSpeedField.getDocument().addDocumentListener(validator);
		dailyHireRateField.getDocument().addDocumentListener(validator);
		fuelTypeField.getDocument().addDocumentListener(validator);
		numberOfDoorsField.getDocument().addDocumentListener(validator);
		numberOfSeatsField.getDocument().addDocumentListener(validator);
		loadingCapacityField.getDocument().addDocumentListener(validator);
		
		//Adding this view to both its validator and the controller
		validator.addView(this);
		controller.addView(this);
		//Showing only car fields, since the car menu is selected by default at launch of the view
		validator.showOnlyCarFields();
	}
	
	private void initialize() {
		setTitle("Add New Vehicle");
		setMinimumSize(new Dimension(900, 700));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel menuPanel = new JPanel();
		menuPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		getContentPane().add(menuPanel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{68, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{14, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		menuPanel.setLayout(gbl_panel);
		
		JLabel lblAddNewUser = new JLabel("Add New Vehicle");
		lblAddNewUser.setFont(new Font("Tahoma", Font.BOLD, 19));
		GridBagConstraints gbc_lblAddNewUser = new GridBagConstraints();
		gbc_lblAddNewUser.insets = new Insets(0, 0, 0, 5);
		gbc_lblAddNewUser.fill = GridBagConstraints.VERTICAL;
		gbc_lblAddNewUser.anchor = GridBagConstraints.WEST;
		gbc_lblAddNewUser.gridx = 0;
		gbc_lblAddNewUser.gridy = 0;
		menuPanel.add(lblAddNewUser, gbc_lblAddNewUser);
		
		 finishInsertButton = new JButton("Finish");
		GridBagConstraints gbc_btnFinish = new GridBagConstraints();
		gbc_btnFinish.insets = new Insets(0, 0, 0, 5);
		gbc_btnFinish.anchor = GridBagConstraints.EAST;
		gbc_btnFinish.gridx = 7;
		gbc_btnFinish.gridy = 0;
		menuPanel.add(finishInsertButton, gbc_btnFinish);
		finishInsertButton.addActionListener(validator);
		finishInsertButton.setFocusPainted(false);
		
		 cancelInsertButton = new JButton("Cancel");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.anchor = GridBagConstraints.EAST;
		gbc_btnCancel.gridx = 8;
		gbc_btnCancel.gridy = 0;
		menuPanel.add(cancelInsertButton, gbc_btnCancel);
		cancelInsertButton.addActionListener(validator);
		cancelInsertButton.setFocusPainted(false);
		
		JPanel insertionPanel = new JPanel();
		insertionPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 2;
		getContentPane().add(insertionPanel, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		insertionPanel.setLayout(gbl_panel_1);
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Car", "Mini-bus", "Lorry"}));
		comboBox.setSelectedItem("Car");
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.anchor = GridBagConstraints.WEST;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 0;
		comboBox.addActionListener(validator);
		insertionPanel.add(comboBox, gbc_comboBox);
		
		JLabel lblRegNumber = new JLabel("Registration Number:");
		GridBagConstraints gbc_lblRegNumber = new GridBagConstraints();
		gbc_lblRegNumber.anchor = GridBagConstraints.EAST;
		gbc_lblRegNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblRegNumber.gridx = 1;
		gbc_lblRegNumber.gridy = 1;
		insertionPanel.add(lblRegNumber, gbc_lblRegNumber);
		
		regNumberField = new JFormattedTextField();
		GridBagConstraints gbc_regNumberField = new GridBagConstraints();
		gbc_regNumberField.anchor = GridBagConstraints.WEST;
		gbc_regNumberField.insets = new Insets(0, 0, 5, 5);
		gbc_regNumberField.gridx = 2;
		gbc_regNumberField.gridy = 1;
		insertionPanel.add(regNumberField, gbc_regNumberField);
		regNumberField.setColumns(10);
		
		regNumberErrorlbl = new JLabel("Minimum 2, Maximum 15, only letters and numbers.");
		GridBagConstraints gbc_regNumberErrorlbl = new GridBagConstraints();
		gbc_regNumberErrorlbl.anchor = GridBagConstraints.WEST;
		gbc_regNumberErrorlbl.insets = new Insets(0, 0, 5, 5);
		gbc_regNumberErrorlbl.gridx = 3;
		gbc_regNumberErrorlbl.gridy = 1;
		insertionPanel.add(regNumberErrorlbl, gbc_regNumberErrorlbl);
		
		JLabel lblModel = new JLabel("Model:");
		GridBagConstraints gbc_lblModel = new GridBagConstraints();
		gbc_lblModel.anchor = GridBagConstraints.EAST;
		gbc_lblModel.insets = new Insets(0, 0, 5, 5);
		gbc_lblModel.gridx = 1;
		gbc_lblModel.gridy = 2;
		insertionPanel.add(lblModel, gbc_lblModel);
		
		modelField = new JFormattedTextField();
		GridBagConstraints gbc_modelField = new GridBagConstraints();
		gbc_modelField.anchor = GridBagConstraints.WEST;
		gbc_modelField.insets = new Insets(0, 0, 5, 5);
		gbc_modelField.gridx = 2;
		gbc_modelField.gridy = 2;
		insertionPanel.add(modelField, gbc_modelField);
		modelField.setColumns(10);
		
		 modelErrorlbl = new JLabel("Minimum 2, Maximum 18, letters and numbers allowed.");
		GridBagConstraints gbc_modelErrorlbl = new GridBagConstraints();
		gbc_modelErrorlbl.anchor = GridBagConstraints.WEST;
		gbc_modelErrorlbl.insets = new Insets(0, 0, 5, 5);
		gbc_modelErrorlbl.gridx = 3;
		gbc_modelErrorlbl.gridy = 2;
		insertionPanel.add(modelErrorlbl, gbc_modelErrorlbl);
		
		JLabel lblMake = new JLabel("Make: ");
		GridBagConstraints gbc_lblMake = new GridBagConstraints();
		gbc_lblMake.anchor = GridBagConstraints.EAST;
		gbc_lblMake.insets = new Insets(0, 0, 5, 5);
		gbc_lblMake.gridx = 1;
		gbc_lblMake.gridy = 3;
		insertionPanel.add(lblMake, gbc_lblMake);
		
		makeField = new JFormattedTextField();
		makeField.setColumns(10);
		GridBagConstraints gbc_makeField = new GridBagConstraints();
		gbc_makeField.anchor = GridBagConstraints.WEST;
		gbc_makeField.insets = new Insets(0, 0, 5, 5);
		gbc_makeField.gridx = 2;
		gbc_makeField.gridy = 3;
		insertionPanel.add(makeField, gbc_makeField);
		
		 makeErrorlbl = new JLabel("Minimum 2, Maximum 18, letters and numbers allowed.");
		GridBagConstraints gbc_makeErrorlbl = new GridBagConstraints();
		gbc_makeErrorlbl.anchor = GridBagConstraints.WEST;
		gbc_makeErrorlbl.insets = new Insets(0, 0, 5, 5);
		gbc_makeErrorlbl.gridx = 3;
		gbc_makeErrorlbl.gridy = 3;
		insertionPanel.add(makeErrorlbl, gbc_makeErrorlbl);
		
		JLabel lblNewLabel = new JLabel("Top Speed: (in Kilometers)");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 4;
		insertionPanel.add(lblNewLabel, gbc_lblNewLabel);
		
		topSpeedField = new JFormattedTextField();
		topSpeedField.setColumns(10);
		GridBagConstraints gbc_topSpeedField = new GridBagConstraints();
		gbc_topSpeedField.anchor = GridBagConstraints.WEST;
		gbc_topSpeedField.insets = new Insets(0, 0, 5, 5);
		gbc_topSpeedField.gridx = 2;
		gbc_topSpeedField.gridy = 4;
		insertionPanel.add(topSpeedField, gbc_topSpeedField);
		
		 topSpeedErrorlbl = new JLabel("Minimum 2, Maximum 4 numbers allowed, 2 mandatory after decimal place. Example: 190.32");
		GridBagConstraints gbc_topSpeedErrorlbl = new GridBagConstraints();
		gbc_topSpeedErrorlbl.anchor = GridBagConstraints.WEST;
		gbc_topSpeedErrorlbl.insets = new Insets(0, 0, 5, 5);
		gbc_topSpeedErrorlbl.gridx = 3;
		gbc_topSpeedErrorlbl.gridy = 4;
		insertionPanel.add(topSpeedErrorlbl, gbc_topSpeedErrorlbl);
		
		JLabel lblNewLabel_1 = new JLabel("Daily Hire Rate: (in Euros)");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 5;
		insertionPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		dailyHireRateField = new JFormattedTextField();
		dailyHireRateField.setText("");
		dailyHireRateField.setColumns(10);
		GridBagConstraints gbc_dailyHireRateField = new GridBagConstraints();
		gbc_dailyHireRateField.anchor = GridBagConstraints.WEST;
		gbc_dailyHireRateField.insets = new Insets(0, 0, 5, 5);
		gbc_dailyHireRateField.gridx = 2;
		gbc_dailyHireRateField.gridy = 5;
		insertionPanel.add(dailyHireRateField, gbc_dailyHireRateField);
		
		 dailyHireRateErrorlbl = new JLabel("Minimum 1, Maximum 4 numbers allowed, 2 mandatory after decimal place. Example: 190.32");
		GridBagConstraints gbc_dailyHireRateErrorlbl = new GridBagConstraints();
		gbc_dailyHireRateErrorlbl.anchor = GridBagConstraints.WEST;
		gbc_dailyHireRateErrorlbl.insets = new Insets(0, 0, 5, 5);
		gbc_dailyHireRateErrorlbl.gridx = 3;
		gbc_dailyHireRateErrorlbl.gridy = 5;
		insertionPanel.add(dailyHireRateErrorlbl, gbc_dailyHireRateErrorlbl);
		
		lblFuelType = new JLabel("Fuel Type: ");
		GridBagConstraints gbc_lblFuelType = new GridBagConstraints();
		gbc_lblFuelType.anchor = GridBagConstraints.EAST;
		gbc_lblFuelType.insets = new Insets(0, 0, 5, 5);
		gbc_lblFuelType.gridx = 1;
		gbc_lblFuelType.gridy = 6;
		insertionPanel.add(lblFuelType, gbc_lblFuelType);
		
		fuelTypeField = new JFormattedTextField();
		fuelTypeField.setColumns(10);
		GridBagConstraints gbc_fuelTypeField = new GridBagConstraints();
		gbc_fuelTypeField.anchor = GridBagConstraints.WEST;
		gbc_fuelTypeField.insets = new Insets(0, 0, 5, 5);
		gbc_fuelTypeField.gridx = 2;
		gbc_fuelTypeField.gridy = 6;
		insertionPanel.add(fuelTypeField, gbc_fuelTypeField);
		
		fuelTypeErrorlbl = new JLabel("Minimum 2, Maximum 18, letters and numbers allowed.");
		GridBagConstraints gbc_fuelTypeErrorlbl = new GridBagConstraints();
		gbc_fuelTypeErrorlbl.anchor = GridBagConstraints.WEST;
		gbc_fuelTypeErrorlbl.insets = new Insets(0, 0, 5, 5);
		gbc_fuelTypeErrorlbl.gridx = 3;
		gbc_fuelTypeErrorlbl.gridy = 6;
		insertionPanel.add(fuelTypeErrorlbl, gbc_fuelTypeErrorlbl);
		
		lblNumberOfDoors = new JLabel("Number of Doors: ");
		GridBagConstraints gbc_lblNumberOfDoors = new GridBagConstraints();
		gbc_lblNumberOfDoors.anchor = GridBagConstraints.EAST;
		gbc_lblNumberOfDoors.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberOfDoors.gridx = 1;
		gbc_lblNumberOfDoors.gridy = 7;
		insertionPanel.add(lblNumberOfDoors, gbc_lblNumberOfDoors);
		
		numberOfDoorsField = new JFormattedTextField();
		numberOfDoorsField.setColumns(10);
		GridBagConstraints gbc_numberOfDoorsField = new GridBagConstraints();
		gbc_numberOfDoorsField.anchor = GridBagConstraints.WEST;
		gbc_numberOfDoorsField.insets = new Insets(0, 0, 5, 5);
		gbc_numberOfDoorsField.gridx = 2;
		gbc_numberOfDoorsField.gridy = 7;
		insertionPanel.add(numberOfDoorsField, gbc_numberOfDoorsField);
		
		numberOfDoorsErrorlbl = new JLabel("Minimum 1, Maximum 2 numbers allowed.");
		GridBagConstraints gbc_numberOfDoorsErrorlbl = new GridBagConstraints();
		gbc_numberOfDoorsErrorlbl.anchor = GridBagConstraints.WEST;
		gbc_numberOfDoorsErrorlbl.insets = new Insets(0, 0, 5, 5);
		gbc_numberOfDoorsErrorlbl.gridx = 3;
		gbc_numberOfDoorsErrorlbl.gridy = 7;
		insertionPanel.add(numberOfDoorsErrorlbl, gbc_numberOfDoorsErrorlbl);
		
		lblNumberOfSeats = new JLabel("Number of Seats: ");
		GridBagConstraints gbc_lblNumberOfSeats = new GridBagConstraints();
		gbc_lblNumberOfSeats.anchor = GridBagConstraints.EAST;
		gbc_lblNumberOfSeats.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberOfSeats.gridx = 1;
		gbc_lblNumberOfSeats.gridy = 8;
		insertionPanel.add(lblNumberOfSeats, gbc_lblNumberOfSeats);
		
		numberOfSeatsField = new JFormattedTextField();
		numberOfSeatsField.setColumns(10);
		GridBagConstraints gbc_numberOfSeatsField = new GridBagConstraints();
		gbc_numberOfSeatsField.anchor = GridBagConstraints.WEST;
		gbc_numberOfSeatsField.insets = new Insets(0, 0, 5, 5);
		gbc_numberOfSeatsField.gridx = 2;
		gbc_numberOfSeatsField.gridy = 8;
		insertionPanel.add(numberOfSeatsField, gbc_numberOfSeatsField);
		
		numberOfSeatsErrorlbl = new JLabel("Minimum 1, Maximum 3 numbers allowed.");
		GridBagConstraints gbc_numberOfSeatsErrorlbl = new GridBagConstraints();
		gbc_numberOfSeatsErrorlbl.anchor = GridBagConstraints.WEST;
		gbc_numberOfSeatsErrorlbl.insets = new Insets(0, 0, 5, 5);
		gbc_numberOfSeatsErrorlbl.gridx = 3;
		gbc_numberOfSeatsErrorlbl.gridy = 8;
		insertionPanel.add(numberOfSeatsErrorlbl, gbc_numberOfSeatsErrorlbl);
		
		lblCargoCapacitytons = new JLabel("Cargo Capacity (tons): ");
		GridBagConstraints gbc_lblCargoCapacitytons = new GridBagConstraints();
		gbc_lblCargoCapacitytons.anchor = GridBagConstraints.EAST;
		gbc_lblCargoCapacitytons.insets = new Insets(0, 0, 5, 5);
		gbc_lblCargoCapacitytons.gridx = 1;
		gbc_lblCargoCapacitytons.gridy = 9;
		insertionPanel.add(lblCargoCapacitytons, gbc_lblCargoCapacitytons);
		
		loadingCapacityField = new JTextField();
		GridBagConstraints gbc_loadingCapacityField = new GridBagConstraints();
		gbc_loadingCapacityField.anchor = GridBagConstraints.WEST;
		gbc_loadingCapacityField.insets = new Insets(0, 0, 5, 5);
		gbc_loadingCapacityField.gridx = 2;
		gbc_loadingCapacityField.gridy = 9;
		insertionPanel.add(loadingCapacityField, gbc_loadingCapacityField);
		loadingCapacityField.setColumns(10);
		
		loadingCapacityErrorlbl = new JLabel("Minimum 1, Maximum 3 numbers allowed. Loading capacity is measured in tons.");
		GridBagConstraints gbc_loadingCapacityErrorlbl = new GridBagConstraints();
		gbc_loadingCapacityErrorlbl.anchor = GridBagConstraints.WEST;
		gbc_loadingCapacityErrorlbl.insets = new Insets(0, 0, 5, 5);
		gbc_loadingCapacityErrorlbl.gridx = 3;
		gbc_loadingCapacityErrorlbl.gridy = 9;
		insertionPanel.add(loadingCapacityErrorlbl, gbc_loadingCapacityErrorlbl);
		
		
		rigidArea = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea = new GridBagConstraints();
		gbc_rigidArea.insets = new Insets(0, 0, 0, 5);
		gbc_rigidArea.gridx = 1;
		gbc_rigidArea.gridy = 10;
		insertionPanel.add(rigidArea, gbc_rigidArea);
		
	}
	


	
	
	/*Declaring Setters and Getters*/
	public int getViewID() {
		return viewID;
	}

	public void setViewID(int viewID) {
		this.viewID = viewID;
	}

	public JButton getFinishInsertButton() {
		return finishInsertButton;
	}

	public JComboBox<String> getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox<String> comboBox) {
		this.comboBox = comboBox;
	}

	public void setFinishInsertButton(JButton finishInsertButton) {
		this.finishInsertButton = finishInsertButton;
	}

	public JButton getCancelInsertButton() {
		return cancelInsertButton;
	}

	public void setCancelInsertButton(JButton cancelInsertButton) {
		this.cancelInsertButton = cancelInsertButton;
	}

	public JFormattedTextField getRegNumberField() {
		return regNumberField;
	}

	public void setRegNumberField(JFormattedTextField regNumberField) {
		this.regNumberField = regNumberField;
	}

	public JFormattedTextField getModelField() {
		return modelField;
	}

	public void setModelField(JFormattedTextField modelField) {
		this.modelField = modelField;
	}

	public JFormattedTextField getMakeField() {
		return makeField;
	}

	public void setMakeField(JFormattedTextField makeField) {
		this.makeField = makeField;
	}

	public JFormattedTextField getTopSpeedField() {
		return topSpeedField;
	}

	public void setTopSpeedField(JFormattedTextField topSpeedField) {
		this.topSpeedField = topSpeedField;
	}

	public JFormattedTextField getDailyHireRateField() {
		return dailyHireRateField;
	}

	public void setDailyHireRateField(JFormattedTextField dailyHireRateField) {
		this.dailyHireRateField = dailyHireRateField;
	}

	public JLabel getRegNumberErrorlbl() {
		return regNumberErrorlbl;
	}

	public void setRegNumberErrorlbl(JLabel regNumberErrorlbl) {
		this.regNumberErrorlbl = regNumberErrorlbl;
	}

	public JLabel getModelErrorlbl() {
		return modelErrorlbl;
	}

	public void setModelErrorlbl(JLabel modelErrorlbl) {
		this.modelErrorlbl = modelErrorlbl;
	}

	public JLabel getMakeErrorlbl() {
		return makeErrorlbl;
	}

	public void setMakeErrorlbl(JLabel makeErrorlbl) {
		this.makeErrorlbl = makeErrorlbl;
	}

	public JLabel getTopSpeedErrorlbl() {
		return topSpeedErrorlbl;
	}

	public void setTopSpeedErrorlbl(JLabel topSpeedErrorlbl) {
		this.topSpeedErrorlbl = topSpeedErrorlbl;
	}

	public JLabel getDailyHireRateErrorlbl() {
		return dailyHireRateErrorlbl;
	}

	public void setDailyHireRateErrorlbl(JLabel dailyHireRateErrorlbl) {
		this.dailyHireRateErrorlbl = dailyHireRateErrorlbl;
	}

	public JFormattedTextField getFuelTypeField() {
		return fuelTypeField;
	}

	public void setFuelTypeField(JFormattedTextField fuelTypeField) {
		this.fuelTypeField = fuelTypeField;
	}

	public JLabel getFuelTypeErrorlbl() {
		return fuelTypeErrorlbl;
	}

	public void setFuelTypeErrorlbl(JLabel fuelTypeErrorlbl) {
		this.fuelTypeErrorlbl = fuelTypeErrorlbl;
	}

	public JFormattedTextField getNumberOfDoorsField() {
		return numberOfDoorsField;
	}

	public void setNumberOfDoorsField(JFormattedTextField numberOfDoorsField) {
		this.numberOfDoorsField = numberOfDoorsField;
	}

	public JLabel getNumberOfDoorsErrorlbl() {
		return numberOfDoorsErrorlbl;
	}

	public void setNumberOfDoorsErrorlbl(JLabel numberOfDoorsErrorlbl) {
		this.numberOfDoorsErrorlbl = numberOfDoorsErrorlbl;
	}

	public JFormattedTextField getNumberOfSeatsField() {
		return numberOfSeatsField;
	}

	public void setNumberOfSeatsField(JFormattedTextField numberOfSeatsField) {
		this.numberOfSeatsField = numberOfSeatsField;
	}

	public JLabel getNumberOfSeatsErrorlbl() {
		return numberOfSeatsErrorlbl;
	}

	public void setNumberOfSeatsErrorlbl(JLabel numberOfSeatsErrorlbl) {
		this.numberOfSeatsErrorlbl = numberOfSeatsErrorlbl;
	}

	public JTextField getLoadingCapacityField() {
		return loadingCapacityField;
	}

	public void setLoadingCapacityField(JTextField loadingCapacityField) {
		this.loadingCapacityField = loadingCapacityField;
	}

	public JLabel getLoadingCapacityErrorlbl() {
		return loadingCapacityErrorlbl;
	}

	public void setLoadingCapacityErrorlbl(JLabel loadingCapacityErrorlbl) {
		this.loadingCapacityErrorlbl = loadingCapacityErrorlbl;
	}

	public JLabel getLblFuelType() {
		return lblFuelType;
	}

	public void setLblFuelType(JLabel lblFuelType) {
		this.lblFuelType = lblFuelType;
	}

	public JLabel getLblNumberOfDoors() {
		return lblNumberOfDoors;
	}

	public void setLblNumberOfDoors(JLabel lblNumberOfDoors) {
		this.lblNumberOfDoors = lblNumberOfDoors;
	}

	public JLabel getLblNumberOfSeats() {
		return lblNumberOfSeats;
	}

	public void setLblNumberOfSeats(JLabel lblNumberOfSeats) {
		this.lblNumberOfSeats = lblNumberOfSeats;
	}

	public JLabel getLblCargoCapacitytons() {
		return lblCargoCapacitytons;
	}

	public void setLblCargoCapacitytons(JLabel lblCargoCapacitytons) {
		this.lblCargoCapacitytons = lblCargoCapacitytons;
	}
	
	

	
	


}
