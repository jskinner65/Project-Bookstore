package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import beans.BookBean;

public class BookDAO {

	DataSource ds;

	public BookDAO(DataSource passedDS) throws ClassNotFoundException {
		ds = passedDS;
	}

	public boolean addBook(BookBean book) throws SQLException {
		String query = "insert into book (bid, title, picture, price, category, courseCode, courseTitle, description) VALUES('"
				+ book.getBid() + "', '" + book.getTitle() + "', '" + book.getPicture() + "', '" + book.getPrice()
				+ "', '" + book.getCategory() + "', '" + book.getCourseCode() + "', '" + book.getCourseTitle() + "', '"
				+ book.getDescription() + "');";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		return p.execute();
	}

	private Map<String, BookBean> retrieveFromQuery(String query) throws SQLException {
		Map<String, BookBean> rv = new HashMap<String, BookBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String name = r.getString("bid");
			BookBean book = new BookBean(r.getString("bid"), r.getString("title"), r.getString("picture"),
					r.getInt("price"), r.getString("category"), r.getString("courseCode"), r.getString("courseTitle"),
					r.getString("description"));
			rv.put(name, book);
		}
		r.close();
		p.close();
		con.close();
		return rv;

	}

	public Map<String, BookBean> retrieveAll() throws SQLException {

		String query = "select * from Book";
		return this.retrieveFromQuery(query);

	}

	public Map<String, BookBean> retrieve(String namePrefix) throws SQLException {
		if (namePrefix.trim().contains(" ")) {
			return null;
		} else {
			if ((namePrefix != null) || (namePrefix != "") || namePrefix.equals("null")) {
				namePrefix = " WHERE title like '%" + namePrefix + "%' or description like '%" + namePrefix + "%' ;";
			}
			String query = "select * from Book" + namePrefix;
			return this.retrieveFromQuery(query);
		}
	}

	public Map<String, BookBean> retrieveByBID(String bid) throws SQLException {
		if (bid.trim().contains(" ")) {
			return null;
		} else {
			String query = "select * from Book WHERE bid='" + bid + "';";
			return this.retrieveFromQuery(query);
		}
	}

	public boolean updateBook(BookBean book) throws SQLException {
		String query = "update book SET title='" + book.getTitle() + "', picture='" + book.getPicture() + "', price="
				+ book.getPrice() + ", category='" + book.getCategory() + "', courseCode='" + book.getCourseCode()
				+ "', courseTitle='" + book.getCourseTitle() + "', description='" + book.getDescription()
				+ "' WHERE bid ='" + book.getBid() + "';";
		try {
			Connection con = this.ds.getConnection();
			PreparedStatement p = con.prepareStatement(query);
			p.execute();
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

	public boolean dropBook(String bid) throws SQLException {
		String query = "delete from  book where bid='" + bid + "';";
		try {
			Connection con = this.ds.getConnection();
			PreparedStatement p = con.prepareStatement(query);
			p.execute();
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}
}
