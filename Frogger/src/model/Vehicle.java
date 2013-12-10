package model;
import java.awt.Color;
import java.awt.Graphics2D;
import java.security.InvalidParameterException;
import java.util.ArrayList;

import utility.OrderedPair;
import utility.Pair;
import view.Display;


public class Vehicle {
	private float length;
	private OrderedPair location;
	
	private float speed;
	private OrderedPair direction;
	
	private Color color;
	
	

	Vehicle() {
		length = 0;
		location = new OrderedPair();
		
		speed = 0;
		direction = new OrderedPair();
	}
	
	Vehicle(float length, OrderedPair location, float speed, OrderedPair direction, Color color) throws InvalidParameterException{
		float dir_sum = direction.getFirst() + direction.getSecond();
		
		if( dir_sum == 0)
			throw new InvalidParameterException( "Direction vector equals 0.");
		
		// Normalize direction of travel
		direction.setFirst(direction.getFirst()/dir_sum);
		direction.setSecond(direction.getSecond()/dir_sum);
		
		this.length = length;
		this.location = new OrderedPair(location);
		
		this.speed = speed;
		this.direction = direction;
		
		this.color = color;
	}
	
	Vehicle(float length, OrderedPair location, float speed, OrderedPair direction) throws InvalidParameterException {
		this(length, location, speed, direction, Color.WHITE);
	}
	
	public void updatePosition(float timestep) {
		location.setFirst(new Float(location.getFirst() + direction.getFirst() * speed * timestep));
		location.setSecond(new Float(location.getSecond() + direction.getSecond() * speed * timestep));
	}
	
	public void paint(Graphics2D g) {
		int camX = Display.getInstance().getCameraX();
		int camY = Display.getInstance().getCameraY();
		
		int headX = Math.round(location.getFirst()) + camX;
		int headY = Math.round(location.getSecond()) + camY;
		int tailX = Math.round(headX + direction.getFirst()*length);
		int tailY = Math.round(headY + direction.getSecond()*length);
		
		g.setColor(color);
		g.drawLine(headX, headY, tailX, tailY);
	}
	
	public void setColor( Color color ) {
		this.color = color;
	}
	
	public OrderedPair getHead() {
		Float x = new Float(location.getFirst());
		Float y = new Float(location.getSecond());
		return new OrderedPair(x, y);
	}
	
	public OrderedPair getTail() {
		Float x = new Float(location.getFirst()) + direction.getFirst()*length;
		Float y = new Float(location.getSecond()) + direction.getSecond()*length;
		return new OrderedPair(x, y);
	}
	
	public Pair<OrderedPair, OrderedPair> getCoords() {
		return new Pair<OrderedPair, OrderedPair>(getHead(), getTail());
	}
	
	public float getSlope() {
		return direction.getSecond()/direction.getFirst();
	}
}


