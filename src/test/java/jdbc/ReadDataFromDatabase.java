package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.cj.jdbc.Driver;

public class ReadDataFromDatabase {
	public static void main(String[] args) throws SQLException {
		
	
	//step 1. Create an instance for Driver class
	//import Driver from com.mysql.cj.jdbc.Driver
	//new Driver(); -> throws sqlexception
	
	Driver dbDriver = new Driver();
	
	//steps 2: Register to driver
	DriverManager.registerDriver(dbDriver);
	
	//steps 3: Establish connection to database
	
	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/selermemo", "root", "root");
	
	//steps 4: create statement
	
	Statement statement = connection.createStatement();
	
	//Steps 5: Execute SQL query
	ResultSet result = statement.executeQuery("select * from employee;");
	
	while(result.next())
	{
		System.out.println(result.getInt("id")+"\t"+result.getString("name")+"\t"+result.getString("role"));
	}
	
	//Steps 6: Close database
	connection.close();
	
	}

}
