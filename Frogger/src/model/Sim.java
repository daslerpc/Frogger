package model;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import view.Display;

public class Sim {
	private static final Sim INSTANCE = new Sim();
	
	ArrayList<Traffic> vehicles;
	int numvar;
	int numclause;

	public Sim() {
		vehicles = new ArrayList<Traffic> ();	
		numvar = 0;
		numclause = 0;
	}
	
	public void addVariable() {
		Random rand = new Random();
		int randomNum = rand.nextInt()%2;
		addVariable(randomNum==1);
	}
	
	public void addVariable(boolean startVal) {
		int y_offset = 200;
		
		ArrayList<Float> direction = new ArrayList<Float>();	
		direction.add(1f);
		direction.add(0f);
		
		ArrayList<Float> location = new ArrayList<Float>();
		location.add(0f);
		location.add(y_offset + 60f + numvar * 60.0f);
		
		int length = 10;
		int speed = 10;

		vehicles.add( new Bit(length, location, speed, direction, startVal) );

		
		direction.clear();
		direction.add(0f);
		direction.add(1f);
		
		
		
		ArrayList<Vehicle> cars = new ArrayList<Vehicle>();
		location.clear();
		location.add(30f);
		location.add(y_offset + 20f + numvar * 60.0f);
		cars.add( new Vehicle(length, location, speed, direction) );
		
		length = 30;
		
		location.clear();
		location.add(10f);
		location.add(y_offset + numvar * 60.0f);
		cars.add( new Vehicle(length, location, speed, direction) );
		
		location.clear();
		location.add(40f);
		location.add(y_offset + 30f + numvar * 60.0f);
		cars.add( new Vehicle(length, location, speed, direction) );
		
		vehicles.add(new WolfPack(cars) );	
		
		numvar++;
		Display.getInstance().repaint();
	}
	
	public void addClause() {
		// TODO Auto-generated method stub
		
		
		numclause++;
		Display.getInstance().repaint();
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
	
	public boolean doCollisionsExist() {
		boolean collision_state = false;
		
		
		
		return collision_state;
	}

}
