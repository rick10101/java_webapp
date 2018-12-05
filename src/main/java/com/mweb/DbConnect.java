package com.mweb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DbConnect {
	
	private static Connection connection = null;

	    public static Connection getConnection() {
	        if (connection != null)
	            return connection;
	        else {
	            try {
	                Class.forName("com.mysql.cj.jdbc.Driver");
	            	//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/blogdb?useUnicode=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
	              connection = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/blogdb22?useUnicode=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "victor123", "qwertyui");
	            } catch (ClassNotFoundException e) {
	                e.printStackTrace();
	            } catch (SQLException e) {
	                e.printStackTrace();
	           }
	            return connection;
	        }

	    }
}
