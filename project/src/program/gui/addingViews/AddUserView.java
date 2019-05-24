package program.gui.addingViews;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;

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

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import program.controller.Controller;

/**
 * View that provides the GUI for user input in order a new User be added into the Model if all input is validated. 
 * @author Triantafyllidis Antonios 
 *
 */
public class AddUserView extends JFrame{

	
	private Controller controller;
	private int viewID;
	
	private AddUserValidator validator;
	
	private JButton finishInsertButton;
	private JButton cancelInsertButton;
	
	private JComboBox<String> comboBox;
	
	private JFormattedTextField usernameField;
	private JFormattedTextField passwordField;
	private JFormattedTextField surnameField;
	private JFormattedTextField firstnameField;
	private JFormattedTextField addressField;
	private JFormattedTextField addressNumField;
	private JFormattedTextField phoneField;
	private JFormattedTextField emailField;
	
	/**
	 * Imported class DatePicker provides proper date selection functionality
	 */
	DatePicker dobField;
	DatePickerSettings settings;
	
	private JLabel usernameErrorlbl;
	private JLabel passwordErrorlbl;
	private JLabel surnameErrorlbl;
	private JLabel firstnameErrorlbl;
	private JLabel addressErrorlbl;
	private JLabel addressNumErrorlbl;
	private JLabel phoneErrorlbl;
	private JLabel emailErrorlbl;
	private JLabel dobErrorlbl;
	


	/**
	 * Initializes the AddUserView
	 * @param controller - The instance of the controller
	 * @param validator - The instance of the validator specific to that class
	 */
	public AddUserView(Controller controller, AddUserValidator validator) {
		this.controller = controller;
		this.validator = validator;
		initialize();
		
		//Adding the validator as document listener to the documents of all the text fields 
		usernameField.getDocument().addDocumentListener(validator);
		passwordField.getDocument().addDocumentListener(validator);
		surnameField.getDocument().addDocumentListener(validator);
		firstnameField.getDocument().addDocumentListener(validator);
		addressField.getDocument().addDocumentListener(validator);
		addressNumField.getDocument().addDocumentListener(validator);
		phoneField.getDocument().addDocumentListener(validator);
		emailField.getDocument().addDocumentListener(validator);
		
		//Adding this view to both its validator and the controller
		validator.addView(this);
		controller.addView(this);
	}
	
