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
import program.gui.table.CustomTable;
import program.gui.table.CustomTableModel;

/**
 * View that contains a table of vehicles among other components for the filtering or manipulation of the table data.
 * @author Triantafyllidis Antonios 
 *
 */
public class VehiclesTableView extends JPanel {
	
	private JTextField textField;
	private JComboBox<String> comboBox;
	private CustomTable vehiclesTable;
	private Controller controller;
	private JLabel lblAvailableProducts;
	private JScrollPane scrollPane;
	
	private JButton addVehicleButton;
	private JButton removeVehicleButton;
	
	private int viewID;





	/**
	 * Initialize the VehicleTableView
	 * @param controller - The controller instance
	 * @param table - The Vehicles table
	 */
	@SuppressWarnings("serial")
	public VehiclesTableView(Controller controller, CustomTable table) {
		this.controller = controller;
		this.vehiclesTable = table;
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
		
		lblAvailableProducts = new JLabel("");
		GridBagConstraints gbc_lblAvailableProducts = new GridBagConstraints();
		gbc_lblAvailableProducts.insets = new Insets(0, 0, 0, 5);
		gbc_lblAvailableProducts.gridx = 0;
		gbc_lblAvailableProducts.gridy = 0;
		filtersPanel.add(lblAvailableProducts, gbc_lblAvailableProducts);
		lblAvailableProducts.setFont(new Font("Tahoma", Font.BOLD, 19));
		
		addVehicleButton = new JButton("Add");
		addVehicleButton.setFocusPainted(false);
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
//		gbc_btnAdd.anchor = GridBagConstraints.EAST;
		gbc_btnAdd.insets = new Insets(0, 0, 0, 5);
		gbc_btnAdd.gridx = 1;
		gbc_btnAdd.gridy = 0;
		filtersPanel.add(addVehicleButton, gbc_btnAdd);
		
		removeVehicleButton = new JButton("Remove");
		GridBagConstraints gbc_btnRemove = new GridBagConstraints();
		removeVehicleButton.setFocusPainted(false);
		gbc_btnRemove.insets = new Insets(0, 0, 0, 5);
		gbc_btnRemove.gridx = 2;
		gbc_btnRemove.gridy = 0;
		filtersPanel.add(removeVehicleButton, gbc_btnRemove);
		
		JLabel lblSearch = new JLabel("Search:");
		GridBagConstraints gbc_lblSearch = new GridBagConstraints();
		gbc_lblSearch.anchor = GridBagConstraints.EAST;
		gbc_lblSearch.insets = new Insets(0, 0, 0, 5);
		gbc_lblSearch.gridx = 3;
		gbc_lblSearch.gridy = 0;
		filtersPanel.add(lblSearch, gbc_lblSearch);
		
		//Forbidding pasting in the search field for error prevention
		textField = new JTextField() {
			public void paste() { }
		};
		
		
		
		lblSearch.setLabelFor(textField);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.EAST;
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.gridx = 4;
		gbc_textField.gridy = 0;
		filtersPanel.add(textField, gbc_textField);
		textField.setColumns(10);


		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"All", "Car", "MiniBus", "Lorry"}));
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
				

		vehiclesTable = table;
		//Specify, that the scrollable child of the scroll pane is this table
		scrollPane.setViewportView(vehiclesTable);
		

		}


	
	

	
	public int getViewID() {
			return viewID;
		}



		public void setViewID(int viewID) {
			this.viewID = viewID;
		}



	public JButton getAddVehicleButton() {
			return addVehicleButton;
		}



		public void setAddVehicleButton(JButton addVehicleButton) {
			this.addVehicleButton = addVehicleButton;
		}



		public JButton getRemoveVehicleButton() {
			return removeVehicleButton;
		}



		public void setRemoveVehicleButton(JButton removeVehicleButton) {
			this.removeVehicleButton = removeVehicleButton;
		}



	public void refreshTable(CustomTable table) {
		scrollPane.setViewportView(table);
	}
	
	
	
	public void setRemoveButtonVisible(boolean visible) {
		if (visible) {
			removeVehicleButton.setVisible(true);
		}else {
			removeVehicleButton.setVisible(false);
		}
	}
	
	public void setAddButtonVisible(boolean visible) {
		if (visible) {
			addVehicleButton.setVisible(true);
		}else {
			addVehicleButton.setVisible(false);
		}
	}
	
	public JLabel getTitleLabel() {
		return lblAvailableProducts;
	}

	public void setTitleLabelText(String string) {
		this.lblAvailableProducts.setText(string);
	}
	
	
	public JTextField getTextField() {
		return textField;
	}


	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public JTable getTable() {
		return vehiclesTable;
	}

	public void setTable_1(CustomTable vehiclesTable) {
		this.vehiclesTable = vehiclesTable;
		
	}

	public JComboBox<String> getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox<String> comboBox) {
		this.comboBox = comboBox;
	}
	

	
}
