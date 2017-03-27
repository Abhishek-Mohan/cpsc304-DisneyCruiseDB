package com.disneycruise.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by abhis on 3/20/2017.
 */
public class Database {
	private static Database connectionClass;
	private static java.sql.Connection connection;

    public Database()
    {
		connectToDb("ora_z4n8", "a20232120");
    }

    //Connect to Database
	public void connectToDb(String username, String password)
    {
        String connectURL = "jdbc:oracle:thin:@localhost:1522:ug";

		try{
			//Registering a driver
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

		}catch(SQLException ex){

			System.out.println("Message: " + ex.getMessage());
		}

		try {
			connection = DriverManager.getConnection(connectURL, username, password);

			System.out.println("\nConnected to Oracle!");
		} catch (SQLException ex) {
			System.out.println("Message: " + ex.getMessage());
		}
	}


	public static Database getInstance()
	{
		if ( connectionClass == null )
		{
			connectionClass = new Database();
		}
		return connectionClass;
	}

	public java.sql.Connection getConnection() {
		return connection;
	}

}

