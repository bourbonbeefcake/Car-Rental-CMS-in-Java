package program.classes;

import java.io.Serializable;

public class Vehicle implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5206226086856859810L;
	protected String model, make, registrationNumber, vehicleImage, vehicleImageName, dropBoxImageName;
	protected double topSpeed, dailyHireRate;
	protected Boolean rented;
	protected Customer renter;
	
	/**
	 * Parent class that contains all the attributes that Car, MiniBus and Lorry classes require and inherit. 
	 */
	public Vehicle(String model, String make, String registrationNumber, double topSpeed, double dailyHireRate,Boolean rented) {
		this.model = model;
		this.make = make;
		this.registrationNumber = registrationNumber;
		this.topSpeed = topSpeed;
		this.dailyHireRate = dailyHireRate;
		this.rented = rented;
		this.vehicleImage = "";
	}
	
	
	
	
	

	public String getVehicleImageName() {
		return vehicleImageName;
	}






	public void setVehicleImageName(String vehicleImageName) {
		this.vehicleImageName = vehicleImageName;
	}






	public String getDropBoxImageName() {
		return dropBoxImageName;
	}






	public void setDropBoxImageName(String dropBoxImageName) {
		this.dropBoxImageName = dropBoxImageName;
	}






	public String getVehicleImage() {
		return vehicleImage;
	}



	public void setVehicleImage(String vehicleImage) {
		this.vehicleImage = vehicleImage;
	}



	public Customer getRenter() {
		return renter;
	}


	public void setRenter(Customer renter) {
		this.renter = renter;
	}



	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public double getTopSpeed() {
		return topSpeed;
	}

	public void setTopSpeed(double topSpeed) {
		this.topSpeed = topSpeed;
	}

	public double getDailyHireRate() {
		return dailyHireRate;
	}

	public void setDailyHireRate(double dailyHireRate) {
		this.dailyHireRate = dailyHireRate;
	}


	public Boolean isRented() {
		return rented;
	}


	public void setRented(Boolean rented) {
		this.rented = rented;
	}
	
	

	

}
