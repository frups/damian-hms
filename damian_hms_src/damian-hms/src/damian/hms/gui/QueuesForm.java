package damian.hms.gui;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import java.util.Objects;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import damian.hms.application.Hospital;
import damian.hms.application.HospitalRoom;
import damian.hms.application.HospitalService;
import damian.hms.application.HospitalServiceQueue;
import damian.hms.application.HospitalStaff;
import damian.hms.constants.HmsGuiConstants;
import damian.hms.enums.PROFESSIONS;

public class QueuesForm extends StandardForm implements ItemListener, ActionListener, FormInterface{
	Panel p = new Panel();
	public QueuesForm() {
		super();
		p.add(new Label(HmsGuiConstants.QUEUES));
		p.add(new Label(HmsGuiConstants.NAME));
		p.add(new TextField(20));
		p.add(new Label(HmsGuiConstants.SERVICE));
		p.add(new JComboBox(Hospital.db.getServiceList()));
		p.add(new Label(HmsGuiConstants.OPERATOR));
		p.add(new JComboBox(Hospital.db.getStaffList()));
		p.add(new Label(HmsGuiConstants.ROOM_NUMBER));
		p.add(new JComboBox(Hospital.db.getRoomsList()));
		saveButton.addActionListener(this);
		p.add(saveButton);
		deleteButton.addActionListener(this);
		p.add(deleteButton);
		getList().addItemListener(this);
		getList().select(0);
		setPanel(p);
	}
	public void updateList() {
		getList().clear();
		for(HospitalServiceQueue i: Hospital.db.getQueues()) {
			if(Objects.nonNull(i)) {
				getList().add(i.toString());
			}
			if(Objects.isNull(i)) {
				getList().add(HmsGuiConstants.CREATE_NEW);
			}
		}
		getList().select(0);
	}
	public void itemStateChanged(ItemEvent arg0) {
		if(!Objects.equals(getList().getSelectedIndex(), getList().getItemCount()-1))
		{
			this.deleteButton.setEnabled(true);
			refillForm(getList().getSelectedIndex());
		}
		else
		{
			clearForm(0);
			this.deleteButton.setEnabled(false);
		}
		repaint();
	}
	public void refillForm(int index) {
		if(index >= 0) {
			setAllComponentsEnable(true);
			if(Objects.nonNull(Hospital.db.getQueues()[index])) {
				((TextField) p.getComponent(2)).setText(Hospital.db.getQueues()[index].getQueueName());
				((JComboBox) p.getComponent(4)).setModel(new DefaultComboBoxModel<String>(Hospital.db.getServiceList()));
				((JComboBox) p.getComponent(4)).setSelectedItem(Hospital.db.getQueues()[index].getService());
				((JComboBox) p.getComponent(6)).setModel(new DefaultComboBoxModel<String>(Hospital.db.getStaffList()));
				((JComboBox) p.getComponent(6)).setSelectedItem(Hospital.db.getQueues()[index].getOperator());
				((JComboBox) p.getComponent(8)).setModel(new DefaultComboBoxModel<String>(Hospital.db.getRoomsList()));
				((JComboBox) p.getComponent(8)).setSelectedItem(Hospital.db.getQueues()[index].getServiceRoom());
			}
		}else {
			clearForm(0);
		}
		
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(saveButton))
		{
			if(nonEmptyValidator(((TextField) p.getComponent(2)).getText())) {
				if(Objects.equals(getList().getSelectedIndex(), getList().getItemCount()-1)) {
					createNew();
				}
				else {
					save(getList().getSelectedIndex());
				}
			}
		}
		else if(e.getSource().equals(deleteButton)) {
			if(Objects.equals(getList().getSelectedIndex(), getList().getItemCount()-1)) {
				clearForm(0);
				updateList();
			}
			else {
				delete(getList().getSelectedIndex());
			}
		}
	}
	public void createNew() {
		Hospital.db.addNewRecord(new HospitalServiceQueue(((TextField) p.getComponent(2)).getText(),
				(String) ((JComboBox) p.getComponent(4)).getSelectedItem(), 
				(String) ((JComboBox) p.getComponent(6)).getSelectedItem(), 
				(String) ((JComboBox) p.getComponent(8)).getSelectedItem()));
		updateList();
	}
	public void save(int index) {
		Hospital.db.getQueues()[index].setQueueName(((TextField) p.getComponent(2)).getText());
		Hospital.db.getQueues()[index].setService((String)((JComboBox) p.getComponent(4)).getSelectedItem().toString());
		Hospital.db.getQueues()[index].setOperator((String)((JComboBox) p.getComponent(6)).getSelectedItem().toString());
		Hospital.db.getQueues()[index].setServiceRoom((String)((JComboBox) p.getComponent(8)).getSelectedItem().toString());
		updateList();
	}
	
	public void delete(int index) {
		Hospital.db.removeRecord(new HospitalServiceQueue(), index);
		clearForm(0);
		updateList();
	}

}
