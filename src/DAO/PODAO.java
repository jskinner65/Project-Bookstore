package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import beans.POBean;

public class PODAO {
	DataSource ds;

	public PODAO(DataSource passedDS) throws ClassNotFoundException {

		ds = passedDS;

	}

	public Map<String, POBean> retrieve() throws SQLException {

		String query = "select * from PO";
		Map<String, POBean> rv = new HashMap<String, POBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String name = r.getString("id");
			POBean book = new POBean(r.getInt("id"), r.getString("email"), r.getString("lname"), r.getString("fname"),
					r.getString("status"), r.getInt("address"), r.getString("day"));
			rv.put(name, book);
		}
		r.close();
		p.close();
		con.close();
		return rv;

	}

	public Map<String, POBean> retrieveByEmail(String email) throws SQLException {
		if (email.trim().contains(" ")) {
			return null;
		} else {
			String query = "select * from PO WHERE email=" + email + ";";
			Map<String, POBean> rv = new HashMap<String, POBean>();
			Connection con = this.ds.getConnection();
			PreparedStatement p = con.prepareStatement(query);
			ResultSet r = p.executeQuery();
			while (r.next()) {
				String name = r.getString("id");
				POBean book = new POBean(r.getInt("id"), r.getString("email"), r.getString("lname"),
						r.getString("fname"), r.getString("status"), r.getInt("address"), r.getString("day"));
				rv.put(name, book);
			}
			r.close();
			p.close();
			con.close();
			return rv;
		}
	}

	public Map<String, POBean> retrieveByID(int ID) throws SQLException {

		String query = "select * from PO WHERE ID=" + ID + ";";
		Map<String, POBean> rv = new HashMap<String, POBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String name = r.getString("id");
			POBean book = new POBean(r.getInt("id"), r.getString("email"), r.getString("lname"), r.getString("fname"),
					r.getString("status"), r.getInt("address"), r.getString("day"));
			rv.put(name, book);
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}

	public int addPO(POBean newPO) throws SQLException {

		String query = "INSERT INTO PO (email, lname, fname, status, address, day) VALUES ('";
		query = query + newPO.getEmail() + "', '" + newPO.getLname() + "', '" + newPO.getFname() + "', '"
				+ newPO.getStatus() + "', " + newPO.getAddress() + ", '" + newPO.getDay() + "');";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		p.execute();
		query = "SELECT MAX(id) as max FROM PO;";
		p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		int max = 0;
		while (r.next()) {
			max = r.getInt("max");

		}
		return max;
	}
}
