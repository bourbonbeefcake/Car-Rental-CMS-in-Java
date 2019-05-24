package program.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import program.database.Databs;

public class Customer extends User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4671487605692590912L;
	
	
	/**
	 * {@link Customer#hiredCars}, {@link Customer#hiredMinBuses} and {@link Customer#hiredLorries} are the three lists that contain
	 * vehicles that the customer has hired currently. 
	 */
	protected List<Vehicle> hiredCars,hiredMinBuses,hiredLorries;
	
	
	/**
	 * Constructor for the parent class of User, Customer. 
	 * Customers can only view available vehicles, return those that they have hired, add an image to their profile.
	 * When initializing a new Customer instance, the <bold>user_id</bold> never gets a value manually. 
	 * It must always get a value through the model, calling the {@link Databs#generateNewCustomerID()}
	 * 
	 */
	public Customer(String surname, String firstname, String address, String phoneNumber, String emailAddress,
			String dateOfBirth, String username, String password, int addressNumber,int user_id) {
		super(surname, firstname, address, phoneNumber, emailAddress, dateOfBirth, username, password, addressNumber, user_id);
		
		hiredCars = new ArrayList<>();
		hiredMinBuses = new ArrayList<>();
		hiredLorries = new ArrayList<>();

	}
	
	
	/**
	 * @return - The number of hired cars of this Customer.
	 */
	public int countRentedCars(){
		if (hiredCars == null) {
			return 0;
		}else {
			return hiredCars.size();
		}
	}
	
	
	/**
	 * @return - The number of hired Mini-Buses of this Customer.
	 */
	public int countRentedMiniBuses(){
		if (hiredMinBuses == null) {
			return 0;
		}else {
			return hiredMinBuses.size();
		}
	}
	
	
	/**
	 * @return - The number of hired Lorries of this Customer.
	 */
	public int countRentedLorries(){
		if (hiredLorries == null) {
			return 0;
		}else {
			return hiredLorries.size();
		}
	}

	/**
	 * @return - The List containing the hired Cars of this Customer.
	 */
	public List<Vehicle> getHiredCars() {
		return hiredCars;
	}

	/**
	 * Adds a car to the List of hired cars of this customer. 
	 * Also sets the renter of the given car as this customer and sets the car as rented.
	 * 
	 * @param car - The Car for hire
	 */
	public void addHiredCars(Car car) {
		hiredCars.add(car);
		car.setRented(true);
		car.setRenter(this);
	}

	/**
	 * @return The List containing the hired MiniBuses of this Customer.
	 */
	public List<Vehicle> getHiredMinBuses() {
		return hiredMinBuses;
	}

	
	/**
	 * Adds a MiniBus to the List of hired MiniBuses of this customer. 
	 * Also sets the renter of the given MiniBus as this customer and sets the MiniBus as rented.
	 * 
	 * @param minibus - The MiniBus for hire
	 */
	public void addHiredMinBuses(MiniBus minibus) {
		hiredMinBuses.add(minibus);
		minibus.setRented(true);
		minibus.setRenter(this);
	}
	/**
	 * @return The List containing the hired Lorries of this Customer.
	 */
	public List<Vehicle> getHiredLorries() {
		return hiredLorries;
	}

	
	/**
	 * Adds a Lorry to the List of hired lorries of this customer. 
	 * Also sets the renter of the given lorry as this customer and sets the lorry as rented.
	 * 
	 * @param lorry - The Lorry for hire
	 */
	public void addHiredLorries(Lorry lorry) {
		hiredLorries.add(lorry);
		lorry.setRented(true);
		lorry.setRenter(this);
	}

	
	/**
	 * Returns a Car from this user back to the fleet.
	 * Unsets the renter of the Car, and sets the Car as not rented.
	 * 
	 * @param car - The Car to return
	 */
	public void removeCar(Car car) {
		if (hiredCars.contains(car)) {
			hiredCars.remove(car);
			car.setRented(false);
			car.setRenter(null);
		}
	}
	
	/**
	 * Returns a MiniBus from this user back to the fleet.
	 * Unsets the renter of the MiniBus, and sets the MiniBus as not rented.
	 * 
	 * @param minibus - The MiniBus to return
	 */
	public void removeMinibus(MiniBus minibus) {
		if (hiredMinBuses.contains(minibus)) {
			hiredMinBuses.remove(minibus);
			minibus.setRented(false);
			minibus.setRenter(null);
		}
	}
	
	/**
	 * Returns a Lorry from this user back to the fleet.
	 * Unsets the renter of the Lorry, and sets the Lorry as not rented.
	 * 
	 * @param lorry - The Lorry to return
	 */
	public void removeLorry(Lorry lorry) {
		if (hiredLorries.contains(lorry)) {
			hiredLorries.remove(lorry);
			lorry.setRented(false);
			lorry.setRenter(null);
		}
	}
	
	
	
	/**
	 * Receives a Vehicle and determines the child class of Vehicle (Car, MiniBus or Lorry).
	 * It afterwards calls the appropriate function for that class to hire the Vehicle out.
	 * 
	 * @param vehicle - The Vehicle to hire out.
	 */
	public void hireAVehicle(Vehicle vehicle) {
		if (vehicle instanceof Car) {
			addHiredCars((Car)vehicle);
		} else if (vehicle instanceof MiniBus) {
			addHiredMinBuses((MiniBus)vehicle);
		} else if (vehicle instanceof Lorry) {
			addHiredLorries((Lorry)vehicle);
		}
	}
	
	
	/**
	 * Receives a Vehicle and determines the child class of Vehicle (Car, MiniBus or Lorry).
	 * It afterwards calls the appropriate function for that class to return the Vehicle back to the fleet.
	 * 
	 * @param vehicle - The Vehicle to return.
	 */
	public void returnAVehicle(Vehicle vehicle) {
		if (vehicle instanceof Car) {
			removeCar((Car)vehicle);
		} else if (vehicle instanceof MiniBus) {
			removeMinibus((MiniBus)vehicle);
		} else if (vehicle instanceof Lorry) {
			removeLorry((Lorry)vehicle);
		}
		
	}
	
	
	
	
	/**
	 * Returns all Vehicles that the lists of this user contain.
	 * @return - All the hired vehicles of this user. 
	 */
	public HashMap<Integer, Vehicle> getAllHiredVehicles () {
		HashMap<Integer, Vehicle> map = new HashMap<>();
		
		if (hiredCars != null) {
			for (Vehicle vehicle : hiredCars) {
				map.put(map.size(), vehicle);
			}
		}
		
		if (hiredMinBuses != null) {
			for (Vehicle vehicle : hiredMinBuses) {
				map.put(map.size(), vehicle);
			}
		}
		
		if (hiredLorries != null) {
			for (Vehicle vehicle : hiredLorries) {
				map.put(map.size(), vehicle);
			}
		}
		
		return map;
	}

}
