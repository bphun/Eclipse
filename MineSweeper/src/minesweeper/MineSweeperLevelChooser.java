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

	private static final long serialVersionUID = 1L;

	/*
	 * the proper way to create a Java GUI is to create a JFrame and 
	 * place at least on JPanel, then place components onto the JPanel
	 * and you can also draw on the JPanel from the paintComponent method
	 */
	 JPanel panel;
	
	/*
	 * This dimension will be assigned to the JPanel so that it opens
     * to this many pixels wide, by this many pixels high
	 */
	Dimension preferredDim = new Dimension(600,400);
	
	/*
	 * don't really need these lists, but it makes it easier to figure 
	 * out which Radio Buttons are selected
	 */
	List<JRadioButton> diffButtonList = new ArrayList<JRadioButton>(),
			           sizeButtonList = new ArrayList<JRadioButton>();
	
	Color background = new Color(120, 50, 200);
	JRadioButton easy = new JRadioButton("Easy"), medium = new JRadioButton("Medium"),
			     hard = new JRadioButton("Hard");
	JRadioButton tall = new JRadioButton("10x10"), grande = new JRadioButton("30X16"),
			     veinte = new JRadioButton("50x50"),custom = new JRadioButton("Custom") ;
	
	//	Play button pressed to start game
	JButton play = new JButton("Play");
	
	public MineSweeperLevelChooser() {
		super("Choose your level");
		panel = new JPanel();
		
		//	Adds JPanel to the JFrame.
		this.add(panel);
		
		panel.setBackground(background);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setPreferredSize(this.preferredDim);
		
		//	Button Groups allow for only one to be selected at a time
		ButtonGroup diffGroup = new ButtonGroup();
		ButtonGroup sizeGroup = new ButtonGroup();
		
		//	I'm using a different panel to hold each button group
		JPanel levelPanel = new JPanel(),
			   sizePanel = new JPanel();
		
		levelPanel.setBorder(new BevelBorder(0, Color.LIGHT_GRAY, Color.DARK_GRAY));
		panel.add(levelPanel);
		diffGroup.add(easy);
		diffGroup.add(medium);
		diffGroup.add(hard);
		
		//	Start off with the easy level button selected by default
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

		/*
		 * This is the proper way to link a button with an event.
		 * when someone clicks the button, any actionlisteners who have been registered
		 * with the button (you can add more than one).
		 * This process is called creating an anonymous class.  The class implements
		 * the ActionListener interface which requires an actionPerformed method
		*/
		play.addActionListener(new ActionListener() {

			@Override
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
				
				//	Figures out which size button is selected
				for (JRadioButton db : sizeButtonList) {
					System.out.println("Checking  " + db);
					if(db.isSelected()) {
						System.out.println(db + " is the selected size!");
						sizeIndex = sizeButtonList.indexOf(db);
						break;
					}
				}
				startGame(diffIndex, sizeIndex);
			}
		});
		
		
		
		//	Makes sure the JFrame is big enough to display all the component within
		pack();
		setVisible(true);
	}

	protected void startGame(int diffIndex, int sizeIndex) {
		//	Remove everything from the Frame
		this.getContentPane().removeAll();
		
		//	Make a new MineSweeperPanel and add it to the Frame
		this.add(new MineSweeperPanel(diffIndex, sizeIndex));
		this.setPreferredSize(this.getPreferredSize());
		this.pack();
	}
	
}
