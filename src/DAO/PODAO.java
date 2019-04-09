package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import beans.POBean;
import beans.TopTenBean;

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
			POBean book = new POBean(r.getInt("id"), r.getInt("uid"), r.getString("lname"), r.getString("fname"),
					r.getString("status"), r.getInt("address"), r.getString("day"));
			rv.put(name, book);
		}
		r.close();
		p.close();
		con.close();
		return rv;

	}

	public Map<String, TopTenBean> retrieveBetweenDates(Date d1, Date d2) throws SQLException {
		DateFormat df = new SimpleDateFormat("yyyymmdd");
		String beginning, end;
		if (d1.before(d2)) {
			beginning = df.format(d1);
			end = df.format(d2);
		} else {
			beginning = df.format(d2);
			end = df.format(d1);
		}
		System.out.println(beginning + " " + end);
		String query = "select book.bid, sum(quantity) as qty from book, po, poitem where po.id=poitem.id and book.bid=poitem.bid and po.day>'"
				+ beginning + "' and po.day<'" + end + "' group by book.bid order by qty desc;";
		Map<String, TopTenBean> rv = new HashMap<String, TopTenBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		int i = 1;
		while (r.next()) {
			String name = (i + "");
			TopTenBean book = new TopTenBean(i, r.getString("bid"), r.getInt("qty"));
			rv.put(name, book);
			i++;
		}
		r.close();
		p.close();
		con.close();
		return rv;

	}

	public Map<String, POBean> retrieveByUID(int uid) throws SQLException {

		String query = "select * from PO WHERE uid=" + uid + ";";
		Map<String, POBean> rv = new HashMap<String, POBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String name = r.getString("id");
			POBean book = new POBean(r.getInt("id"), r.getInt("uid"), r.getString("lname"), r.getString("fname"),
					r.getString("status"), r.getInt("address"), r.getString("day"));
			rv.put(name, book);
		}
		r.close();
		p.close();
		con.close();
		return rv;

	}

	public Map<String, POBean> retrieveByID(int ID) throws SQLException {

		String query = "select * from PO WHERE ID=" + ID + ";";
		Map<String, POBean> rv = new HashMap<String, POBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String name = r.getString("id");
			POBean book = new POBean(r.getInt("id"), r.getInt("uid"), r.getString("lname"), r.getString("fname"),
					r.getString("status"), r.getInt("address"), r.getString("day"));
			rv.put(name, book);
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}

	public int addPO(POBean newPO) throws SQLException {

		String query = "INSERT INTO PO (uid, lname, fname, status, address, day) VALUES (";
		query = query + newPO.getUid() + ", '" + newPO.getLname() + "', '" + newPO.getFname() + "', '"
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
