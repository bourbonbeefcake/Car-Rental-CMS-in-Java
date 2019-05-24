package program.controller;

import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import program.classes.Customer;
import program.classes.Staff;
import program.classes.User;
import program.classes.Vehicle;
import program.database.Databs;
import program.database.DropBox;
import program.gui.LoginView;
import program.gui.ProfileView;
import program.gui.ProfileView_Frame;
import program.gui.UserWindow;
import program.gui.UserWindow_CustomerView;
import program.gui.UserWindow_StaffView;
import program.gui.VehicleProfileView;
import program.gui.addingViews.AddUserValidator;
import program.gui.addingViews.AddUserView;
import program.gui.addingViews.AddVehicleValidator;
import program.gui.addingViews.AddVehicleView;
import program.gui.table.CustomTable;
import program.gui.table.CustomTableModel;
import program.gui.tableViews.UsersTableView;
import program.gui.tableViews.VehiclesTableView;


/**
 * The one and main controller of this program, responsible of manipulating all views,
 * provide controllers to their components and communication between them and the model.
 * @author Triantafyllidis Antonios 
 *
 */
/**
 * @author Triantafyllidis Antonios 
 *
 */
public class Controller{


	private Databs model;
	private DropBox drpBox;
	private int viewID = 0;


	private JPanel MainView;
	private JPanel viewInsideMainView;


	/**
	 * Initializes the Controller.
	 * This happens in the Main class.
	 * @param model - The model or Database of this program
	 * @param drpBox - The instance containing all DropBox account connection related information. 
	 */
	public Controller(Databs model, DropBox drpBox){
		this.model = model;
		this.drpBox = drpBox;
	}



	/**
	 * Serializes the model into a file named "RentalServiceDatabase.dat" into the same directory of this program.
	 */
	public void serializeModel(){
		try {

			FileOutputStream fos = new FileOutputStream("RentalServiceDatabase.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(model);
			oos.close();

		}  catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} 
	}
	
	
	

	/*
	 * Initializing all the ConcurrentHashMaps, each for every kind of view exists in this program.
	 * ConcurentHashMap is used in order to ensure that a table won't "lock" while a certain process is updating it. 
	 * It provides thread-safe operations that can happen concurrently.
	 * */
	private ConcurrentHashMap<Integer, LoginView> loginViewsMap = new ConcurrentHashMap<Integer, LoginView>();
	private ConcurrentHashMap<Integer, UserWindow_StaffView> staffViewsMap = new ConcurrentHashMap<Integer, UserWindow_StaffView>();
	private ConcurrentHashMap<Integer, UserWindow_CustomerView> customerViewsMap = new ConcurrentHashMap<Integer, UserWindow_CustomerView>();
	private ConcurrentHashMap<Integer, VehiclesTableView> vehicleTableViewsMap = new ConcurrentHashMap<Integer, VehiclesTableView>();
	private ConcurrentHashMap<Integer, VehicleProfileView> vehicleProfileViewsMap = new ConcurrentHashMap<Integer, VehicleProfileView>();
	private ConcurrentHashMap<Integer, UsersTableView> customerTableViewsMap = new ConcurrentHashMap<Integer, UsersTableView>();
	private ConcurrentHashMap<Integer, AddUserView> addUserViewsMap = new ConcurrentHashMap<Integer, AddUserView>();
	private ConcurrentHashMap<Integer, ProfileView_Frame> profileViewFramesMap = new ConcurrentHashMap<Integer, ProfileView_Frame>();
	private ConcurrentHashMap<Integer, AddVehicleView> addVehicleViewsMap = new ConcurrentHashMap<Integer, AddVehicleView>();

	

	/**
	 * Adds a view to this controller.
	 * This function checks what class the specified view is an instance of, providing a unique (for the session) view ID to it,
	 * adds the view to the correct Map and calls functions specified in the controller, that add listeners on that view.
	 * @param view - The view to add
	 */
	public void addView(Object view){
		if(view instanceof LoginView){

			LoginView loginView = (LoginView)view;
			viewID++;
			loginView.setViewID(viewID);
			loginViewsMap.put(viewID, loginView);
			addListenersToLoginView(loginView);

		} else if(view instanceof UserWindow_StaffView){

			UserWindow_StaffView staffView = (UserWindow_StaffView)view;
			viewID++;
			staffView.setViewID(viewID);
			staffViewsMap.put(viewID,staffView);
			addListenersToStaffView(staffView);

		} else if (view instanceof UserWindow_CustomerView) {

			UserWindow_CustomerView customerView = (UserWindow_CustomerView)view;
			viewID++;
			customerView.setViewID(viewID);
			customerViewsMap.put(viewID,customerView);
			addListenersToCustomerView(customerView);

		} else if(view instanceof VehiclesTableView){

			VehiclesTableView vTableView = (VehiclesTableView) view;
			viewID++;
			vTableView.setViewID(viewID);
			vehicleTableViewsMap.put(viewID,vTableView);
			addFilteringListenersToVehiclesTableView(vTableView);
			addButtonListenersToVehiclesTableView(vTableView);

		} else if(view instanceof VehicleProfileView){

			VehicleProfileView vProfileView = (VehicleProfileView) view;
			viewID++;
			vProfileView.setViewID(viewID);
			vehicleProfileViewsMap.put(viewID, vProfileView);
			addListenersToVehiclesProfileView(vProfileView);
			addWindowListenerToVehicleProfile(vProfileView);

		} else if (view instanceof UsersTableView) {
			UsersTableView cTableView = (UsersTableView) view;
			viewID++;
			cTableView.setViewID(viewID);
			customerTableViewsMap.put(viewID, cTableView);
			addFilteringListenersToCustomersTableView(cTableView);
			addButtonListenersToCustomersTableView(cTableView);

		} else if (view instanceof AddUserView){
			AddUserView addUserView = (AddUserView) view;
			viewID++;
			addUserView.setViewID(viewID);
			addUserViewsMap.put(viewID, addUserView);
			addWindowListenerToAddUserView(addUserView);

		} else if(view instanceof ProfileView_Frame){

			ProfileView_Frame profileViewFrame = (ProfileView_Frame) view;
			viewID++;
			profileViewFrame.setViewID(viewID);
			profileViewFramesMap.put(viewID, profileViewFrame);
			
		}else if (view instanceof AddVehicleView) {
			
			AddVehicleView addVehicleView = (AddVehicleView) view;
			viewID++;
			addVehicleView.setViewID(viewID);
			addVehicleViewsMap.put(viewID, addVehicleView);
			addWindowListenerToAddVehicleView(addVehicleView);
			
		} else if (view instanceof ProfileView){
			addButtonListenersToProfileView((ProfileView)view);
		}

	}

