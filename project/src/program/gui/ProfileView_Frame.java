package program.gui;

import javax.swing.JFrame;

import program.controller.Controller;

/**
 * The frame that wraps the user's profile panel when it opens in a separate window. 
 * This class is created so that the Controller can have control over every ProfileView_Frame.
 * @author Triantafyllidis Antonios 
 *
 */
public class ProfileView_Frame extends JFrame{
	
	private int viewID;
	private Controller controller;
	
	private ProfileView userProfileView;
	
	/**
	 * Constructor takes the Controller instance and the ProfileView that will be added into the container. 
	 * It calls {@link Controller#addView(Object)} function, adding this view to the Controller.
	 * @param controller - The controller instance
	 * @param userProfileView - The view that will be contained in this ProfileView_Frame
	 */
	public ProfileView_Frame(Controller controller, ProfileView userProfileView){
		this.controller = controller;
		this.userProfileView = userProfileView;
		controller.addView(this);
	}
	
	

	public int getViewID() {
		return viewID;
	}

	public ProfileView getUserProfileView() {
		return userProfileView;
	}

	public void setUserProfileView(ProfileView userProfileView) {
		this.userProfileView = userProfileView;
	}

	public void setViewID(int viewID) {
		this.viewID = viewID;
	}
	
	
	
}
