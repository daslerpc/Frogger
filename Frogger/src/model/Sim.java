package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;

public class Sim {
	private static final Sim INSTANCE = new Sim();
	
	ArrayList<Vehicle> vehicles;

	private Sim() {
		vehicles = new ArrayList<Vehicle> ();
		
		for( int var=0; var<6; var++)
			addVariable(var);
		
	}
	
	public void addVariable(int index) {
		int y_offset = 200;
		
		ArrayList<Float> direction = new ArrayList<Float>();	
		direction.add(1f);
		direction.add(0f);
		
		ArrayList<Float> location = new ArrayList<Float>();
		location.add(0f);
		location.add(y_offset + 60f + index * 60.0f);
		
		int length = 10;
		int speed = 10;
		
		vehicles.add( new Vehicle(length, location, speed, direction, Color.RED) );
		
		location.clear();
		location.add(0f);
		location.add(y_offset + 40f + index * 60.0f);
		vehicles.add( new Vehicle(length, location, speed, direction, Color.GREEN) );
		
		direction.clear();
		direction.add(0f);
		direction.add(1f);
		
		
		
		location.clear();
		location.add(30f);
		location.add(y_offset + 20f + index * 60.0f);
		vehicles.add( new Vehicle(length, location, speed, direction) );
		
		length = 30;
		
		location.clear();
		location.add(10f);
		location.add(y_offset + index * 60.0f);
		vehicles.add( new Vehicle(length, location, speed, direction) );
		
		location.clear();
		location.add(40f);
		location.add(y_offset + 30f + index * 60.0f);
		vehicles.add( new Vehicle(length, location, speed, direction) );
		
	}
	
	public static Sim getInstance() {
		return INSTANCE;
	}
	
	public void stepSim(int timestep) {
		for( int index=0; index < vehicles.size(); index++) 
			vehicles.get(index).updatePosition(timestep);
	}
	
	public void paint(Graphics2D g) {
		for( int index=0; index < vehicles.size(); index++) 
			vehicles.get(index).paint(g);
	}
}
