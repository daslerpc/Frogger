package model;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class WolfPack implements Traffic {
	ArrayList<Vehicle> vehicles;
	
	public WolfPack(ArrayList<Vehicle> cars) {
		vehicles = cars;
	}
	
	public ArrayList<Vehicle> getVehicles() {
		return vehicles;
	}

	@Override
	public void paint(Graphics2D g) {
		for(int x=0; x < vehicles.size(); x++)
			vehicles.get(x).paint(g);
	}

	@Override
	public void updatePosition(float timestep) {
		for(int x=0; x < vehicles.size(); x++)
			vehicles.get(x).updatePosition(timestep);		
	}

}
