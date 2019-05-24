package program.classes;

import java.io.Serializable;

public class Lorry extends Vehicle implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3499630700596375118L;
	protected double loadingCapacity;
	
	
	/**
	 * Constructor for the child Class of Vehicle, Lorry.
	 */
	public Lorry(String model, String make, String registrationNumber, double topSpeed, double dailyHireRate, double loadingCapacity,Boolean rented) {
		super(model, make, registrationNumber, topSpeed, dailyHireRate,rented);

		this.loadingCapacity = dailyHireRate;
	}


	public double getLoadingCapacity() {
		return loadingCapacity;
	}


	public void setLoadingCapacity(double loadingCapacity) {
		this.loadingCapacity = loadingCapacity;
	}


	
	
}
