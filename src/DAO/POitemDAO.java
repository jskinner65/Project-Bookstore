package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import beans.POitemBean;

public class POitemDAO {
	DataSource ds;

	public POitemDAO(DataSource passedDS) throws ClassNotFoundException {

		ds = passedDS;

	}

	public Map<String, POitemBean> retrieve() throws SQLException {

		String query = "select * from POitem";
		Map<String, POitemBean> rv = new HashMap<String, POitemBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String name = r.getString("id");
			POitemBean book = new POitemBean(r.getInt("id"), r.getString("bid"), r.getInt("quantity"),
					r.getInt("price"));
			rv.put(name, book);
		}
		r.close();
		p.close();
		con.close();
		return rv;

	}

}
