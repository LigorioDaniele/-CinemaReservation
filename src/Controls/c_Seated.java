package Controls;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Client.MainForm;
import Common.BaseItems;
import Common.BaseItems.PalimpsestItemList;
import Common.BaseItems.SeatedItemList;
import XML.BaseXMLCommand;
import XML.Command;
import java.util.ArrayList;
import java.util.Iterator;

public class c_Seated extends JPanel {
    MainForm _mainWindow;
    ArrayList<SeatedItemList> _seatedItemListInArray;
    int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
    int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
    JLabel l = new JLabel("", JLabel.CENTER);
    String day = "";
    JPanel d;
    JPanel p1;
    JButton[][] button = new JButton[11][8];
     JLabel lblTitle;

    public c_Seated(MainForm mainWindow) { //JFrame parent
        _mainWindow = mainWindow;
        d = new JPanel();
        d.setLayout(new BorderLayout());
        p1 = new JPanel(new GridLayout(8, 11));
        p1.setPreferredSize(new Dimension(320, 180));
        lblTitle = new JLabel("Sala: ND");
        lblTitle.setPreferredSize(new Dimension(230, 30));
        d.add(lblTitle, BorderLayout.NORTH);
        LoadSeatedList();
    }
    public void LoadSeatedList(){
        try {
        if(_mainWindow.tabBooking != null) {
            p1.removeAll();
            button = new JButton[11][8];
            String[] rowLetter = {"A", "B", "C", "D", "E", "F", "G", "H" };
            String parameters = _mainWindow._currentUserItem.getId() 
                                + "###" + _mainWindow.tabBooking.filmsItemList.getFilmIdAt(_mainWindow.tabBooking.filmsItemList.getSelectedIndex())
                                + "###" + _mainWindow.tabBooking.timeItemList.getTimeIdAt(_mainWindow.tabBooking.timeItemList.getSelectedIndex())
                                + "###" + _mainWindow.tabBooking.datePicker.getPickedDate()
                                + "###" + _mainWindow.tabBooking.seated.GetHallId();
            BaseXMLCommand<String, String, ArrayList<BaseItems.SeatedItemList>> command = new BaseXMLCommand<>("GetSeatedList",  parameters, null);
            _mainWindow._runClient.sendQuery(Command.Encoder(command));
            BaseXMLCommand<String, String, ArrayList<BaseItems.SeatedItemList>> response = Command.Decoder(_mainWindow._runClient.getResponse());
            _seatedItemListInArray = (ArrayList<BaseItems.SeatedItemList>)response.getValue(); 

            for(int y = 0; y< 8; y++){
                for (int x = 0; x < 11; x++) {
                    String buttonTitle = "";
                    buttonTitle = rowLetter[y] + "" + (x+1);
                    button[x][y] = new JButton(buttonTitle);
                    button[x][y].setFocusPainted(false);
                    button[x][y].setBackground(Color.WHITE);
                    SeatedItemList currentSeated = IsSeatedBusy(y, x);
                    if(currentSeated != null){ //Se il posto è occupato, ritorna l'oggetto prenotazione
                        if(currentSeated.getUser_id() == _mainWindow._currentUserItem.getId())
                            button[x][y].setBackground(Color.GREEN);
                        else
                            button[x][y].setBackground(Color.RED);
                    }
                    button[x][y].setName("button_" + y + "_" + x);
                    button[x][y].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {
                            
                            JButton btnSource = ((JButton) ae.getSource());
                            if(btnSource.getBackground() == Color.WHITE){
                                String[] buttonArgs = btnSource.getName().split("_");
                                int current_x = Integer.parseInt(buttonArgs[1]);
                                int current_y = Integer.parseInt(buttonArgs[2]);
                                SeatedItemList seatedItemList = new SeatedItemList(_mainWindow._currentUserItem.getId(), GetHallId(), _mainWindow.tabBooking.timeItemList.getTimeIdAt(_mainWindow.tabBooking.timeItemList.getSelectedIndex()) , current_x, current_y, _mainWindow.tabBooking.filmsItemList.getFilmIdAt(_mainWindow.tabBooking.filmsItemList.getSelectedIndex()), _mainWindow.tabBooking.datePicker.getPickedDate());
                                BaseXMLCommand<String, String, BaseItems.SeatedItemList> command = new BaseXMLCommand<>("UpdateSeatedByUser", null, seatedItemList);
                                _mainWindow._runClient.sendQuery(Command.Encoder(command));
                                BaseXMLCommand<String, String, String> response = Command.Decoder(_mainWindow._runClient.getResponse());
                                //_seatedItemListInArray = (ArrayList<BaseItems.SeatedItemList>)response.getValue(); 
                                if(response.getValue().toString().equals("true"))
                                    btnSource.setBackground(Color.GREEN);
                                
                                _mainWindow.tabBooking.UpdateComponentValue();
                            }
                            else if(btnSource.getBackground() == Color.GREEN){
                                String[] buttonArgs = btnSource.getName().split("_");
                                int current_x = Integer.parseInt(buttonArgs[1]);
                                int current_y = Integer.parseInt(buttonArgs[2]);
                                SeatedItemList seatedItemList = new SeatedItemList(_mainWindow._currentUserItem.getId(), GetHallId(), _mainWindow.tabBooking.timeItemList.getTimeIdAt(_mainWindow.tabBooking.timeItemList.getSelectedIndex()) , current_x, current_y, _mainWindow.tabBooking.filmsItemList.getFilmIdAt(_mainWindow.tabBooking.filmsItemList.getSelectedIndex()), _mainWindow.tabBooking.datePicker.getPickedDate());
                                BaseXMLCommand<String, String, BaseItems.SeatedItemList> command = new BaseXMLCommand<>("DeleteSeatedByUser", null, seatedItemList);
                                _mainWindow._runClient.sendQuery(Command.Encoder(command));
                                BaseXMLCommand<String, String, String> response = Command.Decoder(_mainWindow._runClient.getResponse());
                                // _seatedItemListInArray = (ArrayList<BaseItems.SeatedItemList>)response.getValue(); 
                                if(response.getValue().toString().equals("true"))
                                    btnSource.setBackground(Color.WHITE);
                                _mainWindow.tabBooking.UpdateComponentValue();
                            }
                            
                        }
                    });
                    p1.add(button[x][y]);
                }
            }
            d.add(p1, BorderLayout.CENTER);
            d.setVisible(true);
            this.add(d);
        }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public SeatedItemList IsSeatedBusy(int y, int x){
        SeatedItemList res = null;
        if(_seatedItemListInArray != null){
            Iterator i = _seatedItemListInArray.iterator();
            while(i.hasNext()){
                SeatedItemList currentSeated = (SeatedItemList)i.next();
                if(currentSeated.getSit_x() == y && currentSeated.getSit_y() == x){
                    res = currentSeated;
                    break;
                }
            }
        }
        return res;
    }
    public int CountSeated(){
        
        int res = 0;
        try {
            if(button != null){
                for(int y = 0; y< 8; y++){
                    for (int x = 0; x < 11; x++) {
                        if(button[x][y].getBackground() == Color.GREEN)
                            res++;
                    }
                }
            }
        }
        catch(Exception e){
            
        }
        return res;
    }
    
