package program.gui.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 * This class extends and applies custom configuration to the Jtable. 
 * All the display settings of the table are here. 
 * Functional settings and data handling can be found at {@link CustomTableModel}
 * @author Triantafyllidis Antonios 
 *
 */
public class CustomTable extends JTable{
	
	private DefaultTableModel model;

	/**
	 * Initializes the custom table. 
	 * @param model - The custom table model, not to be confused with the model of the program.
	 */
	public CustomTable(CustomTableModel model) {
		super(model);
		this.model = model;
		
		//Set the table to allow a single selection
		 setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 //Forbid reordering of columns
		 getTableHeader().setReorderingAllowed(false);
		 //Allow row selection
		 setRowSelectionAllowed(true);
		 //Forbid column selection
		 setColumnSelectionAllowed(false);
		 //forbid cell selection
		 setCellSelectionEnabled(false);
		 //set spacing between cells to 1 px height
		 setIntercellSpacing(new Dimension(0, 1));
		 //Set the font of the table
		 setFont(new Font("Calibri", Font.BOLD, 14));
		 //Make the rows taller
		 setRowHeight(getRowHeight()+10);
		 
		 //Set the default renderer of the table
		 setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
	            @Override
	            public Component getTableCellRendererComponent(JTable table,
	                    Object value, boolean isSelected, boolean hasFocus, int row, int col) {
	                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
	                //remove the focus border
			        setBorder(noFocusBorder);
			        //aligning text to center (Using swingConstants as it is a more generic type than JLabel that in this case would be out of the context)
			        setHorizontalAlignment( SwingConstants.CENTER );

	                return this;
	            }   
	        });
		 
		 
		
	}
	
	//Configuring the color of the table based on the value of the last cell of each row.
    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
        Component c = super.prepareRenderer(renderer, row, col);
        //Status holds a value that determines what row was selected.
        String status = "";
        int statusInt=0;
        try {
        	//Store the value of the last cell in status variable
        	 status = (String)getValueAt(row, this.getColumnCount()-1);
        	 //If the value of status is "Car"
        	 if (("Car").equals(status)) {
        		 //make background of the row GREEN 
 	            c.setBackground(new Color(152,251,152));
 	            c.setForeground(Color.black);
 	            
 	            
 	        }
        	//If the value of status is "Van"
        	 if(("MiniBus").equals(status)) {
        		//make background of the row LIGHT BLUE 
 	            c.setBackground(new Color(173,216,230));
 	            c.setForeground(Color.black);

 	            
 	        }
        	//If the value of status is "Lorry"
        	 if(("Lorry").equals(status)) {
        		//make background of the row RED 
 	            c.setBackground(new Color(255,127,127));
 	            c.setForeground(Color.BLACK);
 	        }
		} catch (Exception e) {
			// TODO: handle exception
		}
        
        try {
        	 statusInt = (int)getValueAt(row, 0);
 	        //check the user id
 	        //if its even its a customer
 	        //if its odd its staff
        	 if ( (statusInt & 1) == 0 ) {
        		//make background of the row LIGHT BLUE 
 	            c.setBackground(new Color(173,216,230));
 	            c.setForeground(Color.black);
 	       }
 	       else {
 	    	   //make background of the row GREEN 
 	            c.setBackground(new Color(152,251,152));
 	            c.setForeground(Color.black);
 	       }
 	       
		} catch (Exception e) {
			// TODO: handle exception
		}
	        //if a row is selected
	        if (isRowSelected(row)) {
	        	//change background to NAVY color
	        	c.setBackground(new Color(0,0,128));
	        	//change letter color to white
	        	c.setForeground(Color.white);
	        	
	        }
        return c;
    }

    
    
}
