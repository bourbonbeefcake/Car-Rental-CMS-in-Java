package program.gui.addingViews;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import program.classes.Customer;
import program.classes.Staff;
import program.controller.Controller;

/**
 * Class that validates every text field in the AddUserView. It also provides listeners for the option buttons of the same view. 
 * @author Triantafyllidis Antonios 
 *
 */
public class AddUserValidator implements DocumentListener, ActionListener{
	
	private AddUserView addUserView;
	private Controller controller;

	private JTextField usernameBox;
	private JTextField passwordBox;
	private JTextField surnameBox;
	private JTextField firstnameBox;
	private JTextField addressBox;
	private JTextField addressNumBox;
	private JTextField phoneBox;
	private JTextField emailBox;
	
	/**
	 * The constructor only takes an instance of the Controller
	 * @param controller
	 */
	public AddUserValidator(Controller controller) {
		
		this.controller = controller;
	}
	
	
	/**
	 * Adds a AddUserView to this validator.
	 * @param addUserView - The view to validate
	 */
	public void addView(AddUserView addUserView) {
		this.addUserView = addUserView;
		
		//Each field that is to be validated is returned from the view and saved in the validator
		usernameBox = addUserView.getUsernameField();
		passwordBox = addUserView.getPasswordField();
		surnameBox = addUserView.getSurnameField();
		firstnameBox = addUserView.getFirstnameField();
		addressBox = addUserView.getAddressField();
		addressNumBox = addUserView.getAddressNumField();
		phoneBox = addUserView.getPhoneField();
		emailBox = addUserView.getEmailField();
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
		if (ev.getDocument().equals(usernameBox.getDocument())) {
			validateUsername();
		}else if (ev.getDocument().equals(passwordBox.getDocument())) {
			validatePassword();
		}else if (ev.getDocument().equals(surnameBox.getDocument())) {
			validateSurname();
		}else if (ev.getDocument().equals(firstnameBox.getDocument())) {
			validateFirstname();
		}else if (ev.getDocument().equals(addressBox.getDocument())) {
			validateAddress();
		}else if (ev.getDocument().equals(addressNumBox.getDocument())) {
			validateAddressNum();
		}else if (ev.getDocument().equals(phoneBox.getDocument())) {
			validatePhone();
		}else if (ev.getDocument().equals(emailBox.getDocument())) {
			validateEmail();
		}
		
	}
		
		
		/**
		 * Validate the Username field. <br>
		 * <strong>Constraints: </strong> <br>
		 * The username must be unique <br>
		 * The username must contain only letters and numbers, minimum 2 characters, maximum 13.<br>
		 * Uses red color on the Error label for invalid insertion and green for correct. 
		 * @return True if the username successful passed the validation and false if not. 
		 */
		private boolean validateUsername() {
			JLabel errorLabel = addUserView.getUsernameErrorlbl();
			
			Pattern pattern = Pattern.compile("^[a-zA-Z]{1}[a-zA-Z0-9]{1,11}+$");
			String text = usernameBox.getText();
			Matcher m = pattern.matcher(text);
			if (!m.matches()) {
				if (controller.getModel().userNameExists(text)) {
					errorLabel.setForeground(Color.red);
					errorLabel.setText("<html>Username already exists!</html>");
					usernameBox.setBorder(new LineBorder(Color.red));
				}else {
					errorLabel.setForeground(Color.red);
					errorLabel.setText("Minimum 2, Maximum 13, only letters and numbers.");
					usernameBox.setBorder(new LineBorder(Color.red));
				}
				return false;
			}else {
				errorLabel.setForeground(Color.green);
				errorLabel.setText("Username OK!");
				usernameBox.setBorder(new LineBorder(Color.green));
				return true;
			}
		}
		
		
		
		
		/**
		 * Validate the Password field. <br>
		 * <strong>Constraints: </strong> <br>
		 * The password can contain any character , minimum 4 characters, maximum 16.<br>
		 * Uses red color on the Error label for invalid insertion and green for correct. 
		 * @return True if the password successful passed the validation and false if not. 
		 */
		private boolean validatePassword() {
			JLabel errorLabel = addUserView.getPasswordErrorlbl();
			
			Pattern pattern = Pattern.compile("^.{4,16}+$");
			String text = passwordBox.getText();
			Matcher m = pattern.matcher(text);
			if (!m.matches()) {
					errorLabel.setForeground(Color.red);
					errorLabel.setText("Minimum 4, Maximum 16 characters");
					passwordBox.setBorder(new LineBorder(Color.red));
					return false;
			}else {
				errorLabel.setForeground(Color.green);
				errorLabel.setText("Password OK!");
				passwordBox.setBorder(new LineBorder(Color.green));
				return true;
			}
		}
		
		
		
		
		/**
		 * Validate the Surname field. <br>
		 * <strong>Constraints: </strong> <br>
		 * The Surname must contain only letters, its first letter must be capital,  minimum 3 characters, maximum 20 characters. Symbol ' is allowed as well (incase surname type: 'O'Brian')<br>
		 * Uses red color on the Error label for invalid insertion and green for correct. 
		 * @return True if the surname successful passed the validation and false if not. 
		 */
		private boolean validateSurname() {
			JLabel errorLabel = addUserView.getSurnameErrorlbl();
			
			Pattern pattern = Pattern.compile("^[A-Z]{1}[a-zA-Z\']{2,18}+$");
			String text = surnameBox.getText();
			Matcher m = pattern.matcher(text);
			if (!m.matches()) {
					errorLabel.setForeground(Color.red);
					errorLabel.setText("Minimum 3,Maximum 20, letters, symbol ' allowed. First capital.");
					surnameBox.setBorder(new LineBorder(Color.red));		
					return false;
			}else {
				errorLabel.setForeground(Color.green);
				errorLabel.setText("Surname OK!");
				surnameBox.setBorder(new LineBorder(Color.green));
				return true;
			}
		}
		
		
		
		
		/**
		 * Validate the Firstname field. 
		 * <strong>Constraints: </strong> <br>
		 * The Firstname must contain only letters, its first letter must be capital,  minimum 3 characters, maximum 20 characters. Symbol ' is allowed as well (incase surname type: 'O'Brian')<br>
		 * Uses red color on the Error label for invalid insertion and green for correct. 
		 * @return True if the Firstname successful passed the validation and false if not. 
		 */
		private boolean validateFirstname() {
			JLabel errorLabel = addUserView.getFirstnameErrorlbl();
			
			Pattern pattern = Pattern.compile("^[A-Z]{1}[a-zA-Z\']{2,18}+$");
			String text = firstnameBox.getText();
			Matcher m = pattern.matcher(text);
			if (!m.matches()) {
					errorLabel.setForeground(Color.red);
					errorLabel.setText("Minimum 3,Maximum 20, letters, symbol ' allowed. First capital.");
					firstnameBox.setBorder(new LineBorder(Color.red));		
					return false;
			}else {
				errorLabel.setForeground(Color.green);
				errorLabel.setText("Firstname OK!");
				firstnameBox.setBorder(new LineBorder(Color.green));
				return true;
			}
		}
		
		
		
		
		/**
		 * Validate the Address field. <br>
		 * <strong>Constraints: </strong> <br>
		 * The Address must contain only letters,  minimum 2 characters, maximum 20 characters. Spaces allowed as well.<br>
		 * Uses red color on the Error label for invalid insertion and green for correct. 
		 * @return True if the Address successful passed the validation and false if not. 
		 */
		private boolean validateAddress() {

			JLabel errorLabel = addUserView.getAddressErrorlbl();
			
			Pattern pattern = Pattern.compile("^[a-zA-Z\\s]{2,20}+$");
			String text = addressBox.getText();
			Matcher m = pattern.matcher(text);
			if (!m.matches()) {
					errorLabel.setForeground(Color.red);
					errorLabel.setText("Minimum 2, Maximum 20 letters and/or spaces allowed.");
					addressBox.setBorder(new LineBorder(Color.red));	
					return false;
			}else {
				errorLabel.setForeground(Color.green);
				errorLabel.setText("Address OK!");
				addressBox.setBorder(new LineBorder(Color.green));
				return true;
			}
		}
		
		
		
		
		/**
		 * Validate the Address Number field. <br>
		 * <strong>Constraints: </strong> <br>
		 * The Address Number must contain only numbers,  minimum 1 digits, maximum 5 digits.<br>
		 * Uses red color on the Error label for invalid insertion and green for correct. 
		 * @return True if the Address Number successful passed the validation and false if not. 
		 */
		private boolean validateAddressNum() {

			JLabel errorLabel = addUserView.getAddressNumErrorlbl();
			
			Pattern pattern = Pattern.compile("^[0-9]{1,5}+$");
			String text = addressNumBox.getText();
			Matcher m = pattern.matcher(text);
			if (!m.matches()) {
					errorLabel.setForeground(Color.red);
					errorLabel.setText("Minimum 1, Maximum 5 numbers allowed.");
					addressNumBox.setBorder(new LineBorder(Color.red));		
					return false;
			}else {
				errorLabel.setForeground(Color.green);
				errorLabel.setText("Address Number OK!");
				addressNumBox.setBorder(new LineBorder(Color.green));
				return true;
			}
		}
		
		
		
		
		/**
		 * Validate the Phone field. <br>
		 * <strong>Constraints: </strong> <br>
		 * The Phone must contain only numbers,  minimum 3 digits, maximum 20 digits.<br>
		 * Uses red color on the Error label for invalid insertion and green for correct. 
		 * @return True if the Phone successful passed the validation and false if not. 
		 */
		private boolean validatePhone() {

			JLabel errorLabel = addUserView.getPhoneErrorlbl();
			
			Pattern pattern = Pattern.compile("^[0-9]{3,20}+$");
			String text = phoneBox.getText();
			Matcher m = pattern.matcher(text);
			if (!m.matches()) {
					errorLabel.setForeground(Color.red);
					errorLabel.setText("Minimum 3, Maximum 20 numbers allowed.");
					phoneBox.setBorder(new LineBorder(Color.red));
					return false;
			}else {
				errorLabel.setForeground(Color.green);
				errorLabel.setText("Phone OK!");
				phoneBox.setBorder(new LineBorder(Color.green));
				return true;
			}
		}
		
		
		
		
		/**
		 * Validate the Email field. <br>
		 * <strong>Constraints: </strong> <br>
		 * The Email must comply with the email format described in RFC3696.<br>
		 * Uses red color on the Error label for invalid insertion and green for correct. 
		 * @return True if the Email successful passed the validation and false if not. 
		 */
		private boolean validateEmail() {

			JLabel errorLabel = addUserView.getEmailErrorlbl();
//			regex source: https://www.journaldev.com/638/java-email-validation-regex  (In the comments)
			Pattern pattern = Pattern.compile("^[\\w!#$%&’*+/=\\-?^_`{|}~]+(\\.[\\w!#$%&’*+/=\\-?^_`{|}~]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$");
											


			String text = emailBox.getText();
			Matcher m = pattern.matcher(text);
			if (!m.matches()) {
					errorLabel.setForeground(Color.red);
					errorLabel.setText("Example: mail@service.com");
					emailBox.setBorder(new LineBorder(Color.red));	
					return false;
			}else {
				errorLabel.setForeground(Color.green);
				errorLabel.setText("Email OK!");
				emailBox.setBorder(new LineBorder(Color.green));
				return true;
			}
		}

		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(addUserView.getFinishInsertButton())) {
				
				//Only if ALL fields are validated, create a new instance of a user, either customer or staff depending on the selection of the dropdown box. 
				//New instances are added to the model through the controller. 
				//A relevant message is been displayed about the success and on closing it, the adding view is disposed. 
				if (validateUsername() && validatePassword() && validateSurname() && validateFirstname() && validateAddress() && validateAddressNum() && validatePhone() && validateEmail()) {
					if (addUserView.getComboBox().getSelectedItem().toString().equals("Staff")) {
						controller.getModel().addNewUser(new Staff(surnameBox.getText(), firstnameBox.getText(), addressBox.getText(), phoneBox.getText(), emailBox.getText(), addUserView.getDobField().getDateStringOrEmptyString(), usernameBox.getText(), passwordBox.getText(), Integer.parseInt(addressNumBox.getText()), controller.getModel().generateNewStaffID()));
						JOptionPane.showMessageDialog(addUserView, "Staff member is added!");
						controller.serializeModel();
						controller.removeView(addUserView);
						addUserView.dispose();
					}else if(addUserView.getComboBox().getSelectedItem().toString().equals("Customer")) {
						controller.getModel().addNewUser(new Customer(surnameBox.getText(), firstnameBox.getText(), addressBox.getText(), phoneBox.getText(), emailBox.getText(), addUserView.getDobField().getDateStringOrEmptyString(), usernameBox.getText(), passwordBox.getText(), Integer.parseInt(addressNumBox.getText()), controller.getModel().generateNewCustomerID()));
						JOptionPane.showMessageDialog(addUserView, "Customer member is added!");
						controller.serializeModel();
						controller.removeView(addUserView);
						addUserView.dispose();
					}
				}
				//If user presses the cancel button the view is disposed and no changes are made. 
			}else if(e.getSource().equals(addUserView.getCancelInsertButton())){
				controller.removeView(addUserView);
				addUserView.dispose();
			}
			
		}

	}


