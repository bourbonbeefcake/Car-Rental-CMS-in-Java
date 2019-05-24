package program.classes;

import java.io.Serializable;

/**
 *
 * 
 *
 */

public class Car extends Vehicle implements Serializable{
	

	private static final long serialVersionUID = 4823562841639056626L;
	protected String fuelType;
	protected int numberOfDoors;
	
	
	/**
	 * Constructor for the child Class of Vehicle, Car.
	 */
	public Car(String model, String make, String registrationNumber, double topSpeed, double dailyHireRate,String fuelType, int numberOfDoors,Boolean rented) {
		super(model, make, registrationNumber, topSpeed, dailyHireRate,rented);
		this.fuelType = fuelType;
		this.numberOfDoors = numberOfDoors;
	}

	
	public String getFuelType() {
		return fuelType;
	}


	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}


	public int getNumberOfDoors() {
		return numberOfDoors;
	}


	public void setNumberOfDoors(int numberOfDoors) {
		this.numberOfDoors = numberOfDoors;
	}
	
	
	

	
	

}
