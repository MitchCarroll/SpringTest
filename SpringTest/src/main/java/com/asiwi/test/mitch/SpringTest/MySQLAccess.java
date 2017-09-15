package com.asiwi.test.mitch.SpringTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.sql.DriverManager;

public class MySQLAccess {
	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public void readDataBase() throws Exception {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/test?" + "user=sqluser&password=dbpassword");
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from people order by dob;");
			writeResultSet(resultSet);
		}
		catch(Exception e) {
			System.out.println("Exception in readDataBase: "+e.getMessage());
		}
	}
	
	private void writeResultSet(ResultSet resultSet) {
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
		List<String> ret = null;
		
		try {
			while(resultSet.next()) {
				String lastName = resultSet.getString("last_name");
				String firstName = resultSet.getString("first_name");
				String middleName = resultSet.getString("middle_name");
				ret.add(""+firstName+" "+middleName.substring(0, 1)+". "+lastName);
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