	/**
	 * Removes a view from this controller.
	 * This function checks what class the specified view is an instance of, and it removes it from the appropriate map.
	 */
	public void removeView(Object view){

		if(view instanceof LoginView){

			LoginView loginView = (LoginView)view;

			loginViewsMap.remove(loginView.getViewID());
		} else 	if(view instanceof UserWindow_StaffView){

			UserWindow_StaffView staffView = (UserWindow_StaffView)view;

			staffViewsMap.remove(staffView.getViewID());

		}else if(view instanceof UserWindow_CustomerView){
			UserWindow_CustomerView customerView = (UserWindow_CustomerView)view;

			customerViewsMap.remove(customerView.getViewID());

		} else if(view instanceof VehiclesTableView){

			VehiclesTableView vehicleTableView = (VehiclesTableView)view;
			vehicleTableViewsMap.remove(vehicleTableView.getViewID());

		} else if (view instanceof VehicleProfileView) {

			VehicleProfileView vProfileView = (VehicleProfileView)view;

			vehicleProfileViewsMap.remove(vProfileView.getViewID());

		} else if (view instanceof ProfileView_Frame) {

			ProfileView_Frame profileViewFrame = (ProfileView_Frame)view;

			profileViewFramesMap.remove(profileViewFrame.getViewID());

		} else if (view instanceof AddUserView) {
			AddUserView addUserView = (AddUserView)view;

			addUserViewsMap.remove(addUserView.getViewID());
			
		}else if (view instanceof AddVehicleView) {
			AddVehicleView addVehicleView = (AddVehicleView) view;
			
			addVehicleViewsMap.remove(addVehicleView.getViewID());
		}
	}




	/**
	 * Creates a {@link ProfileView}.
	 * This functions is intended to be used inside an initialization of a listener, where specifying "this" for this controller is impossible. 
	 * @param user - The User that the Profile View will be created for
	 * @return - The view.
	 */
	public ProfileView createProfileView(User user){
		ProfileView profView = new  ProfileView(this, user);
		return profView;
	}

	/**
	 * Creates a {@link VehiclesTableView}.
	 * This functions is intended to be used inside an initialization of a listener, where specifying "this" for this controller is impossible. 
	 * @return - The view.
	 */
	public VehiclesTableView createVehiclesTableView(){

		VehiclesTableView vTableView;
		//Check if the logged in User is an instance of Staff
		if (model.getLoggedInUser() instanceof Staff) {
			//Then create a table that contains all vehicles, available and not
			vTableView = new VehiclesTableView(this, createVehiclesTable());
			vTableView.setTitleLabelText("All Vehicles");
		//if not
		}else {
			//Then create a table that contains only the available vehicles
			vTableView = new VehiclesTableView(this, createAvailableVehiclesTable());
			vTableView.setTitleLabelText("Available Vehicles");
		}

		return vTableView;
	}
	
	
	/**
	 * Creates a {@link VehicleProfileView}.
	 * This functions is intended to be used inside an initialization of a listener, where specifying "this" for this controller is impossible. 
	 * @param vehicle - The Vehicle that the Profile view will be created for.
	 * @return - The view.
	 */
	public VehicleProfileView createVehicleProfileView(Vehicle vehicle){
		VehicleProfileView vProfileView = new VehicleProfileView(this,vehicle,false);
		return vProfileView;
	}

	/**
	 * Creates a {@link UsersTableView} with all Users displayed.
	 * This functions is intended to be used inside an initialization of a listener, where specifying "this" for this controller is impossible. 
	 * @return - The view.
	 */
	public UsersTableView createUsersTableView(){
		UsersTableView cTableView = new UsersTableView(this, createUsersTable());
		return cTableView;
	}
	
	/**
	 * Creates a {@link UsersTableView} with only the customers displayed.
	 * This functions is intended to be used inside an initialization of a listener, where specifying "this" for this controller is impossible. 
	 * @return - The view.
	 */
	public UsersTableView createCustomersTableView(){
		UsersTableView cTableView = new UsersTableView(this, createCustomersTable());
		return cTableView;
	}


	/**
	 * Creates a {@link AddUserView}.
	 * This functions is intended to be used inside an initialization of a listener, where specifying "this" for this controller is impossible. 
	 * @return - The view.
	 */
	public AddUserView createAddUserView(){
		AddUserView addUserView = new AddUserView(this,new AddUserValidator(this));
		return addUserView;
	}

	/**
	 * Creates a {@link ProfileView_Frame}.
	 * This functions is intended to be used inside an initialization of a listener, where specifying "this" for this controller is impossible. 
	 * @param userProfileView - The {@link ProfileView} that will be contained by the frame
	 * @return - The view.
	 */
	public ProfileView_Frame createFrameForUserProfile(ProfileView userProfileView){
		ProfileView_Frame userFrame = new ProfileView_Frame(this, userProfileView);
		return userFrame;
	}
	
	
	
	/**
	 * Creates a {@link AddVehicleView}.
	 * This functions is intended to be used inside an initialization of a listener, where specifying "this" for this controller is impossible. 
	 * @return - The view.
	 */
	public AddVehicleView createAddVehicleView(){
		AddVehicleView addVehicleView = new AddVehicleView(this,new AddVehicleValidator(this));
		return addVehicleView;
	}







