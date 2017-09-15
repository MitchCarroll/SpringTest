package com.asiwi.test.mitch.SpringTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLAccess {
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	
	public void readDataBase() throws Exception {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/test?" + "user=sqluser&password=dbpassword");
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select last_name, first_name, middle_name, dob from people order by dob desc;");
		}
		catch(Exception e) {
			System.out.println("Exception in readDataBase: "+e.getMessage());
		}
	}
	
	public void writeResultSet() {
		try {
			while(resultSet.next()) {
				String lastName = resultSet.getString("last_name");
				String firstName = resultSet.getString("first_name");
				String middleName = resultSet.getString("middle_name");
				
				System.out.println(""+firstName+" "+middleName.substring(0, 1)+". "+lastName);
			}
		} catch (SQLException e) {
			System.out.println("Exception in writeResultSet: "+e.getMessage());		}
	}


	public List<String> getNameList() {
		ArrayList<String> ret = new ArrayList<String>();
		try {
			while(resultSet.next()) {
				String lastName = resultSet.getString("last_name");
				String firstName = resultSet.getString("first_name");
				String middleName = resultSet.getString("middle_name");
				ret.add(firstName+" "+middleName.substring(0, 1)+". "+lastName);
			}
		} catch (SQLException e) {
			System.out.println("Exception in writeResultSet: "+e.getMessage());		
			return null;
		}
		return ret;
	}

	public void close() {
        try {
            if (resultSet != null) 	resultSet.close();
            if (statement != null) 	statement.close();
            if (connection != null)	connection.close();
        } catch (Exception e) {
        	System.out.println("Exception in close(): "+e.getMessage());
        }
    }
}
