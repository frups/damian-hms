package damian.hms.application;

import java.io.Serializable;

import damian.hms.enums.EQ_TYPES;
import damian.hms.enums.MED_TYPES;

public class HospitalEquipmentMedicament extends HospitalEquipment implements Serializable{
	private MED_TYPES medicamentType;
	private double quantity;
	private int dose;
	
	public HospitalEquipmentMedicament(String eqName,  MED_TYPES medicamentType,
			double quantity, int dose) {
		super(eqName, EQ_TYPES.MEDICATION);
		this.medicamentType = medicamentType;
		this.quantity = quantity;
		this.dose = dose;
	}

	public HospitalEquipmentMedicament() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MED_TYPES getMedicamentType() {
		return medicamentType;
	}

	public void setMedicamentType(MED_TYPES medicamentType) {
		this.medicamentType = medicamentType;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public int getDose() {
		return dose;
	}

	public void setDose(int dose) {
		this.dose = dose;
	}

	@Override
	public String toString() {
		return getMedicamentType()+" "+super.getEqName()+" quantity: "+ getQuantity() + ", dose: " + getDose() + "mg";
	}

	
	
}