	/**
	 * Updates, or re-initializes all open, or in the controller's map, VehicleProfileViews.
	 * This doesnt simply changes the content of the views, but re-creates the frames. 
	 */
	public void updateAllVehicleProfiles(){

		for (VehicleProfileView vProfileView : vehicleProfileViewsMap.values()) {
			

			Rectangle rect = vProfileView.getBounds();
			String title = vProfileView.getTitle();
			vProfileView.dispose();


			VehicleProfileView viewNew = new VehicleProfileView(this, vProfileView.getRequestedVehicle(),true);
			viewNew.setViewID(vProfileView.getViewID());
			vehicleProfileViewsMap.replace(vProfileView.getViewID(), viewNew);


			viewNew.setBounds(rect);
			viewNew.setTitle(title);

			viewNew.setLabelText(viewNew.getRequestedVehicle().getMake() + " " + viewNew.getRequestedVehicle().getModel() + "'s Profile");
			viewNew.setTitle("Vehicle: " + viewNew.getRequestedVehicle().getRegistrationNumber());
			viewNew.pack();
			viewNew.setVisible(true);
			addListenersToVehiclesProfileView(viewNew);
			addWindowListenerToVehicleProfile(viewNew);
		}
	}



	/**
	 * Updates, or re-initializes all open, or in the controller's map, CustomerProfileViews.
	 * In contrast to {@link updateAllVehicleProfiles} function, this updates only each view's content,
	 * and it doesnt recreate the frame.
	 * If a user has been deleted and the frame is still, this function take cares to close it. 
	 */
	public void updateAllCustomerProfiles(){

		for (ProfileView_Frame cProfileViewFrame : profileViewFramesMap.values()) {

			ProfileView profileView = cProfileViewFrame.getUserProfileView();
			User user = profileView.getUser();
			//if the user has been deleted but his profile is still open, close it
			if (model.userExists(user.getUserID())) {
					cProfileViewFrame.remove(profileView);
					cProfileViewFrame.revalidate();
					cProfileViewFrame.repaint();
					ProfileView newProfileView = createProfileView(user);
					newProfileView.setTitleLabelText(profileView.getTitleLabel().getText());
					cProfileViewFrame.add(newProfileView);
					
					cProfileViewFrame.revalidate();
					cProfileViewFrame.repaint();
					cProfileViewFrame.setUserProfileView(newProfileView);

			}else {
				removeView(cProfileViewFrame);
				cProfileViewFrame.dispose();
			}
		}
	}






	/**
	 * Creates a {@link CustomTable} that contains all the vehicles in the database.
	 * @return - the table
	 */
	public CustomTable createVehiclesTable() {
		CustomTableModel tableModel = new CustomTableModel(getModel().getVehicles());
		CustomTable vehiclesTable = new CustomTable(tableModel);
		return vehiclesTable;
	}

	/**
	 * Creates a {@link CustomTable} that contains only the available (not hired) vehicles in the database.
	 * @return - the table
	 */
	public CustomTable createAvailableVehiclesTable() {
		HashMap<Integer,Vehicle> hashmapOld = getModel().getVehicles();
		HashMap<Integer,Vehicle> hashmapnew = new HashMap<>();
		for (Integer key : hashmapOld.keySet()) {
			if (!hashmapOld.get(key).isRented()) {
				hashmapnew.put(hashmapnew.size(),hashmapOld.get(key));
			}
		}
		CustomTableModel tableModel = new CustomTableModel(hashmapnew);
		CustomTable vehiclesTable = new CustomTable(tableModel);
		return vehiclesTable;
	}

	
	/**
	 * Creates a {@link CustomTable} that contains only hired vehicles in the database.
	 * @return - the table
	 */
	public CustomTable createRentedVehiclesTable(Customer customer) {
		HashMap<Integer,Vehicle> hashmapOld = customer.getAllHiredVehicles();
		HashMap<Integer,Vehicle> hashmapnew = new HashMap<>();
		for (Integer key : hashmapOld.keySet()) {
			hashmapnew.put(hashmapnew.size(),hashmapOld.get(key));
		}
		CustomTableModel tableModel = new CustomTableModel(hashmapnew);
		CustomTable vehiclesTable = new CustomTable(tableModel);
		return vehiclesTable;
	}

	
	/**
	 * Creates a {@link CustomTable} that contains all the users in the database, both staff and customers.
	 * @return - the table
	 */
	public CustomTable createUsersTable() {
		CustomTableModel tableModel = new CustomTableModel(getModel().getUsers());
		CustomTable customersTable = new CustomTable(tableModel);
		return customersTable;
	}


	
	/**
	 * Creates a {@link CustomTable} that contains the customers in the database.
	 * @return - the table
	 */
	public CustomTable createCustomersTable() {
		HashMap<Integer,User> hashmapOld = model.getUsers();
		HashMap<Integer,User> hashmapnew = new HashMap<>();
		for (Integer key : hashmapOld.keySet()) {
			if (hashmapOld.get(key) instanceof Customer) {
				hashmapnew.put(hashmapnew.size(),hashmapOld.get(key));
			}
		}
		CustomTableModel tableModel = new CustomTableModel(hashmapnew);
		CustomTable customersTable = new CustomTable(tableModel);
		return customersTable;
	}



	
	/**
	 * Changes the content that resides in he main view, the central window of the user interface,
	 * with the specified panel. 
	 * @param panel - the panel to put into the main view.
	 */
	public void changeViewInsideMainView(JPanel panel) {

		if (viewInsideMainView != null) {
			if (viewInsideMainView.getParent() == MainView) {
				MainView.remove(viewInsideMainView);
				MainView.revalidate();
				MainView.repaint();
			}
		}

		GridBagConstraints gbc_View = new GridBagConstraints();
		gbc_View.fill = GridBagConstraints.BOTH;
		gbc_View.gridx = 0;
		gbc_View.gridy = 0;
		MainView.add(panel,gbc_View);

		viewInsideMainView = panel;

		MainView.repaint();
		MainView.revalidate();
		viewInsideMainView.repaint();
		viewInsideMainView.revalidate();

		MainView.setVisible(true);
		viewInsideMainView.setVisible(true);
	}


