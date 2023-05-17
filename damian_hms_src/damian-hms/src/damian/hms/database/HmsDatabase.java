package damian.hms.database;

import java.io.Serializable;
import java.util.Objects;

import damian.hms.application.HospitalEquipmentMedicament;
import damian.hms.application.HospitalFloor;
import damian.hms.application.HospitalPatient;
import damian.hms.application.HospitalServiceQueue;
import damian.hms.application.HospitalRoom;
import damian.hms.application.HospitalRoomPharmacy;
import damian.hms.application.HospitalService;
import damian.hms.application.HospitalServiceQueue;
import damian.hms.application.HospitalStaff;
import damian.hms.enums.ROOM_TYPES;

public class HmsDatabase extends StandardList implements Serializable{
	HospitalStaff[] staff = new HospitalStaff[1];
	HospitalService[] services = new HospitalService[1];
	HospitalRoom[] rooms = new HospitalRoom[1];
	HospitalServiceQueue[] queues = new HospitalServiceQueue[1];
	HospitalPatient[] patients = new HospitalPatient[1];
	HospitalEquipmentMedicament[] medicines = new HospitalEquipmentMedicament[1];
	HospitalFloor[] floors = new HospitalFloor[1];
	public HmsDatabase(boolean p) {
		this.addNewRecord(new HospitalFloor(1));
		this.addNewRecord(new HospitalRoomPharmacy(101));
	}
	public HmsDatabase() {
		
	}
	public void addNewRecord(HospitalStaff r) {
		HospitalStaff[] newArray  = new HospitalStaff[staff.length +1];
		staff[staff.length-1]=r;
		System.arraycopy(staff, 0, newArray, 0, staff.length);
		staff = newArray;
	}
	public void addNewRecord(HospitalService r) {
		HospitalService[] newArray  = new HospitalService[services.length +1];
		services[services.length-1]=r;
		System.arraycopy(services, 0, newArray, 0, services.length);
		services = newArray;
		
	}
	/*public void addNewRecord(HospitalRoom r, int noFloor) {
		HospitalRoom[] newArray  = new HospitalRoom[floors[noFloor-1].getRooms().length +1];
		floors[noFloor-1].rooms[floors[noFloor-1].getRooms().length-1]=r;
		System.arraycopy(floors[noFloor-1].getRooms(), 0, newArray, 0, floors[noFloor-1].getRooms().length);
		floors[noFloor-1].setRooms(newArray);
		
	}*/
	public void addNewRecord(HospitalRoom r) {
		HospitalRoom[] newArray  = new HospitalRoom[rooms.length +1];
		rooms[rooms.length-1]=r;
		System.arraycopy(rooms, 0, newArray, 0, rooms.length);
		rooms = newArray;
		
	}
	public void addNewRecord(HospitalServiceQueue r) {
		HospitalServiceQueue[] newArray  = new HospitalServiceQueue[queues.length +1];
		queues[queues.length-1]=r;
		System.arraycopy(queues, 0, newArray, 0, queues.length);
		queues = newArray;
		
	}
	public void addNewRecord(HospitalPatient r) {
		HospitalPatient[] newArray  = new HospitalPatient[patients.length +1];
		patients[patients.length-1]=r;
		System.arraycopy(patients, 0, newArray, 0, patients.length);
		patients = newArray;
		
	}
	public void addNewRecord(HospitalEquipmentMedicament r) {
		HospitalEquipmentMedicament[] newArray  = new HospitalEquipmentMedicament[getMedicines().length +1];
		getMedicines()[getMedicines().length-1]=r;
		System.arraycopy(getMedicines(), 0, newArray, 0, getMedicines().length);
		((HospitalRoomPharmacy) getRooms()[0]).setPharmacyMedicines(newArray);
		
	}
	public void addNewRecord(HospitalFloor r) {
		HospitalFloor[] newArray  = new HospitalFloor[floors.length +1];
		floors[floors.length-1]=r;
		System.arraycopy(floors, 0, newArray, 0, floors.length);
		floors = newArray;
		
	}
	public HospitalStaff[] getStaff() {
		return staff;
	}
	public HospitalService[] getServices() {
		return services;
	}
	/*public HospitalRoom[] getRooms(int noFloor) {
		return floors[noFloor].getRooms();
	}*/
	public HospitalRoom getRoom(int index) {
		return getRooms()[index];
	}
	/*public HospitalRoom[] getRooms() {
		HospitalRoom[] allRooms = new HospitalRoom[1];
		HospitalFloor[] floors = getFloors();
		int k=0;
		for(HospitalFloor i:floors) {
			if(Objects.nonNull(i)) {
				for(HospitalRoom j:i.getRooms()) {
					if(Objects.nonNull(j)) {
						HospitalRoom[] temp = new HospitalRoom[allRooms.length + 1];
					    System.arraycopy(allRooms, 0, temp, 0, allRooms.length);
					    allRooms = temp;
						allRooms[k]=j;
						k++;
					}
				}
			}
			}
		return allRooms;
	}*/
	public HospitalRoom[] getRooms() {
		return rooms;
	}
	public HospitalServiceQueue[] getQueues() {
		return queues;
	}
	public HospitalPatient[] getPatients() {
		return patients;
	}
	public HospitalEquipmentMedicament[] getMedicines() {
		return ((HospitalRoomPharmacy) getRooms()[0]).getPharmacyMedicines();
	}
	public HospitalFloor[] getFloors() {
		return floors;
	}
	public String[] getMedicinesList() {
		String[] list = new String[getMedicines().length-1];
		int j = 0;
		for(HospitalEquipmentMedicament i : getMedicines()) {
			if(Objects.nonNull(i)) {
				list[j]=i.getEqName();
				j++;
			}
		}
		return list;
	}
	public String[] getStaffList() {
		String[] list = new String[getStaff().length-1];
		int j = 0;
		for(HospitalStaff i : getStaff()) {
			if(Objects.nonNull(i)) {
				list[j]=i.getProfession()+" "+i.getSurname();
				j++;
			}
		}
		return list;
	}
	public String[] getServiceList() {
		String[] list = new String[getServices().length-1];
		int j = 0;
		for(HospitalService i : getServices()) {
			if(Objects.nonNull(i)) {
				list[j]=i.getServiceName()+" "+i.getDuration();
				j++;
			}
		}
		return list;
	}
	public String[] getRoomsList() {
		String[] list = new String[getRooms().length-1];
		int j = 0;
		for(HospitalRoom i : getRooms()) {
			if(Objects.nonNull(i)) {
				list[j]=i.getRoomNumber()+" "+i.getRoomType();
				j++;
			}
		}
		return list;
	}
	public void removeRecord(HospitalPatient hospitalPatient, int index) {
		if(patients.length>0 && index<patients.length) {
			HospitalPatient[] temp = new HospitalPatient[patients.length-1];
			for(int i = 0, k = 0; i<patients.length;i++) {
				if(i==index) {
					continue;
				}
				temp[k++]=patients[i];
			}
			patients=temp;
		}
	}
	public void removeRecord(HospitalStaff hospitalStaff, int index) {
		if(staff.length>0 && index<staff.length) {
			HospitalStaff[] temp = new HospitalStaff[staff.length-1];
			for(int i = 0, k = 0; i<staff.length;i++) {
				if(i==index) {
					continue;
				}
				temp[k++]=staff[i];
			}
			staff=temp;
		}
	}
	public void removeRecord(HospitalEquipmentMedicament medicament, int index) {
		if(getMedicines().length>0 && index<getMedicines().length) {
			HospitalEquipmentMedicament[] temp = new HospitalEquipmentMedicament[getMedicines().length-1];
			for(int i = 0, k = 0; i<getMedicines().length;i++) {
				if(i==index) {
					continue;
				}
				temp[k++]=getMedicines()[i];
			}
			((HospitalRoomPharmacy) getRooms()[0]).setPharmacyMedicines(temp);
		}
	}
	public void removeRecord(HospitalRoom hospitalRoom, int index) {
		if(rooms.length>0 && index<rooms.length) {
			HospitalRoom[] temp = new HospitalRoom[rooms.length-1];
			for(int i = 0, k = 0; i<rooms.length;i++) {
				if(i==index) {
					continue;
				}
				temp[k++]=rooms[i];
			}
			rooms=temp;
		}
		
	}
	public void removeRecord(HospitalService hospitalService, int index) {
		if(services.length>0 && index<services.length) {
			HospitalService[] temp = new HospitalService[services.length-1];
			for(int i = 0, k = 0; i<services.length;i++) {
				if(i==index) {
					continue;
				}
				temp[k++]=services[i];
			}
			services=temp;
		}
	}
	public void removeRecord(HospitalServiceQueue hospitalServiceQueue, int index) {
		if(queues.length>0 && index<queues.length) {
			HospitalServiceQueue[] temp = new HospitalServiceQueue[queues.length-1];
			for(int i = 0, k = 0; i<queues.length;i++) {
				if(i==index) {
					continue;
				}
				temp[k++]=queues[i];
			}
			queues=temp;
		}
		
	}
}
