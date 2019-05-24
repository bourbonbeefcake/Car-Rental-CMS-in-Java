package program.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;

/**
 * Parent class of UserWindow_CustomerView and UserWindow_StaffView, contains components and functionalities that are common to both of the child classes. 
 * @author Triantafyllidis Antonios 
 *
 */
public class UserWindow extends JFrame{

	protected JButton logoutButton;
	protected JLabel lblUsername;
	protected JButton productsButton;
	protected JButton profileButton;
	protected JPanel MainArea;
	protected JPanel insideMainArea;
	protected JPanel navBarPanel;
	protected JLabel usrImage;
	
	
	/**
	 * Create the GUI.
	 * This class does not get an instance of the controller. The classes that extend it do. 
	 * This class is not instantiated but only extended. 
	 * 
	 */
	public UserWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginView.class.getResource("/images/if_aiga_taxi_134116.png")));
		setTitle(" Rental Service");
		setBounds(100, 100, 848, 502);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(1100, 800));
		getContentPane().setLayout(new MigLayout("", "[832px,grow,center]", "[463px,grow,center]"));
		
		navBarPanel = new JPanel();
		navBarPanel.setBorder(new LineBorder(Color.GRAY));
		getContentPane().add(navBarPanel, "flowx,cell 0 0,alignx left,growy");
		GridBagLayout gbl_navBarPanel = new GridBagLayout();
		gbl_navBarPanel.columnWidths = new int[]{128, 0};
		gbl_navBarPanel.rowHeights = new int[]{128, 0, 14, 0, 0, 0, 0, 0, 0};
		gbl_navBarPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_navBarPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		navBarPanel.setLayout(gbl_navBarPanel);
		
		 usrImage = new JLabel();
		GridBagConstraints gbc_usrImage = new GridBagConstraints();
		gbc_usrImage.anchor = GridBagConstraints.WEST;
		gbc_usrImage.insets = new Insets(0, 0, 5, 0);
		gbc_usrImage.gridx = 0;
		gbc_usrImage.gridy = 0;
		navBarPanel.add(usrImage, gbc_usrImage);
		
		
		logoutButton = new JButton("Logout");
		logoutButton.setFocusPainted(false);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea_1 = new GridBagConstraints();
		gbc_rigidArea_1.insets = new Insets(0, 0, 5, 0);
		gbc_rigidArea_1.gridx = 0;
		gbc_rigidArea_1.gridy = 1;
		navBarPanel.add(rigidArea_1, gbc_rigidArea_1);
		
		lblUsername = new JLabel();
		lblUsername.setHorizontalAlignment(JLabel.CENTER);
		lblUsername.setVerticalAlignment(JLabel.CENTER);
		
		lblUsername.setBorder(new CompoundBorder(new LineBorder(new Color(128, 128, 128)), new EmptyBorder(2, 2, 2, 2)));
		lblUsername.setRequestFocusEnabled(false);
		lblUsername.setFocusable(false);
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 0);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 2;
		navBarPanel.add(lblUsername, gbc_lblUsername);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea = new GridBagConstraints();
		gbc_rigidArea.insets = new Insets(0, 0, 5, 0);
		gbc_rigidArea.gridx = 0;
		gbc_rigidArea.gridy = 3;
		navBarPanel.add(rigidArea, gbc_rigidArea);
		GridBagConstraints gbc_logoutButton = new GridBagConstraints();
		gbc_logoutButton.insets = new Insets(0, 0, 5, 0);
		gbc_logoutButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_logoutButton.gridx = 0;
		gbc_logoutButton.gridy = 4;
		navBarPanel.add(logoutButton, gbc_logoutButton);
		
		MainArea = new JPanel();
		MainArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		getContentPane().add(MainArea, "cell 0 0,grow");
		GridBagLayout gbl_contentArea = new GridBagLayout();
		gbl_contentArea.columnWidths = new int[]{600, 0};
		gbl_contentArea.rowHeights = new int[]{430, 0};
		gbl_contentArea.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentArea.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		MainArea.setLayout(gbl_contentArea);
	}

	
	public JButton getLogoutButton() {
		return logoutButton;
	}

	public void setLogoutButton(JButton logoutButton) {
		this.logoutButton = logoutButton;
	}

	public void addLogoutBtnListener(ActionListener listener) {
		logoutButton.addActionListener(listener);
	}

	

	
	
	public JPanel getMainArea() {
		return MainArea;
	}

	public void setContentArea(JPanel contentArea) {
		this.MainArea = contentArea;
	}

	public JPanel getInsideMainArea() {
		return insideMainArea;
	}

	public void setContentAreaContained(JPanel insideMainArea) {
		this.insideMainArea = insideMainArea;
	}

	public JPanel getNavBarPanel() {
		return navBarPanel;
	}

	public void setNavBarPanel(JPanel navBarPanel) {
		this.navBarPanel = navBarPanel;
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

	public JLabel getUsrImage() {
		return usrImage;
	}

	public void setUsrImage(JLabel usrImage) {
		this.usrImage = usrImage;
	}

	public JLabel getLblUsername() {
		return lblUsername;
	}

	public void setLblUsername(JLabel lblUsername) {
		this.lblUsername = lblUsername;
	}
	
	public void setLblUsernameText(String string) {
		this.lblUsername.setText(string);
	}
	
	
}
