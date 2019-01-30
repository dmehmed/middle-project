package system;

import java.util.HashSet;
import java.util.Set;

import users.User;

public class UsersStorage {
	private Set<User> users;

	UsersStorage() {
		this.users = new HashSet<User>();
	}

	
	boolean add(User user) {
		if (user == null) {
			return false;
		}
		
		if(!this.users.contains(user)) {
			this.users.add(user);
			return true;
		} else {
			return false;
		}
	}

	boolean remove(User user) {
		if (user == null) {
			return false;
		}
		
		if(!this.users.contains(user)) {
			return false;
		}
		
		this.users.remove(user);
		return true;
	}
	 
	void listAll() {
		if(this.users.isEmpty()) {
			java.lang.System.out.println("No users to list!");
			return;
		}
		
		for(User user : this.users) {
			java.lang.System.out.println(user);
		}
	}

}
