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
	
	private OrderedPair<Float> location;
	private OrderedPair<Float> goal;
	private OrderedPair<Float> direction;
	
	private float startTime;
	private float deadline;
	
	private float speed;
	private State state;
	private boolean value;
	private Color color;
	
	private float delay;
	
	String name;
	
	public enum State {WAITING, GOING, TRUE, FALSE, DONE}
	public Color[] Colors = {Color.WHITE, Color.BLUE, Color.GREEN, Color.RED, Color.LIGHT_GRAY};
	

	Vehicle() {
		length = 0;
		location = new OrderedPair<Float>(0f, 0f);
		goal = new OrderedPair<Float>(0f, 0f);
		direction = new OrderedPair<Float>(0f, 0f);
		
		startTime = 0f;
		deadline = 0f;
		
		speed = 0;
		state = State.WAITING;
		color = Color.WHITE;
		
		delay = 0;
		
		String name = "no_name";
		
	}
	
	Vehicle(float length, OrderedPair<Float> location, OrderedPair<Float> goal, float startTime, float deadline, float speed, boolean value, State state, String name) {
		this.length = length;
		
		this.location = location;
		this.goal = goal;
		
		Float x_dir = new Float(goal.getFirst() - location.getFirst());
		Float y_dir = new Float(goal.getSecond() - location.getSecond());
		Float sum = x_dir + y_dir;
		
		// normalize direction of travel
		x_dir /= sum;
		y_dir /= sum;
		
		this.direction = new OrderedPair<Float>(x_dir, y_dir);
		
		this.startTime = startTime;
		this.deadline = deadline;
		
		this.value = value;
		
		if(value)
			delay = 1;
		
		this.speed = speed;
		changeState(state);
		
		this.name = name;
	}
	
	Vehicle(float length, OrderedPair<Float> location, OrderedPair<Float> goal, float startTime, float deadline, float speed, boolean value, String name) {
		this(length, location, goal, startTime, deadline, speed, value, State.WAITING, name);
	}
	
	Vehicle(float length, OrderedPair<Float> location, OrderedPair<Float> goal, float startTime, float deadline, float speed, boolean value) {
		this(length, location, goal, startTime, deadline, speed, false, State.WAITING, "no_name");
	}
	
	public void updatePosition(float timestep) {
		if( delay < timestep ) {
			
			if( delay != 0) {
				timestep -= delay;
				delay = 0;
				changeState(State.TRUE);
			}
			
			location.setFirst(new Float(location.getFirst() + direction.getFirst() * speed * timestep));
			location.setSecond(new Float(location.getSecond() + direction.getSecond() * speed * timestep));
			
			if( direction.getFirst() > 0 && location.getFirst() > goal.getFirst() || 
					direction.getFirst() < 0 && location.getFirst() < goal.getFirst())
				location.setFirst(goal.getFirst());
			
			if( direction.getSecond() > 0 && location.getSecond() > goal.getSecond() || 
					direction.getSecond() < 0 && location.getSecond() < goal.getSecond())
				location.setSecond(goal.getSecond());
		}
		else {
			delay -= timestep;	
		}
		

	}
	
	public void changeState(State newState) {
		state = newState;
		color = Colors[state.ordinal()];
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
		
		headX = Math.round(goal.getFirst()) + camX;
		headY = Math.round(goal.getSecond()) + camY;
		tailX = Math.round(headX + direction.getFirst()*length);
		tailY = Math.round(headY + direction.getSecond()*length);
		
		g.setColor(Color.DARK_GRAY);
		g.drawLine(headX, headY, tailX, tailY);
		
	}
	
	public void setColor( Color color ) {
		this.color = color;
	}
	
	public OrderedPair<Float> getHead() {
		Float x = new Float(location.getFirst());
		Float y = new Float(location.getSecond());
		return new OrderedPair<Float>(x, y);
	}
	
	public OrderedPair<Float> getTail() {
		Float x = new Float(location.getFirst()) + direction.getFirst()*length;
		Float y = new Float(location.getSecond()) + direction.getSecond()*length;
		return new OrderedPair<Float>(x, y);
	}
	
	public Pair<OrderedPair<Float>, OrderedPair<Float>> getCoords() {
		return new Pair<OrderedPair<Float>, OrderedPair<Float>>(getHead(), getTail());
	}
	
	public float getSlope() {
		return direction.getSecond()/direction.getFirst();
	}
}


