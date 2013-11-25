package model;
import java.security.InvalidParameterException;
import java.util.ArrayList;


public class Vehicle {
	private float length;
	private ArrayList<Float> location;
	
	private float speed;
	private ArrayList<Float> direction;
	
	

	Vehicle() {
		length = 0;
		location = new ArrayList<Float>();
		
		speed = 0;
		direction = new ArrayList<Float>();
	}
	
	Vehicle(float length, ArrayList<Float> location, float speed, ArrayList<Float> direction) throws InvalidParameterException{
		if( location.size() != direction.size() )
			throw new InvalidParameterException( "Dimensionality of direction and location do not match." );
		
		float dir_sum = 0;
		for(int index=0; index < direction.size(); index++)
			dir_sum += direction.get(index);
		
		if( dir_sum == 0)
			throw new InvalidParameterException( "Direction vector equals 0.");
		
		// Normalize direction of travel
		for(int index = 0; index < direction.size(); index++)
			direction.set(index, direction.get(index)/dir_sum);
		
		this.length = length;
		this.location = location;
		
		this.speed = speed;
		this.direction = direction;
	}
	
	void updatePosition(float timestep) {
		for(int index = 0; index < location.size(); index++)
			location.set(index, location.get(index) + direction.get(index) * speed * timestep);
	}
}


