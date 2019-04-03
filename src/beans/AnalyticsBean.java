package beans;

public class AnalyticsBean {
	public String zipCode;
	public int uid;
	public double amount;

	public AnalyticsBean(String zipCode, int uid, double amount) {
		super();
		this.zipCode = zipCode;
		this.uid = uid;
		this.amount = amount;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
