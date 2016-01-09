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
import Common.BaseItems.FilmsItemList;
import XML.BaseXMLCommand;
import XML.Command;
import java.util.ArrayList;
import javax.swing.*;

public class c_FilmsItemList extends JList implements MouseListener {
	
	private MainForm _mainWindow = null;
	public ArrayList<FilmsItemList> _filmItemListInArray = null;
        public DefaultListModel FilmItemListModel;
	
	public c_FilmsItemList(MainForm mainWindow){
		_mainWindow = mainWindow;
		JScrollPane scrollPane = new JScrollPane(this);
		this.addMouseListener(this);
		Border listPanelBorder =
				BorderFactory.createTitledBorder("Film");
		setBorder(listPanelBorder);
		this.setSize(400, 100);
		this.setVisible(true);
	}
	
        public int getFilmIdAt(int index) {
            if(_filmItemListInArray != null)
                return _filmItemListInArray.get(index).getId();
            else
                return 0;
        }
        public void SetValue(String day){
            BaseXMLCommand<String, String, ArrayList<FilmsItemList>> command = new BaseXMLCommand<>("GetFilmByDay", day, null);
            _mainWindow._runClient.sendQuery(Command.Encoder(command));
            BaseXMLCommand<String, String, ArrayList<FilmsItemList>> response = Command.Decoder(_mainWindow._runClient.getResponse());

            _filmItemListInArray = (ArrayList<FilmsItemList>)response.getValue(); 
            FilmItemListModel = new DefaultListModel() {
                ArrayList<BaseItems.FilmsItemList> values = _filmItemListInArray; 
                public int getSize() {
                    if(values != null)
                            return values.size();
                    else
                            return 0;
                }
                public Object getElementAt(int index) {
                    if(values != null)
                            return values.get(index).getTitle();
                    else
                            return null;
                }

            };
            this.setModel(FilmItemListModel);
            
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

	/**
	 * ListCellRenderer per colorare la JList
	 */
	public class JListColored extends JLabel implements ListCellRenderer {

	    public JListColored() {
	        setOpaque(true);
	    }

	    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
	        // Assumes the stuff in the list has a pretty toString
	        setText(value.toString());

	        // based on the index you set the color.  This produces the every other effect.
	        /*
	        if (index % 2 == 0) setBackground(Color.RED);
	        else setBackground(Color.BLUE);
	        */
	        /*
	        switch(_timeItemListInArray[index]._status){
	        	case InProgress:
	        		setForeground(Color.GREEN);
	        		break;
	        	case Stopped:
	        		setForeground(Color.RED);
	        		break;
	        }
	        */
	        if(isSelected)
	        	setFont(new Font("Courier New", Font.BOLD, 12));
	        else
	        	setFont(new Font("Courier New", Font.PLAIN, 12));
	        
	        return this;
	    }
	}
}
