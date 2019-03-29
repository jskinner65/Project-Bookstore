package beans;

public class BookBean {
	private String bid;
	private String title;
	private String picture;
	private double price;
	private String category;
	private String courseCode;
	private String courseTitle;
	private String description;

	public BookBean(String bid, String title, String picture, double price, String category, String courseCode,
			String courseTitle, String description) {
		super();
		this.bid = bid;
		this.title = title;
		this.picture = picture;
		this.price = price;
		this.category = category;
		this.courseCode = courseCode;
		this.courseTitle = courseTitle;
		this.description = description;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
