package damian.hms.application;

import java.io.Serializable;

public class HospitalFloor implements Serializable{
	private int floorNumber;
	public static HospitalRoom[] rooms = new HospitalRoom[1];
	
	public HospitalFloor(int floorNumber) {
		this.floorNumber = floorNumber;
	}

	public int getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(int number) {
		this.floorNumber = number;
	}

	public HospitalRoom[] getRooms() {
		return rooms;
	}

	@Override
	public String toString() {
		return "HospitalFloor [getNumber()=" + getFloorNumber() + "]";
	}

	public void setRooms(HospitalRoom[] rooms) {
		this.rooms = rooms;
		
	}
	
}
