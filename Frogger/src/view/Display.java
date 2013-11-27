package view;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Display extends JPanel {
	public Display() {
		setBackground(Color.BLACK);
		JLabel lbl = new JLabel("Display");
		lbl.setForeground(Color.WHITE);
		add(lbl);
	}

}
