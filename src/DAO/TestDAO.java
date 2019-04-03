package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class TestDAO { // testDAO
	public static void main(String[] args) throws SQLException {
		try {
			DataSource ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
			Connection con = ds.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM address");
			while (rs.next()) {
				String em = rs.getString("id");
				String fname = rs.getString("street");
				System.out.println("\t" + em + ",\t" + fname + "\t ");
			} // end while loop
			con.close();
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}
}