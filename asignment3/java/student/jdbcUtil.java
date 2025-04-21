package practise;

import java.sql.Connection;
import java.sql.DriverManager;

public class jdbcUtil {
	public static Connection buildConnection() throws Exception{
				DriverManager.getConnection("jdbc:mysql://localhost:3306/cdac","root","password");
		return dbConnection;
	}
}
