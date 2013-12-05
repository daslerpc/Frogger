package model;
import java.awt.Color;
import java.awt.Graphics2D;
import java.security.InvalidParameterException;
import java.util.ArrayList;

import view.Display;


public class Vehicle implements Traffic {
	private float length;
	private ArrayList<Float> location;
	
	private float speed;
	private ArrayList<Float> direction;
	
	private Color color;
	
	

	Vehicle() {
		length = 0;
		location = new ArrayList<Float>();
		
		speed = 0;
		direction = new ArrayList<Float>();
	}
	
	Vehicle(float length, ArrayList<Float> location, float speed, ArrayList<Float> direction, Color color) throws InvalidParameterException{
		if( location.size() != direction.size() )
			throw new InvalidParameterException( "Dimensionality of direction and location do not match." );
		
		if( location.size() != 2 )
			throw new InvalidParameterException( "This applet only supports two-dimensional data.  Please check your location and direction vectors.");
		
		float dir_sum = 0;
		for(int index=0; index < direction.size(); index++)
			dir_sum += direction.get(index);
		
		if( dir_sum == 0)
			throw new InvalidParameterException( "Direction vector equals 0.");
		
		// Normalize direction of travel
		for(int index = 0; index < direction.size(); index++)
			direction.set(index, direction.get(index)/dir_sum);
		
		this.length = length;
		this.location = new ArrayList<Float>(location);
		
		this.speed = speed;
		this.direction = new ArrayList<Float>(direction);
		
		this.color = color;
	}
	
	Vehicle(float length, ArrayList<Float> location, float speed, ArrayList<Float> direction) throws InvalidParameterException {
		this(length, location, speed, direction, Color.WHITE);
	}
	
	@Override
	public void updatePosition(float timestep) {
		for(int index = 0; index < location.size(); index++)
			location.set(index, location.get(index) + direction.get(index) * speed * timestep);
	}
	
	@Override
	public void paint(Graphics2D g) {
		int camX = Display.getInstance().getCameraX();
		int camY = Display.getInstance().getCameraY();
		
		int headX = Math.round(location.get(0)) + camX;
		int headY = Math.round(location.get(1)) + camY;
		int tailX = Math.round(headX + direction.get(0)*length);
		int tailY = Math.round(headY + direction.get(1)*length);
		
		g.setColor(color);
		g.drawLine(headX, headY, tailX, tailY);
	}
	
	public void setColor( Color color ) {
		this.color = color;
	}
}


