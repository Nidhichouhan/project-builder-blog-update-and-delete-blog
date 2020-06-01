package utility;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
		Properties properties=null;
		properties=loadPropertiesFile();
		final String driver=properties.getProperty("driver");
		final String url=properties.getProperty("url");
		final String username=properties.getProperty("username");
		final String pass=properties.getProperty("password");
		
		Connection con=null;
		
		Class.forName(driver);
		
		con=DriverManager.getConnection(url, username, pass);
	
		return con;
		
	}
	
	public static Properties loadPropertiesFile() throws IOException {
		Properties prop=new Properties();
		InputStream input=ConnectionManager.class.getClassLoader().getResourceAsStream("jdbc.properties");
		prop.load(input);
		input.close();
		return prop;
		
	}
}
