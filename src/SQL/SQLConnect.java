package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;


public class SQLConnect {
	Connection conn = null;
	Statement statement = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	public SQLConnect(){

	}
	public boolean init(){
		boolean res = false;
		try {
	    	//jdbc:mysql://host_name:port/dbname
	    	Class.forName("com.mysql.jdbc.Driver").newInstance();
	    	conn = DriverManager.getConnection("jdbc:mysql://localhost/gipsy_fingers",
	    			"root", "");
	    	
	    	if(!conn.isClosed()){
	    		res = true;
	    		System.out.println("Successfully connected to " +
	    				"MySQL server using TCP/IP...");
	    		/*
	    		 * Start Timer to refresh download status
	    		 */
                        Timer timer=new Timer();
                        timer.schedule(new PingSqlServer(),1000,5000);//parti dopo 1 secondo e itera ogni 5 secondi 
	    	}
		} catch(Exception e) {
			System.err.println("Exception: " + e.getMessage());
	    } 
            return res;
	}
	class PingSqlServer extends TimerTask{
            public void run(){
                if(conn != null){
                    try {
                        Statement statement = conn.createStatement();
                        // Result set get the result of the SQL query
                        statement.executeQuery("select COUNT(*) from users");
                        }
                    catch (Exception ex){
                            ex.getStackTrace();
                    }
                }
            }
	}
}
