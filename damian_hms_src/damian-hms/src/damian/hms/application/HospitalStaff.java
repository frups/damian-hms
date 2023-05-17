package damian.hms.application;

import java.io.Serializable;
import java.util.Date;

import damian.hms.enums.PROFESSIONS;

public class HospitalStaff extends Human implements Serializable{
	double salary;
	PROFESSIONS profession;
	public HospitalStaff(String firstName, String middleName, String surname, boolean gender, Date birthday,
			double salary, PROFESSIONS profession) {
		super(firstName, middleName, surname, gender, birthday);
		this.salary = salary;
		this.profession = profession;
	}
	public HospitalStaff(String firstName, String middleName, String surname, boolean gender, double salary, PROFESSIONS profession) {
		super(firstName, middleName, surname, gender);
		this.salary = salary;
		this.profession = profession;
	}
	public HospitalStaff(String firstName, String surname, boolean gender, double salary, PROFESSIONS profession) {
		super(firstName, surname, gender);
		this.salary = salary;
		this.profession = profession;
	}
	public HospitalStaff(String firstName, String middleName, String surname, boolean gender, Date birthday) {
		super(firstName, middleName, surname, gender, birthday);
		// TODO Auto-generated constructor stub
	}
	public HospitalStaff(String firstName, String middleName, String surname, boolean gender) {
		super(firstName, middleName, surname, gender);
		// TODO Auto-generated constructor stub
	}
	public HospitalStaff() {
		// TODO Auto-generated constructor stub
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public PROFESSIONS getProfession() {
		return profession;
	}
	public void setProfession(PROFESSIONS profession) {
		this.profession = profession;
	}
	@Override
	public String toString() {
		return getProfession()+" "+super.getSurname();
	}
	
}
