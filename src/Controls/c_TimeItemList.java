package Controls;


import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;

import Client.MainForm;
import Common.BaseItems;
import Common.BaseItems.TimeItemList;
import XML.BaseXMLCommand;
import XML.Command;
import java.util.ArrayList;
import javax.swing.*;

public class c_TimeItemList extends JList implements MouseListener {
	
	private MainForm _mainWindow = null;
	public ArrayList<TimeItemList> _timeItemListInArray = null;
        public DefaultListModel TimeItemListModel;
	
	public c_TimeItemList(MainForm mainWindow){
            _mainWindow = mainWindow;
            JScrollPane scrollPane = new JScrollPane(this);
            this.addMouseListener(this);
            Border listPanelBorder =
                            BorderFactory.createTitledBorder("Orario");
            setBorder(listPanelBorder);
            this.setSize(200, 100);
            this.setVisible(true);
	}
	
        public void SetValue(String day){
            BaseXMLCommand<String, String, ArrayList<BaseItems.TimeItemList>> command = new BaseXMLCommand<>("GetTimeByDay", day, null);
            _mainWindow._runClient.sendQuery(Command.Encoder(command));
            BaseXMLCommand<String, String, ArrayList<BaseItems.TimeItemList>> response = Command.Decoder(_mainWindow._runClient.getResponse());

            _timeItemListInArray = (ArrayList<BaseItems.TimeItemList>)response.getValue(); 
            TimeItemListModel = new DefaultListModel() {
                ArrayList<TimeItemList> values = _timeItemListInArray; 
                public int getSize() {
                    if(values != null)
                            return values.size();
                    else
                            return 0;
                }
                public Object getElementAt(int index) {
                    if(values != null)
                            return values.get(index)._name;
                    else
                            return null;
                }

                public void setValues(ArrayList<TimeItemList> values) {
                    this.values = values;
                }
            };
            this.setModel(TimeItemListModel);
        }
        public int getTimeIdAt(int index) {
            if(_timeItemListInArray != null)
                return _timeItemListInArray.get(index).getId();
            else
                return 0;
        }
	/*
	 * Events
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
            _mainWindow.tabBooking.UpdateComponentValue();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
