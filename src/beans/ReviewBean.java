package beans;

public class ReviewBean {

	private int reviewID;
	private int rating;
	private String bid;
	private int uid;
	private String reviewtext;

	public ReviewBean(int reviewID, int rating, String bid, int uid, String reviewtext) {
		super();
		this.reviewID = reviewID;
		this.rating = rating;
		this.bid = bid;
		this.uid = uid;
		this.reviewtext = reviewtext;
	}

	public int getReviewID() {
		return reviewID;
	}

	public void setReviewID(int reviewID) {
		this.reviewID = reviewID;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getReviewtext() {
		return reviewtext;
	}

	public void setReviewtext(String reviewtext) {
		this.reviewtext = reviewtext;
	}

}
