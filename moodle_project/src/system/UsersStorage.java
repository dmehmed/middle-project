package system;

import java.util.HashSet;
import java.util.Set;

import users.User;

public class UsersStorage {
	private Set<User> users;

	public UsersStorage() {
		this.users = new HashSet<User>();
	}

	void add(User user) {
		if (user != null) {
			this.users.add(user);
		}
	}

	void remove(User user) {
		if (user != null) {
			this.users.remove(user);
		}
	}

	/*
	 * void search(User user) { if(user != null) {
	 * 
	 * } }
	 */

}
