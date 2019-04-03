package beans;

public class VisitEventBean {
	private String day;
	private String bid;
	private int uid;
	private String eventtype;
	private int quantity;
	private double price;

	public VisitEventBean(String day, String bid, int uid, String eventtype, int quantity, double price) {
		super();
		this.day = day;
		this.bid = bid;
		this.eventtype = eventtype;
		this.setUid(uid);
		this.setQuantity(quantity);
		this.setPrice(price);
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getEventtype() {
		return eventtype;
	}

	public void setEventtype(String eventtype) {
		this.eventtype = eventtype;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

}
