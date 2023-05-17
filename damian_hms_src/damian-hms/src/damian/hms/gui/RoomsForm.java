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

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import damian.hms.application.Hospital;
import damian.hms.application.HospitalFloor;
import damian.hms.application.HospitalRoom;
import damian.hms.application.HospitalStaff;
import damian.hms.constants.HmsGuiConstants;
import damian.hms.constants.HmsMessagesConstants;
import damian.hms.enums.PROFESSIONS;
import damian.hms.enums.ROOM_TYPES;

public class RoomsForm extends StandardForm implements ItemListener, ActionListener, FormInterface{
	Panel p = new Panel();
	public RoomsForm() {
		super();
		p.add(new Label(HmsGuiConstants.ROOMS));
		p.add(new Label(HmsGuiConstants.ROOM_NUMBER));
		p.add(new TextField(5));
		p.add(new Label(HmsGuiConstants.ROOM_TYPE));
		p.add(new JComboBox(ROOM_TYPES.values()));
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
		for(HospitalRoom i: Hospital.db.getRooms()) {
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
			if(Objects.nonNull(Hospital.db.getRooms()[index])) {
				((TextField) p.getComponent(2)).setText(String.valueOf(Hospital.db.getRooms()[index].getRoomNumber()));
				((JComboBox) p.getComponent(4)).setSelectedItem(Hospital.db.getRooms()[index].getRoomType());
			}
		}else {
			clearForm(0);
		}
		
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(saveButton))
		{
			if(nonEmptyValidator(((TextField) p.getComponent(2)).getText())) {
				if(numericValidator(((TextField) p.getComponent(2)).getText())) {
					if(Objects.equals(getList().getSelectedIndex(), getList().getItemCount()-1)) {
						createNew();
					}
					else {
						save(getList().getSelectedIndex());
					}
				}
			}
		}
		else if(e.getSource().equals(deleteButton)) {
			if(Objects.equals(getList().getSelectedIndex(), getList().getItemCount()-1)) {
				clearForm(0);
				updateList();
			}
			else {
				if(Hospital.db.getRoom(getList().getSelectedIndex()).isDeletable()) {
					delete(getList().getSelectedIndex());
				}
				else {
					String message = HmsMessagesConstants.NONDELETABLE_RECORD_EXCEPTION;
					JOptionPane.showMessageDialog(new JFrame(), "\"Deleting error\"\n"+ message);
				}
			}
		}
	}
	public void createNew() {
		Hospital.db.addNewRecord(new HospitalRoom(Integer.valueOf(((TextField) p.getComponent(2)).getText()), 
				(ROOM_TYPES)((JComboBox) p.getComponent(4)).getSelectedItem()));
		updateList();
	}
	public void save(int index) {
		Hospital.db.getRooms()[index].setRoomNumber(Integer.valueOf(((TextField) p.getComponent(2)).getText()));
		Hospital.db.getRooms()[index].setRoomType((ROOM_TYPES)((JComboBox) p.getComponent(4)).getSelectedItem());
		updateList();
	}
	
	public void delete(int index, int floor) {
		Hospital.db.removeRecord(new HospitalRoom(), index);
		clearForm(0);
		updateList();
	}
	@Override
	public void delete(int index) {
		Hospital.db.removeRecord(new HospitalRoom(), index);
		clearForm(0);
		updateList();
		
	}
}
