package model;

import java.awt.Graphics2D;

public interface Traffic {
	
	public void paint(Graphics2D g);
	void updatePosition(float timestep);
}
