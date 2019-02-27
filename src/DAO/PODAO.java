package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import beans.POBean;

public class PODAO {
	DataSource ds;

	public PODAO() throws ClassNotFoundException {
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	public Map<String, POBean> retrieve() throws SQLException {

		String query = "select * from PO";
		Map<String, POBean> rv = new HashMap<String, POBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String name = r.getString("id");
			POBean book = new POBean(r.getInt("id"), r.getString("lname"), r.getString("fname"), r.getString("status"),
					r.getInt("address"));
			rv.put(name, book);
		}
		r.close();
		p.close();
		con.close();
		return rv;

	}

}
