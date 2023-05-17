package damian.hms.application;

import java.io.Serializable;

import damian.hms.enums.SERVICE_TYPES;

public class HospitalService implements Serializable{
	private String serviceName;
	private double duration;
	private SERVICE_TYPES serviceType;
	private boolean isAnethesia;
	private HospitalServiceQueue[] servicequeue = new HospitalServiceQueue[1];
	
	public HospitalService(String serviceName, double duration, SERVICE_TYPES serviceType, boolean isAnethesia) {
		this.serviceName = serviceName;
		this.duration = duration;
		this.serviceType = serviceType;
		this.isAnethesia = isAnethesia;
	}

	public HospitalService() {
		// TODO Auto-generated constructor stub
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public SERVICE_TYPES getServiceType() {
		return serviceType;
	}

	public void setServiceType(SERVICE_TYPES serviceType) {
		this.serviceType = serviceType;
	}

	public boolean getIsAnethesia() {
		return isAnethesia;
	}

	public void setIsAnethesia(boolean isAnethesia) {
		this.isAnethesia = isAnethesia;
	}

	public HospitalServiceQueue[] getServicequeue() {
		return servicequeue;
	}

	public void setServicequeue(HospitalServiceQueue[] servicequeue) {
		this.servicequeue = servicequeue;
	}

	@Override
	public String toString() {
		return getServiceType()+" "+getServiceName() + ", duration: " + getDuration()
				+ "min" ;
	}
	
	
	
}
