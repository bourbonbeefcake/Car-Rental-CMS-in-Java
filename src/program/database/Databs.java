package program.database;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import program.classes.Staff;
import program.classes.User;
import program.classes.Vehicle;

public class Databs implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2372730628185340758L;
	
	/**
	 * The main HashMap that holds all Vehicle objects.
	 */
	private HashMap<Integer, Vehicle> vehicles = new HashMap<Integer, Vehicle>();
	/**
	 * The main HashMap that holds all User objects.
	 */
	private HashMap<Integer, User> users = new HashMap<Integer, User>();
	
	
	
	
	/**
	 * This variable holds the customer IDs. <br>
	 * All the customer IDs are <strong> even numbers.</strong> <br>
	 * When a customer is added, this integer is incremented. <br>
	 * It always is initialized to 0 and is incremented by 2.
	 * This variable must never be altered. Doing so might result to unwanted behavior during customer insertion.
	 */
	private int customerIDs = 0;
	
	
	
	/**
	 * This variable holds the staff IDs. <br>
	 * All the staff IDs are <strong> odd numbers.</strong> <br>
	 * When a staff is added, this integer is incremented. <br>
	 * It always is initialized to 1 and is incremented by 2.
	 * This variable must never be altered. Doing so might result to unwanted behavior during staff insertion.
	 */
	private int staffIDs = 1;
	
	
	/**
	 * Holds the instance of the User currently logged in. 
	 */
	private User loggedInUser = null;
	
	
	
	/**
	 * Returns the User currently logged in
	 * @return - Currently logged in user
	 */
	public User getLoggedInUser() {
		return loggedInUser;
	}

	/**
	 * Sets the logged in User
	 * @param loggedInUser - The User that is to be set as Logged in
	 */
	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}
	
	/**
	 * Adds a new Vehicle to the Database
	 * @param vehicle - The Vehicle to add
	 */
	public void addNewVehicle(Vehicle vehicle) {
		vehicles.put(vehicles.size() + 1, vehicle);
	}
	
	
	/**
	 * Adds a new User to the Database
	 * @param user - The User to add
	 */
	public void addNewUser(User user) {
		users.put(users.size() + 1, user);
		
		
	}
	
	
	/**
	 * Removes a Vehicle from the Database
	 * This function first returns the hired vehicles before deleting them,
	 * in order to update any User's profile if he happened to have the Vehicles for deletion, hired. 
	 * @param vehicle - The Vehicle to remove
	 */
	public void removeVehicle(Vehicle vehicle) {
		
		if (vehicles.containsValue(vehicle)) {
			
			if (vehicle.isRented()) {
				vehicle.setRented(false);
				vehicle.setRenter(null);
			} 
			vehicles.remove(getKeyByValue(vehicles,vehicle)) ;
		}
		
	}
	
	
	/**
	 * Removes a User from the Database
	 * @param user - The User to remove
	 */
	public void removeUser(User user) {
		
		if (users.containsValue(user)) {
			users.remove(getKeyByValue(users,user)) ;
		}
	}
	
	
	/**
	 * Counts the number of vehicles in the database.
	 * @return - The size of the vehicles Hashmap.
	 */
	public int countVehicles() {
		return vehicles.size();
	}
	
	/**
	 * Counts the number of users in the database.
	 * @return - The size of the users Hashmap.
	 */
	public int countUsers() {
		return users.size();
	}
	
	
	
	/**
	 * Whenever a Customer is added, this is the function that must be invoked to the user's constructor at the ID field. 
	 * It increments {@link Databs#customerIDs} by 2 everytime it is called.
	 * Customers have even numbers as IDs.
	 * @return - The next Customer ID
	 */
	public int generateNewCustomerID() {
		customerIDs += 2;
		return customerIDs;
	}
	
	
	/**
	 * Whenever a Staff is added, this is the function that must be invoked to the user's constructor at the ID field. 
	 * It increments {@link Databs#staffIDs} by 2 everytime it is called.
	 * Staff have odd numbers as IDs.
	 * @return - The next Staff ID
	 */
	public int generateNewStaffID() {
		staffIDs += 2;
		return staffIDs;
	}
	
	/**
	 * Loops through the users Hashmap and searches for the specified name.
	 * @param username - The username to search for
	 * @return - If the username exists or not
	 */
	public boolean userNameExists(String username) {
		boolean exists = false;
		for (Iterator<Map.Entry<Integer, User>> it = users.entrySet().iterator(); it.hasNext(); ) {
			Map.Entry<Integer, User> entry = it.next();
		      if(entry.getValue().getUsername().equals(username)) {
		        exists = true;
		      }
		}
		return exists;
	}
	
	
	/**
	 * Loops through the vehicles Hashmap and searches for the specified registration number.
	 * @param regNumber - The registration number to search for
	 * @return - If the registration number exists or not
	 */
	public boolean vehicleRegNumberExists(String regNumber) {
		boolean exists = false;
		for (Iterator<Map.Entry<Integer, Vehicle>> it = vehicles.entrySet().iterator(); it.hasNext(); ) {
			Map.Entry<Integer, Vehicle> entry = it.next();
		      if(entry.getValue().getRegistrationNumber().equals(regNumber)) {
		        exists = true;
		      }
		}
		return exists;
	}
	
	/**
	 * This method searches all the values when it is only needed to search only through registration numbers.
	 * Preferring to loop through the hashmap instead to use "contains" method, for safety. 
	 * @param regNumber - The registration number to search for
	 * @return - if the vehicle with the specified registration number exists or not
	 */
	public boolean vehicleExists(String regNumber) {
		boolean exists = false;
		//
		
		for (Iterator<Map.Entry<Integer, Vehicle>> it = vehicles.entrySet().iterator(); it.hasNext(); ) {
			Map.Entry<Integer, Vehicle> entry = it.next();
		      if(entry.getValue().getRegistrationNumber().equals(regNumber)) {
		        exists = true;
		      }
		}
		return exists;
	}
	
	/**
	 * Searches if the User with the specified ID exists.
	 *	Preferring to loop through the hashmap instead to use "contains" method, for safety.
	 * @param userID - The specified user ID to search 
	 * @return - If the user with the specified ID exists or not
	 */
	public boolean userExists(int userID) {
		boolean exists = false;

		for (Iterator<Map.Entry<Integer, User>> it = users.entrySet().iterator(); it.hasNext(); ) {
			Map.Entry<Integer, User> entry = it.next();
		      if(entry.getValue().getUserID()==userID) {
		        exists = true;
		      }
		}
		return exists;
	}
	
	
	
	/**
	 * 
	 * Gets the Vehicle with the specified registration Number
	 * @param regNumber - The registration number of the Vehicle to get
	 * @return - The Vehicle with the specified registration number or null
	 */
	public Vehicle getVehicleByRegNumber(String regNumber) {
		Vehicle vehicle = null;
		for (Iterator<Map.Entry<Integer, Vehicle>> it = vehicles.entrySet().iterator(); it.hasNext(); ) {
			Map.Entry<Integer, Vehicle> entry = it.next();
		      if(entry.getValue().getRegistrationNumber().equals(regNumber)) {
		        vehicle = entry.getValue();
		      }
		}
				return vehicle;
	}
	
	/**
	 * Gets the User with the specified ID
	 * @param userID - The userID of the User to get
	 * @return - The User with the specified userID or null
	 */
	public User getUserByID(int userID) {
		User user = null;
		//Preferring to loop through the hashmap instead to use "contains" method, for safety. This method searches all the values when it is only needed to loop through user IDs
		for (Iterator<Map.Entry<Integer, User>> it = users.entrySet().iterator(); it.hasNext(); ) {
			Map.Entry<Integer, User> entry = it.next();
		      if(entry.getValue().getUserID()== userID) {
		        user = entry.getValue();
		      }
		}
				return user;
	}

	
	
	/**
	 * Loops through the specified Map, searching for the key of the specified value.
	 * @param map - The Map to search in. 
	 * @param value - The value which the key is needed
	 * @return - The key of the specified value or null if it doesn't exist
	 * <br>
	 * https://stackoverflow.com/questions/1383797/java-hashmap-how-to-get-key-from-value
	 *
	 */
	public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
	    for (Entry<T, E> entry : map.entrySet()) {
	        if (Objects.equals(value, entry.getValue())) {
	            return entry.getKey();
	        }
	    }
	    return null;
	}
	
	public boolean userLogin(String username, String password) {
		boolean state = false;
		for(Entry<Integer, User> entry: users.entrySet()) {
			if (entry.getValue().getPassword().equals(password) && entry.getValue().getUsername().equals(username)) {
				
				loggedInUser = entry.getValue();
				state =  true;
			}
		}
		return state;
	}	
	
	
	
	
	/**
	 * Sets the {@link Databs#loggedInUser} as null.
	 */
	public void userLogout() {
		if (loggedInUser != null) {
			loggedInUser = null;
		}
	}

	public HashMap<Integer, Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(HashMap<Integer, Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public HashMap<Integer, User> getUsers() {
		return users;
	}

	public void setUsers(HashMap<Integer, User> users) {
		this.users = users;
	}
	
}
