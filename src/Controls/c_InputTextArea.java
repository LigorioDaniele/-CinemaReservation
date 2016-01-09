package Controls;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import Client.MainForm;

public class c_InputTextArea extends JTextField implements KeyListener{

	private MainForm _mainWindow = null;
	public c_InputTextArea(MainForm mainWindow){
		_mainWindow = mainWindow;
		this.addKeyListener(this);
		
	}
	/*
	 * Events (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			//_mainWindow.tabStatus.appendToStatus(this.getText());
//			if(_mainWindow.IRCconn != null){
//				
//				_mainWindow.IRCconn.sendMessage("AR|Anime|010", "xdcc send #1");
//				this.setText("");
//				
//				//AR|Anime|007
//			}
			
		}
		
	}
}
