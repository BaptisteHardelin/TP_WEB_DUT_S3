package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
	
	public static DataSource instance = new DataSource();
	
	private DataSource() {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws SQLException {
		String url = "jdbc:h2:tcp://localhost:9092/~/test";
		String login = "sa";
		String mdp = "";
		
		return DriverManager.getConnection(url,login,mdp);
	}

}
