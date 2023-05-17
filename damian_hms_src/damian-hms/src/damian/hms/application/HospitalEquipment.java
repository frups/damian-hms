package damian.hms.application;

import java.io.Serializable;

import damian.hms.enums.EQ_TYPES;

public class HospitalEquipment implements Serializable{
	private String eqName;
	private EQ_TYPES eqType;
	public HospitalEquipment(String eqName, EQ_TYPES eqType) {
		this.eqName = eqName;
		this.eqType = eqType;
	}
	public HospitalEquipment() {
		// TODO Auto-generated constructor stub
	}
	public String getEqName() {
		return eqName;
	}
	public void setEqName(String eqName) {
		this.eqName = eqName;
	}
	public EQ_TYPES getEqType() {
		return eqType;
	}
	public void setEqType(EQ_TYPES eqType) {
		this.eqType = eqType;
	}
	@Override
	public String toString() {
		return "HospitalEquipment [getEqName()=" + getEqName() + ", getEqType()=" + getEqType() + "]";
	}
	
	
}
