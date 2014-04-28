package model;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import utility.OrderedPair;
import utility.Pair;
import view.Display;

public class Sim {
	private static final Sim INSTANCE = new Sim();
	public static final float UNIT = 10.0f;
	
	ArrayList<Vehicle> vehicles;
	ArrayList<Boolean> values;
	
	int numvar;
	int numclause;

	public Sim() {
		vehicles = new ArrayList<Vehicle> ();	
		values = new ArrayList<Boolean> ();
		numvar = 3;
		numclause = 1;
	}
	
	public void addVariables(int num) {
		Random rand = new Random();
		int randomNum = rand.nextInt()%2;
	
		for(int x=0; x<num; x++)
			values.add(randomNum==1);
		
		addVariables(values);
	}
	
	public void addVariables(ArrayList<Boolean> startVals) {
		int numvar = startVals.size(); 
		
		for(int var = 0; var < numvar; var++) {
			float length = UNIT;
			float speed = UNIT;
			
			OrderedPair<Float> location = new OrderedPair<Float>(0f, var * 3*UNIT);
			OrderedPair<Float> goal = new OrderedPair<Float>(0f, (numvar*2*numclause + var)*3*UNIT );		
			
			float startTime = 0f;
			float deadline = (goal.getSecond() - location.getFirst())/UNIT - 1;
	
			vehicles.add( new Vehicle(length, location, goal, startTime, deadline, speed, startVals.get(var), "x_"+var));
			
			location = new OrderedPair<Float>(1*UNIT, var * 3*UNIT);
			goal = new OrderedPair<Float>(1*UNIT, (numvar*2*numclause + var)*3*UNIT );	
			vehicles.add( new Vehicle(length, location, goal, startTime, deadline, speed, !startVals.get(var), "x_"+var+"\'"));
		}
		

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
