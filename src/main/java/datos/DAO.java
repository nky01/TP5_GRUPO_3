package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
	
	public Connection getConnection() throws SQLException {
	    try {
	    	Class.forName("com.mysql.jdbc.Driver");  // fuerza a cargar el driver
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	    return DriverManager.getConnection(
	        "jdbc:mysql://localhost:3306/sistema_clientes?useSSL=false&serverTimezone=UTC",
	        "root",
	        "root"
	    );
	}
	
	
}
