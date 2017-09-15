package com.asiwi.test.mitch.SpringTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLAccess {
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	
	public void readDataBase() throws Exception {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/test?"
													+"user=sqluser&password=dbpassword");
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
				String middleInitial = null;
				if(middleName==null )
					middleInitial = "";
				else
					middleInitial = middleName.substring(0, 1)+".";
				System.out.println(""+firstName+" "+middleInitial+" "+lastName);
			}
		} catch (Exception e) {
			System.out.println("Exception in writeResultSet: "+e.getMessage());
			e.printStackTrace();
		}
	}


	public List<String> getNameList() {
		ArrayList<String> ret = new ArrayList<String>();
		try {
			while(resultSet.next()) {
				String lastName = resultSet.getString("last_name");
				String firstName = resultSet.getString("first_name");
				String middleName = resultSet.getString("middle_name");
				String middleInitial = "";
				if(middleName!=null)
					middleInitial = middleName.substring(0, 1)+".";

				ret.add(firstName+" "+middleInitial+" "+lastName);
			}
		} catch (Exception e) {
			System.out.println("Exception in getNameList: "+e.getMessage());
			System.out.println("Exception in writeResultSet: "+e.getMessage());		
			e.printStackTrace();
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
			e.printStackTrace();
        }
    }

/*
	public void insert(String lname, String fname, String mname, String dob, String sex) {
		String query = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/test?"
													+"user=sqluser&password=dbpassword");
			statement = connection.createStatement();

			query = "insert into people (last_name, first_name, middle_name, dob, sex) values ("
					+lname+", "
					+fname+", "
					+mname+", "
					+dob+", "
					+sex
					+") "
					+"where not exists "
					+"(select * from people where "
					+"last_name = '"+lname+"' "
					+"and first_name = '"+fname+"' "
					+"and middle_name = '"+mname+"' "
					+"and dob = '"+dob+"' "
					+"and sex = '"+sex+"' "
					+");";
			statement.executeQuery(query);
			resultSet = statement.executeQuery("select last_name, first_name, middle_name, dob from people order by dob desc;"); 
		} catch (Exception e) {
			System.out.println("INSERT statement failed: "+e.getMessage());
			e.printStackTrace();
		} finally {
			System.out.println("INSERT query: "+query);
		}
	}
*/
	
}
