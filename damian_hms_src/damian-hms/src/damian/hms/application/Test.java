package damian.hms.application;


import java.util.Date;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import damian.hms.constants.HmsGuiConstants;
import damian.hms.database.HmsDatabase;
import damian.hms.enums.DISEASES;
import damian.hms.enums.MED_TYPES;
import damian.hms.enums.PROFESSIONS;
import damian.hms.enums.ROOM_TYPES;
import damian.hms.enums.SERVICE_TYPES;
import damian.hms.gui.*;

public class Test {
	static StandardWindow w = new StandardWindow(HmsGuiConstants.APP_TITLE);
	static RoomsForm roomsForn = new RoomsForm();
	static PharmacyForm pharmacyForm = new PharmacyForm();
	static PatientsForm patientsForm = new PatientsForm();
	static StaffForm staffForm = new StaffForm();
	static ServicesForm servicesForm = new ServicesForm();
	static QueuesForm queuesForm = new QueuesForm();
	static StartForm startForm = new StartForm();
	public static void main(String[] args) {
		Hospital.db.addNewRecord(new HospitalEquipmentMedicament("Paracetamol", MED_TYPES.TABLET, 23, 500));
		Hospital.db.addNewRecord(new HospitalEquipmentMedicament("Morphine", MED_TYPES.INJECTION, 10, 5));
		Hospital.db.addNewRecord(new HospitalPatient("Pawel", "Uniok", "Olbrych", false, 60, 150, DISEASES.PSYCHO, Hospital.db.getMedicines()[0].getEqName()));
		Hospital.db.addNewRecord(new HospitalService("Latarjet procedure", 180, SERVICE_TYPES.SURGERY, true));
		Hospital.db.addNewRecord(new HospitalStaff("Gregory","House",true,4000,PROFESSIONS.DOCTOR));
		Hospital.db.addNewRecord(new HospitalRoom(210, ROOM_TYPES.OPERATING_THEATER));
		Hospital.db.addNewRecord(new HospitalServiceQueue("1", Hospital.db.getServiceList()[0], Hospital.db.getStaffList()[0], String.valueOf(Hospital.db.getRooms()[1].getRoomNumber())));
		//StandardWindow w = new StandardWindow("TestForm");
		
		w.addPannel(HmsGuiConstants.HOME, startForm);
		w.addPannel(HmsGuiConstants.ROOMS, roomsForn);
		w.addPannel(HmsGuiConstants.PHARMACY, pharmacyForm);
		w.addPannel(HmsGuiConstants.PATIENTS, patientsForm);
		w.addPannel(HmsGuiConstants.STAFF, staffForm);
		w.addPannel(HmsGuiConstants.SERVICES, servicesForm);
		w.addPannel(HmsGuiConstants.QUEUES, queuesForm);
		w.tabbedPane.addChangeListener(new ChangeListener() {
	        public void stateChanged(ChangeEvent e) {
	        	patientsForm.refillForm(patientsForm.getList().getSelectedIndex());
	        	staffForm.refillForm(staffForm.getList().getSelectedIndex());
	        	pharmacyForm.refillForm(pharmacyForm.getList().getSelectedIndex());
	        	roomsForn.refillForm(roomsForn.getList().getSelectedIndex());
	        	servicesForm.refillForm(servicesForm.getList().getSelectedIndex());
	        	queuesForm.refillForm(queuesForm.getList().getSelectedIndex());
	        	patientsForm.updateList();
	    		staffForm.updateList();
	    		pharmacyForm.updateList();
	    		roomsForn.updateList();
	    		servicesForm.updateList();
	    		queuesForm.updateList();
	        }
	    });
		w.tabbedPane.setEnabled(false);
		patientsForm.updateList();
		staffForm.updateList();
		pharmacyForm.updateList();
		roomsForn.updateList();
		servicesForm.updateList();
		queuesForm.updateList();
	}
	public static void enableTabbedPane() {
		w.tabbedPane.setEnabled(true);
	}
}
