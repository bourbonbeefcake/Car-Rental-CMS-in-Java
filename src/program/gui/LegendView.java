package program.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

/**
 * 
 *This class is responsible of creating the legend under the option buttons that indicates what the colors in the table mean.
 *It is initialized in {@link UserWindow_StaffView} and {@link UserWindow_CustomerView}.
 * @author Triantafyllidis Antonios 
 *
 */
public class LegendView extends JPanel{
	
	private JLabel lbl1;
	private JLabel lblN2;
	private JLabel lbl3;
	
	private JLabel lbl1text;
	private JLabel lbl2text;
	private JLabel lbl3text;
	
	
	/**
	 * This class is responsible of creating the legend under the option buttons that indicates what the colors in the table mean.
	 * It is initialized in {@link UserWindow_StaffView} and {@link UserWindow_CustomerView}.
	 * @param choice - The String is the name of the table, 'Vehicles' or 'Users'.
	 */
	public LegendView(String choice){
		
		initialize(choice);
		
	}
	
	
	private void initialize(String choice){
		
		if (choice.equals("Vehicles")) {
			
			lbl1 = createColorLabel(152,251,152);
			lbl1text = new JLabel("Car");
			lblN2 =createColorLabel(173,216,230) ;
			lbl2text = new JLabel("Mini-Bus");
			lbl3 = createColorLabel(255,127,127);
			lbl3text = new JLabel("Lorry");
			
		}else if (choice.equals("Users")) {
			lbl1 = createColorLabel(152,251,152);
			lbl1text = new JLabel("Staff");
			lblN2 =createColorLabel(173,216,230) ;
			lbl2text = new JLabel("Customers");
		}
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel legend = new JLabel("Legend");
		GridBagConstraints gbc_legend = new GridBagConstraints();
		gbc_legend.insets = new Insets(0, 0, 5, 5);
		gbc_legend.gridx = 0;
		gbc_legend.gridy = 0;
		add(legend, gbc_legend);
		 
		GridBagConstraints gbc_lbl1 = new GridBagConstraints();
		gbc_lbl1.insets = new Insets(0, 0, 5, 5);
		gbc_lbl1.gridx = 0;
		gbc_lbl1.gridy = 1;
		add(lbl1, gbc_lbl1);
		
		 
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		add(lbl1text, gbc_lblNewLabel);
		
		 
		GridBagConstraints gbc_lblN2 = new GridBagConstraints();
		gbc_lblN2.insets = new Insets(0, 0, 5, 5);
		gbc_lblN2.gridx = 0;
		gbc_lblN2.gridy = 2;
		add(lblN2, gbc_lblN2);
		
		 
		GridBagConstraints gbc_lbl2 = new GridBagConstraints();
		gbc_lbl2.anchor = GridBagConstraints.WEST;
		gbc_lbl2.insets = new Insets(0, 0, 5, 0);
		gbc_lbl2.gridx = 1;
		gbc_lbl2.gridy = 2;
		add(lbl2text, gbc_lbl2);
		
		 if (choice.equals("Vehicles")) {
			 GridBagConstraints gbc_lbl3 = new GridBagConstraints();
				gbc_lbl3.insets = new Insets(0, 0, 0, 5);
				gbc_lbl3.gridx = 0;
				gbc_lbl3.gridy = 3;
				add(lbl3, gbc_lbl3);
				
				
				GridBagConstraints gbc_lblLorry = new GridBagConstraints();
				gbc_lblLorry.anchor = GridBagConstraints.WEST;
				gbc_lblLorry.gridx = 1;
				gbc_lblLorry.gridy = 3;
				add(lbl3text, gbc_lblLorry);
		}
		
	}
	

	
	
	
	/**
	 * Creates a custom label to be used as an icon for the Legend panel.
	 * The label is a square 20x20 pixels with Lowered Etched border. The color is given as parameters through the RGB format.
	 * @param r - Red
	 * @param g - Green
	 * @param b - Blue
	 * @return - The label
	 */
	private JLabel createColorLabel(int r, int g, int b){
		JLabel myLabel = new JLabel();
		
		myLabel.setIcon(new Icon() {
			@Override
			public void paintIcon(Component c, Graphics gr, int x, int y) {
				
				gr.setColor(new Color(r,g,b));
				gr.fillRect(0,0,21, 21);
				gr.dispose();
				
			}
			
			@Override
			public int getIconWidth() {
				return 20;
			}
			
			@Override
			public int getIconHeight() {
				return 20;
			}
		});
		
		myLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		
		return myLabel;
	}
	
}
