package damian.hms.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import damian.hms.application.Hospital;
import damian.hms.application.Test;
import damian.hms.constants.HmsGuiConstants;
import damian.hms.constants.HmsMessagesConstants;
import damian.hms.database.HmsDatabase;

public class StartForm extends JPanel implements ActionListener{
	Button loginButton = new Button(HmsGuiConstants.LOGIN);
	Button importButton = new Button(HmsGuiConstants.IMPORT);
	Button exportButton = new Button(HmsGuiConstants.EXPORT);
	public StartForm() {
		super();/*
		setLayout(new BorderLayout());
		setBounds(0,0,StandardWindow.WIDTH-450,StandardWindow.HEIGHT-50);
		setBackground(Color.gray);*/
		setBackground(Color.gray);
		add(new Label(Hospital.hospitalName));
		add(new Label(HmsGuiConstants.LOGIN));
		add(new TextField(20));
		add(new Label(HmsGuiConstants.PASSWORD));
		add(new TextField(20));
		loginButton.addActionListener((ActionListener) this);
		add(loginButton);
		add(new Label(HmsGuiConstants.FILE_NAME));
		add(new TextField(30));
		importButton.addActionListener((ActionListener) this);
		add(importButton);
		exportButton.addActionListener((ActionListener) this);
		add(exportButton);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(importButton)) {
			importBackup();
		}
		else if(e.getSource().equals(exportButton)) {
			exportBackup();
		}
		else if(e.getSource().equals(loginButton)) {
			login();
		}
	}
	public boolean nonEmptyValidator(String val) {
		if(Objects.isNull(val)||val.equals("")||val.trim().isEmpty()) {
			String message = HmsMessagesConstants.INVALID_EMPTY_EXCEPTION_PART1+val+HmsMessagesConstants.INVALID_EMPTY_EXCEPTION_PART2;
			JOptionPane.showMessageDialog(new JFrame(), "\"Error\"\n"+ message);
			//throw new InvalidEmptyInputException(message, new Exception());
			return false;
		}
		return true;
	}
	private void login() {
		if(nonEmptyValidator(((TextField) getComponent(2)).getText())) {
			if(nonEmptyValidator(((TextField) getComponent(4)).getText())) {
				if(Objects.equals(((TextField) getComponent(4)).getText(), HmsGuiConstants.DEMO_PASSWORD)&&Objects.equals(((TextField) getComponent(2)).getText(), HmsGuiConstants.DEMO_LOGIN)) {
					Test.enableTabbedPane();
					JOptionPane.showMessageDialog(new JFrame(), "\""+HmsMessagesConstants.LOGIN_SUCCEDED+"\"\n");
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(), "\""+HmsMessagesConstants.LOGIN_FAILED+"\"\n");
				}
			}
		}
	}
	private void exportBackup() {
		if(nonEmptyValidator(((TextField) getComponent(7)).getText())) {
			try {
				FileOutputStream fos = new FileOutputStream(((TextField) getComponent(7)).getText()+".bak");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(Hospital.db);
				System.out.println(HmsMessagesConstants.EXPORT_SUCCEDED_SHORT);
				String message = HmsMessagesConstants.EXPORT_SUCCEDED_LONG+((TextField) getComponent(7)).getText()+".bak";
				JOptionPane.showMessageDialog(new JFrame(), "\""+HmsMessagesConstants.EXPORT_SUCCEDED_SHORT+"\"\n"+ message);
				oos.close();
				fos.close();
			} catch (IOException e) {
				System.out.println(HmsMessagesConstants.EXPORT_FAILED_SHORT);
				String message = HmsMessagesConstants.EXPORT_FAILED_LONG_PART1+((TextField) getComponent(7)).getText()+".bak";
				JOptionPane.showMessageDialog(new JFrame(), "\""+HmsMessagesConstants.EXPORT_FAILED_SHORT+"\"\n"+ message);
				e.printStackTrace();
			}
		}
	}
	private void importBackup() {
		if(nonEmptyValidator(((TextField) getComponent(7)).getText())) {
		try {
			FileInputStream fileIn = new FileInputStream(((TextField) getComponent(7)).getText()+".bak");
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
	
			Object obj = objectIn.readObject();
			Hospital.db = new HmsDatabase();
			Hospital.db = (HmsDatabase) obj;
			
			System.out.println(HmsMessagesConstants.IMPORT_SUCCEDED_SHORT);
			String message = HmsMessagesConstants.IMPORT_SUCCEDED_LONG+((TextField) getComponent(7)).getText()+".bak";
			JOptionPane.showMessageDialog(new JFrame(), "\""+HmsMessagesConstants.IMPORT_SUCCEDED_SHORT+"\"\n"+ message);
			objectIn.close();
			} catch (Exception ex) {
				System.out.println(HmsMessagesConstants.IMPORT_FAILED_SHORT);
				String message = HmsMessagesConstants.IMPORT_FAILED_LONG_PART1+((TextField) getComponent(7)).getText()+".bak";
				JOptionPane.showMessageDialog(new JFrame(), "\""+HmsMessagesConstants.IMPORT_FAILED_SHORT+"\"\n"+ message);
				ex.printStackTrace();
			}
		}
	}
}
