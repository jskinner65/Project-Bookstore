package beans;

//Shows top ten as beans
public class TopTenBean {
	private int place;
	private String bid;
	private int count;

	public TopTenBean(int place, String bid, int count) {
		this.place = place;
		this.bid = bid;
		this.count = count;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
