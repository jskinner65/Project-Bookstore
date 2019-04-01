package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import beans.ReviewBean;

public class ReviewDAO {
	DataSource ds;

	public ReviewDAO(DataSource passedDS) throws ClassNotFoundException {

		ds = passedDS;

	}

	public boolean addReivew(ReviewBean review) throws SQLException {
		String query = "insert into review (reviewID, rating, bid, uid, reviewtext) VALUES('" + review.getReviewID()
				+ "', '" + review.getRating() + "', '" + review.getBid() + "', '" + review.getUid() + "', '"
				+ review.getReviewtext() + "');";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		return p.execute();
	}

	public Map<String, ReviewBean> getReviews(String bid) throws SQLException {

		String query = "select * from Reviews where bid=" + bid;
		Map<String, ReviewBean> rv = new HashMap<String, ReviewBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String name = r.getInt("reviewID") + "";
			ReviewBean review = new ReviewBean(r.getInt("reviewID"), r.getInt("rating"), r.getString("bid"),
					r.getInt("uid"), r.getString("reviewtext"));
			rv.put(name, review);
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}
}
