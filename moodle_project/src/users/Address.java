package users;

import exceptions.NameException;
import exceptions.ObjectCreationException;
import helper.Helper;

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
		return (this.country != null ? this.country : "") + (this.country != null && this.city != null ? ", " : "")
				+ (this.city != null ? this.city : "");
	}

	private void setCountry(String country) {
		this.country = country;
	}

	private void setCity(String city) {
		this.city = city;
	}

}
