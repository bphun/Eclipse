import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ControlPanel extends JPanel {

	private BallBounce ballBounce;
	private JButton resetButton;
	private JButton generateButton;
	private JButton scatterButton;
	private JSlider gravitySlider;

	public ControlPanel(int width, int height, BallBounce ballBounce) {
		this.ballBounce = ballBounce;
		this.setPreferredSize(new Dimension(width, height));

		this.setupButtons();
	}

	private void setupButtons() {
		resetButton = new JButton("Reset");
		generateButton = new JButton("Generate");
		scatterButton = new JButton("Scatter");
		
		gravitySlider = new JSlider(JSlider.HORIZONTAL, 0, 3000, 2000);
		gravitySlider.setBorder(BorderFactory.createTitledBorder("Gravity - " + gravitySlider.getValue() + "px/s"));
		gravitySlider.setMajorTickSpacing(200);
		gravitySlider.setMinorTickSpacing(100);
		gravitySlider.setPaintTicks(true);

		this.add(gravitySlider);
		this.add(scatterButton);
		this.add(generateButton);
		this.add(resetButton);


		scatterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ballBounce.scatterButtonAction();
			}	
		});
		generateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ballBounce.generateButtonAction();
			}	
		});
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ballBounce.resetButtonAction();
			}	
		});

		gravitySlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				gravitySlider.setBorder(BorderFactory.createTitledBorder("Gravity - " + gravitySlider.getValue() + "px/s"));
				ballBounce.gravitySliderDidChange(gravitySlider.getValue());
			}
		});
	}

}