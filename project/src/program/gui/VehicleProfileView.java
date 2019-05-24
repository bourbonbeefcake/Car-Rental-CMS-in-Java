package program.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import program.classes.Car;
import program.classes.Lorry;
import program.classes.MiniBus;
import program.classes.Staff;
import program.classes.Vehicle;
import program.controller.Controller;
import program.database.Databs;
import program.gui.tableViews.UsersTableView;

/**
 * View that displays the information related to a vehicle.
 * @author Triantafyllidis Antonios 
 *
 */
public class VehicleProfileView extends JFrame{
	
	private int viewID;
	private Controller controller;
	private Vehicle requestedVehicle;
	
	
	private JPanel contentPane;
	private JLabel lblVehicleProfile;
	private JLabel isHiredLabel;
	private JButton returnButton;
	private JButton hireOutButton;
	
	private JLabel lblImg;
	private JButton addImageBtn;
	
	private UsersTableView tableView;
	
	/**
	 * Initializes the profile of the requested vehicle.
	 * If a change was made, and the vehicle profile is being updated, then the 'isBeingReplaced' field is true.
	 * Being true, means that it will not again be added to the lists of the controller and it will keep its own View ID. 
	 * Only if it is not replaced, the view calls {@link Controller#addView(Object)} function, adding this view to the Controller.
	 * 
	 * @param controller - The instance of the controller
	 * @param vehicle - The requested vehicle to show its profile
	 * @param isBeingReplaced - Whether this view is just opened or if it is replacing an already open view.
	 */
	public VehicleProfileView(Controller controller, Vehicle vehicle,boolean isBeingReplaced) {
		this.controller = controller;
		this.requestedVehicle = vehicle;
		initialize();
		
		if (!isBeingReplaced) {
			controller.addView(this);
		}
		
	}
	
