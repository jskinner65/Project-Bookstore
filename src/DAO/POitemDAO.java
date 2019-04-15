package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import beans.POitemBean;
import beans.TopTenBean;

public class POitemDAO {
	DataSource ds;

	public POitemDAO(DataSource passedDS) throws ClassNotFoundException {

		ds = passedDS;

	}

	public boolean addPOItem(POitemBean poItem) throws SQLException {
		String query = "insert into poitem (id, bid, quantity, price) VALUES('" + poItem.getId() + "', '"
				+ poItem.getBid() + "', '" + poItem.getQuantity() + "', '" + poItem.getPrice() + "');";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		return p.execute();
	}

	public Map<String, TopTenBean> retrieveTen() throws SQLException {
		String query = "select bid, sum(quantity) as cBid from POitem group by BID order by cBid desc limit 10;";
		Map<String, TopTenBean> rv = new HashMap<String, TopTenBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		int i = 1;
		while (r.next()) {
			String name = i + "";
			TopTenBean bean = new TopTenBean(i, r.getString("bid"), r.getInt("cBid"));
			rv.put(name+r.getString("bid"), bean);
			i++;
		}
		r.close();
		p.close();
		con.close();
		return rv;

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

	public Map<String, POitemBean> retrieveByPO(int PO) throws SQLException {

		String query = "select * from POitem where id=" + PO + ";";
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
	
	public Map<String, POitemBean> mostPopular(int PO) throws SQLException {
		String query = "select * from PO where status=ORDERED;";
		
		Map<String, POitemBean> rv = new HashMap<String, POitemBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		
		while (r.next()) {
			String name = r.getString("day");
			POitemBean book = new POitemBean(r.getInt("id"), r.getString("bid"), r.getInt("quantity"),
					r.getInt("price"));
			rv.put(name, book);
			name.substring(3,6);
			System.out.println(name.substring(3,6));
		}
		
		r.close();
		p.close();
		con.close();
		return rv;
	}
}
