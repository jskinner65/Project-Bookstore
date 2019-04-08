package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import beans.UserBean;

public class UserDAO {
	DataSource ds;

	public UserDAO(DataSource ds) {
		this.ds = ds;
	}

	public boolean addUser(UserBean newUser) throws SQLException {
		String query = "INSERT INTO users (uid, fname, lname, email, password, privilege) VALUES (";
		query = query + newUser.getUid() + ", " + newUser.getFname() + ", " + newUser.getLname() + ", "
				+ newUser.getEmail() + ", " + newUser.getPassword() + ", " + newUser.getPrivilege() + "); ";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		return p.execute();

	}

	public String getPassword(String email) throws SQLException {
		String query = "SELECT password FROM users WHERE email=" + email + ";";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		String name = "";
		while (r.next()) {
			name = r.getString("password") + "";

		}
		r.close();
		p.close();
		con.close();
		return name;

	}

	public Map<String, UserBean> getUserByZip(String zip) throws SQLException {
		Map<String, UserBean> rv = new HashMap<String, UserBean>();
		String query = "SELECT * FROM users, address WHERE users.email=address.email AND address.zip=" + zip + ";";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String name = r.getInt("uid") + "";
			UserBean user = new UserBean(r.getInt("uid"), r.getString("fname"), r.getString("lname"),
					r.getString("email"), r.getString("password"), r.getString("privilege"));
			rv.put(name, user);
		}
		r.close();
		p.close();
		con.close();
		return rv;

	}
}
