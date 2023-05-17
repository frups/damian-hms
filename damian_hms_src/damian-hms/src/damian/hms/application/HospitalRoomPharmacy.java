package damian.hms.application;

import java.io.Serializable;
import java.util.Arrays;

import damian.hms.enums.ROOM_TYPES;

public class HospitalRoomPharmacy extends HospitalRoom implements Serializable{
	private HospitalEquipmentMedicament[] pharmacyMedicines = new HospitalEquipmentMedicament[1];

	public HospitalRoomPharmacy(int roomNumber) {
		super(roomNumber, ROOM_TYPES.PHARMACY, false);
	}
	
	public HospitalEquipmentMedicament[] getPharmacyMedicines() {
		return pharmacyMedicines;
	}

	public void setPharmacyMedicines(HospitalEquipmentMedicament[] pharmacyMedicines) {
		this.pharmacyMedicines = pharmacyMedicines;
	}

	
}
