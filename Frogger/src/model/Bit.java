package model;

import java.awt.Color;
import java.util.ArrayList;

import utility.OrderedPair;

public class Bit extends Vehicle {
	boolean value;
	int delay;
	
	public Bit(float length, OrderedPair location, float speed, OrderedPair direction) {
		super();
		
		value = false;
		delay = 0;
	}
	
	public Bit(float length, OrderedPair location, float speed, OrderedPair direction, boolean value) {
		super(length, location, speed, direction, Color.RED);
		
		this.value = value;
		delay = 0;
		
		if( value ) 
			setColor(Color.GREEN);
	}
	
	public void updatePosition(float timestep) {
		if ( value && delay < 2 ) {
			delay++;
		}
		else
			super.updatePosition(timestep);
	}

}
