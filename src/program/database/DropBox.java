package program.database;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FilenameUtils;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadErrorException;

import program.classes.Car;
import program.classes.Customer;
import program.classes.Lorry;
import program.classes.MiniBus;
import program.classes.Staff;
import program.classes.User;
import program.classes.Vehicle;

/**
 * This class establishes connection with DropBox and contains the functions responsible to download or upload a Vehicle's or a User's image.
 * @author Triantafyllidis Antonios 
 *
 */
public class DropBox {
	
	/**
	 * The Access token needed to access my dropbox account. 
	 * This key is specific to it. 
	 */
	String ACCESS_TOKEN = "###########CONNECT YOUR OWN APP TO DROPBOX AND GET YOUR OWN KEY##########";
	
	
	/**
	 * The new configuration to be created.
	 */
	DbxRequestConfig config = new DbxRequestConfig("NRS v0.1");
	
	
    /**
     * The DbxClientV2 instance gives access to the account's assets suchs as account information, files and more. 
     */
    DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
    
    
    
    
    
	
    /**
     * Checks whether the specified Vehicle is a Car, MiniBus or Lorry and appropriately selects the folder and the Vehicle registration number as one part of the path in in Dropbox.
     * Furthermore, it invokes {@link DropBox#chooseFile(String)} method passing in the path that was formed.
     * @param vehicle - The Vehicle to upload the image for. 
     */
    public void uploadVehicleImage(Vehicle vehicle){
    	String name = "/Vehicles/";
    	if (vehicle instanceof Car) {
    		name += "Cars/" + vehicle.getRegistrationNumber();
    		
		}
    	else if (vehicle instanceof MiniBus) {
    		name += "Minibuses/" + vehicle.getRegistrationNumber();
		}
    	else if (vehicle instanceof Lorry) {
    		name += "Lorries/" + vehicle.getRegistrationNumber();
		}
    	chooseFile(name);
    }
    
    
    
    
    