	private void initialize(){

		setBounds(100, 100, 652, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{244, 137, 0};
		gbl_panel.rowHeights = new int[]{23, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		 lblVehicleProfile = new JLabel("Vehicle Profile");
		lblVehicleProfile.setFont(new Font("Tahoma", Font.BOLD, 19));
		GridBagConstraints gbc_lblVehicleProfile = new GridBagConstraints();
		gbc_lblVehicleProfile.insets = new Insets(0, 0, 0, 5);
		gbc_lblVehicleProfile.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblVehicleProfile.gridx = 0;
		gbc_lblVehicleProfile.gridy = 0;
		panel.add(lblVehicleProfile, gbc_lblVehicleProfile);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		contentPane.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		lblImg = new JLabel();
		//If there is no Dropbox image file name in the Vehicle Object, use the default vehicle image. 
		if (requestedVehicle.getDropBoxImageName() == null) {
			lblImg.setIcon((new ImageIcon(UserWindow.class.getResource("/images/vehicle_default_img.png"))));
		}
		GridBagConstraints gbc_lblImg = new GridBagConstraints();
		gbc_lblImg.insets = new Insets(0, 0, 5, 5);
		gbc_lblImg.gridx = 1;
		gbc_lblImg.gridy = 1;
		panel_1.add(lblImg, gbc_lblImg);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea_1 = new GridBagConstraints();
		gbc_rigidArea_1.insets = new Insets(0, 0, 5, 5);
		gbc_rigidArea_1.gridx = 3;
		gbc_rigidArea_1.gridy = 3;
		panel_1.add(rigidArea_1, gbc_rigidArea_1);
		
		//Only if the logged in user is Staff show the options for Hire.
		if (controller.getModel().getLoggedInUser() instanceof Staff) {
			
			JLabel lblHired = new JLabel("Hired:");
			GridBagConstraints gbc_lblHired = new GridBagConstraints();
			gbc_lblHired.insets = new Insets(0, 0, 5, 5);
			gbc_lblHired.gridx = 4;
			gbc_lblHired.gridy = 3;
			panel_1.add(lblHired, gbc_lblHired);
			
			 isHiredLabel = new JLabel(); 
			 //If there this Vehicle is rented, the User must be displayed. Otherwise, 'N/A' will be displayed. 
			 //The function getRender will throw an exception because there is no renter. Then, catching that exception will result on the displaying of the text 'N/A'
			 try {
				 isHiredLabel.setText(requestedVehicle.getRenter().getUsername() + " (" + requestedVehicle.getRenter().getUserID() + ")"); 
			} catch (Exception e) {
				isHiredLabel.setText("N/A");
			}
			GridBagConstraints gbc_lblValue_5 = new GridBagConstraints();
			gbc_lblValue_5.insets = new Insets(0, 0, 5, 5);
			gbc_lblValue_5.gridx = 5;
			gbc_lblValue_5.gridy = 3;
			panel_1.add(isHiredLabel, gbc_lblValue_5);
		}
		
		
		
		JLabel lblNewLabel = new JLabel("Registration Number:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 3;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblValue = new JLabel(requestedVehicle.getRegistrationNumber());
		GridBagConstraints gbc_lblValue = new GridBagConstraints();
		gbc_lblValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblValue.gridx = 2;
		gbc_lblValue.gridy = 3;
		panel_1.add(lblValue, gbc_lblValue);
		

		
		JLabel lblNewLabel_2 = new JLabel("Model:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 4;
		panel_1.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel label = new JLabel(requestedVehicle.getModel());
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 2;
		gbc_label.gridy = 4;
		panel_1.add(label, gbc_label);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("Make:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 5;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel label_1 = new JLabel(requestedVehicle.getMake());
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 2;
		gbc_label_1.gridy = 5;
		panel_1.add(label_1, gbc_label_1);
		
		JLabel lblNewLabel_3 = new JLabel("Top Speed:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 6;
		panel_1.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		JLabel label_2 = new JLabel(String.valueOf(requestedVehicle.getTopSpeed()));
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 2;
		gbc_label_2.gridy = 6;
		panel_1.add(label_2, gbc_label_2);
		
		JLabel lblNewLabel_4 = new JLabel("Daily Hire Rate");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 7;
		panel_1.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		JLabel label_3 = new JLabel(String.valueOf(requestedVehicle.getDailyHireRate()));
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 2;
		gbc_label_3.gridy = 7;
		panel_1.add(label_3, gbc_label_3);
		
		if (requestedVehicle instanceof Lorry) {
			
			
			JLabel lblLoadingCapacity = new JLabel("Loading Capacity:");
			GridBagConstraints gbc_lblLoadingCapacity = new GridBagConstraints();
			gbc_lblLoadingCapacity.insets = new Insets(0, 0, 5, 5);
			gbc_lblLoadingCapacity.gridx = 1;
			gbc_lblLoadingCapacity.gridy = 9;
			panel_1.add(lblLoadingCapacity, gbc_lblLoadingCapacity);
			
			JLabel lblValue_1 = new JLabel(String.valueOf(((Lorry) requestedVehicle).getLoadingCapacity()));
			GridBagConstraints gbc_lblValue_1 = new GridBagConstraints();
			gbc_lblValue_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblValue_1.gridx = 2;
			gbc_lblValue_1.gridy = 9;
			panel_1.add(lblValue_1, gbc_lblValue_1);
		}

		if (requestedVehicle instanceof MiniBus) {
			
			JLabel lblSeatingCapacity = new JLabel("Seating Capacity:");
			GridBagConstraints gbc_lblSeatingCapacity = new GridBagConstraints();
			gbc_lblSeatingCapacity.insets = new Insets(0, 0, 5, 5);
			gbc_lblSeatingCapacity.gridx = 1;
			gbc_lblSeatingCapacity.gridy = 10;
			panel_1.add(lblSeatingCapacity, gbc_lblSeatingCapacity);
			
			JLabel lblValue_2 = new JLabel(String.valueOf(((MiniBus) requestedVehicle).getSeatingCapacity()));
			GridBagConstraints gbc_lblValue_2 = new GridBagConstraints();
			gbc_lblValue_2.insets = new Insets(0, 0, 5, 5);
			gbc_lblValue_2.gridx = 2;
			gbc_lblValue_2.gridy = 10;
			panel_1.add(lblValue_2, gbc_lblValue_2);
			
		}
		
		
		
		if (requestedVehicle instanceof Car) {
			
			JLabel lblFuelType = new JLabel("Fuel Type:");
			GridBagConstraints gbc_lblFuelType = new GridBagConstraints();
			gbc_lblFuelType.insets = new Insets(0, 0, 5, 5);
			gbc_lblFuelType.gridx = 1;
			gbc_lblFuelType.gridy = 11;
			panel_1.add(lblFuelType, gbc_lblFuelType);
			
			JLabel lblValue_3 = new JLabel(((Car) requestedVehicle).getFuelType());
			GridBagConstraints gbc_lblValue_3 = new GridBagConstraints();
			gbc_lblValue_3.insets = new Insets(0, 0, 5, 5);
			gbc_lblValue_3.gridx = 2;
			gbc_lblValue_3.gridy = 11;
			panel_1.add(lblValue_3, gbc_lblValue_3);
			
			JLabel lblNumberOfDoors = new JLabel("Number of doors:");
			GridBagConstraints gbc_lblNumberOfDoors = new GridBagConstraints();
			gbc_lblNumberOfDoors.insets = new Insets(0, 0, 5, 5);
			gbc_lblNumberOfDoors.gridx = 1;
			gbc_lblNumberOfDoors.gridy = 12;
			panel_1.add(lblNumberOfDoors, gbc_lblNumberOfDoors);
			
			JLabel lblValue_4 = new JLabel(String.valueOf(((Car) requestedVehicle).getNumberOfDoors()));
			GridBagConstraints gbc_lblValue_4 = new GridBagConstraints();
			gbc_lblValue_4.insets = new Insets(0, 0, 5, 5);
			gbc_lblValue_4.gridx = 2;
			gbc_lblValue_4.gridy = 12;
			panel_1.add(lblValue_4, gbc_lblValue_4);
		}
		
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea = new GridBagConstraints();
		gbc_rigidArea.insets = new Insets(0, 0, 0, 5);
		gbc_rigidArea.gridx = 1;
		gbc_rigidArea.gridy = 13;
		panel_1.add(rigidArea, gbc_rigidArea);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 2;
		contentPane.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0};
		gbl_panel_2.rowHeights = new int[]{0};
		gbl_panel_2.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		
		//Show the Return button only if this vehicle is rented.
		if (requestedVehicle.isRented()) {
				
				 returnButton = new JButton("Return");
					returnButton.setFocusPainted(false);
					GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
					gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
					gbc_btnNewButton.gridx = 4;
					gbc_btnNewButton.gridy = 4;
					panel_1.add(returnButton, gbc_btnNewButton);
			
		} 
		//If the vehicle is not rented and the logged-in user is Staff, show the hireout button and the table of customers that the vehicle can be hired out.
		if (!requestedVehicle.isRented() && controller.getModel().getLoggedInUser() instanceof Staff) {
			
			tableView = controller.createCustomersTableView();
			tableView.setContainedInVehicleProfile(this);
			tableView.setAddButtonVisible(false);
			tableView.setRemoveButtonVisible(false);
			tableView.setTitleLabelText("Rent to: ");
			
			
			GridBagConstraints gbc_p = new GridBagConstraints();
			gbc_p.fill = GridBagConstraints.BOTH;
			panel_2.add(tableView, gbc_p);
			
			 hireOutButton = new JButton("Hire Out");
			 hireOutButton.setFocusPainted(false);
			 hireOutButton.setEnabled(false);
			GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
			gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
			gbc_btnNewButton.gridx = 4;
			gbc_btnNewButton.gridy = 4;
			panel_1.add(hireOutButton, gbc_btnNewButton);
		} 
		
		if (controller.getModel().getLoggedInUser() instanceof Staff) {
			 addImageBtn = new JButton("Add Profile Image");
			 addImageBtn.setFocusPainted(false);
			 GridBagConstraints gbc_addImgButton = new GridBagConstraints();
			 gbc_addImgButton.insets = new Insets(0, 0, 5, 0);
			 gbc_addImgButton.gridx = 5;
			 gbc_addImgButton.gridy = 5;
			panel.add(addImageBtn, gbc_addImgButton);
			
		}
	}
	
	
	
	public UsersTableView getTableView() {
		return tableView;
	}



	public JButton getAddImageBtn() {
		return addImageBtn;
	}

	public void setAddImageBtn(JButton addImageBtn) {
		this.addImageBtn = addImageBtn;
	}

	public JLabel getLblImg() {
		return lblImg;
	}

	public void setLblImg(JLabel lblImg) {
		this.lblImg = lblImg;
	}

	public void setTableView(UsersTableView tableView) {
		this.tableView = tableView;
	}
	
	



	public int getViewID() {
		return viewID;
	}





	public void setViewID(int viewID) {
		this.viewID = viewID;
	}





	public Vehicle getRequestedVehicle() {
		return requestedVehicle;
	}

	public void setRequestedVehicle(Vehicle requestedVehicle) {
		this.requestedVehicle = requestedVehicle;
	}

	public JButton getReturnButton() {
		return returnButton;
	}



	public void setReturnButton(JButton returnButton) {
		this.returnButton = returnButton;
	}



	public JButton getHireOutButton() {
		return hireOutButton;
	}



	public void setHireOutButton(JButton hireOutButton) {
		this.hireOutButton = hireOutButton;
	}



	public void setLabelText(String text) {
		lblVehicleProfile.setText(text);
	}

}


