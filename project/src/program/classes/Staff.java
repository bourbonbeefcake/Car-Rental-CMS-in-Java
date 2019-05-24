package program.classes;

import java.io.Serializable;

import program.database.Databs;

public class Staff extends User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8707676147761097481L;


	/**
	 * Constructor for the child class of User, Staff. 
	 * All accounts that can perform changes to the database, are of Staff instance.
	 * When initializing a new Staff instance, the <bold>user_id</bold> never gets a value manually. 
	 * It must always get a value through the model, calling the {@link Databs#generateNewStaffID()}
	 * 
	 */
	public Staff(String surname, String firstname, String address, String phoneNumber, String emailAddress,
			String dateOfBirth, String username, String password, int addressNumber, int user_id) {
		super(surname, firstname, address, phoneNumber, emailAddress, dateOfBirth, username, password, addressNumber,user_id);
	}


}
