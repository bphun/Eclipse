import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Drawing {

	private DrawingPanel panel;
	private JFrame frame;
	private Timer t;

	public static void main(String[] args) {
		new Drawing().start();
	}

	private void start() {
		panel = new DrawingPanel(this);
		frame = new JFrame("Drawing");
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startTimer();
	}

	private void startTimer() {
		t = new Timer(11, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panel.refresh();
			}	
		});
		t.start();
	}

	

}
