package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import beans.AddressBean;

public class AddressDAO {

	DataSource ds;

	public AddressDAO(DataSource passedDS) throws ClassNotFoundException {

		ds = passedDS;

	}

	public Map<String, AddressBean> retrieve() throws SQLException {

		String query = "select * from Address";
		Map<String, AddressBean> rv = new HashMap<String, AddressBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String name = r.getInt("id") + "";
			AddressBean book = new AddressBean(r.getInt("id"), r.getString("email"), r.getString("street"),
					r.getString("province"), r.getString("country"), r.getString("zip"), r.getString("phone"));
			rv.put(name, book);
		}
		r.close();
		p.close();
		con.close();
		return rv;

	}
}