    /**
     * * Checks whether the specified User is a Staff or Customer and appropriately selects the folder and the User ID as one part of the image path in Dropbox.
     * Furthermore, it invokes {@link DropBox#chooseFile(String)} method passing in the path that was formed.
     * @param user - The User to upload the image for.
     */
    public void uploadUserImage(User user){
    	String name = "/Users/";
    	if (user instanceof Customer) {
    		name += "Customers/" + user.getUserID();
		}
    	else if (user instanceof Staff) {
    		name += "Staff/" + user.getUserID();
		}
    	
    	chooseFile(name);
    }
    
    
    
    
    /**
     * Downloads the image of the specified User from Dropbox after determining if the user is a Customer or Staff.
     * Exceptions throw a MessageDialog, informing that there was an error with the download. 
     * @param user - The user to download the image for
     * @return - The full size image of the user or null
     * 
     * This function has not been completely optimized yet!
     */
    public BufferedImage downloadUserImage(User user){
    	
    	//Using ByteArrayOutputStream so that it is possible to pipe that stream to the ByteArrayInputStream
    	//Afterwards, the input stream 'feeds' the BufferedImage object.
    	//
		 ByteArrayOutputStream out = new ByteArrayOutputStream();
		 ByteArrayInputStream in = null;
		 BufferedImage img = null;
    	 
		 String path = "/Users/";
		 
		 if (user instanceof Customer) {
			path += "Customers/" + user.getDropBoxImageName();
		} else if (user instanceof Staff) {
			path += "Staff/"+ user.getDropBoxImageName();
		}
		 
		 try
         {
             try
             {
            	 FileMetadata metadata = client.files().downloadBuilder(path)
                     .download(out);
             }
             finally
             {
            	 in = new ByteArrayInputStream(out.toByteArray());
            	  img = ImageIO.read(in);
            	 out.close();
            	 in.close();
             }
         }
         //exception handled
         catch (DbxException e)
         {
             //error downloading file
             JOptionPane.showMessageDialog(null, "Unable to download file. \n Error: " + e);
         }
         catch (IOException e)
         {
             //error downloading file
             JOptionPane.showMessageDialog(null, "Unable to download file. \n Error: " + e);
         }
		 
		 
    	 return img;
    }
    
    
    
    
    /**
     * Downloads the image of the specified Vehicle from Dropbox after determining if the Vehicle is a Car, MiniBus or Lorry.
     * Exceptions throw a MessageDialog, informing that there was an error with the download. 
     * @param vehicle - The Vehicle to download the image for
     * @return - The full size image of the Vehicle or null
     * 
     * This function has not been completely optimized yet!
     */
    public BufferedImage downloadVehicleImage(Vehicle vehicle){
    	
    	
    	//Using ByteArrayOutputStream so that it is possible to pipe that stream to the ByteArrayInputStream
    	//Afterwards, the input stream 'feeds' the BufferedImage object.
		 ByteArrayOutputStream out = new ByteArrayOutputStream();
		 ByteArrayInputStream in = null;
		 BufferedImage img = null;
   	 
		 String path = "/Users/";
		 
		 if (vehicle instanceof Car) {
			path += "Cars/" + vehicle.getDropBoxImageName();
		} else if (vehicle instanceof MiniBus) {
			path += "Minibuses/"+ vehicle.getDropBoxImageName();
		}else if (vehicle instanceof Lorry) {
			path += "Lorries/"+ vehicle.getDropBoxImageName();
		}
		 
		 try
        {
            try
            {
           	 FileMetadata metadata = client.files().downloadBuilder(path)
                    .download(out);
            }
            finally
            {
           	 in = new ByteArrayInputStream(out.toByteArray());
           	  img = ImageIO.read(in);
           	 out.close();
           	 in.close();
            }
        }
        //exception handled
        catch (DbxException e)
        {
            //error downloading file
            JOptionPane.showMessageDialog(null, "Unable to download file. \n Error: " + e);
        }
        catch (IOException e)
        {
            //error downloading file
            JOptionPane.showMessageDialog(null, "Unable to download file. \n Error: " + e);
        }
   	 return img;
   }
    


    
	/**
	 * Showing the JFileChooser for the user, limiting his options only to JPG and PNG images.
	 * When the user selects a file, the file chooser closes and another frame opens that remains until uploading is complete. 
	 * Uploading and the disposal of the frame are done with {@link DropBox#uploadToDropbox(File, JFrame, String)} function.
	 * @param name - The name of the path formed by {@link DropBox#uploadUserImage(User)} or {@link DropBox#uploadVehicleImage(Vehicle)} respectively. The file extension is missing.
	 */
	private void chooseFile(String name){      

	       JFileChooser chooser = new JFileChooser();
	       FileNameExtensionFilter filter = new FileNameExtensionFilter(
	           "JPG & PNG Images", "jpg", "png");
	       chooser.setFileFilter(filter);
	       chooser.setAcceptAllFileFilterUsed(false);
	       
	       int returnVal = chooser.showOpenDialog(new JFrame());
	       if(returnVal == JFileChooser.APPROVE_OPTION) {
	       	File file = new File(chooser.getSelectedFile().getAbsolutePath());

	       		JFrame f = new JFrame("Please wait.");
	       		f.setUndecorated(true);
	       		f.setMinimumSize(new Dimension(50, 50));
	       		f.setLocationRelativeTo(null);
	       		
	       		f.addWindowListener(new WindowListener() {
						
						@Override
						public void windowOpened(WindowEvent e) {
							
							uploadToDropbox(file,f, name);
							
							
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
							
							
						}
						
						@Override
						public void windowClosed(WindowEvent e) {
							
							
						}
						
						@Override
						public void windowActivated(WindowEvent e) {
							
							
						}
					});
	       		JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
	       		JLabel l = new JLabel("Please wait...");
	       		l.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
	       	
	       		p.add(l);
	       		f.add(p);
	       		f.pack();
	       		f.setVisible(true);
	       
	       }
	}
	
	
    /**
     * This function is called from {@link DropBox#chooseFile(String)} and performs the upload to DropBox.
     * The extension for the path is added here from the file that was chosen. 
     * The frame that informed the user to wait is disposed regardless if the image was uploaded or not. 
     * On success, there is an informing message on the screen. In case of failure, there is appropriate exception handling that shows a generic error message to the user. 
     * @param file - The file to upload
     * @param f - The frame that is prompting the user to wait.
     * @param name - The path to store the image in DropBox
     */
    private void uploadToDropbox(File file, JFrame f, String name){
    	String extension = FilenameUtils.getExtension(file.getAbsolutePath());
    	FileMetadata metadata = null;
        try {
        	InputStream in = new FileInputStream(file);
			 metadata = client.files().uploadBuilder(name + "." + extension)
					.uploadAndFinish(in);
			if (metadata.getContentHash() != null) {
				f.dispose();
				JOptionPane.showMessageDialog(new JFrame(), "The file upload is completed with no errors.");
			}else {
				f.dispose();
				JOptionPane.showMessageDialog(new JFrame(), "There was a problem uploading the image.");
				
			}
			
		} catch (UploadErrorException e1) {
			f.dispose();
			JOptionPane.showMessageDialog(new JFrame(), "There was a problem uploading the image.");
		} catch (DbxException e1) {
			f.dispose();
			JOptionPane.showMessageDialog(new JFrame(), "There was a problem uploading the image.");
		} catch (IOException e1) {
			f.dispose();
			JOptionPane.showMessageDialog(new JFrame(), "There was a problem uploading the image.");
		}catch (Exception e) {
			f.dispose();
			JOptionPane.showMessageDialog(new JFrame(), "There was a problem uploading the image.");
		}
    }
}
	



