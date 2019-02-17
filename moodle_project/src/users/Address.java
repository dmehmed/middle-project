package users;

public class Address {

	private String country; // optional
	private String city; // optional

	private Address() {
	}

	public static Address getAddress(String country, String city) {
		Address a = new Address();

		if (country != "") {
			a.setCountry(country);
		}

		if (city != "") {
			a.setCity(city);
		}

		return a;
	}

	@Override
	public String toString() {
		return (this.city != null ? this.city : "") + (this.country != null && this.city != null ? ", " : "")
				+ (this.country != null ? this.country : "");
	}

	void setCountry(String country) {
		this.country = country;
	}

	void setCity(String city) {
		this.city = city;
	}

}
