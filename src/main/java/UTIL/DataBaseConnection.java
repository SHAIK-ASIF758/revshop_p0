package UTIL;

import java.sql.*;

public class DataBaseConnection {
	private static  DataBaseConnection instance;
	private static Connection connection=null;
	private DataBaseConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/revshop", "root","root");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static DataBaseConnection getInstance() {
		if(instance==null) {
			instance=new DataBaseConnection();
		}
		return instance;
	}
	public static Connection getConnection() {
		return connection;
	}
	
}

