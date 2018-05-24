package databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnect {
	public static final String DBDRIVER = "com.mysql.jdbc.Driver";
	public static final String DBURL = "jdbc:mysql://localhost:3306/grading_system";
	public static final String DBUSER = "root";
	public static final String DBPASS = "";
	private Connection conn;

	public Connection connetDB() {
		try {
			Class.forName(DBDRIVER);
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
		} 
		catch (SQLException e) {
			return null;
		}

		return conn;

	}

	public void closeDB() {
		try {
			conn.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
