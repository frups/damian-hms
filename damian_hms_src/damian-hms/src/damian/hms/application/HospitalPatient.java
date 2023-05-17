package damian.hms.application;

import java.io.Serializable;
import java.util.Date;

import damian.hms.enums.DISEASES;

public class HospitalPatient extends Human implements Serializable{

	double weight;
	double height;
	DISEASES disease;
	String allergy;
	//HospitalEquipmentMedicament[] allergies;
	private HospitalPatient(String firstName, String middleName, String surname, boolean gender, Date birthday) {
		super(firstName, middleName, surname, gender, birthday);
		// TODO Auto-generated constructor stub
	}
	private HospitalPatient(String firstName, String middleName, String surname, boolean gender) {
		super(firstName, middleName, surname, gender);
		// TODO Auto-generated constructor stub
	}
	private HospitalPatient(String firstName, String surname, boolean gender) {
		super(firstName, surname, gender);
		// TODO Auto-generated constructor stub
	}
	public HospitalPatient(String firstName, String middleName, String surname, boolean gender, Date birthday,
			double weight, double height, DISEASES disease, String allergy) {
		super(firstName, middleName, surname, gender, birthday);
		this.weight = weight;
		this.height = height;
		this.disease = disease;
		this.allergy = allergy;
	}
	public HospitalPatient(String firstName, String middleName, String surname, boolean gender,
			double weight, double height, DISEASES disease, String allergy) {
		super(firstName, middleName, surname, gender);
		this.weight = weight;
		this.height = height;
		this.disease = disease;
		this.allergy = allergy;
	}
	public HospitalPatient() {
		super();
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public DISEASES getDisease() {
		return disease;
	}
	public void setDisease(DISEASES disease) {
		this.disease = disease;
	}
	public String getAllergy() {
		return allergy;
	}
	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}
	@Override
	public String toString() {
		return super.getFirstName()+" "+super.getMiddleName()+" "+super.getSurname()+" "+getDisease();
	}
	

	
	
}
