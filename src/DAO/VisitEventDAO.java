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

import beans.VisitEventBean;

public class VisitEventDAO {
	DataSource ds;

	public VisitEventDAO() throws ClassNotFoundException {
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	public Map<String, VisitEventBean> retrieve() throws SQLException {

		String query = "select * from VisitEvent";
		Map<String, VisitEventBean> rv = new HashMap<String, VisitEventBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String name = r.getString("id");
			VisitEventBean book = new VisitEventBean(r.getString("day"), r.getString("bid"), r.getString("eventtype"));
			rv.put(name, book);
		}
		r.close();
		p.close();
		con.close();
		return rv;

	}
}
