package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import beans.VisitEventBean;

public class VisitEventDAO {
	DataSource ds;

	public VisitEventDAO(DataSource passedDS) throws ClassNotFoundException {

		ds = passedDS;

	}

	public Map<String, VisitEventBean> retrieve() throws SQLException {

		String query = "select * from VisitEvent";
		Map<String, VisitEventBean> rv = new HashMap<String, VisitEventBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String name = r.getString("id");
			VisitEventBean book = new VisitEventBean(r.getString("day"), r.getString("bid"), r.getInt("uid"),
					r.getString("eventtype"), r.getInt("quantity"));
			rv.put(name, book);
		}
		r.close();
		p.close();
		con.close();
		return rv;

	}

	public Map<String, VisitEventBean> retrieveByUID(int uid) throws SQLException {
		if (uid > 0) {
			String query = "select * from VisitEvent WHERE uid=" + uid + " order by day;";
			Map<String, VisitEventBean> rv = new HashMap<String, VisitEventBean>();
			Connection con = this.ds.getConnection();
			PreparedStatement p = con.prepareStatement(query);
			ResultSet r = p.executeQuery();
			while (r.next()) {
				String name = r.getString("id");
				VisitEventBean book = new VisitEventBean(r.getString("day"), r.getString("bid"), r.getInt("uid"),
						r.getString("eventtype"), r.getInt("quantity"));
				rv.put(name, book);
			}
			r.close();
			p.close();
			con.close();
			return rv;
		} else {
			return null;
		}

	}

	public Map<String, VisitEventBean> retrieveCartByUID(int uid) throws SQLException {
		if (uid > 0) {
			String query = "select * from VisitEvent WHERE eventtype='cart' and uid=" + uid + " order by day;";
			Map<String, VisitEventBean> rv = new HashMap<String, VisitEventBean>();
			Connection con = this.ds.getConnection();
			PreparedStatement p = con.prepareStatement(query);
			ResultSet r = p.executeQuery();
			while (r.next()) {
				String name = r.getString("id");

				VisitEventBean book = new VisitEventBean(r.getString("day"), r.getString("bid"), r.getInt("uid"),
						r.getString("eventtype"), r.getInt("quantity"));
				rv.put(name, book);
			}
			r.close();
			p.close();
			con.close();
			return rv;
		} else {
			return null;
		}

	}

	public boolean addToCart(String day, String bid, int uid, int quantity, double price) {
		return true;
	} // not done
}
