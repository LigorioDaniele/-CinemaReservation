package SQL;

import Common.BaseItems.SeatedItemList;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SeatedDAL {
	
	public static ArrayList<SeatedItemList> GetSeatedList(int inputUser_id, int inputFilm_id, int inputTime, String inputDate, int inputHall_id, SQLConnect sqlConn){
		ArrayList<SeatedItemList> res = null;
		try {
			Statement statement = sqlConn.conn.createStatement();
			// Result set get the result of the SQL query
                        // user_id = " + inputUser_id + " AND
			ResultSet resultSet = statement
					.executeQuery("SELECT * FROM seated WHERE  film_id = " + inputFilm_id + " AND time = " + inputTime + " AND date = '" + inputDate + "' AND hall_id = " + inputHall_id);
                        res = new ArrayList<SeatedItemList>();
			while (resultSet.next()) {
                            int user_id = resultSet.getInt("user_id");	
                            int hall_id = resultSet.getInt("hall_id");
                            int time = resultSet.getInt("time");
                            int sit_x = resultSet.getInt("sit_x");
                            int sit_y = resultSet.getInt("sit_y");
                            int film_id = resultSet.getInt("film_id");
                            String date = resultSet.getString("date");
                            //String Status = resultSet.getString("Status");
                            //int PacketId = resultSet.getInt("PacketId");
                            res.add(new SeatedItemList(user_id, hall_id, time, sit_x, sit_y, film_id, date));
			}
		}
		catch (Exception ex){
			ex.getStackTrace();
		}
		return res;
	}
	
        public static boolean SetSeatedListByUserId(SeatedItemList inputSeatedItemList, SQLConnect sqlConn){
            boolean res = false;
            try {
                    // Prepare a statement to update a record
                    Statement statement = sqlConn.conn.createStatement();
                    // Result set get the result of the SQL query
                    int iRes = statement.executeUpdate("INSERT INTO seated (user_id, hall_id, time, sit_x, sit_y, film_id, date) VALUES (" +
                                                        inputSeatedItemList.getUser_id()
                                                        + ", " + inputSeatedItemList.getHall_id()
                                                        + ", " + inputSeatedItemList.getTime() 
                                                        + ", " + inputSeatedItemList.getSit_x() 
                                                        + ", " + inputSeatedItemList.getSit_y()
                                                        + ", " + inputSeatedItemList.getFilm_id() 
                                                        + ", '" + inputSeatedItemList.getDate() + "')");
                    if(iRes > 0)
                        res = true;
            }
            catch (Exception ex){
                    ex.getStackTrace();
            }
            return res;
	}
        public static boolean DeleteSeatedListByUserId(SeatedItemList inputSeatedItemList, SQLConnect sqlConn){
            boolean res = false;
            try {
                    // Prepare a statement to update a record
                    Statement statement = sqlConn.conn.createStatement();
                    // Result set get the result of the SQL query
                    int iRes = statement.executeUpdate("DELETE FROM seated WHERE sit_x = " + inputSeatedItemList.getSit_x() 
                                                        + " AND  sit_y = " + inputSeatedItemList.getSit_y()
                                                        + " AND  time = " + inputSeatedItemList.getTime()
                                                        + " AND  user_id = " + inputSeatedItemList.getUser_id()
                                                        + " AND  date = '" + inputSeatedItemList.getDate() + "'"
                                                        + " AND  film_id = " + inputSeatedItemList.getFilm_id());
                                                        
                    if(iRes > 0)
                        res = true;
            }
            catch (Exception ex){
                    ex.getStackTrace();
            }
            return res;
	}
}
