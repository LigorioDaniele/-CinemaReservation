package SQL;

import Common.BaseItems;
import Common.BaseItems.PalimpsestItemList;
import Common.BaseItems.TimeItemList;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class PalimpsestDAL {
	public static ArrayList<TimeItemList> GetTimeByDay(String day, SQLConnect sqlConn){
            ArrayList<TimeItemList> res = null;
            try {
                Statement statement = sqlConn.conn.createStatement();
                // Result set get the result of the SQL query
                ResultSet resultSet = statement
                                .executeQuery("select * from palimpsest WHERE day = " + day + " ORDER BY time");
                if(resultSet != null) {
                    res = new ArrayList<BaseItems.TimeItemList>();

                    while (resultSet.next()) {
                        int time = resultSet.getInt("time");
                        String timeName = "18-20";
                        switch(time){
                            case 0:
                                timeName = "18-20";
                                break;
                            case 1:
                                timeName = "20-22";
                                break;
                            case 2:
                                timeName = "22-24";
                                break;
                        }    
                        res.add(new TimeItemList(time, timeName));
                    }
                }
            }
            catch (Exception ex){
                    ex.getStackTrace();
            }
            return res;
	}
        
        public static PalimpsestItemList GetPalimpsestByday_time_film_id(String inputDay, String inputTime, String inputFilm_id, SQLConnect sqlConn){
            PalimpsestItemList res = null;
            try {
                Statement statement = sqlConn.conn.createStatement();
                // Result set get the result of the SQL query
                ResultSet resultSet = statement
                                .executeQuery("select * from palimpsest WHERE day = " + inputDay + " AND time = " + inputTime + " AND film_id = " + inputFilm_id);
                if(resultSet != null) {
                    

                    while (resultSet.next()) {
                        int time = resultSet.getInt("hall_id");
                        res = new PalimpsestItemList(Integer.parseInt(inputDay), Integer.parseInt(inputTime), Integer.parseInt(inputFilm_id), time);
                    }
                }
            }
            catch (Exception ex){
                    ex.getStackTrace();
            }
            return res;
	}
        public static ArrayList<PalimpsestItemList> GetPalimpsestByFilmId(String inputFilm_id, SQLConnect sqlConn){
            ArrayList<PalimpsestItemList> res = new ArrayList<PalimpsestItemList>();
            try {
                Statement statement = sqlConn.conn.createStatement();
                // Result set get the result of the SQL query
                ResultSet resultSet = statement
                                .executeQuery("select * from palimpsest WHERE film_id = " + inputFilm_id);
                if(resultSet != null) {
                    while(resultSet.next()) {
                        int day = resultSet.getInt("day");
                        int time = resultSet.getInt("time");
                        int film_id = resultSet.getInt("film_id");
                        int hall = resultSet.getInt("hall_id");
                        res.add(new PalimpsestItemList(day, time, film_id, hall));
                    }
                }
            }
            catch (Exception ex){
                    ex.getStackTrace();
            }
            return res;
	}
        public static boolean SetPalimpsest(PalimpsestItemList inputPalimpsestItem, SQLConnect sqlConn){
            boolean res = false;
            try {
                Statement statement = sqlConn.conn.createStatement();
                statement.executeUpdate("DELETE FROM palimpsest WHERE day = '" + inputPalimpsestItem.getDay() + "' AND film_id = '" + inputPalimpsestItem.getFilm_id() + "'");
                int iRes = statement.executeUpdate("INSERT INTO palimpsest (day, time, film_id, hall_id) VALUES ("
                                                    + "'" + inputPalimpsestItem.getDay() + "', "
                                                    + "'" + inputPalimpsestItem.getTime() + "', " 
                                                    + "'" + inputPalimpsestItem.getFilm_id() + "', "
                                                    + "'" + inputPalimpsestItem.getHall_id() + "')");
                                                    
                if(iRes > 0)
                    res = true;
            }
            catch (Exception ex){
                    ex.getStackTrace();
            }
            return res;
	}
        
        public static boolean DeletePalimpsest(PalimpsestItemList inputPalimpsestItem, SQLConnect sqlConn){
            boolean res = false;
            try {
                Statement statement = sqlConn.conn.createStatement();
                int iRes = statement.executeUpdate("DELETE FROM palimpsest WHERE day = '" + inputPalimpsestItem.getDay() + "' AND film_id = '" + inputPalimpsestItem.getFilm_id() + "'");
                
                if(iRes > 0)
                    res = true;
            }
            catch (Exception ex){
                    ex.getStackTrace();
            }
            return res;
	}
}
