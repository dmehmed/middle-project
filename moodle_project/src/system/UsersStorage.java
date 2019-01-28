package system;

import java.util.HashSet;
import java.util.Set;

public class UsersStorage {
	private Set<User> users;
		
	public UserStorage() {
		this.users = new HashSet<User>();
	}
		
	void add(User user) {
		if(user != null) {
			this.users.add(user);
		}
	}
	
	void remove(User user) {
		if(user != null) {
			this.users.remove(user);
		}
	}
	
	/*void search(User user) {
		if(user != null) {
			
		}
	}*/
	
}
