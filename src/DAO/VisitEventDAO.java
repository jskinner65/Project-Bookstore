package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
					r.getString("eventtype"), r.getInt("quantity"), r.getDouble("price"));
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
						r.getString("eventtype"), r.getInt("quantity"), r.getDouble("price"));
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
				String name = r.getString("day") + r.getString("bid") + r.getInt("uid");

				VisitEventBean book = new VisitEventBean(r.getString("day"), r.getString("bid"), r.getInt("uid"),
						r.getString("eventtype"), r.getInt("quantity"), r.getDouble("price"));
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

	public boolean removeFromCart(String day, String bid, int uid) throws SQLException {
		String query = "";
		query = "DELETE FROM visitevent where day='" + day + "' and bid='" + bid + "' and uid=" + uid + "; ";

		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		return p.execute();
	}

	public boolean changeToPurchased(Map<String, VisitEventBean> events) throws SQLException {
		String query = "";
		for (Entry<String, VisitEventBean> visit : events.entrySet()) {
			query = query + "update visitevent set eventtype='PURCHASE' where day='" + visit.getValue().getDay()
					+ "' and bid='" + visit.getValue().getBid() + "' and uid=" + visit.getValue().getUid() + "; ";
		}
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		return p.execute();

	}

	public boolean updateCart(String day, String bid, int uid, int newQuantity) throws SQLException {

		String query = "update visitevent set quantity=" + newQuantity + " where day='" + day + "' and bid='" + bid
				+ "' and uid=" + uid + ";";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		return p.execute();
	}

	public boolean addToCart(VisitEventBean visit) throws SQLException {

		String query = "insert into visitevent (day, bid, uid, eventtype, quantity, price) VALUES('" + visit.getDay()
				+ "', '" + visit.getBid() + "', '" + visit.getUid() + "', '" + visit.getEventtype() + "', '"
				+ visit.getQuantity() + "', '" + visit.getPrice() + "');";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		return p.execute();
	}
}
