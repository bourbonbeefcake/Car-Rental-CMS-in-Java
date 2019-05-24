package program.gui.tableViews;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import program.controller.Controller;
import program.gui.VehicleProfileView;
import program.gui.table.CustomTable;
import program.gui.table.CustomTableModel;

/**
 * View that contains a table of users among other components for the filtering or manipulation of the table data.
 * @author Triantafyllidis Antonios 
 *
 */
public class UsersTableView extends JPanel{
	
	private int viewID;
	
	private JTextField searchTerm;
	private CustomTable table;
	private JComboBox<String> comboBox;
	private CustomTableModel customTableModel;
	private CustomTable customersTable;
	private Controller controller;
	private JLabel titleLabel;
	private JScrollPane scrollPane;
	
	private VehicleProfileView containedInVehicleProfile;
	
	private JButton addUserButton;
	private JButton removeUserButton;
	
	/**
	 * Initializes the UserTableView
	 * @param controller - The instance of the Controller
	 * @param table - The table to contain
	 */
	public UsersTableView (Controller controller, CustomTable table) {
		this.controller = controller;
		this.customersTable = table;
		initialize(table);
		controller.addView(this);
	}
	
	private void initialize(CustomTable table){
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel filtersPanel = new JPanel();
		GridBagConstraints gbc_filtersPanel = new GridBagConstraints();
		gbc_filtersPanel.insets = new Insets(0, 0, 5, 0);
		gbc_filtersPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_filtersPanel.anchor = GridBagConstraints.NORTH;
		gbc_filtersPanel.gridx = 0;
		gbc_filtersPanel.gridy = 1;
		add(filtersPanel, gbc_filtersPanel);
		GridBagLayout gbl_filtersPanel = new GridBagLayout();
		gbl_filtersPanel.columnWidths = new int[]{177, 0, 0, 116, 58, 50, 0};
		gbl_filtersPanel.rowHeights = new int[]{23, 0};
		gbl_filtersPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_filtersPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		filtersPanel.setLayout(gbl_filtersPanel);
		
		titleLabel = new JLabel("Users");
		GridBagConstraints gbc_lblAvailableProducts = new GridBagConstraints();
		gbc_lblAvailableProducts.insets = new Insets(0, 0, 0, 5);
		gbc_lblAvailableProducts.gridx = 0;
		gbc_lblAvailableProducts.gridy = 0;
		filtersPanel.add(titleLabel, gbc_lblAvailableProducts);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
		
		addUserButton = new JButton("Add");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		addUserButton.setFocusPainted(false);
		gbc_btnAdd.insets = new Insets(0, 0, 0, 5);
		gbc_btnAdd.gridx = 1;
		gbc_btnAdd.gridy = 0;
		filtersPanel.add(addUserButton, gbc_btnAdd);
		
		removeUserButton = new JButton("Remove");
		GridBagConstraints gbc_btnRemove = new GridBagConstraints();
		removeUserButton.setFocusPainted(false);
		gbc_btnRemove.insets = new Insets(0, 0, 0, 5);
		gbc_btnRemove.gridx = 2;
		gbc_btnRemove.gridy = 0;
		filtersPanel.add(removeUserButton, gbc_btnRemove);
		
		JLabel lblSearch = new JLabel("Search:");
		GridBagConstraints gbc_lblSearch = new GridBagConstraints();
		gbc_lblSearch.anchor = GridBagConstraints.EAST;
		gbc_lblSearch.insets = new Insets(0, 0, 0, 5);
		gbc_lblSearch.gridx = 3;
		gbc_lblSearch.gridy = 0;
		filtersPanel.add(lblSearch, gbc_lblSearch);
		
		//Forbidding pasting in the search field for error prevention
		searchTerm = new JTextField() {
			public void paste() { }
		};
		lblSearch.setLabelFor(searchTerm);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.EAST;
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.gridx = 4;
		gbc_textField.gridy = 0;
		filtersPanel.add(searchTerm, gbc_textField);
		searchTerm.setColumns(10);

		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"All", "Staff", "Customers"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.anchor = GridBagConstraints.EAST;
		gbc_comboBox.gridx = 5;
		gbc_comboBox.gridy = 0;
		filtersPanel.add(comboBox, gbc_comboBox);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);
			
		customersTable = table;
		//Specify, that the scrollable child of the scroll pane is this table
		scrollPane.setViewportView(customersTable);
		

	}
	
	
	public int getViewID() {
		return viewID;
	}

	public void setViewID(int viewID) {
		this.viewID = viewID;
	}
	
	
	


	public VehicleProfileView getContainedInVehicleProfile() {
		return containedInVehicleProfile;
	}

	public void setContainedInVehicleProfile(VehicleProfileView containedInVehicleProfile) {
		this.containedInVehicleProfile = containedInVehicleProfile;
	}

	public JButton getAddUserButton() {
		return addUserButton;
	}

	public void setAddUserButton(JButton addUserButton) {
		this.addUserButton = addUserButton;
	}

	public JButton getRemoveUserButton() {
		return removeUserButton;
	}

	public void setRemoveUserButton(JButton removeUserButton) {
		this.removeUserButton = removeUserButton;
	}

	public void setRemoveButtonVisible(boolean visible) {
		if (visible) {
			removeUserButton.setVisible(true);
		}else {
			removeUserButton.setVisible(false);
		}
	}
	
	public void setAddButtonVisible(boolean visible) {
		if (visible) {
			addUserButton.setVisible(true);
		}else {
			addUserButton.setVisible(false);
		}
	}
	
	public JLabel getTitleLabel() {
		return titleLabel;
	}

	public void setTitleLabelText(String string) {
		this.titleLabel.setText(string);
	}
	
	
	public JTextField getSearchTerm() {
		return searchTerm;
	}


	public void setSearchTerm(JTextField textField) {
		this.searchTerm = textField;
	}

	public CustomTable getTable() {
		return customersTable;
	}

	public void setTable(CustomTable customersTable) {
		this.customersTable = customersTable;
	}

	public JComboBox<String> getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox<String> comboBox) {
		this.comboBox = comboBox;
	}
	
	
}
