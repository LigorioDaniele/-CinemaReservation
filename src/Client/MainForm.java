package Client;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import Common.BaseItems.UserItem;
import Components.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainForm {

	public String _uploadPath = null;
	public UserItem _currentUserItem = null;
	public RunClient _runClient;
        
	public JFrame MainForm;
	public ucBooking tabBooking;
        public ucUserManagement tabUserManagement;
	public ucPalinsest tabPalinsest;
        public ucFilmManagement tabFilmManagement;
	/**
	 * Create the application.
	 */
	public MainForm(UserItem currentUserItem, RunClient runClient) {
            _currentUserItem = currentUserItem;
            _runClient = runClient;
            _uploadPath = "Images/";
            initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		MainForm = new JFrame();
		MainForm.setTitle("Gestione Cinema");
		MainForm.setBounds(100, 100, 983, 510);
		MainForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainForm.getContentPane().setLayout(null);
                MainForm.setResizable(false);
		/*
		 * JTabbedPane
		 */
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP); 
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBounds(16, 11, 938, 396);
		MainForm.getContentPane().add(tabbedPane);
		/*
		 * TAB: Booking
		 */
		tabBooking = new ucBooking(this);
		tabbedPane.addTab("Prenotazioni", null, tabBooking, null);
                if(_currentUserItem.getIsAdmin()){
                    /*
                    * TAB: User Management
                    */
                    tabUserManagement = new ucUserManagement(this);
                    tabbedPane.addTab("Gestione Utenti", null, tabUserManagement, null);
                    /*
                    * TAB: Palinsest
                    */
                    tabPalinsest = new ucPalinsest(this);
                    tabbedPane.addTab("Gestione palinsesto", null, tabPalinsest, null);
                    /*
                    * TAB: Film Management
                    */
                    tabFilmManagement = new ucFilmManagement(this);
                    tabbedPane.addTab("Gestione Film", null, tabFilmManagement, null);
                }
		tabbedPane.setSelectedIndex(0);
                tabbedPane.addChangeListener(new ChangeListener() {
                    // This method is called whenever the selected tab changes
                    public void stateChanged(ChangeEvent evt) {
                        JTabbedPane pane = (JTabbedPane)evt.getSource();
                        // Get current tab
                        int sel = pane.getSelectedIndex();
                        if(sel == 3){
                            tabPalinsest.LoadFilmList();
                        }
                    }
                });
	}
}
