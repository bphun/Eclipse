
public class MountainBike_ClassExtensions extends Bicycle_Classes {
	
	public int seatHeight;
	
	//Constructor
	public MountainBike_ClassExtensions(int startHeight, int startCadence, int startSpeed, int startGear) {
		super(startCadence, startSpeed, startGear);
		seatHeight = startHeight;
	}
	
	public void setSeatHeight(int newSeatHeight) {
		seatHeight = newSeatHeight;
	}
	
}