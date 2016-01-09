package SQL;

import Common.BaseItems;
import Common.BaseItems.FilmsItemList;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class FilmsDAL {
	
	public static ArrayList<FilmsItemList> GetFilmsList(SQLConnect sqlConn){
		ArrayList<FilmsItemList> res = null;
		try {
			Statement statement = sqlConn.conn.createStatement();
			ResultSet resultSet = statement
					.executeQuery("SELECT * FROM films");
                        res = new ArrayList<FilmsItemList>();
			while (resultSet.next()) {
                            int film_id = resultSet.getInt("film_id");	
                            String film_title = resultSet.getString("film_title");
                            int film_duration = resultSet.getInt("film_duration");
                            String film_producer = resultSet.getString("film_producer");
                            String film_image_url = resultSet.getString("film_image_url");
                            res.add(new FilmsItemList(film_id, film_title, film_duration, film_producer, film_image_url));
			}
		}
		catch (Exception ex){
			ex.getStackTrace();
		}
		return res;
	}
	public static ArrayList<FilmsItemList> GetFilmsListByDay(String day, SQLConnect sqlConn){
		ArrayList<FilmsItemList> res = null;
		try {
			Statement statement = sqlConn.conn.createStatement();
			ResultSet resultSet = statement
					.executeQuery("SELECT F.* FROM films AS F INNER JOIN palimpsest AS P ON F.film_id = P.film_id WHERE P.day = " + day);
                        res = new ArrayList<FilmsItemList>();
			while (resultSet.next()) {
                            int film_id = resultSet.getInt("film_id");	
                            String film_title = resultSet.getString("film_title");
                            int film_duration = resultSet.getInt("film_duration");
                            String film_producer = resultSet.getString("film_producer");
                            String film_image_url = resultSet.getString("film_image_url");
                            res.add(new FilmsItemList(film_id, film_title, film_duration, film_producer, film_image_url));
			}
		}
		catch (Exception ex){
			ex.getStackTrace();
		}
		return res;
	}
        public static int GetFilmidByTitle(String title, SQLConnect sqlConn){
            int res = -1;
            try {
                    Statement statement = sqlConn.conn.createStatement();
                    ResultSet resultSet = statement
                                    .executeQuery("SELECT film_id FROM films WHERE film_title = '" + title + "'");
                    while (resultSet.next()) {
                        res = resultSet.getInt("film_id");	
                    }
            }
            catch (Exception ex){
                    ex.getStackTrace();
            }
            return res;
	}
        
        public static boolean CreateFilm(BaseItems.FilmsItemList inputFilmItemList, SQLConnect sqlConn){
            boolean res = false;
            try {
                Statement statement = sqlConn.conn.createStatement();
                ResultSet resultSet = statement
					.executeQuery("select * from films WHERE film_title = '" + inputFilmItemList.getTitle() + "'");
                if(!resultSet.first()) {
                    // Result set get the result of the SQL query
                    int iRes = statement.executeUpdate("INSERT INTO films (film_title, film_duration, film_producer) VALUES('" + inputFilmItemList.getTitle() + "'"
                                                        + ", '" + inputFilmItemList.getDuration() + "'" 
                                                        + ", '" + inputFilmItemList.getProducer() + "')");
                    if(iRes > 0)
                        res = true;
                }
            }
            catch (Exception ex){
                    ex.getStackTrace();
            }
            return res;
	}
        
        public static boolean UpdateFilmByTitle(BaseItems.FilmsItemList inputFilmItemList, SQLConnect sqlConn){
            boolean res = false;
            try {
                Statement statement = sqlConn.conn.createStatement();
                int iRes = statement.executeUpdate("UPDATE films SET film_title = '" + inputFilmItemList.getTitle() + "'"
                                                    + ", film_duration = '" + inputFilmItemList.getDuration() + "'" 
                                                    + ", film_producer = '" + inputFilmItemList.getProducer() + "' WHERE film_title = '" + inputFilmItemList.getTitle() + "'");
                if(iRes > 0)
                    res = true;
            }
            catch (Exception ex){
                    ex.getStackTrace();
            }
            return res;
	}
        
        public static boolean DeleteFilmFilmByTitle(BaseItems.FilmsItemList inputFilmItemList, SQLConnect sqlConn){
            boolean res = false;
            try {
                Statement statement = sqlConn.conn.createStatement();
                int iRes = statement.executeUpdate("DELETE FROM films WHERE film_title = '" + inputFilmItemList.getTitle() + "'");
                if(iRes > 0)
                    res = true;
            }
            catch (Exception ex){
                    ex.getStackTrace();
            }
            return res;
	}
        //
}