	/**
	 * Refreshes the content of the view that resides in the main view. 
	 * If for example the user deleted a car, or if a user  hired out a vehicle, this change needs to show on the table that is in the main view.
	 */
	public void refreshViewInsideMainView(){

		if (viewInsideMainView != null) {
			if (viewInsideMainView.getParent() == MainView) {
				MainView.remove(viewInsideMainView);
				MainView.revalidate();
				MainView.repaint();
			}

			if (viewInsideMainView instanceof VehiclesTableView) {
				viewInsideMainView = createVehiclesTableView();

			}else if(viewInsideMainView instanceof UsersTableView){
				viewInsideMainView = createUsersTableView();

			} else if(viewInsideMainView instanceof ProfileView){
				viewInsideMainView = createProfileView(((ProfileView) viewInsideMainView).getUser());
				((ProfileView) viewInsideMainView).getImageLabel().setVisible(false);
			}


			GridBagConstraints gbc_View = new GridBagConstraints();
			gbc_View.fill = GridBagConstraints.BOTH;
			gbc_View.gridx = 0;
			gbc_View.gridy = 0;
			MainView.add(viewInsideMainView,gbc_View);



			MainView.repaint();
			MainView.revalidate();
			viewInsideMainView.repaint();
			viewInsideMainView.revalidate();

			MainView.setVisible(true);
			viewInsideMainView.setVisible(true);
		}
	}



	/**
	 * Used before a user is deleted. This function takes care of returning all the vehicles that a user 
	 * has hired , back to the database.
	 * @param user - The user from whom the hired vehicles to be returned
	 */
	private void returnAllVehiclesFromUser(User user){
		if (user instanceof Customer) {
			Customer cust = (Customer) user;

			for (Vehicle vehicle : cust.getAllHiredVehicles().values()) {
				vehicle.setRented(false);
				vehicle.setRenter(null);
			}
		}
	}

	/**
	 * Determine if the user that logged in is staff or customer and show the relevant view after the login.
	 * This function is invoked after a user has successfully logged in.
	 * @param user - the user that has logged in.
	 */
	private void changeViewAfterLogin(User user) {

		if (user instanceof Staff) {

			UserWindow_StaffView sView = new UserWindow_StaffView(this);
			if (user.getDropBoxImageName() == null) {
				//use default image if there is no user image
				sView.getUsrImage().setIcon((new ImageIcon(UserWindow.class.getResource("/images/user_default_img.png"))));
			}else {
				//GET THE IMAGE OF THE USER
			}
			MainView = sView.getMainArea();
			ProfileView profileView = this.createProfileView(user);

			profileView.getImageLabel().setVisible(false);
			changeViewInsideMainView(profileView);
			sView.getProfileButton().setEnabled(false);
			sView.getProductsButton().setEnabled(true);
			sView.setVisible(true);
			


		}else if (user instanceof Customer) {
			UserWindow_CustomerView cview = new UserWindow_CustomerView(this);
			MainView = cview.getMainArea();
			ProfileView profileView = this.createProfileView(user);
			profileView.getImageLabel().setVisible(false);
			changeViewInsideMainView(profileView);
			cview.getProfileButton().setEnabled(false);
			cview.getProductsButton().setEnabled(true);
			cview.setVisible(true);
			
			if (user.getDropBoxImageName() == null) {

				cview.getUsrImage().setIcon((new ImageIcon(UserWindow.class.getResource("/images/user_default_img.png"))));

			}else {
				//GET THE IMAGE OF THE USER
			}
		}
	}

	/**
	 * Log the user logged in out, dispose all open views, empty all maps in the controller, and show the login view again.
	 * @param view - the view to close 
	 */
	private void changeViewAfterLogout(UserWindow view) {
		model.userLogout();

		LoginView loginView = new LoginView(this);

		loginView.setVisible(true);

		Window win = SwingUtilities.getWindowAncestor(view.getLogoutButton());
		removeView(view);
		disposeOpenViews();
		emptyAllMaps();
		win.dispose();
	}

	/**
	 * Clear all maps in the controller
	 */
	private void emptyAllMaps(){		

		staffViewsMap.clear();
		customerViewsMap.clear();
		vehicleTableViewsMap.clear();
		vehicleProfileViewsMap.clear();
		customerTableViewsMap.clear();
		addUserViewsMap.clear();
		profileViewFramesMap.clear();
		vehicleProfileViewsMap.clear();
	}
	
	/**
	 * Go through all maps, and dispose all open views
	 */
	private void disposeOpenViews(){
		vehicleProfileViewsMap.forEach((Integer, VehicleProfileView)-> VehicleProfileView.dispose());
		profileViewFramesMap.forEach((Integer, ProfileView_Frame)-> ProfileView_Frame.dispose());
		addUserViewsMap.forEach((Integer, AddUserView)-> AddUserView.dispose());
		addVehicleViewsMap.forEach((Integer, AddVehicleView)-> AddVehicleView.dispose());
	}


