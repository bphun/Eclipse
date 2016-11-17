package minesweeper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;

public class MineSweeperLevelChooser extends JFrame {
	// the proper way to create a Java GUI is to create a JFrame and 
	// place at least on JPanel, then place components onto the JPanel
	// and you can also draw on the JPanel from the paintComponent method
	JPanel panel;
	
	// This dimension will be assigned to the JPanel so that it opens
	// to this many pixels wide, by this many pixels high
	Dimension preferredDim = new Dimension(600,400);
	// don't really need these lists, but it makes it easier to figure 
	// out which Radio Buttons are selected
	List<JRadioButton> diffButtonList = new ArrayList(),
			           sizeButtonList = new ArrayList();
	
	Color background = new Color(120, 50, 200);
	JRadioButton easy = new JRadioButton("Easy"), medium = new JRadioButton("Medium"),
			     hard = new JRadioButton("Hard");
	JRadioButton tall = new JRadioButton("10x10"), grande = new JRadioButton("30X16"),
			     veinte = new JRadioButton("50x50"),custom = new JRadioButton("Custom") ;
	JButton play = new JButton("Play");// go button to be pressed to start game
	
	public MineSweeperLevelChooser() {
		super("Choose your level");// What does the String do?
		panel = new JPanel();
		
		this.add(panel);	// adds the JPanel to the JFrame.  This is proper
		
		panel.setBackground(background);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ALWAYS DO THIS!!!!
		panel.setPreferredSize(this.preferredDim);
		
		// Button Groups allow for only one to be selected at a time
		ButtonGroup diffGroup = new ButtonGroup();
		ButtonGroup sizeGroup = new ButtonGroup();
		
		// I'm using a different panel to hold each button group
		JPanel levelPanel = new JPanel(),
			   sizePanel = new JPanel();
		// playing around with borders, which are cool
		levelPanel.setBorder(new BevelBorder(0, Color.LIGHT_GRAY, Color.DARK_GRAY));
		panel.add(levelPanel);
		diffGroup.add(easy);
		diffGroup.add(medium);
		diffGroup.add(hard);
		
		// start off with the easy level button selected by default
		easy.setSelected(true);
		
		sizeGroup.add(tall);
		sizeGroup.add(grande);
		sizeGroup.add(veinte);
		sizeGroup.add(custom);
		
		levelPanel.add(new JLabel("Select Difficulty Level"));
		levelPanel.add(easy);
		easy.setSelected(true);
		levelPanel.add(medium);
		levelPanel.add(hard);
		
		diffButtonList.add(easy);
		diffButtonList.add(medium);
		diffButtonList.add(hard);
		
		sizePanel.setBorder(new RadioButtonBorder(Color.GRAY, Color.black, Color.CYAN, Color.magenta));
		panel.add(sizePanel);
		sizePanel.add(new JLabel("Select Size of Grid"));
		sizePanel.add(tall);
		tall.setSelected(true);
		sizePanel.add(grande);
		sizePanel.add(veinte);
		sizePanel.add(custom);
		sizeButtonList.add(tall);
		sizeButtonList.add(grande);
		sizeButtonList.add(veinte);
		sizeButtonList.add(custom);
		
		JPanel playButtonPanel = new JPanel();
		playButtonPanel.setBorder(new MatteBorder(5,5,1,1, new Color(140, 100, 30)));
		playButtonPanel.add(play);
		panel.add(playButtonPanel);

		// This is the proper way to link a button with an event.
		// when someone clicks the button, any actionlisteners who have been registered
		// with the button (you can add more than one).
		// This process is called creating an anonymous class.  The class implements
		// the ActionListener interface which requires an actionPerformed method
		play.addActionListener(new ActionListener() {

			@Override
			// What do we do when button is pressed?
			public void actionPerformed(ActionEvent arg0) {
				int diffIndex = -1, sizeIndex = -1;
				// figures out which difficulty button is selected
				for (JRadioButton db : diffButtonList) {
					System.out.println("Checking  " + db);
					if(db.isSelected()) {
						System.out.println(db + " is the selected diff!");
						diffIndex = diffButtonList.indexOf(db);
						break;
					}
				}
				// figures out which size button is selected
				for (JRadioButton db : sizeButtonList) {
					System.out.println("Checking  " + db);
					if(db.isSelected()) {
						System.out.println(db + " is the selected size!");
						sizeIndex = sizeButtonList.indexOf(db);
						break;
					}
				}
				// calls the makeNewGame passing in the index of each 
				// radio button.  That will be passed along to the 
				// constructor of the game panel
				makeNewGame(diffIndex, sizeIndex);
				
			}	
		});
		
		
		
		pack();// this makes sure the JFrame is big enough to display all the component within
		setVisible(true);// show this frame
	}

	protected void makeNewGame(int diffIndex, int sizeIndex) {
		// remove everything from the Frame
		this.getContentPane().removeAll();
		// make a new MineSweeperPanel and add it to the Frame
		this.add(new MineSweeperPanel(diffIndex, sizeIndex));
		this.pack();// see above
		
	}
	
}
