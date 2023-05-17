package damian.hms.gui;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import damian.hms.application.Hospital;
import damian.hms.constants.HmsGuiConstants;
import damian.hms.constants.HmsMessagesConstants;
import damian.hms.database.HmsDatabase;
import damian.hms.exceptions.InvalidEmptyInputException;
import damian.hms.exceptions.InvalidNumericInputException;

public class StandardForm extends JPanel{
	private List list = new List();
	Panel panel = new Panel();

	Button saveButton = new Button(HmsGuiConstants.SAVE);
	Button deleteButton = new Button(HmsGuiConstants.DELETE);
	public StandardForm() {
		super();
		setBackground(Color.gray);
		setLayout(new GridLayout(1, 2));
		add(getList());
		getList().getSelectedItem();
		deleteButton.setEnabled(false);
		//add(panel);
	}
	public void setPanel(Panel p) {
		remove(panel);
		panel=p;
		add(panel);
	}
	public void addTolist(String s) {
		remove(getList());
		getList().add(s);
		add(getList());
	}
	public void clearForm(int index) {
		Component[] comp = panel.getComponents();
		for(Component i: comp) {
			if(i instanceof TextField) {
				((TextField) i).setText("");
			}
		}
	}
	public void setAllComponentsEnable(boolean s) {
		Component[] comp = panel.getComponents();
		for(Component i: comp) {
			i.setEnabled(s);
		}
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public boolean numericValidator(String val) {
		if(!(Objects.isNull(val)||val.equals("")||val.trim().isEmpty())) {
			try {
				Double.valueOf(val);
			}
			catch(java.lang.NumberFormatException ex) {
				String message = HmsMessagesConstants.INVALID_NUMERIC_EXCEPTION_PART1+val+HmsMessagesConstants.INVALID_NUMERIC_EXCEPTION_PART2;
				JOptionPane.showMessageDialog(new JFrame(), "\"Saving error\"\n"+ message);
				throw new InvalidNumericInputException(message, ex);
			}
		}
		return true;
	}
	public boolean nonEmptyValidator(String val) {
		if(Objects.isNull(val)||val.equals("")||val.trim().isEmpty()) {
			String message = HmsMessagesConstants.INVALID_EMPTY_EXCEPTION_PART1+val+HmsMessagesConstants.INVALID_EMPTY_EXCEPTION_PART2;
			JOptionPane.showMessageDialog(new JFrame(), "\"Saving error\"\n"+ message);
			//throw new InvalidEmptyInputException(message, new Exception());
			return false;
		}
		return true;
	}
}
