package damian.hms.application;

import java.io.Serializable;
import java.util.Date;

public class Human implements Serializable{
	private String firstName;
	private String middleName;
	private String surname;
	private boolean gender;
	private Date birthday;
	public Human(String firstName, String middleName, String surname, boolean gender, Date birthday) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.surname = surname;
		this.gender = gender;
		this.birthday = birthday;
	}
	public Human(String firstName, String middleName, String surname, boolean gender) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.surname = surname;
		this.gender = gender;
	}
	public Human(String firstName, String surname, boolean gender) {
		this.firstName = firstName;
		this.surname = surname;
		this.gender = gender;
	}
	public Human() {
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Override
	public String toString() {
		return "Human [getFirstName()=" + getFirstName() + ", getMiddleName()=" + getMiddleName() + ", getSurname()="
				+ getSurname() + ", isGender()=" + isGender() + ", getBirthday()=" + getBirthday() + "]";
	}

}
