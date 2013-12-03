package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Sim;

public class Display extends JPanel implements MouseMotionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Display INSTANCE = new Display();
	
	private static int cameraX;
	private static int cameraY;
	
	private static int moveCamX;
	private static int moveCamY;
	
	private static float zoom;
	
	private Display() {
		cameraX = 0;
		cameraY = 0;
		moveCamX = 0;
		moveCamY = 0;
		
		zoom = 1;
		
		setBackground(Color.BLACK);
		JLabel lbl = new JLabel("Display");
		lbl.setForeground(Color.WHITE);
		add(lbl);
		
		addMouseMotionListener(this);
	}
	
	public static Display getInstance() {
		return INSTANCE;
	}
	
	public int getCameraX() {
		return cameraX;
	}
	
	public int getCameraY() {
		return cameraY;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.WHITE);
		
		Sim.getInstance().paint((Graphics2D) g);
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getX());
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
