package damian.hms.application;

import java.io.Serializable;
import java.util.Arrays;

public class HospitalServiceQueue implements Serializable{
	private String queueName;
	private String service;
	private String operator;
	private String serviceRoom;
	private HospitalPatient patient;
	public HospitalServiceQueue(String queueName, String service, String operator,
			String serviceRoom) {
		super();
		this.queueName = queueName;
		this.service = service;
		this.operator = operator;
		this.serviceRoom = serviceRoom;
	}
	public HospitalServiceQueue() {
		// TODO Auto-generated constructor stub
	}
	public String getQueueName() {
		return queueName;
	}
	public String getService() {
		return service;
	}
	public String getOperator() {
		return operator;
	}
	public String getServiceRoom() {
		return serviceRoom;
	}
	public HospitalPatient getPatient() {
		return patient;
	}
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
	public void setService(String service) {
		this.service = service;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public void setServiceRoom(String serviceRoom) {
		this.serviceRoom = serviceRoom;
	}
	public void setPatient(HospitalPatient patient) {
		this.patient = patient;
	}
	@Override
	public String toString() {
		return getQueueName() + ", " + getService() + ", " + getOperator() + ", room: " + getServiceRoom();
	}
	
	
}
