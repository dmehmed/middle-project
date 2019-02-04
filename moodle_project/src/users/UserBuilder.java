package users;

public abstract class UserBuilder {

	private static final String ADMIN_CREATION_COMMAND = "yes";

	public static User createUser(String isAdmin, String username, String password, String firstName, String surname,
			Address address) {

		if (isAdmin.equals(ADMIN_CREATION_COMMAND)) {
			return new Admin(username, password, firstName, surname, address);
		}

		return new User(username, password, firstName, surname, address);
	}

}
