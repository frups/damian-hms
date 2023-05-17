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

import javax.print.DocFlavor.SERVICE_FORMATTED;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import damian.hms.application.Hospital;
import damian.hms.application.HospitalPatient;
import damian.hms.application.HospitalService;
import damian.hms.constants.HmsGuiConstants;
import damian.hms.enums.DISEASES;
import damian.hms.enums.SERVICE_TYPES;

public class ServicesForm extends StandardForm implements ItemListener, ActionListener, FormInterface{
	Panel p = new Panel();
	public ServicesForm() {
		super();
		p.add(new Label(HmsGuiConstants.SERVICES));
		p.add(new Label(HmsGuiConstants.NAME));
		p.add(new TextField(20));
		p.add(new Label(HmsGuiConstants.DURATION));
		p.add(new TextField(5));
		p.add(new Label(HmsGuiConstants.SERVICE_TYPE));
		p.add(new JComboBox(SERVICE_TYPES.values()));
		p.add(new Label(""));
		p.add(new JCheckBox(HmsGuiConstants.IS_ANESTHESIA));
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
		for(HospitalService i: Hospital.db.getServices()) {
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
			if(Objects.nonNull(Hospital.db.getServices()[index])) {
				((TextField) p.getComponent(2)).setText(Hospital.db.getServices()[index].getServiceName());
				((TextField) p.getComponent(4)).setText(String.valueOf(Hospital.db.getServices()[index].getDuration()));
				((JComboBox) p.getComponent(6)).setSelectedItem(Hospital.db.getServices()[index].getServiceType());
				((JCheckBox) p.getComponent(8)).setSelected(Hospital.db.getServices()[index].getIsAnethesia());
			}
		}else {
			clearForm(0);
		}
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(saveButton))
		{
			if(nonEmptyValidator(((TextField) p.getComponent(2)).getText())) {
				if(nonEmptyValidator(((TextField) p.getComponent(4)).getText())) {
					if(numericValidator(((TextField) p.getComponent(4)).getText())) {
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
		Hospital.db.addNewRecord(new HospitalService(((TextField) p.getComponent(2)).getText(), 
				Double.parseDouble(((TextField) p.getComponent(4)).getText()), 
				(SERVICE_TYPES) ((JComboBox) p.getComponent(6)).getSelectedItem(), 
				((JCheckBox) p.getComponent(8)).isSelected()));
		updateList();
	}
	public void save(int index) {
		Hospital.db.getServices()[index].setServiceName(((TextField) p.getComponent(2)).getText());
		Hospital.db.getServices()[index].setDuration(Double.parseDouble(((TextField) p.getComponent(4)).getText()));
		Hospital.db.getServices()[index].setServiceType((SERVICE_TYPES) ((JComboBox) p.getComponent(6)).getSelectedItem());
		Hospital.db.getServices()[index].setIsAnethesia(((AbstractButton) p.getComponent(8)).isSelected());
		updateList();
	}
	
	public void delete(int index) {
		Hospital.db.removeRecord(new HospitalService(), index);
		clearForm(0);
		updateList();
	}

}
