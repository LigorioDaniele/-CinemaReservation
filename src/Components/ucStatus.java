package Components;


import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Client.MainForm;

public class ucStatus extends JPanel {
	public MainForm _mainWindow = null;
	JTextArea txtStatus;
	
	public ucStatus(MainForm mainWindow){
		_mainWindow = mainWindow;
		txtStatus = new JTextArea(19,67);	
		txtStatus.setBackground(Color.WHITE);
		txtStatus.setEditable(false);
		txtStatus.setLineWrap(true);
		JScrollPane scrollPane = new JScrollPane(txtStatus);
		scrollPane.setHorizontalScrollBar(null);
		scrollPane.setVerticalScrollBar(new JScrollBar());
		
		this.add(scrollPane);
	}
	public void appendToStatus(String text) {
		txtStatus.setText(text + "\r\n" + txtStatus.getText());
	}
}
