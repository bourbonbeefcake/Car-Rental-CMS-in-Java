package program.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import net.miginfocom.swing.MigLayout;
import program.controller.Controller;

/**
 * The First view that the user sees.
 * @author (Triantafyllidis Antonios) 17419238 
 *
 */
public class LoginView extends JFrame{
	
	private Controller controller;
	private int viewID;
	
	private JTextField userNameTxtField;
	private JPasswordField passwordField;
	private JButton btnSubmit;
	private JLabel messageLabel;
	
	/**
	 * The Constructor builds the GUI, adds the controller to the view, and afterwards calls {@link Controller#addView(Object)} function, adding the view to the Controller.
	 * @param controller - The controller instance
	 */
	public LoginView(Controller controller){
		initialize();
		this.controller = controller;
		controller.addView(this);
	}
	
	/**
	 * Building the GUI for the Login View
	 */
	private void initialize() {
		getContentPane().setMaximumSize(new Dimension(21, 21));
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginView.class.getResource("/images/if_aiga_taxi_134116.png")));
		setTitle(" Rental Service");
		setBounds(100, 100, 1351, 793);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("", "[832px,grow,center]", "[463px,grow,center]"));
		setVisible(true);
		
		JPanel login_container = new JPanel();
		login_container.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		getContentPane().add(login_container, "cell 0 0,alignx center,aligny center");
		GridBagLayout gbl_login_container = new GridBagLayout();
		gbl_login_container.columnWidths = new int[]{400, 0}; 
		gbl_login_container.rowHeights = new int[]{60, 0, 0, 0, 0, 0, 0, 0, 0}; 
		gbl_login_container.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_login_container.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		login_container.setLayout(gbl_login_container);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 21));
		GridBagConstraints gbc_lblLogin = new GridBagConstraints();
		gbc_lblLogin.insets = new Insets(0, 0, 5, 0);
		gbc_lblLogin.gridx = 0;
		gbc_lblLogin.gridy = 0;
		login_container.add(lblLogin, gbc_lblLogin);
		
		messageLabel = new JLabel("<html>Welcome to  Rental Service! <br> Please login using your staff/customer username and password.");
		messageLabel.setFont(new Font("Century Gothic", Font.BOLD, 12));
		GridBagConstraints gbc_messageLabel = new GridBagConstraints();
		gbc_messageLabel.insets = new Insets(0, 0, 5, 0);
		gbc_messageLabel.gridx = 0;
		gbc_messageLabel.gridy = 1;
		login_container.add(messageLabel, gbc_messageLabel);
		
		JLabel lblUsername = new JLabel("Username:"); 
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.insets = new Insets(0, 0, 5, 0);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 3;
		login_container.add(lblUsername, gbc_lblUsername);
		
		userNameTxtField = new JTextField();
		userNameTxtField.setFont(new Font("Tahoma",Font.PLAIN,  16));
		GridBagConstraints gbc_userNameTxtField = new GridBagConstraints();
		gbc_userNameTxtField.insets = new Insets(0, 0, 5, 0);
		gbc_userNameTxtField.gridx = 0;
		gbc_userNameTxtField.gridy = 4;
		login_container.add(userNameTxtField, gbc_userNameTxtField);
		userNameTxtField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(0, 0, 5, 0);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 5;
		login_container.add(lblPassword, gbc_lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setFont(new Font("Tahoma",Font.PLAIN,  16));
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.gridx = 0;
		gbc_passwordField.gridy = 6;
		login_container.add(passwordField, gbc_passwordField);
		
		btnSubmit = new JButton("Submit");
		
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.gridx = 0;
		gbc_btnSubmit.gridy = 7;
		btnSubmit.setPreferredSize(new Dimension(btnSubmit.getWidth() + 100, btnSubmit.getHeight() + 50));
		login_container.add(btnSubmit, gbc_btnSubmit);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea = new GridBagConstraints();
		gbc_rigidArea.gridx = 0;
		gbc_rigidArea.gridy = 8;
		login_container.add(rigidArea, gbc_rigidArea);
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public int getViewID() {
		return viewID;
	}

	public void setViewID(int viewID) {
		this.viewID = viewID;
	}

	public JTextField getUserNameTxtField() {
		return userNameTxtField;
	}

	public void setUserNameTxtField(JTextField userNameTxtField) {
		this.userNameTxtField = userNameTxtField;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	public JButton getBtnSubmit() {
		return btnSubmit;
	}

	public void setBtnSubmit(JButton btnSubmit) {
		this.btnSubmit = btnSubmit;
	}

	public JLabel getMessageLabel() {
		return messageLabel;
	}

	public void setMessageLabel(JLabel messageLabel) {
		this.messageLabel = messageLabel;
	}
	
	
}
