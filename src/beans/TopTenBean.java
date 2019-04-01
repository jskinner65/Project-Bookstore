package beans;

//Shows top ten as beans
public class TopTenBean {

	private String bid;
	private int count;

	public TopTenBean(String bid, int count) {
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
