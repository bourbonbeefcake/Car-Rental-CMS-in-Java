package program.gui;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import program.controller.Controller;

/**
 * Extends UserWindow in order to add components that are only needed to the Customer.
 * @author Triantafyllidis Antonios 
 *
 */
public class UserWindow_CustomerView extends UserWindow{

	private Controller controller;
	private int viewID;


	protected JLabel lblUsername;
	protected JButton productsButton;
	protected JButton profileButton;
	protected JPanel contentArea;
	protected JPanel contentAreaContained;
	
	protected LegendView colorsView;
	
	
	
	/**
	 * Creates GUI specifically needed for the Customer view.
	 * It calls {@link Controller#addView(Object)} function, adding this view to the Controller.
	 * @param controller - The controller instance. 
	 */
	public UserWindow_CustomerView(Controller controller) {
		initialize();
		this.controller = controller;
		controller.addView(this);
	}
	
	public void initialize() {
		profileButton = new JButton("Profile");
		profileButton.setFocusPainted(false);
		GridBagConstraints gbc_profileButton = new GridBagConstraints();
		gbc_profileButton.insets = new Insets(0, 0, 5, 0);
		gbc_profileButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_profileButton.gridx = 0;
		gbc_profileButton.gridy = 5;
		navBarPanel.add(profileButton, gbc_profileButton);
		
		productsButton = new JButton("Vehicles");
		productsButton.setFocusPainted(false);
		GridBagConstraints gbc_productsButton = new GridBagConstraints();
		gbc_productsButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_productsButton.gridx = 0;
		gbc_productsButton.gridy = 6;
		navBarPanel.add(productsButton, gbc_productsButton);
		
		
	}
	
	
	
	/**
	 * Creates a Legend for the Vehicles Table and places it under the buttons on the left side of the view. 
	 */
	public void addLegendVehicles(){
		if (colorsView != null) {
			navBarPanel.remove(colorsView);
		}
		colorsView = new LegendView("Vehicles");
		GridBagConstraints gbc_colorView = new GridBagConstraints();
		gbc_colorView.fill = GridBagConstraints.HORIZONTAL;
		gbc_colorView.gridx = 0;
		gbc_colorView.gridy = 7;
		navBarPanel.add(colorsView, gbc_colorView);
	}
	
	/**
	 * Removes any legend that exists in the View
	 */
	public void removeLegend(){
		if (colorsView != null) {
			navBarPanel.remove(colorsView);
			repaint();
			revalidate();
		}
	}
	
	
	
	
	public int getViewID() {
		return viewID;
	}

	public void setViewID(int viewID) {
		this.viewID = viewID;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
	



	public JLabel getLblUsername() {
		return lblUsername;
	}

	public void setLblUsername(JLabel lblUsername) {
		this.lblUsername = lblUsername;
	}

	public JPanel getContentArea() {
		return contentArea;
	}

	public void setContentArea(JPanel contentArea) {
		this.contentArea = contentArea;
	}

	public JPanel getContentAreaContained() {
		return contentAreaContained;
	}

	public void setContentAreaContained(JPanel contentAreaContained) {
		this.contentAreaContained = contentAreaContained;
	}

	public JButton getProductsButton() {
		return productsButton;
	}

	public void setProductsButton(JButton productsButton) {
		this.productsButton = productsButton;
	}

	public JButton getProfileButton() {
		return profileButton;
	}

	public void setProfileButton(JButton profileButton) {
		this.profileButton = profileButton;
	}


}
