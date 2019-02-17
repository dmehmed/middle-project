package system;

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import exceptions.NullObjectException;
import helper.Helper;
import users.User;

public class UsersStorage {

	private static UsersStorage storage = null;
	private Map<String, User> users;
	private JSONWriter writer = JSONWriter.getInstance();

	private UsersStorage() {
		this.users = new HashMap<String, User>();
	}

	
	public static UsersStorage getInstance() {
		if (UsersStorage.storage == null) {
			UsersStorage.storage = new UsersStorage();
		}
		return UsersStorage.storage;
	}

	
	public void loadData() {
		Gson gson = new GsonBuilder().create();
		
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(
				new FileInputStream(
						new File(".\\users_json_files\\users.json")))))
		{
			
			Type type = new TypeToken<Map<String, User>>(){}.getType();
			this.users = gson.fromJson(buffer, type);
			//System.out.println(user.toString());
			//this.users.put(user.getUsername(), user);
			for(Entry<String, User> e : this.users.entrySet()) {
				System.out.println(e.getKey() + " " + e.getValue());
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}	
		
	}
	
	
	public User getUser(String username) {

		if (!this.users.containsKey(username)) {
			System.out.println("Such user does not exist!");
			return null;
		}

		User user = this.users.get(username);
		return user;
	}

	public User getUser(String username, String password) {

		if (!this.users.containsKey(username)) {
			System.out.println("Such user does not exist!");
			return null;
		}

		User user = this.users.get(username);

		if (!user.getPassword().equals(password)) {
			System.out.println("Wrong password!");
			return null;
		}

		user.setOnline();
		return user;
	}

	public void addUser(User newUser) {
		if (!Helper.isValid(newUser)) {
			return;
		}

		this.users.put(newUser.getUsername(), newUser);
//		try {
//			writer.writeObjectToJSONFile(newUser);
//		} catch (NullObjectException e) {
//			e.printStackTrace();
//		}
	}

	public void saveUsersDataToJSONFile() {
		try {
			this.writer.writeObjectToJSONFile(this.users);
			System.out.println("Successful user registration!");
		} catch (NullObjectException e) {
			e.printStackTrace();
		}
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

	public static boolean searchUser(String username) {
		return UsersStorage.storage.users.containsKey(username);
	}

}
