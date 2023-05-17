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

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import damian.hms.application.Hospital;
import damian.hms.application.HospitalStaff;
import damian.hms.constants.HmsGuiConstants;
import damian.hms.enums.DISEASES;
import damian.hms.enums.PROFESSIONS;

public class StaffForm extends StandardForm implements ItemListener, ActionListener, FormInterface{
	Panel p = new Panel();
	public StaffForm() {
		super();
		p.add(new Label(HmsGuiConstants.STAFF));
		p.add(new Label(HmsGuiConstants.NAME));
		p.add(new TextField(20));
		p.add(new Label(HmsGuiConstants.MIDDLE_NAME));
		p.add(new TextField(20));
		p.add(new Label(HmsGuiConstants.SURNAME));
		p.add(new TextField(20));
		p.add(new Label(HmsGuiConstants.SALARY));
		p.add(new TextField(5));
		p.add(new Label(HmsGuiConstants.PROFFESION));
		p.add(new JComboBox(PROFESSIONS.values()));
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
		for(HospitalStaff i: Hospital.db.getStaff()) {
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
			if(Objects.nonNull(Hospital.db.getStaff()[index])) {
				((TextField) p.getComponent(2)).setText(Hospital.db.getStaff()[index].getFirstName());
				((TextField) p.getComponent(4)).setText(Hospital.db.getStaff()[index].getMiddleName());
				((TextField) p.getComponent(6)).setText(Hospital.db.getStaff()[index].getSurname());
				((TextField) p.getComponent(8)).setText(String.valueOf(Hospital.db.getStaff()[index].getSalary()));
				((JComboBox) p.getComponent(10)).setSelectedItem(Hospital.db.getStaff()[index].getProfession());
			}
		}else {
			clearForm(0);
		}
		
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(saveButton))
		{
			if(nonEmptyValidator(((TextField) p.getComponent(2)).getText())) {
				if(nonEmptyValidator(((TextField) p.getComponent(6)).getText())) {
					if(nonEmptyValidator(((TextField) p.getComponent(8)).getText())) {
							if(numericValidator(((TextField) p.getComponent(8)).getText())) {
								if(Objects.equals(getList().getSelectedIndex(), getList().getItemCount()-1)) {
									createNew();
								}
								else {
									save(getList().getSelectedIndex());
								}
							}
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
				delete(getList().getSelectedIndex());
			}
		}
	}
	public void createNew() {
		Hospital.db.addNewRecord(new HospitalStaff(((TextField) p.getComponent(2)).getText(), 
				((TextField) p.getComponent(4)).getText(), 
				((TextField) p.getComponent(6)).getText(), 
				true, 
				new Date(),Double.valueOf(((TextField) p.getComponent(8)).getText()),
				(PROFESSIONS) ((JComboBox) p.getComponent(10)).getSelectedItem()));
		updateList();
	}
	public void save(int index) {
		Hospital.db.getStaff()[index].setFirstName(((TextField) p.getComponent(2)).getText());
		Hospital.db.getStaff()[index].setMiddleName(((TextField) p.getComponent(4)).getText());
		Hospital.db.getStaff()[index].setSurname(((TextField) p.getComponent(6)).getText());
		Hospital.db.getStaff()[index].setSalary(Double.valueOf(((TextField) p.getComponent(8)).getText()));
		Hospital.db.getStaff()[index].setProfession((PROFESSIONS)((JComboBox) p.getComponent(10)).getSelectedItem());
		updateList();
	}
	
	public void delete(int index) {
		Hospital.db.removeRecord(new HospitalStaff(), index);
		clearForm(0);
		updateList();
	}

}
