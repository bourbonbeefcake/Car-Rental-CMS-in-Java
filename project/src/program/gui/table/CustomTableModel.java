package program.gui.table;

import java.util.HashMap;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import program.classes.Car;
import program.classes.Customer;
import program.classes.Lorry;
import program.classes.MiniBus;
import program.classes.Staff;

/**
 * This class extends and customizes DefaultTableModel class. 
 * It contains configuration for the manipulation of data and columns in the table.
 * @author Triantafyllidis Antonios 
 *
 */
public class CustomTableModel extends DefaultTableModel{
	

	private  HashMap<Integer, ?> hashmap;


	/**
	 * Initializes the table. 
	 * @param hashmap - The hashmap that its data will be displayed on the table
	 */
	public CustomTableModel(HashMap<Integer, ?> hashmap) {
		super();
		this.hashmap = hashmap;
		
		//Populates the table with the data from the hashmap, and gives names to the columns
		this.setDataVector(makeObjectArrayFromMap(hashmap), makeColumns(hashmap));
	}
	
	
	/**
	 * Takes a Hashmap to populate the table with the data contained in the hashmap.
	 * This method, expects a Hashmap containing either VEHICLES or USERS. 
	 * Expanding this method into manipulating more objects is possible. 
	 *  
	 * @param hashmap - The hashmap whose data will populate the table. 
	 * @return - Return a multidimensional Array Object which contains the data ordered in their respective rows
	 */
	private Object[][] makeObjectArrayFromMap(HashMap<Integer, ?> hashmap) {
		
		//Get the table data into a multidimensional Array. 
		Object[][] tableData = new Object[hashmap.keySet().size()][100];
		
		//Everytime a row is populated with all the data from the object, index increments by 1
		int index = 0;
		
		Car car;
		MiniBus van;
		Lorry lorry;
		Customer customer;
		Staff staff;
		//Loop through the hashmap
		for (Integer key : hashmap.keySet())
		{
			//If the current element in the hashmap is an instance of Class Vehicle
		if (hashmap.get(key).getClass().getSuperclass().getSimpleName().equals("Vehicle")) {
			
			//If the instance is an instance of Class Car
			if(hashmap.get(key).getClass().getSimpleName().equals("Car")) {
					
					//Get the Car instance
				     car = (Car)hashmap.get(key);
				     //Populate the current row with its data
						tableData[index][0] = car.getRegistrationNumber();
						tableData[index][1] = car.getMake();
					    tableData[index][2] = car.getModel();
					    tableData[index][3] = null;
					    tableData[index][4] = null;
					    tableData[index][5] = car.getFuelType();
					    tableData[index][6] = car.getNumberOfDoors();
					    tableData[index][7] = car.getDailyHireRate();
					    tableData[index][8] = car.getTopSpeed();
					    try {
					    	//If there is a renter place him on the cell
					    	tableData[index][9] = car.getRenter().getUserID();
						} catch (Exception e) {
							//Otherwise, place null
							tableData[index][9] = null;
						}
					    
					    tableData[index][10] = car.isRented();
					    //IMPORTANT! The last column must ALWAYS have the type of the vehicle in order row coloring to work!
					    tableData[index][11] = "Car";
						index++;
						
				//In the other case that the current element in the hashmap is an instance of Class MiniBus
			}else if(hashmap.get(key).getClass().getSimpleName().equals("MiniBus")) {
				
				//Get the MiniBus instance
				     van = (MiniBus)hashmap.get(key);
				   //Populate the current row with its data
						tableData[index][0] = van.getRegistrationNumber();
						tableData[index][1] = van.getMake();
					    tableData[index][2] = van.getModel();
					    tableData[index][3] = van.getSeatingCapacity();
					    tableData[index][4] = null;
					    tableData[index][5] = null;
					    tableData[index][6] = null;
					    tableData[index][7] = van.getDailyHireRate();
					    tableData[index][8] = van.getTopSpeed();
					    try {
					    	//If there is a renter place him on the cell
					    	tableData[index][9] = van.getRenter().getUserID();
						} catch (Exception e) {
							//Otherwise, place null
							tableData[index][9] = null;
						}
					    tableData[index][10] = van.isRented();
					  //IMPORTANT! The last column must ALWAYS have the type of the vehicle in order row coloring to work!
					    tableData[index][11] = "MiniBus";
						index++;
						
				
						//In the other case that the current element in the hashmap is an instance of Class Lorry
			}else if(hashmap.get(key).getClass().getSimpleName().equals("Lorry")) {
				
				//Get the Lorry instance
				     lorry = (Lorry)hashmap.get(key);
				   //Populate the current row with its data
						tableData[index][0] = lorry.getRegistrationNumber();
						tableData[index][1] = lorry.getMake();
					    tableData[index][2] = lorry.getModel();
					    tableData[index][3] = null;
					    tableData[index][4] = lorry.getLoadingCapacity();
					    tableData[index][5] = null;
					    tableData[index][6] = null;
					    tableData[index][7] = lorry.getDailyHireRate();
					    tableData[index][8] = lorry.getTopSpeed();
					    try {
					    	//If there is a renter place him on the cell
					    	tableData[index][9] = lorry.getRenter().getUserID();
						} catch (Exception e) {
							//Otherwise, place null
							tableData[index][9] = null;
						}
					    tableData[index][10] = lorry.isRented();
					  //IMPORTANT! The last column must ALWAYS have the type of the vehicle in order row coloring to work!
					    tableData[index][11] = "Lorry";
						index++;
				
				
			}
			//Same logic with the staff
		}else if(hashmap.get(key).getClass().getSuperclass().getSimpleName().equals("User")) {
			if (hashmap.get(key).getClass().getSimpleName().equals("Staff")) {
				
				     staff = (Staff)hashmap.get(key);
				   //IMPORTANT! The first column must ALWAYS have the user ID in order row coloring to work!
						tableData[index][0] = staff.getUserID();
						tableData[index][1] = staff.getSurname();
					    tableData[index][2] = staff.getFirstname();
					    tableData[index][3] = staff.getUsername();
					    tableData[index][4] = staff.getEmailAddress();
					    tableData[index][5] = staff.getDateOfBirth();
					    tableData[index][6] = staff.getAddress();
					    tableData[index][7] = staff.getAddressNumber();
					    tableData[index][8] = staff.getPhoneNumber();
					    tableData[index][9] = null;
					    tableData[index][10] = null;
					    tableData[index][11] = null;
					    //passwords are not shown for security
						index++;
				
				
			}else if(hashmap.get(key).getClass().getSimpleName().equals("Customer")) {
	
				     customer = (Customer)hashmap.get(key);
				   //IMPORTANT! The first column must ALWAYS have the user ID in order row coloring to work!
						tableData[index][0] = customer.getUserID();
						tableData[index][1] = customer.getSurname();
					    tableData[index][2] = customer.getFirstname();
					    tableData[index][3] = customer.getUsername();
					    tableData[index][4] = customer.getEmailAddress();
					    tableData[index][5] = customer.getDateOfBirth();
					    tableData[index][6] = customer.getAddress();
					    tableData[index][7] = customer.getAddressNumber();
					    tableData[index][8] = customer.getPhoneNumber();
					    tableData[index][9] = customer.getHiredCars().size();
					    tableData[index][10] = customer.getHiredMinBuses().size();
					    tableData[index][11] = customer.getHiredLorries().size();
					    //passwords are not shown for security
						index++;
			}
		}
			
		}
		//return the table data
		return tableData;

	}
	
	
	/**
	 * Takes a Hashmap to check the its elements and what kind of classes they are instances of.
	 * Depending on the class (Vehicle or User) the object is a child instance of, the appropriate column names are returned. 
	 * Expanding this method into more options on column names is possible and also necessary if {@link CustomTableModel#makeObjectArrayFromMap(HashMap)} was expanded.
	 * @param hashmap - The Hashmap of either Vehicles or Users. 
	 * @return - An array containing Strings; the names of the columns
	 */
	private String[] makeColumns(HashMap<Integer, ?> hashmap) {
		String[] columns = null;
		if ( hashmap != null &&  !hashmap.isEmpty()) {
			
			//Getting the first key from the map that exists. 
			 Map.Entry<Integer,?> entry = hashmap.entrySet().iterator().next();
			 int key = entry.getKey();
			 
			if (hashmap.get(key).getClass().getSuperclass().getSimpleName().equals("Vehicle")) {
				String[] columnVehicles = {"Reg. Number","Make", "Model", "Seating Capacity", "Loading Capacity","Fuel Type","No. of Doors","Daily Hire Rate","Top Speed","Hired By","Rented","Type"};
				columns = columnVehicles;
				
			}else if(hashmap.get(key).getClass().getSuperclass().getSimpleName().equals("User")) {
				String[] columnUsers = {"ID", "Surname", "Firstname", "Username","Email Address","DOB","Address","Address No.","Phone Number","Hired Cars","Hired Min.Buses","Hired Lorries"};
				columns = columnUsers;
			}
		}
		
		return columns;
	}
	
	
	//Make sure that cells are not editable. 
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	

}