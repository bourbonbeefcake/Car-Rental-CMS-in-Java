package program.classes;

import java.io.Serializable;

import javax.swing.ImageIcon;

import program.database.Databs;

public class User implements Serializable{
	
	private static final long serialVersionUID = 1205424532848514795L;
	
	//dropBoxImageName holds the name of the picture that this user has uploaded to DropBox. 
	protected String surname, firstname, address, phoneNumber, emailAddress, dateOfBirth,username,password, userImageName, dropBoxImageName;
	protected int addressNumber;
	protected int user_id;
	
	

	/**
	 * Parent class for Staff and Customer. 
	 * User class holds all the attributes that both of its child classes contain.
	 */
	public User(String surname, String firstname, String address, String phoneNumber, String emailAddress,
			String dateOfBirth, String username, String password, int addressNumber,int user_id) {
		
		this.surname = surname;
		this.firstname = firstname;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.dateOfBirth = dateOfBirth;
		this.username = username;
		this.password = password;
		this.addressNumber = addressNumber;
		this.user_id = user_id;
	}


		
	





	public String getDropBoxImageName() {
		return dropBoxImageName;
	}


	public void setDropBoxImageName(String dropBoxImageName) {
		this.dropBoxImageName = dropBoxImageName;
	}


	public String getUserImageName() {
		return userImageName;
	}


	public void setUserImageName(String userImageName) {
		this.userImageName = userImageName;
	}


	public int getUserID() {
		return user_id;
	}


	public void setUserID(int user_id) {
		this.user_id = user_id;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getEmailAddress() {
		return emailAddress;
	}


	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}


	public String getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getAddressNumber() {
		return addressNumber;
	}


	public void setAddressNumber(int addressNumber) {
		this.addressNumber = addressNumber;
	}
	
	
	
	
}
