package SQL;

import Common.BaseItems;
import java.sql.ResultSet;
import java.sql.Statement;

import Common.BaseItems.UserItem;
import java.util.ArrayList;

public class UsersDAL {
	public static UserItem CheckUserLoginByUsername_Password(String username, String password, SQLConnect sqlConn){
		UserItem res = null;
		try {
			Statement statement = sqlConn.conn.createStatement();
			// Result set get the result of the SQL query
			ResultSet resultSet = statement
					.executeQuery("select * from users WHERE username = '" + username + "' AND password = '" + password + "'");
			if(resultSet != null) {
				resultSet.first();
                                int curentId = resultSet.getInt("id");
				String currentUsername = resultSet.getString("username");
				String currentPassword = resultSet.getString("password");
				boolean isAdmin = false;
				if(resultSet.getString("is_admin").equals("1"))
					isAdmin = true;
				
				res = new UserItem(currentUsername, currentPassword, isAdmin);
                                res.setId(curentId);
                                res.setIsAdmin(isAdmin);
			}
		}
		catch (Exception ex){
			ex.getStackTrace();
		}
		return res;
	}
        public static ArrayList<UserItem> GetUserItemList(SQLConnect sqlConn){
		ArrayList<UserItem> res = null;
		try {
			Statement statement = sqlConn.conn.createStatement();
			// Result set get the result of the SQL query
                        // user_id = " + inputUser_id + " AND
			ResultSet resultSet = statement
					.executeQuery("SELECT * FROM users");
                        res = new ArrayList<UserItem>();
			while (resultSet.next()) {
                            String username = resultSet.getString("username");	
                            String password = resultSet.getString("password");
                            boolean isAdmin = resultSet.getBoolean("is_admin");
                            res.add(new UserItem(username, password, isAdmin));
			}
		}
		catch (Exception ex){
			ex.getStackTrace();
		}
		return res;
	}
        public static boolean CreateUser(UserItem inputUserItem, SQLConnect sqlConn){
            boolean res = false;
            try {
                /*
                 * Controllo se l'username esiste già.
                 * */ 
                Statement statement = sqlConn.conn.createStatement();
                ResultSet resultSet = statement
					.executeQuery("select * from users WHERE username = '" + inputUserItem.getUsername() + "'");
                if(!resultSet.first()) {
                    // Result set get the result of the SQL query
                    int iRes = statement.executeUpdate("INSERT INTO users (username, password, is_admin) VALUES('" + inputUserItem.getUsername() + "'"
                                                        + ", '" + inputUserItem.getPassword() + "'" 
                                                        + ", 0)");
                    if(iRes > 0)
                        res = true;
                }
            }
            catch (Exception ex){
                    ex.getStackTrace();
            }
            return res;
	}
        public static boolean UpdateUserByUsername(UserItem inputUserItem, SQLConnect sqlConn){
            boolean res = false;
            try {
                int isAdminInt = 0;
                if(inputUserItem.IsAdmin())
                    isAdminInt = 1;
                // Prepare a statement to update a record
                Statement statement = sqlConn.conn.createStatement();
                // Result set get the result of the SQL query
                int iRes = statement.executeUpdate("UPDATE users SET username = '" + inputUserItem.getUsername() + "'"
                                                    + ", password = '" + inputUserItem.getPassword() + "'" 
                                                    + ", is_admin = " + isAdminInt + " WHERE username = '" + inputUserItem.getUsername() + "'");
                if(iRes > 0)
                    res = true;
            }
            catch (Exception ex){
                    ex.getStackTrace();
            }
            return res;
	}
        public static boolean DeleteUserByUsername(UserItem inputUserItem, SQLConnect sqlConn){
            boolean res = false;
            try {
                Statement statement = sqlConn.conn.createStatement();
                int iRes = statement.executeUpdate("DELETE FROM users WHERE username = '" + inputUserItem.getUsername() + "'");
                if(iRes > 0)
                    res = true;
            }
            catch (Exception ex){
                    ex.getStackTrace();
            }
            return res;
	}
}
