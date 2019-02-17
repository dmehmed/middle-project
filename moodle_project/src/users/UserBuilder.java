package users;

import helper.Helper;
import system.UsersStorage;

public abstract class UserBuilder {

	private static final String ADMIN_CREATION_COMMAND = "yes";
	private static final String COMMAND_SKIP_OPTIONAL_FIELDS_REGISTRATION = "skip";

	public static User createUser(String isAdmin, String username, String password, String firstName, String surname,
			String country, String city) {

		if (!UserBuilder.areRequiredFieldsValid(username, password, firstName, surname)) {
			return null;
		}

		if (UsersStorage.searchUser(username)) {
			System.out.println("There is user with this username");
			return null;
		}

		boolean isCountryFieldSkipped = country.toLowerCase().equals(COMMAND_SKIP_OPTIONAL_FIELDS_REGISTRATION);
		boolean isCityFieldSkipped = city.toLowerCase().equals(COMMAND_SKIP_OPTIONAL_FIELDS_REGISTRATION);

		Address userAddress = UserBuilder.generateAddress(country, city, isCountryFieldSkipped, isCityFieldSkipped);

		if (isAdmin.equals(ADMIN_CREATION_COMMAND)) {
			return new Admin(username, password, firstName, surname, userAddress);
		}

		return new User(username, password, firstName, surname, userAddress);
	}

	private static Address generateAddress(String country, String city, boolean isCountryFieldSkipped,
			boolean isCityFieldSkipped) {

		Address address = null;

		if (!isCountryFieldSkipped) {
			if (!isCityFieldSkipped) {
				address = Address.getAddress(country, city);
			} else {
				address = Address.getAddress(country, "");
			}
		} else {
			if (!isCityFieldSkipped) {
				address = Address.getAddress("", city);
			}
		}

		return address;
	}

	private static boolean areRequiredFieldsValid(String username, String password, String firstName, String surname) {
		if (!Helper.isValid(username)) {
			System.out.println("Invalid username!");
			return false;
		}

		if (!Helper.isValid(password) || password.length() < User.getMinPasswordLength()) {
			System.out.println("Invalid password!");
			return false;
		}

		if (!Helper.isValid(firstName)) {
			System.out.println("Invalid first name!");
			return false;
		}

		if (!Helper.isValid(surname)) {
			System.out.println("Invalid surname!");
			return false;
		}

		return true;
	}
}
