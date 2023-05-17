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

import damian.hms.application.Hospital;
import damian.hms.application.HospitalEquipmentMedicament;
import damian.hms.constants.HmsGuiConstants;
import damian.hms.enums.MED_TYPES;
import damian.hms.enums.PROFESSIONS;

public class PharmacyForm extends StandardForm implements ItemListener, ActionListener, FormInterface{
	Panel p = new Panel();
	public PharmacyForm() {
		super();
		p.add(new Label(HmsGuiConstants.PHARMACY));
		p.add(new Label(HmsGuiConstants.NAME));
		p.add(new TextField(20));
		p.add(new Label(HmsGuiConstants.MED_TYPE));
		p.add(new JComboBox(MED_TYPES.values()));
		p.add(new Label(HmsGuiConstants.QUANTITY));
		p.add(new TextField(5));
		p.add(new Label(HmsGuiConstants.DOSE));
		p.add(new TextField(5));
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
		for(HospitalEquipmentMedicament i: Hospital.db.getMedicines()) {
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
			if(Objects.nonNull(Hospital.db.getMedicines()[index])) {
				((TextField) p.getComponent(2)).setText(Hospital.db.getMedicines()[index].getEqName());
				((JComboBox) p.getComponent(4)).setSelectedItem(Hospital.db.getMedicines()[index].getMedicamentType());
				((TextField) p.getComponent(6)).setText(String.valueOf(Hospital.db.getMedicines()[index].getQuantity()));
				((TextField) p.getComponent(8)).setText(String.valueOf(Hospital.db.getMedicines()[index].getDose()));
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
						if(numericValidator(((TextField) p.getComponent(6)).getText())) {
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
		Hospital.db.addNewRecord(new HospitalEquipmentMedicament(((TextField) p.getComponent(2)).getText(),
				(MED_TYPES) ((JComboBox) p.getComponent(4)).getSelectedItem(),
				Double.valueOf(((TextField) p.getComponent(6)).getText()),
				Integer.valueOf(((TextField) p.getComponent(8)).getText())));
		updateList();
	}
	public void save(int index) {
		Hospital.db.getMedicines()[index].setEqName(((TextField) p.getComponent(2)).getText());
		Hospital.db.getMedicines()[index].setMedicamentType((MED_TYPES)((JComboBox) p.getComponent(4)).getSelectedItem());
		Hospital.db.getMedicines()[index].setQuantity(Double.valueOf(((TextField) p.getComponent(6)).getText()));
		Hospital.db.getMedicines()[index].setDose(Integer.valueOf(((TextField) p.getComponent(8)).getText()));
		updateList();
	}
	
	public void delete(int index) {
		Hospital.db.removeRecord(new HospitalEquipmentMedicament(), index);
		clearForm(0);
		updateList();
	}

}
