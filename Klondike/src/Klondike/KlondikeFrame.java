import javax.swing.JFrame;


public class KlondikeFrame extends JFrame {
	
	
	public KlondikeFrame() {
		super("Klondike");// What does the String do?
		
		this.setResizable(true);
		this.add(new KlondikePanel());
		
		
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);


		pack();
		setVisible(true);
	}

	
}
