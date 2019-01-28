package users;

import exceptions.NameException;
import exceptions.ObjectCreationException;
import helper.Helper;

public class Address {

	private String country; // required
	private String city; // required
	private String street; // optional

	Address(String country, String city) throws ObjectCreationException {
		try {
			this.setCountry(country);
			this.setCity(city);
		} catch (NameException e) {
			e.printStackTrace();
			throw new ObjectCreationException("Cannot create address", e);
		}
	}

	Address(String country, String city, String street) throws ObjectCreationException {
		this(country, city);

		try {
			this.setStreet(street);
		} catch (NameException e) {
			e.printStackTrace();
			throw new ObjectCreationException("Cannot create address", e);
		}
	}

	@Override
	public String toString() {
		return this.country + ", " + this.city + (street != null ? ", " + this.street : "");
	}

	private void setCountry(String country) throws NameException {
		if (!Helper.isValid(country)) {
			throw new NameException("Invalid address country name!");
		}

		this.country = country;
	}

	private void setCity(String city) throws NameException {
		if (!Helper.isValid(city)) {
			throw new NameException("Invalid address city name!");
		}

		this.city = city;
	}

	private void setStreet(String street) throws NameException {
		if (!Helper.isValid(street)) {
			throw new NameException("Invalid address country!");
		}

		this.street = street;
	}

}
