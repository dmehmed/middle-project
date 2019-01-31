package system;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import users.User;

public class UsersStorage {

	private static UsersStorage storage = null;
	private Map<String, User> users;

	private UsersStorage() {
		this.users = new HashMap<String, User>();
	}

	public User getUser(String username) {

		if (!users.containsKey(username)) {
			java.lang.System.out.println("There is no such user!");
			return null;
		}

		return this.users.get(username);
	}

	public static UsersStorage getInstance() {
		if (UsersStorage.storage == null) {
			UsersStorage.storage = new UsersStorage();
		}

		return UsersStorage.storage;
	}

	boolean add(User user) {
		if (user == null) {
			return false;
		}

		if (!this.users.containsKey(user.getUsername())) {
			this.users.put(user.getUsername(), user);
			return true;
		} else {
			return false;
		}
	}

	boolean remove(User user) {
		if (user == null) {
			return false;
		}

		if (!this.users.containsKey(user)) {
			return false;
		}

		this.users.remove(user);
		return true;
	}

	void listAll() {
		if (this.users.isEmpty()) {
			java.lang.System.out.println("No users to list!");
			return;
		}

		for (Entry<String, User> entry : this.users.entrySet()) {
			java.lang.System.out.println(entry.getValue());
		}
	}

}
