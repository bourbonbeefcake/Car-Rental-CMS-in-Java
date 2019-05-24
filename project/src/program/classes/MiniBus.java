package program.classes;

import java.io.Serializable;

public class MiniBus extends Vehicle implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3111349219523695177L;
	protected int seatingCapacity; 

	/**
	 * Constructor for the child Class of Vehicle, MiniBus.
	 */
	public MiniBus(String model, String make, String registrationNumber, double topSpeed, double dailyHireRate, int seatingCapacity,Boolean rented) {
		super(model, make, registrationNumber, topSpeed, dailyHireRate,rented);
		
		this.seatingCapacity = seatingCapacity;
	}

	public int getSeatingCapacity() {
		return seatingCapacity;
	}

	public void setSeatingCapacity(int seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}
	
	

}
