package beans;

public class AddressBean {
	private int id;
	private int uid;
	private String street;
	private String province;
	private String country;
	private String zip;
	private String phone;
	private String addressType;

	public AddressBean(int id, int uid, String street, String province, String country, String zip, String phone,
			String addressType) {
		super();
		this.id = id;
		this.setUid(uid);
		this.street = street;
		this.province = province;
		this.country = country;
		this.zip = zip;
		this.phone = phone;
		this.setAddressType(addressType);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		String result = this.getId() + ", " + this.getStreet() + ", " + this.getProvince() + ", " + this.getCountry()
				+ ", " + this.getPhone();
		return result;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

}
