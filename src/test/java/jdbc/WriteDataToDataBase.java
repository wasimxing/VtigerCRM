package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class WriteDataToDataBase {
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
		
		//Step 5: modify  data in database
		int result = statement.executeUpdate("insert into employee(id, name, role) values (109,'jaanu', 'UI' )");
		
		System.out.println(result+"row effected");
		
		//steps 6: close database
		connection.close();
	}

}
