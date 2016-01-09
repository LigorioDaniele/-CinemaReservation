package Components;


import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

import Client.MainForm;
import Controls.*;

public class ucBooking extends JPanel {
	public MainForm _mainWindow = null;
	public c_DatePicker datePicker;
	public c_TimeItemList timeItemList;
        public c_FilmsItemList filmsItemList;
        public c_Seated seated;
        public c_BookingSummary bookingSummary;
	public JTextArea txtStatus;
	
	public ucBooking(MainForm mainWindow){
            _mainWindow = mainWindow;
            this.setLayout(null);
            this.setBounds(10, 10, 500, 500);

            datePicker = new c_DatePicker(_mainWindow);
            datePicker.setBounds(10, 11, 250, 150);
            this.add(datePicker);

            timeItemList = new c_TimeItemList(_mainWindow);
            timeItemList.setBounds(265, 11, 97, 200);
            timeItemList.setBackground(null);
            //setLayout(null);
            timeItemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            this.add(timeItemList);

            filmsItemList = new c_FilmsItemList(_mainWindow);
            filmsItemList.setBounds(370, 11, 200, 200);
            filmsItemList.setBackground(null);
            filmsItemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            this.add(filmsItemList);
    
            seated = new c_Seated(_mainWindow);
            seated.setBounds(590, 0, 320, 260);
            seated.setBackground(null);
            seated.setVisible(false);
            this.add(seated);
            
            bookingSummary = new c_BookingSummary(_mainWindow);
            bookingSummary.setBounds(11, 150, 359, 260);
            this.add(bookingSummary);           
	}
	
        public c_TimeItemList getTimeItemList(){
            return timeItemList;
        }
        
        public void ResetComponentValue(){
            bookingSummary.ResetValue();
        }
                
        public void UpdateComponentValue(){
            
            bookingSummary.UpdateValue(true);
            seated.UpdateHallId();
        }
}
