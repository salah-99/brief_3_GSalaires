package gestionSalaire;


import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class Conection {

	
	Connection conn;
	Statement stat;
	ResultSet rs;
	PreparedStatement pr;
	
	
	public void Connect() {
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionpersonnel", "root" , "");
			//System.out.println("ok");
		}catch (SQLException e) {
			System.out.print(e);
		}
	}
}