	/**
	 * Add listeners to the login view
	 * @param loginView - the login view to attach listeners to 
	 */
	private void addListenersToLoginView(LoginView loginView){

		ActionListener submitButtonActionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (model.userLogin(loginView.getUserNameTxtField().getText(), String.valueOf(loginView.getPasswordField().getPassword()))) {
					String warningText = "<html><font color='green'><br>Correct credentials! <br> Welcome.</font></html>";
					loginView.getMessageLabel().setText(warningText);
					changeViewAfterLogin(model.getLoggedInUser());

					//https://stackoverflow.com/questions/29357055/close-window-jpanel-in-java
					Window win = SwingUtilities.getWindowAncestor(loginView.getMessageLabel());
					removeView(loginView);
					win.dispose();
				}else {
					String warningText = "<html><font color='red'><br>No correct username or password! <br> Please try again!</font></html>";
					loginView.getMessageLabel().setText(warningText);
				}	
			}
		};

		loginView.getBtnSubmit().addActionListener(submitButtonActionListener);
	}


	
	/**
	 * Resizes the image given to the specified height and width
	 * @param img - the image to resize
	 * @param height - the height to resize to
	 * @param width - the width to resize to
	 * @return - the resized image
	 */
	private static BufferedImage resizeImage(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
	
//	public void getUserImage(UserWindow view){
//		
//		ImageIcon icon = new ImageIcon(resize(drpBox.downloadUserImage(model.getLoggedInUser()),128,128));
//		view.getUsrImage().setIcon(icon);
//
////			if (model.getLoggedInUser().getDropBoxImageName() == null) {
////				view.getUsrImage().setIcon(new ImageIcon(UserWindow.class.getResource("/images/user_default_img.png")));
////				model.getLoggedInUser().setUserImageName("user_default_img.png");
////			}else{
////
////				if ( !model.getLoggedInUser().getDropBoxImageName().equals(model.getLoggedInUser().getUserImageName())) {
////					ImageIcon icon = new ImageIcon(resize(drpBox.downloadUserImage(model.getLoggedInUser()),128,128));
////					view.getUsrImage().setIcon(icon);
////					model.getLoggedInUser().setUserImageName(model.getLoggedInUser().getDropBoxImageName());
////				}
////			}
//
//			
//		}
//	
//	public void getVehicleImage(VehicleProfileView view){
//		
//		ImageIcon icon = new ImageIcon(resize(drpBox.downloadVehicleImage(view.getRequestedVehicle()),128,128));
//		view.getLblImg().setIcon(icon);
//
////		if (view.getRequestedVehicle().getDropBoxImageName() == null) {
////			view.getLblImg().setIcon((new ImageIcon(UserWindow.class.getResource("/images/vehicle_default_img.png"))));
////			view.getRequestedVehicle().setVehicleImageName("vehicle_default_img.png");
////		}else{
////
////			if ( !view.getRequestedVehicle().getDropBoxImageName().equals(view.getRequestedVehicle().getVehicleImageName())) {
////				ImageIcon icon = new ImageIcon(resize(drpBox.downloadVehicleImage(view.getRequestedVehicle()),128,128));
////				view.getLblImg().setIcon(icon);
////				view.getRequestedVehicle().setVehicleImageName(view.getRequestedVehicle().getDropBoxImageName());
////			}
////		}
//
//		
//	}
	
	


	/**
	 * Adds listeners to the {@link UserWindow_StaffView}
	 * Specifically to: <br>
	 * Logout Button<br>
	 * Vehicles Button<br>
	 * Profile Button<br>
	 * Customers Button<br>
	 * @param staffView - The view to add listeners to 
	 */
	private void addListenersToStaffView(UserWindow_StaffView staffView){

		ActionListener logoutBtnListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				changeViewAfterLogout(staffView);

			}
		};

		ActionListener vehicleBtnListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				staffView.addLegendVehicles();
				
				VehiclesTableView vehiclesView = createVehiclesTableView();

				vehiclesView.setAddButtonVisible(true);
				vehiclesView.setRemoveButtonVisible(true);

				changeViewInsideMainView(vehiclesView);

				staffView.getCustomersButton().setEnabled(true);
				staffView.getProfileButton().setEnabled(true);
				staffView.getProductsButton().setEnabled(false);

			}
		};

		ActionListener profileBtnListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				staffView.removeLegend();
				ProfileView profileView = createProfileView(model.getLoggedInUser());
				profileView.getImageLabel().setVisible(false);
				changeViewInsideMainView(profileView);

				staffView.getProfileButton().setEnabled(false);
				staffView.getProductsButton().setEnabled(true);
				staffView.getCustomersButton().setEnabled(true);
			}
		};

		ActionListener customerBtnListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				staffView.addLegendUsers();
				changeViewInsideMainView(createUsersTableView());


				staffView.getCustomersButton().setEnabled(false);
				staffView.getProfileButton().setEnabled(true);
				staffView.getProductsButton().setEnabled(true);
			}
		};

		staffView.getLogoutButton().addActionListener(logoutBtnListener);
		staffView.getProductsButton().addActionListener(vehicleBtnListener);
		staffView.getProfileButton().addActionListener(profileBtnListener);
		staffView.getCustomersButton().addActionListener(customerBtnListener);
		staffView.setLblUsernameText(model.getLoggedInUser().getUsername());
	}







	/**
	 * Adds listeners to the {@link UserWindow_CustomerView}
	 * Specifically to: <br>
	 * Logout Button<br>
	 * Profile Button<br>
	 * Vehicles Button <br>
	 * @param customerView - The view to add listeners to 
	 */
	private void addListenersToCustomerView(UserWindow_CustomerView customerView){
		ActionListener logoutBtnListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				changeViewAfterLogout(customerView);
			}
		};



		ActionListener vehiclesBtnListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				customerView.addLegendVehicles();
				VehiclesTableView vehiclesView = createVehiclesTableView();

				vehiclesView.setAddButtonVisible(false);
				vehiclesView.setRemoveButtonVisible(false);

				changeViewInsideMainView(vehiclesView);


				customerView.getProfileButton().setEnabled(true);
				customerView.getProductsButton().setEnabled(false);

			}
		};


		ActionListener profileBtnListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ProfileView profileView = createProfileView(model.getLoggedInUser());
				profileView.getImageLabel().setVisible(false);
				changeViewInsideMainView(profileView);
				customerView.getProfileButton().setEnabled(false);
				customerView.getProductsButton().setEnabled(true);
			}
		};


		customerView.getLogoutButton().addActionListener(logoutBtnListener);
		customerView.getProductsButton().addActionListener(vehiclesBtnListener);
		customerView.getProfileButton().addActionListener(profileBtnListener);
		customerView.setLblUsernameText(model.getLoggedInUser().getUsername());
	}
	
	
	
	
	/**
	 * Adds listeners to the {@link ProfileView}
	 * Specifically to: <br>
	 * Add User image button
	 * 
	 * This function uploads an image to dropbox
	 * @param view - The view to add listeners to 
	 */
	private void addButtonListenersToProfileView(ProfileView view){
		
		ActionListener addImageListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				drpBox.uploadUserImage(view.getUser());
//				ImageIcon usrImg = new ImageIcon(drpBox.downloadUserImage(view.getUser()));
//				view.getImageLabel().setIcon(usrImg);

			}
		};
		if (view.getAddImageBtn() != null) {
			view.getAddImageBtn().addActionListener(addImageListener);
		}
	}


	/**
	 * Checks if the specified string matches the specified regex
	 * @param regex - the regex to compare the string to
	 * @param string - the string to validate
	 * @return - true if the string passes the validation, false otherwise
	 */
	private boolean matchesRegex(String regex, String string) {
		boolean matches = false;
		Pattern patern = Pattern.compile(regex);
		Matcher matcher = patern.matcher(string);

		if (matcher.matches()) {
			matches = true;
		}

		return matches;
	}

	/**
	 * Adds listeners to the {@link VehiclesTableView}
	 * Specifically to: <br>
	 * Logout Button<br>
	 * Profile Button<br>
	 * Vehicles Button <br>
	 * @param customerView - The view to add listeners to 
	 */
	private void addFilteringListenersToVehiclesTableView(VehiclesTableView vTableView){


		String regex = "^[a-zA-Z0-9]+$";

		JTextField searchTerm = vTableView.getTextField();
		JComboBox<String> comboBox = vTableView.getComboBox();

		final TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(vTableView.getTable().getModel());
		vTableView.getTable().setRowSorter(rowSorter);

		searchTerm.getDocument().addDocumentListener(new DocumentListener(){
			//validate the search field as the user inserts characters in
			@Override
			public void insertUpdate(DocumentEvent e) {
				String textSearch = searchTerm.getText();
				String textCombo = comboBox.getSelectedItem().toString();
				if (textSearch.trim().length() == 0) {
					rowSorter.setRowFilter(null);
					//validate based on the selection in the combobox AND the search term
				}else if(textCombo.equals("All")) {
					if (matchesRegex(regex, textSearch)) {
						//Validate the specified regex with the text that was typed. Case insensitive.
						rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + textSearch));
					}else {
						rowSorter.setRowFilter(null);
					}

				}
				else {
					List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>(2);
					if (matchesRegex(regex, textSearch)) {
						filters.add(RowFilter.regexFilter("(?i)" + textSearch));
					}
					filters.add(RowFilter.regexFilter("(?i)" + textCombo));
					RowFilter<Object,Object> combinedFilters = RowFilter.andFilter(filters);

					rowSorter.setRowFilter(combinedFilters);
				}
			}
			
			
			//validate as the user removes characters from the search box
			@Override
			public void removeUpdate(DocumentEvent e) {
				String textSearch = searchTerm.getText();
				String textCombo = comboBox.getSelectedItem().toString();
				if (textSearch.trim().length() == 0) {
					rowSorter.setRowFilter(null);

				}else if(textCombo.equals("All")) {
					if (matchesRegex(regex, textSearch)) {
						rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + textSearch));
					}else {
						rowSorter.setRowFilter(null);
					}
				} else {
					List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>(2);
					if (matchesRegex(regex, textSearch)) {
						filters.add(RowFilter.regexFilter("(?i)" + textSearch));
					}
					filters.add(RowFilter.regexFilter("(?i)" + textCombo));
					RowFilter<Object,Object> combinedFilters = RowFilter.andFilter(filters);

					rowSorter.setRowFilter(combinedFilters);
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}

		});

		//validate the combo box 
		vTableView.getComboBox().addItemListener(new ItemListener() {
			
			@Override
			//validate when the user selects an item. Validate in accordance with the search term not only the search term.
			public void itemStateChanged(ItemEvent e) {
				String textCombo = comboBox.getSelectedItem().toString();
				String textSearch = searchTerm.getText();
				if (textCombo.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				}else if(textCombo.equals("All")) {
					if (matchesRegex(regex, textSearch)) {
						rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + textSearch));
					}else {
						rowSorter.setRowFilter(null);
					}
				} else {
					List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>(2);
					if (matchesRegex(regex, textSearch)) {
						filters.add(RowFilter.regexFilter("(?i)" + textSearch));
					}
					filters.add(RowFilter.regexFilter("(?i)" + textCombo));
					RowFilter<Object,Object> combinedFilters = RowFilter.andFilter(filters);

					rowSorter.setRowFilter(combinedFilters);
				}

			}
		});
		
		
		vTableView.getTable().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//if the user double clicks on a table, 
				if (e.getClickCount() == 2) {
					//get the row that is selected
					int selectedRow = vTableView.getTable().getSelectedRow();
					//get the value at the 0th place of the row (vehicle reg number)
					String regNumber =  (String)vTableView.getTable().getValueAt(selectedRow, 0);
					//check if the reg number exists
					if (model.vehicleExists(regNumber)) {
						//open the requested vehicle's profile
						Vehicle requestedVehicle = model.getVehicleByRegNumber(regNumber);
						VehicleProfileView vehicleProfile = createVehicleProfileView(requestedVehicle);
						vehicleProfile.setLabelText(requestedVehicle.getMake() + " " + requestedVehicle.getModel() + "'s Profile");
						vehicleProfile.setTitle("Vehicle: " + requestedVehicle.getRegistrationNumber());
						vehicleProfile.pack();
						vehicleProfile.setVisible(true);
						addWindowListenerToVehicleProfile(vehicleProfile);
					}
				}
			}
		});
	}
	
	
	
	
	

	/**
	 * Add listeners to the vehicle profile.
	 * @param vehicleProfile - the vehicle profile to add listeners to.
	 */
	private void addWindowListenerToVehicleProfile(VehicleProfileView vehicleProfile){
		//prevent the window to close when the user closes it
		vehicleProfile.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		vehicleProfile.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {

			}

			@Override
			public void windowIconified(WindowEvent e) {

			}

			@Override
			public void windowDeiconified(WindowEvent e) {

			}

			@Override
			public void windowDeactivated(WindowEvent e) {

			}
			@Override
			public void windowClosing(WindowEvent e) {
				//first before closing, remove the view from the listner's Maps
				removeView(vehicleProfile);
				//and then dispose it
				vehicleProfile.dispose();
			}

			@Override
			public void windowClosed(WindowEvent e) {

			}

			@Override
			public void windowActivated(WindowEvent e) {
			}
		});
	}
	
	
	
	
	

	/**
	 * Adds listeners to the {@link VehiclesTableView}
	 * Specifically to: <br>
	 * Add Vehicle Button<br>
	 * Remove Vehicle Button<br>
	 * @param vTableView - The view to add listeners to 
	 */
	private void addButtonListenersToVehiclesTableView(VehiclesTableView vTableView){


		ActionListener vehicleAddBtnListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				createAddVehicleView().setVisible(true);

			}
		};


		ActionListener vehicleRemoveBtnListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow;

				if (vTableView.getTable().getSelectedRow() != -1) {
					selectedRow = vTableView.getTable().getSelectedRow();
					//0 is the column that contains the registration number
					String regNumber =  (String) vTableView.getTable().getValueAt(selectedRow, 0);

					if (model.vehicleExists(regNumber)) {
						
						
						//Confirm Removal
						int reply = JOptionPane.showConfirmDialog(vTableView, 
								"Are you sure you want to delete vehicle " + regNumber +" from the list?\n"
										+ "This action is irrevokable.", 
										"Vehicle Deletion", 
										JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {

							model.removeVehicle(model.getVehicleByRegNumber(regNumber));

							CustomTable table = new CustomTable(new CustomTableModel(model.getVehicles()));

							vTableView.setTable_1(table);
							vTableView.refreshTable(table);
							//Re-assigning the listeners
							addFilteringListenersToVehiclesTableView(vTableView);
							vTableView.getRootPane().repaint();
							vTableView.getRootPane().revalidate();
							serializeModel();
						}
					}
				}
			}
		};

		vTableView.getAddVehicleButton().addActionListener(vehicleAddBtnListener);
		vTableView.getRemoveVehicleButton().addActionListener(vehicleRemoveBtnListener);
	}


	
	
	
	/**
	 * Adds listeners to the {@link VehicleProfileView}
	 * Specifically to: <br>
	 * Hire Out Button<br>
	 * Return Button<br>
	 * Add Image Button<br>
	 * @param vProfileView - The view to add listeners to 
	 */
	public void addListenersToVehiclesProfileView(VehicleProfileView vProfileView){

		ActionListener vehicleHireOutButtonListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				int selectedRow = vProfileView.getTableView().getTable().getSelectedRow();

				Customer selectedUser = (Customer) model.getUserByID((int) vProfileView.getTableView().getTable().getValueAt(selectedRow, 0));

				selectedUser.hireAVehicle(vProfileView.getRequestedVehicle());
				refreshViewInsideMainView();
				updateAllCustomerProfiles();
				updateAllVehicleProfiles();
				serializeModel();
			}

		};

		ActionListener vehicleReturnButtonListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Customer selectedUser = (Customer) vProfileView.getRequestedVehicle().getRenter();

				selectedUser.returnAVehicle(vProfileView.getRequestedVehicle());

				refreshViewInsideMainView();
				updateAllCustomerProfiles();
				updateAllVehicleProfiles();
				serializeModel();
			}

		};
		
		ActionListener addVehicleImgButtonListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				drpBox.uploadVehicleImage(vProfileView.getRequestedVehicle());

			}

		};

		if (vProfileView.getReturnButton() != null) {
			vProfileView.getReturnButton().addActionListener(vehicleReturnButtonListener);

		}else if (vProfileView.getHireOutButton() != null) {
			vProfileView.getHireOutButton().addActionListener(vehicleHireOutButtonListener);
		}
		if (model.getLoggedInUser() instanceof Staff ) {
			vProfileView.getAddImageBtn().addActionListener(addVehicleImgButtonListener);
		}
		
	}

	
	
	


	/**
	 * Adds filtering listeners to the {@link UsersTableView}
	 * Validation of input in Search term and listing of table rows that contain String matching what was typed into the search term.
	 * In combination to the search term, filter table rows based on the selection in the dropdown menu.
	 * @param cTableView - The view to add listeners to 
	 */
	public void addFilteringListenersToCustomersTableView(UsersTableView cTableView){

		String regex = "^[a-zA-Z0-9]+$";
		String regexOddNumbers = "^\\d*[13579]+$";
		String regexEvenNumbers = "^\\d*[02468]+$";

		JTextField searchTerm = cTableView.getSearchTerm();
		JComboBox<String> comboBox = cTableView.getComboBox();

		final TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(cTableView.getTable().getModel());
		cTableView.getTable().setRowSorter(rowSorter);

		searchTerm.getDocument().addDocumentListener(new DocumentListener(){

			@Override
			public void insertUpdate(DocumentEvent e) {
				String textSearch = searchTerm.getText();
				String textCombo = comboBox.getSelectedItem().toString();


				if (textSearch.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				}else if(textCombo.equals("All")) {
					if (matchesRegex(regex, textSearch)) {
						rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + textSearch));
					}else {
						rowSorter.setRowFilter(null);
					}
				}
				else {
					List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>(2);
					if (matchesRegex(regex, textSearch)) {
						filters.add(RowFilter.regexFilter("(?i)" + textSearch));
					}
					if (textCombo.equals("Staff")) {
						filters.add(RowFilter.regexFilter(regexOddNumbers,0));
					}else if (textCombo.equals("Customers")) {
						filters.add(RowFilter.regexFilter(regexEvenNumbers,0));
					}

					RowFilter<Object,Object> combinedFilters = RowFilter.andFilter(filters);

					rowSorter.setRowFilter(combinedFilters);
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				String textSearch = searchTerm.getText();
				String textCombo = comboBox.getSelectedItem().toString();
				if (textSearch.trim().length() == 0) {
					rowSorter.setRowFilter(null);

				}else if(textCombo.equals("All")) {
					if (matchesRegex(regex, textSearch)) {
						rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + textSearch));
					}else {
						rowSorter.setRowFilter(null);
					}
				} else {
					List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>(2);
					if (matchesRegex(regex, textSearch)) {
						filters.add(RowFilter.regexFilter("(?i)" + textSearch));
					}
					if (textCombo.equals("Staff")) {
						filters.add(RowFilter.regexFilter(regexOddNumbers,0));
					}else if (textCombo.equals("Customers")) {
						filters.add(RowFilter.regexFilter(regexEvenNumbers,0));
					}

					RowFilter<Object,Object> combinedFilters = RowFilter.andFilter(filters);

					rowSorter.setRowFilter(combinedFilters);
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
			}

		});
		cTableView.getComboBox().addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				String textCombo = comboBox.getSelectedItem().toString();
				String textSearch = searchTerm.getText();

				if (textCombo.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				}else if(textCombo.equals("All")) {
					if (matchesRegex(regex, textSearch)) {
						rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + textSearch));
					}else {
						rowSorter.setRowFilter(null);
					}
				} else {
					List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>(2);
					if (matchesRegex(regex, textSearch)) {
						filters.add(RowFilter.regexFilter("(?i)" + textSearch));
					}
					if (textCombo.equals("Staff")) {
						filters.add(RowFilter.regexFilter(regexOddNumbers,0));
					}else if (textCombo.equals("Customers")) {
						filters.add(RowFilter.regexFilter(regexEvenNumbers,0));
					}

					RowFilter<Object,Object> combinedFilters = RowFilter.andFilter(filters);

					rowSorter.setRowFilter(combinedFilters);
				}

			}
		});

		
		cTableView.getTable().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//if hte user double clicks a table row, open the selected user's profile
				if (e.getClickCount() == 2) {
					int selectedRow = cTableView.getTable().getSelectedRow();
					int userID =  (int) cTableView.getTable().getValueAt(selectedRow, 0);

					if (model.userExists(userID)) {
						User requestedUser = model.getUserByID(userID);
						ProfileView profPanel = createProfileView(requestedUser);
						profPanel.setTitleLabelText(requestedUser.getUsername() + "'s Profile");
						//profPanel.add(requestedUser.getUserImage()) TODO 
						ProfileView_Frame frame = createFrameForUserProfile(profPanel);
						frame.setTitle("User Profile: "+ userID);
						frame.add(profPanel);
						frame.pack();
						frame.setVisible(true);
					}
				} else if (e.getClickCount() == 1) {
					if (cTableView.getContainedInVehicleProfile() != null) {
						cTableView.getContainedInVehicleProfile().getHireOutButton().setEnabled(true);
					}

				} 
			}
		});
	}

	
	
	
	
	
	/**
	 * Adds listeners to the {@link UsersTableView}
	 * Specifically to: <br>
	 * Add  user Button<br>
	 * Remove User Button<br>
	 * @param cTableView - The view to add listeners to 
	 */
	private void addButtonListenersToCustomersTableView(UsersTableView cTableView){

		ActionListener userAddBtnListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				createAddUserView().setVisible(true);

			}

		};

		ActionListener userRemoveBtnListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow;

				if (cTableView.getTable().getSelectedRow() != -1) {
					selectedRow = cTableView.getTable().getSelectedRow();
					//0 is the column that contains the registration number. DO NOT CHANGE THAT!
					int userID =  (int) cTableView.getTable().getValueAt(selectedRow, 0);

					if (model.userExists(userID)) {

						int reply = JOptionPane.showConfirmDialog(cTableView, 
								"Are you sure you want to delete user " + userID +" from the list?\n"
										+ "This action is irrevokable.", 
										"User Deletion", 
										JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {

							returnAllVehiclesFromUser(model.getUserByID(userID));
							model.removeUser(model.getUserByID(userID));
							

							refreshViewInsideMainView();
							updateAllVehicleProfiles();
							updateAllCustomerProfiles();
							serializeModel();
						}
					}
				}
			}
		};

		cTableView.getAddUserButton().addActionListener(userAddBtnListener);
		cTableView.getRemoveUserButton().addActionListener(userRemoveBtnListener);

	}


	
	
	
	/**
	 * Adds window listeners to {@link AddUserView}
	 * @param addUserView - The view to add listeners to 
	 */
	private void addWindowListenerToAddUserView(AddUserView addUserView){
		addUserView.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addUserView.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {

			}

			@Override
			public void windowIconified(WindowEvent e) {

			}

			@Override
			public void windowDeiconified(WindowEvent e) {

			}

			@Override
			public void windowDeactivated(WindowEvent e) {

			}

			@Override
			public void windowClosing(WindowEvent e) {
				//if the user closes the window while the date popup is open, there is an exception.
				//so making sure that the date popup is closed before the frame is disposed.
				addUserView.getDobField().closePopup();
				addUserView.dispose();
				removeView(addUserView);

			}

			@Override
			public void windowClosed(WindowEvent e) {
				refreshViewInsideMainView();
				updateAllCustomerProfiles();

			}

			@Override
			public void windowActivated(WindowEvent e) {

			}
		});
	}

	
	/**
	 * Add window listeners to {@link addVehicleView}
	 * @param addVehicleView - the view to add listeners to
	 */
	private void addWindowListenerToAddVehicleView(AddVehicleView addVehicleView){
		addVehicleView.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addVehicleView.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {

			}

			@Override
			public void windowIconified(WindowEvent e) {

			}

			@Override
			public void windowDeiconified(WindowEvent e) {

			}

			@Override
			public void windowDeactivated(WindowEvent e) {

			}

			@Override
			public void windowClosing(WindowEvent e) {
				//first remove the view from the Controller and its lists and then dispose it
				addVehicleView.dispose();
				removeView(addVehicleView);

			}

			@Override
			public void windowClosed(WindowEvent e) {
				//once its closed, refresh all opened customer profiles and the main view
				refreshViewInsideMainView();
				updateAllCustomerProfiles();

			}

			@Override
			public void windowActivated(WindowEvent e) {

			}
		});
	}






	public Databs getModel() {
		return model;
	}

	public JPanel getMainView() {
		return MainView;
	}

	public void setMainView(JPanel mainView) {
		MainView = mainView;
	}

	public JPanel getViewInsideMainView() {
		return viewInsideMainView;
	}

	public void setViewInsideMainView(JPanel viewInsideMainView) {
		this.viewInsideMainView = viewInsideMainView;
	}

}
