package damian.hms.application;

import java.io.Serializable;

import damian.hms.enums.ROOM_TYPES;

public class HospitalRoom implements Serializable{
	private int roomNumber;
	private ROOM_TYPES roomType;
	private boolean isDeletable;
	public HospitalRoom(int roomNumber, ROOM_TYPES roomType) {
		this.roomNumber = roomNumber;
		this.roomType = roomType;
		this.isDeletable = true;
	}

	public HospitalRoom(int roomNumber, ROOM_TYPES roomType, boolean isDeletable) {
		this.roomNumber = roomNumber;
		this.roomType = roomType;
		this.isDeletable = isDeletable;
	}
	public HospitalRoom() {
		// TODO Auto-generated constructor stub
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public ROOM_TYPES getRoomType() {
		return roomType;
	}

	public void setRoomType(ROOM_TYPES roomType) {
		this.roomType = roomType;
	}
	public boolean isDeletable() {
		return this.isDeletable;
	}
	@Override
	public String toString() {
		return getRoomNumber() +" "+ getRoomType();
	}

	
}
