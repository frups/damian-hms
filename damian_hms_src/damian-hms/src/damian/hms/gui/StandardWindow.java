package damian.hms.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import damian.hms.application.*;

public class StandardWindow extends JFrame{
	static int WIDTH = 900;
	static int HEIGHT = 450;
	public JTabbedPane tabbedPane = new JTabbedPane();
	public StandardWindow(String title) {
		super(title);
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    getContentPane().add(tabbedPane, BorderLayout.CENTER);
	     
	}
	public void addPannel(String description, JPanel panel) {
		tabbedPane.addTab(description, null, panel);
	    tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
	}
}