	private void initialize() {
		setTitle("Add New User");
		setMinimumSize(new Dimension(800, 600));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel menuPanel = new JPanel();
		menuPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		add(menuPanel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{68, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{14, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		menuPanel.setLayout(gbl_panel);
		
		JLabel lblAddNewUser = new JLabel("Add New User");
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
		add(insertionPanel, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		insertionPanel.setLayout(gbl_panel_1);
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Staff", "Customer"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.anchor = GridBagConstraints.WEST;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 0;
		insertionPanel.add(comboBox, gbc_comboBox);
		
		JLabel lblUsername = new JLabel("Username:");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.EAST;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 1;
		gbc_lblUsername.gridy = 1;
		insertionPanel.add(lblUsername, gbc_lblUsername);
		
		usernameField = new JFormattedTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		insertionPanel.add(usernameField, gbc_textField);
		usernameField.setColumns(10);
		
		usernameErrorlbl = new JLabel("Minimum 2, Maximum 13, only letters and numbers, first letter capital.");
		GridBagConstraints gbc_usernameErrorlbl = new GridBagConstraints();
		gbc_usernameErrorlbl.anchor = GridBagConstraints.WEST;
		gbc_usernameErrorlbl.insets = new Insets(0, 0, 5, 0);
		gbc_usernameErrorlbl.gridx = 3;
		gbc_usernameErrorlbl.gridy = 1;
		insertionPanel.add(usernameErrorlbl, gbc_usernameErrorlbl);
		
		JLabel lblPassword = new JLabel("Password:");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 2;
		insertionPanel.add(lblPassword, gbc_lblPassword);
		
		passwordField = new JFormattedTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.anchor = GridBagConstraints.WEST;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 2;
		insertionPanel.add(passwordField, gbc_textField_1);
		passwordField.setColumns(10);
		
		 passwordErrorlbl = new JLabel("Minimum 4, Maximum 16 characters");
		GridBagConstraints gbc_passwordErrorlbl = new GridBagConstraints();
		gbc_passwordErrorlbl.anchor = GridBagConstraints.WEST;
		gbc_passwordErrorlbl.insets = new Insets(0, 0, 5, 0);
		gbc_passwordErrorlbl.gridx = 3;
		gbc_passwordErrorlbl.gridy = 2;
		insertionPanel.add(passwordErrorlbl, gbc_passwordErrorlbl);
		
		JLabel lblSurname = new JLabel("Surname:");
		GridBagConstraints gbc_lblSurname = new GridBagConstraints();
		gbc_lblSurname.anchor = GridBagConstraints.EAST;
		gbc_lblSurname.insets = new Insets(0, 0, 5, 5);
		gbc_lblSurname.gridx = 1;
		gbc_lblSurname.gridy = 3;
		insertionPanel.add(lblSurname, gbc_lblSurname);
		
		surnameField = new JFormattedTextField();
		surnameField.setColumns(10);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.anchor = GridBagConstraints.WEST;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.gridx = 2;
		gbc_textField_2.gridy = 3;
		insertionPanel.add(surnameField, gbc_textField_2);
		
		 surnameErrorlbl = new JLabel("Minimum 3,Maximum 20, letters, symbol ' allowed. First capital.");
		GridBagConstraints gbc_surnameErrorlbl = new GridBagConstraints();
		gbc_surnameErrorlbl.anchor = GridBagConstraints.WEST;
		gbc_surnameErrorlbl.insets = new Insets(0, 0, 5, 0);
		gbc_surnameErrorlbl.gridx = 3;
		gbc_surnameErrorlbl.gridy = 3;
		insertionPanel.add(surnameErrorlbl, gbc_surnameErrorlbl);
		
		JLabel lblNewLabel = new JLabel("Firstname:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 4;
		insertionPanel.add(lblNewLabel, gbc_lblNewLabel);
		
		firstnameField = new JFormattedTextField();
		firstnameField.setColumns(10);
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.anchor = GridBagConstraints.WEST;
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.gridx = 2;
		gbc_textField_3.gridy = 4;
		insertionPanel.add(firstnameField, gbc_textField_3);
		
		 firstnameErrorlbl = new JLabel("Minimum 3,Maximum 20, letters, symbol ' allowed. First capital.");
		GridBagConstraints gbc_firstnameErrorlbl = new GridBagConstraints();
		gbc_firstnameErrorlbl.anchor = GridBagConstraints.WEST;
		gbc_firstnameErrorlbl.insets = new Insets(0, 0, 5, 0);
		gbc_firstnameErrorlbl.gridx = 3;
		gbc_firstnameErrorlbl.gridy = 4;
		insertionPanel.add(firstnameErrorlbl, gbc_firstnameErrorlbl);
		
		JLabel lblNewLabel_1 = new JLabel("Address:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 5;
		insertionPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		addressField = new JFormattedTextField();
		addressField.setColumns(10);
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.anchor = GridBagConstraints.WEST;
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.gridx = 2;
		gbc_textField_4.gridy = 5;
		insertionPanel.add(addressField, gbc_textField_4);
		
		 addressErrorlbl = new JLabel("Minimum 2, Maximum 20 letters and/or spaces allowed.");
		GridBagConstraints gbc_addressErrorlbl = new GridBagConstraints();
		gbc_addressErrorlbl.anchor = GridBagConstraints.WEST;
		gbc_addressErrorlbl.insets = new Insets(0, 0, 5, 0);
		gbc_addressErrorlbl.gridx = 3;
		gbc_addressErrorlbl.gridy = 5;
		insertionPanel.add(addressErrorlbl, gbc_addressErrorlbl);
		
		JLabel lblNewLabel_2 = new JLabel("Address Number:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 6;
		insertionPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		addressNumField = new JFormattedTextField();
		addressNumField.setColumns(10);
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.anchor = GridBagConstraints.WEST;
		gbc_textField_5.insets = new Insets(0, 0, 5, 5);
		gbc_textField_5.gridx = 2;
		gbc_textField_5.gridy = 6;
		insertionPanel.add(addressNumField, gbc_textField_5);
		
		 addressNumErrorlbl = new JLabel("Minimum 1, Maximum 5 numbers allowed.");
		GridBagConstraints gbc_addressnumErrorlbl = new GridBagConstraints();
		gbc_addressnumErrorlbl.anchor = GridBagConstraints.WEST;
		gbc_addressnumErrorlbl.insets = new Insets(0, 0, 5, 0);
		gbc_addressnumErrorlbl.gridx = 3;
		gbc_addressnumErrorlbl.gridy = 6;
		insertionPanel.add(addressNumErrorlbl, gbc_addressnumErrorlbl);
		
		JLabel lblNewLabel_3 = new JLabel("Phone Number:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 7;
		insertionPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		phoneField = new JFormattedTextField();
		phoneField.setColumns(10);
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.anchor = GridBagConstraints.WEST;
		gbc_textField_6.insets = new Insets(0, 0, 5, 5);
		gbc_textField_6.gridx = 2;
		gbc_textField_6.gridy = 7;
		insertionPanel.add(phoneField, gbc_textField_6);
		
		 phoneErrorlbl = new JLabel("Minimum 3, Maximum 20 numbers allowed.");
		GridBagConstraints gbc_phoneErrorlbl = new GridBagConstraints();
		gbc_phoneErrorlbl.anchor = GridBagConstraints.WEST;
		gbc_phoneErrorlbl.insets = new Insets(0, 0, 5, 0);
		gbc_phoneErrorlbl.gridx = 3;
		gbc_phoneErrorlbl.gridy = 7;
		insertionPanel.add(phoneErrorlbl, gbc_phoneErrorlbl);
		
		JLabel lblNewLabel_4 = new JLabel("Email Address:");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 8;
		insertionPanel.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		emailField = new JFormattedTextField();
		emailField.setColumns(10);
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		gbc_textField_7.anchor = GridBagConstraints.WEST;
		gbc_textField_7.insets = new Insets(0, 0, 5, 5);
		gbc_textField_7.gridx = 2;
		gbc_textField_7.gridy = 8;
		insertionPanel.add(emailField, gbc_textField_7);
		
		 emailErrorlbl = new JLabel("Example: mail@service.com");
		GridBagConstraints gbc_emailErrorlbl = new GridBagConstraints();
		gbc_emailErrorlbl.anchor = GridBagConstraints.WEST;
		gbc_emailErrorlbl.insets = new Insets(0, 0, 5, 0);
		gbc_emailErrorlbl.gridx = 3;
		gbc_emailErrorlbl.gridy = 8;
		insertionPanel.add(emailErrorlbl, gbc_emailErrorlbl);
		
		JLabel lblNewLabel_5 = new JLabel("Date of Birth:");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 1;
		gbc_lblNewLabel_5.gridy = 9;
		insertionPanel.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
//		CONFIGURING DATE
		LocalDate minDate = LocalDate.now().minusYears(10);
		LocalDate maxDate = LocalDate.now().minusYears(80);
		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		dobField = new DatePicker(dateSettings);
		dateSettings.setDateRangeLimits(maxDate,minDate);
		dobField.setDate(minDate);
		
		GridBagConstraints gbc_textField_8 = new GridBagConstraints();
		gbc_textField_8.insets = new Insets(0, 0, 5, 5);
		gbc_textField_8.anchor = GridBagConstraints.WEST;
		gbc_textField_8.gridx = 2;
		gbc_textField_8.gridy = 9;
		insertionPanel.add(dobField, gbc_textField_8);
		
		 dobErrorlbl = new JLabel("Earlier dates up to 10 years, older up to 80.");
		GridBagConstraints gbc_dobErrorlbl = new GridBagConstraints();
		gbc_dobErrorlbl.anchor = GridBagConstraints.WEST;
		gbc_dobErrorlbl.insets = new Insets(0, 0, 5, 0);
		gbc_dobErrorlbl.gridx = 3;
		gbc_dobErrorlbl.gridy = 9;
		insertionPanel.add(dobErrorlbl, gbc_dobErrorlbl);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea_1 = new GridBagConstraints();
		gbc_rigidArea_1.insets = new Insets(0, 0, 0, 5);
		gbc_rigidArea_1.gridx = 2;
		gbc_rigidArea_1.gridy = 10;
		insertionPanel.add(rigidArea_1, gbc_rigidArea_1);
	}
	


	
	
	/*Declaring setters and getters*/
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

	public JTextField getUsernameField() {
		return usernameField;
	}


	public void setUsernameField(JFormattedTextField usernameField) {
		this.usernameField = usernameField;
	}


	public JTextField getPasswordField() {
		return passwordField;
	}


	public void setPasswordField(JFormattedTextField passwordField) {
		this.passwordField = passwordField;
	}


	public JTextField getSurnameField() {
		return surnameField;
	}


	public void setSurnameField(JFormattedTextField surnameField) {
		this.surnameField = surnameField;
	}


	public JTextField getFirstnameField() {
		return firstnameField;
	}


	public void setFirstnameField(JFormattedTextField firstnameField) {
		this.firstnameField = firstnameField;
	}


	public JTextField getAddressField() {
		return addressField;
	}


	public void setAddressField(JFormattedTextField addressField) {
		this.addressField = addressField;
	}


	public JTextField getAddressNumField() {
		return addressNumField;
	}


	public void setAddressNumField(JFormattedTextField addressNumField) {
		this.addressNumField = addressNumField;
	}


	public JTextField getPhoneField() {
		return phoneField;
	}


	public void setPhoneField(JFormattedTextField phoneField) {
		this.phoneField = phoneField;
	}


	public JTextField getEmailField() {
		return emailField;
	}


	public void setEmailField(JFormattedTextField emailField) {
		this.emailField = emailField;
	}


	public DatePicker getDobField() {
		return dobField;
	}


	public void setDobField(DatePicker dobField) {
		this.dobField = dobField;
	}


	public JLabel getUsernameErrorlbl() {
		return usernameErrorlbl;
	}


	public void setUsernameErrorlbl(String text) {
		this.usernameErrorlbl.setText(text);
	}


	public JLabel getPasswordErrorlbl() {
		return passwordErrorlbl;
	}


	public void setPasswordErrorlbl(String text) {
		this.passwordErrorlbl.setText(text);
	}


	public JLabel getSurnameErrorlbl() {
		return surnameErrorlbl;
	}


	public void setSurnameErrorlbl(String text) {
		this.surnameErrorlbl.setText(text);
	}


	public JLabel getFirstnameErrorlbl() {
		return firstnameErrorlbl;
	}


	public void setFirstnameErrorlbl(String text) {
		this.firstnameErrorlbl.setText(text);
	}


	public JLabel getAddressErrorlbl() {
		return addressErrorlbl;
	}


	public void setAddressErrorlbl(String text) {
		this.addressErrorlbl.setText(text);
	}


	public JLabel getAddressNumErrorlbl() {
		return addressNumErrorlbl;
	}


	public void setAddressNumErrorlbl(String text) {
		this.addressNumErrorlbl.setText(text);
	}


	public JLabel getPhoneErrorlbl() {
		return phoneErrorlbl;
	}


	public void setPhoneErrorlbl(String text) {
		this.phoneErrorlbl.setText(text);
	}


	public JLabel getEmailErrorlbl() {
		return emailErrorlbl;
	}


	public void setEmailErrorlbl(String text) {
		this.emailErrorlbl.setText(text);
	}


	public JLabel getDobErrorlbl() {
		return dobErrorlbl;
	}


	public void setDobErrorlbl(String text) {
		this.dobErrorlbl.setText(text);
	}
	
	


}
