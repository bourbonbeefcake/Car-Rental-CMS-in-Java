package program.start;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import program.classes.Car;
import program.classes.Customer;
import program.classes.Staff;
import program.classes.User;
import program.classes.Vehicle;
import program.controller.Controller;
import program.database.Databs;
import program.database.DropBox;
import program.gui.LoginView;

/**
 * @author Triantafyllidis Antonios 
 *
 */
public class InitializeProgram {

	/**
	 * Launch the application.
	 */
	
	
	/**
	 * De-serializes the .dat file that contains the Databs object. (The model of the program.) <br>
	 * If there is no file, the program creates a new one, with a single Master User. <br>
	 * Name and password of the user is "MASTER"
	 * 
	 * @return - The Databs object (the model)
	 */
	private static Databs deserializeModel(){
		ObjectInputStream objectinputstream = null;
		 FileInputStream streamIn;
		 Databs databs = new Databs();
		 

		try {
			streamIn = new FileInputStream(new File(".").getAbsolutePath().toString() + File.separator +"RentalServiceDatabase.dat");
			
			objectinputstream = new ObjectInputStream(streamIn);
			 databs = (Databs) objectinputstream.readObject();
			streamIn.close();
			objectinputstream.close();
			
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(new JFrame("Warning"), "File RentalServiceDatabase.dat was not found in this directory. Please login with username and password 'MASTER'");
			//The default account is created when there is no database file in the same path with the JAR
			databs.addNewUser(new  Staff("Master", "Master", "Master", "Master", "masterAccount@master.com","99/99/99","MASTER","MASTER",0,databs.generateNewStaffID()));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(new JFrame("Error Occured"), "An error occured while an attempt was made to load RentalServiceDatabase.dat. Please try again.");
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(new JFrame("Error Occured"), "An error occured while an attempt was made to load RentalServiceDatabase.dat. Please try again.");
		}	
		return databs;
		    
	}
	
	
	
	public static void main(String[] args) {
		
		//First de-serialize the model or get a new one
		Databs model = deserializeModel();
		//Initialize the object that contains the Dropbox functionalities 
		DropBox dropBox = new DropBox();
		//Initialize the controller
		Controller controller = new Controller(model,dropBox);
		
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				//Initialize and show the Login View
				LoginView loginForm = new LoginView(controller);
			}
		});
	}
}



