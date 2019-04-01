package beans;

public class VisitEventBean {
	private String day;
	private String bid;
	private int uid;
	private String eventtype;
	private int quantity;

	public VisitEventBean(String day, String bid, int uid, String eventtype, int quantity) {
		super();
		this.day = day;
		this.bid = bid;
		this.eventtype = eventtype;
		this.uid = uid;
		this.quantity = quantity;
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

}
