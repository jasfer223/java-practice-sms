package access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
    	String databaseUrl = "jdbc:ucanaccess://students.accdb";
    	
    	try {
			Connection con = DriverManager.getConnection(databaseUrl);
			
			String sql = "INSERT INTO students (name, grade) VALUES (?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "Jasper2");
			stmt.setInt(2, 100);
			stmt.execute();
			
			System.out.println("Student added!");

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}
