package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import beans.AnalyticsBean;

public class AnalyticsDAO {

	DataSource ds;

	public AnalyticsDAO(DataSource ds) {
		this.ds = ds;
	}

	public Map<String, AnalyticsBean> retrieve() throws SQLException {
		String query = "select address.zip, user.uid, sum(poItem.price * poItem.quantity) as amount FROM user, address, poitem, po WHERE user.email=po.email AND po.id=poitem.id AND user.email=address.email Group by zip;";
		Map<String, AnalyticsBean> rv = new HashMap<String, AnalyticsBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String name = r.getString("zip") + r.getInt("uid");
			AnalyticsBean book = new AnalyticsBean(r.getString("zip"), r.getInt("uid"), r.getDouble("amount"));
			rv.put(name, book);
		}
		r.close();
		p.close();
		con.close();
		return rv;

	}
}
