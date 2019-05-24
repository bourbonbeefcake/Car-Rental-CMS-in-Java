package program.gui;



import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import program.classes.Customer;
import program.classes.Staff;
import program.classes.User;
import program.controller.Controller;
import program.gui.tableViews.VehiclesTableView;

/**
 * The User's Profile view
 * @author Triantafyllidis Antonios 
 *
 */
public class ProfileView extends JPanel {

	JLabel lblYourProfile;
	private JLabel imageLabel;
	private Controller controller;
	private int viewID;
	private User user;
	
	private JButton addImageBtn;
	
	
	
	
	

	/**
	 * Creates the GUI for the specified User's Profile.
	 * It calls {@link Controller#addView(Object)} function, adding this view to the Controller.
	 * @param controller - The Controller instance
	 * @param user - The user whose profile to show
	 */
	public ProfileView(Controller controller,User user) {
		this.controller = controller;
		this.user = user;
		initialize();
		controller.addView(this);
	}
	
	private void initialize(){
		setLayout(new BorderLayout(0, 10));
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		add(panel_1, BorderLayout.NORTH);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{113, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		lblYourProfile = new JLabel("Your Profile");
		lblYourProfile.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblYourProfile = new GridBagConstraints();
		gbc_lblYourProfile.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblYourProfile.insets = new Insets(0, 0, 0, 5);
		gbc_lblYourProfile.gridx = 0;
		gbc_lblYourProfile.gridy = 0;
		panel_1.add(lblYourProfile, gbc_lblYourProfile);
		lblYourProfile.setFont(new Font("Tahoma", Font.BOLD, 19));
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{49, 46, 54, 46, 77, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 14, 14, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		 imageLabel = new JLabel();
		imageLabel.setIcon(new ImageIcon(ProfileView.class.getResource("/images/user_default_img.png")));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		panel.add(imageLabel, gbc_label);

		JLabel lblNewLabel = new JLabel("Surname: ");
		lblNewLabel.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel(user.getSurname());
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 2;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		

		
		if (user instanceof Customer) {
			JLabel lblHiredCars = new JLabel("Hired Cars: ");
			lblHiredCars.setFont(new Font("Century Gothic", Font.PLAIN, 13));
			GridBagConstraints gbc_lblHiredCars = new GridBagConstraints();
			gbc_lblHiredCars.insets = new Insets(0, 0, 5, 5);
			gbc_lblHiredCars.gridx = 3;
			gbc_lblHiredCars.gridy = 1;
			panel.add(lblHiredCars, gbc_lblHiredCars);
			
			Customer customer = (Customer) user;
			JLabel lblHiredCarsNum = new JLabel(String.valueOf(customer.countRentedCars()));
			GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
			gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 0);
			gbc_lblNewLabel_8.gridx = 4;
			gbc_lblNewLabel_8.gridy = 1;
			panel.add(lblHiredCarsNum, gbc_lblNewLabel_8);
		}
		
		
		JLabel lblNewLabel_1 = new JLabel("Firstname: ");
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 3;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel(user.getFirstname());
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 3;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		

		
		
		if (user instanceof Customer) {
			JLabel lblHiredMinibus = new JLabel("Hired Mini-busses: ");
			lblHiredMinibus.setFont(new Font("Century Gothic", Font.PLAIN, 13));
			GridBagConstraints gbc_lblHiredMinibus = new GridBagConstraints();
			gbc_lblHiredMinibus.insets = new Insets(0, 0, 5, 5);
			gbc_lblHiredMinibus.gridx = 3;
			gbc_lblHiredMinibus.gridy = 2;
			panel.add(lblHiredMinibus, gbc_lblHiredMinibus);
			
			Customer customer = (Customer) user;
			JLabel lblHiredMinibusNum = new JLabel(String.valueOf(customer.countRentedMiniBuses()));
			GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
			gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 0);
			gbc_lblNewLabel_9.gridx = 4;
			gbc_lblNewLabel_9.gridy = 2;
			panel.add(lblHiredMinibusNum, gbc_lblNewLabel_9);
		}
		
		
		JLabel lblAddress = new JLabel("Address: ");
		lblAddress.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		GridBagConstraints gbc_lblAddress = new GridBagConstraints();
		gbc_lblAddress.anchor = GridBagConstraints.NORTH;
		gbc_lblAddress.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddress.gridx = 0;
		gbc_lblAddress.gridy = 4;
		panel.add(lblAddress, gbc_lblAddress);
		
		JLabel lblNewLabel_4 = new JLabel(user.getAddress());
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 4;
		panel.add(lblNewLabel_4, gbc_lblNewLabel_4);
		

		
		if (user instanceof Customer) {
			JLabel lblHiredLorries = new JLabel("Hired Lorries: ");
			lblHiredLorries.setFont(new Font("Century Gothic", Font.PLAIN, 13));
			GridBagConstraints gbc_lblHiredLorries = new GridBagConstraints();
			gbc_lblHiredLorries.insets = new Insets(0, 0, 5, 5);
			gbc_lblHiredLorries.gridx = 3;
			gbc_lblHiredLorries.gridy = 3;
			panel.add(lblHiredLorries, gbc_lblHiredLorries);
			
			Customer customer = (Customer) user;
			JLabel lblHiredLorriesNum = new JLabel(String.valueOf(customer.countRentedLorries()));
			GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
			gbc_lblNewLabel_10.insets = new Insets(0, 0, 5, 0);
			gbc_lblNewLabel_10.gridx = 4;
			gbc_lblNewLabel_10.gridy = 3;
			panel.add(lblHiredLorriesNum, gbc_lblNewLabel_10);
		}
		
		
		
		JLabel lblPhoneNumber = new JLabel("Phone Number: ");
		lblPhoneNumber.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		GridBagConstraints gbc_lblPhoneNumber = new GridBagConstraints();
		gbc_lblPhoneNumber.anchor = GridBagConstraints.NORTH;
		gbc_lblPhoneNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhoneNumber.gridx = 0;
		gbc_lblPhoneNumber.gridy = 5;
		panel.add(lblPhoneNumber, gbc_lblPhoneNumber);
		
		JLabel lblNewLabel_5 = new JLabel(user.getPhoneNumber());
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 1;
		gbc_lblNewLabel_5.gridy = 5;
		panel.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		JLabel lblEmailAddress = new JLabel("Email Address: ");
		lblEmailAddress.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		GridBagConstraints gbc_lblEmailAddress = new GridBagConstraints();
		gbc_lblEmailAddress.anchor = GridBagConstraints.NORTH;
		gbc_lblEmailAddress.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmailAddress.gridx = 0;
		gbc_lblEmailAddress.gridy = 6;
		panel.add(lblEmailAddress, gbc_lblEmailAddress);
		
		JLabel lblNewLabel_6 = new JLabel(user.getEmailAddress());
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 1;
		gbc_lblNewLabel_6.gridy = 6;
		panel.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth:");
		lblDateOfBirth.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		GridBagConstraints gbc_lblDateOfBirth = new GridBagConstraints();
		gbc_lblDateOfBirth.insets = new Insets(0, 0, 0, 5);
		gbc_lblDateOfBirth.gridx = 0;
		gbc_lblDateOfBirth.gridy = 7;
		panel.add(lblDateOfBirth, gbc_lblDateOfBirth);
		
		JLabel lblNewLabel_7 = new JLabel(user.getDateOfBirth());
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_7.gridx = 1;
		gbc_lblNewLabel_7.gridy = 7;
		panel.add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		User loggedInUser = controller.getModel().getLoggedInUser();
		//IF THE REQUESTED USER IS A CUSTOMER
		if(user instanceof Customer) {
			
			JPanel panel_2 = new JPanel();
			add(panel_2, BorderLayout.SOUTH);
			panel_2.setLayout(new BorderLayout(0, 0));
			
			VehiclesTableView tableView = new VehiclesTableView(controller, controller.createRentedVehiclesTable((Customer)user));
			tableView.setAddButtonVisible(false);
			tableView.setRemoveButtonVisible(false);
			tableView.setTitleLabelText("Rented Vehicles");
			GridBagLayout gridBagLayout_1 = (GridBagLayout) tableView.getLayout();
			gridBagLayout_1.columnWeights = new double[]{1.0};

			GridBagConstraints gbc_tableView = new GridBagConstraints();
			gbc_tableView.fill = GridBagConstraints.HORIZONTAL;
			gbc_tableView.fill = GridBagConstraints.BOTH;
			gbc_tableView.gridx = 1;
			gbc_tableView.gridy = 6;
			panel_2.add(tableView);

		}

			//IF THE LOGGED IN USER IS STAFF OR IF A CUSTOMER OPENS THEIR PROFILE
			//SHOW THEIR PASSWORD
			if(loggedInUser instanceof Staff || loggedInUser.equals(user)) {
				JLabel lblpassword = new JLabel("Password:");
				lblpassword.setFont(new Font("Century Gothic", Font.PLAIN, 13));
				GridBagConstraints gbc_lblPassword = new GridBagConstraints();
				gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
				gbc_lblPassword.gridx = 3;
				gbc_lblPassword.gridy = 5;
				panel.add(lblpassword, gbc_lblPassword);
				
				JLabel lblNewLabel_8 = new JLabel(user.getPassword());
				lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 11));
				GridBagConstraints gbc_lblNewLabel_66 = new GridBagConstraints();
				gbc_lblNewLabel_66.insets = new Insets(0, 0, 5, 0);
				gbc_lblNewLabel_66.gridx = 4;
				gbc_lblNewLabel_66.gridy = 5;
				panel.add(lblNewLabel_8, gbc_lblNewLabel_66);
			}
			
			
			
			if (loggedInUser.equals(user)) {
				 addImageBtn = new JButton("Add Profile Image");
				 addImageBtn.setFocusPainted(false);
				 GridBagConstraints gbc_addImgButton = new GridBagConstraints();
				 gbc_addImgButton.insets = new Insets(0, 0, 5, 0);
				 gbc_addImgButton.gridx = 3;
				 gbc_addImgButton.gridy = 6;
				panel.add(addImageBtn, gbc_addImgButton);
				
			}
		}
		
		

	
	
	public JButton getAddImageBtn() {
		return addImageBtn;
	}

	public void setAddImageBtn(JButton addImageBtn) {
		this.addImageBtn = addImageBtn;
	}

	public JLabel getTitleLabel() {
		return lblYourProfile;
	}
	public JLabel getImageLabel() {
		return imageLabel;
	}

	public void setImageLabel(JLabel imageLabel) {
		this.imageLabel = imageLabel;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getViewID() {
		return viewID;
	}

	public void setViewID(int viewID) {
		this.viewID = viewID;
	}

	public void setTitleLabelText(String text) {
		this.lblYourProfile.setText(text);
	}
}
