package damian.hms.gui;

import java.awt.Button;
import java.awt.Component;
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import damian.hms.application.*;
import damian.hms.application.HospitalPatient;
import damian.hms.constants.HmsGuiConstants;
import damian.hms.database.HmsDatabase;
import damian.hms.enums.DISEASES;
import damian.hms.enums.*;
public class PatientsForm extends StandardForm implements ItemListener, ActionListener, FormInterface{
	Panel p = new Panel();
	ButtonGroup gender = new ButtonGroup();
	JRadioButton male = new JRadioButton(HmsGuiConstants.MALE); 
	JRadioButton female = new JRadioButton(HmsGuiConstants.FEMALE); 
	public PatientsForm() {
		super();
		p.add(new Label(HmsGuiConstants.PATIENTS));
		p.add(new Label(HmsGuiConstants.NAME));
		p.add(new TextField(20));
		p.add(new Label(HmsGuiConstants.MIDDLE_NAME));
		p.add(new TextField(20));
		p.add(new Label(HmsGuiConstants.SURNAME));
		p.add(new TextField(20));
		p.add(new Label(HmsGuiConstants.GENDER));
		gender.add(male);
		gender.add(female);
		p.add(male);
		p.add(female);
		
		
		p.add(new Label(HmsGuiConstants.WEIGHT));
		p.add(new TextField(5));
		p.add(new Label(HmsGuiConstants.HEIGHT));
		p.add(new TextField(5));
		p.add(new Label(HmsGuiConstants.DISEAES));
		p.add(new JComboBox(DISEASES.values()));
		p.add(new Label(HmsGuiConstants.ALLERGIES));
		p.add(new JComboBox(Hospital.db.getMedicinesList()));
		saveButton.addActionListener(this);
		p.add(saveButton);
		deleteButton.addActionListener(this);
		p.add(deleteButton);
		getList().addItemListener(this);
		setPanel(p);
	}
	public void updateList() {
		getList().clear();
		for(HospitalPatient i: Hospital.db.getPatients()) {
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
			if(Objects.nonNull(Hospital.db.getPatients()[index])) {
				((TextField) p.getComponent(2)).setText(Hospital.db.getPatients()[index].getFirstName());
				((TextField) p.getComponent(4)).setText(Hospital.db.getPatients()[index].getMiddleName());
				((TextField) p.getComponent(6)).setText(Hospital.db.getPatients()[index].getSurname());
				male.setSelected(Hospital.db.getPatients()[index].isGender());
				female.setSelected(!Hospital.db.getPatients()[index].isGender());
				((TextField) p.getComponent(11)).setText(String.valueOf(Hospital.db.getPatients()[index].getWeight()));
				((TextField) p.getComponent(13)).setText(String.valueOf(Hospital.db.getPatients()[index].getHeight()));
				((JComboBox) p.getComponent(15)).setSelectedItem(Hospital.db.getPatients()[index].getDisease());
				((JComboBox) p.getComponent(17)).setModel(new DefaultComboBoxModel<String>(Hospital.db.getMedicinesList()));
				((JComboBox) p.getComponent(17)).setSelectedItem(Hospital.db.getPatients()[index].getAllergy());
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
						if(nonEmptyValidator(((TextField) p.getComponent(11)).getText())) {
							if(nonEmptyValidator(((TextField) p.getComponent(13)).getText())) {
								if(numericValidator(((TextField) p.getComponent(11)).getText())) {
									if(numericValidator(((TextField) p.getComponent(13)).getText())) {
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
		};
	}
	public void createNew() {
		Hospital.db.addNewRecord(new HospitalPatient(((TextField) p.getComponent(2)).getText(), 
				((TextField) p.getComponent(4)).getText(), 
				((TextField) p.getComponent(6)).getText(), 
				true, 
				new Date(), 
				Double.valueOf(((TextField) p.getComponent(11)).getText()), 
				Double.valueOf(((TextField) p.getComponent(13)).getText()), 
				(DISEASES) ((JComboBox) p.getComponent(15)).getSelectedItem(), (String) ((JComboBox) p.getComponent(17)).getSelectedItem()));
		updateList();
	}
	public void save(int index) {
		Hospital.db.getPatients()[index].setFirstName(((TextField) p.getComponent(2)).getText());
		Hospital.db.getPatients()[index].setMiddleName(((TextField) p.getComponent(4)).getText());
		Hospital.db.getPatients()[index].setSurname(((TextField) p.getComponent(6)).getText());
		if(gender.isSelected(male.getModel())) {
			Hospital.db.getPatients()[index].setGender(true);
		}
		else if(gender.isSelected(female.getModel())) {
			Hospital.db.getPatients()[index].setGender(false);
		}
		Hospital.db.getPatients()[index].setWeight(Double.valueOf(((TextField) p.getComponent(11)).getText()));
		Hospital.db.getPatients()[index].setHeight(Double.valueOf(((TextField) p.getComponent(13)).getText()));
		Hospital.db.getPatients()[index].setDisease((DISEASES) ((JComboBox) p.getComponent(15)).getSelectedItem());
		Hospital.db.getPatients()[index].setAllergy((String) ((JComboBox) p.getComponent(17)).getSelectedItem().toString());
		updateList();
	}
	
	public void delete(int index) {
		Hospital.db.removeRecord(new HospitalPatient(), index);
		clearForm(0);
		updateList();
	}

}
