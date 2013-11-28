package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Sim;

public class Display extends JPanel {
	private static final Display INSTANCE = new Display();
	
	private Display() {
		setBackground(Color.BLACK);
		JLabel lbl = new JLabel("Display");
		lbl.setForeground(Color.WHITE);
		add(lbl);
	}
	
	public static Display getInstance() {
		return INSTANCE;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.WHITE);
		
		Sim.getInstance().paint((Graphics2D) g);
	}

}
