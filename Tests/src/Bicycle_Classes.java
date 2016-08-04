
public class Bicycle_Classes {
	
	public int cadence;
	public int gear;
	public int speed;
	
	//Bicycle Constructor 
	public Bicycle_Classes(int startCadence, int startGear, int startSpeed) {
		cadence = startCadence;
		gear = startGear;
		speed = startSpeed;
	}
	

	//Method Declaration
	public void setCadence(int newCadence) {
		cadence = newCadence;
	}
	
	public void setGear(int newGear) {
		gear = newGear;
	}
	
	public void applyBreak(int decrement) {
		speed -= decrement;
	}
	
	public void  increaseSpeed(int increment) {
		speed += increment;
	}
	
}


