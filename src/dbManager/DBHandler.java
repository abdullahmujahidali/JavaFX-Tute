package dbManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler extends ConnectionClass {
	Connection dbConnection;
	
	public Connection getDbConnection() throws ClassNotFoundException, SQLException {
		String connectionString = "jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbName;
		
		Class.forName("com.mysql.jdbc.Driver");
		
		dbConnection = DriverManager.getConnection(connectionString,userName,password);
		
		return dbConnection;
	}
}
