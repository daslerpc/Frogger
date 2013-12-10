package model;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import utility.OrderedPair;
import utility.Pair;
import view.Display;

public class Sim {
	private static final Sim INSTANCE = new Sim();
	
	ArrayList<Vehicle> vehicles;
	int numvar;
	int numclause;

	public Sim() {
		vehicles = new ArrayList<Vehicle> ();	
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
		
		OrderedPair direction = new OrderedPair(1f, 0f);	
		
		OrderedPair location = new OrderedPair(0f, y_offset + 60f + numvar * 60.0f);
		
		int length = 10;
		int speed = 1;

		vehicles.add( new Bit(length, location, speed, direction, startVal) );

		direction = new OrderedPair(0f, 1f);
		location = new OrderedPair(30f, y_offset + 20f + numvar * 60.0f);
		vehicles.add( new Vehicle(length, location, speed, direction) );
		
		length = 30;
		
		direction = new OrderedPair(0f, 1f);
		location = new OrderedPair(10f, y_offset + numvar * 60.0f);
		vehicles.add( new Vehicle(length, location, speed, direction) );
		
		direction = new OrderedPair(0f, 1f);
		location = new OrderedPair(40f, y_offset + 30f + numvar * 60.0f);
		vehicles.add( new Vehicle(length, location, speed, direction) );

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
		
		for(int x=0; x < vehicles.size(); x++) {
			for(int y = x+1; y < vehicles.size(); y++) {
				doVehiclesCollide(vehicles.get(x), vehicles.get(y));
			}	
		}
		
		return collision_state;
	}

	private void doVehiclesCollide(Vehicle car1, Vehicle car2) {
		System.out.println("WARNING: Collision detection is not fully implemented.");
		Float slope1 = car1.getSlope();
		Float slope2 = car2.getSlope();
		
		// Find the point at which the cars may intersect (i.e. where their linear paths intersect)
		Float x_intercept = 0f;
		Float y_intercept = 0f;
		
		System.out.println(slope1);
		System.out.println(slope2);
	}

}
