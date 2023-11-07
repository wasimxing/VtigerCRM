package genericLibraries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.Driver;

public class DataBaseUtility {
	/**
	 * This class contains reusable methods to perform operations on Database
	 */
	private Connection connection;
	/**
	 * This method is used to initialize and establish database connection
	 * @param jdbcUrl
	 * @param username
	 * @param password
	 */
	public void databaseInitialization(String jdbcUrl, String username, String password)
	{
		Driver dbDriver =null;
		try {
			dbDriver = new Driver();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			DriverManager.registerDriver(dbDriver);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection(jdbcUrl, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *  this method returns list of data from database based on query,colName and throws exception
	 * @param query
	 * @param colName
	 * @return
	 * @throws SQLException
	 */
	public List<String> getDataFromDatabase(String query, String colName) throws SQLException
	{
		
		
		Statement statment  = connection.createStatement();
		
		ResultSet result = statment.executeQuery(query);
		
		List<String> list = new ArrayList<>();
		while(result.next())
		{
			list.add(result.getString(colName)); 
		}
		
		return list;
		
	}
	
	/**
	 * this method is used to modify the database
	 * @param query 
	 * @return 
	 */
	public int  modifyDatabase(String query)
	{
		Statement statement=null;
		try {
			statement= connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		int result = 0;
		try {
			 result = statement.executeUpdate(query);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return result;
		
	}
	/**
	 * this mehtod is used to close the database
	 */
	public void closeDatabase()
	{
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
}