    public String listSeatedValueString(){
        String res = "";
        try {
            if(button != null){
                for(int y = 0; y< 8; y++){
                    for (int x = 0; x < 11; x++) {
                        if(button[x][y].getBackground() == Color.GREEN)
                            res += button[x][y].getText() + ",";
                    }
                }
            }
        }
        catch(Exception e){
            
        }
        return res;
    }
    public int GetHallId(){
        int res = -1;
        try {
            String parameters = _mainWindow.tabBooking.datePicker.getSelectedDay()
                                + "###" + _mainWindow.tabBooking.timeItemList.getTimeIdAt(_mainWindow.tabBooking.timeItemList.getSelectedIndex())
                                + "###" + _mainWindow.tabBooking.filmsItemList.getFilmIdAt(_mainWindow.tabBooking.filmsItemList.getSelectedIndex());
            BaseXMLCommand<String, String, PalimpsestItemList> command = new BaseXMLCommand<>("GetPalimpsestByday_time_film_id", parameters, null);
            _mainWindow._runClient.sendQuery(Command.Encoder(command));
            BaseXMLCommand<String, String, PalimpsestItemList> response = Command.Decoder(_mainWindow._runClient.getResponse());
            res = response.getValue().getHall_id();
        }
        catch(Exception e){
            
        }
        return res;
    }
    public void UpdateHallId(){
        try {
        
        lblTitle.setText("Sala: " + GetHallId());
        }
        catch(Exception e){
            
        }
    }
}