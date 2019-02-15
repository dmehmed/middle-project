package system;

import java.util.Scanner;

import helper.Helper;
import listeners.CommandListener;
import listeners.GuestCommandListener;
import users.Address;
import users.User;
import users.UserBuilder;

public class WebSystem implements IWebSystem {

	private static final int REMOVE_PARTICIPANT_IN_COURSE_COMMAND = 6;

	private static final int ADD_PARTICIPANT_IN_COURSE_COMMAND = 5;

	private static final int EXIT_SYSTEM_COMMAND = 0;
	private static final String COMMAND_SKIP_OPTIONAL_FIELDS_REGISTRATION = "skip";

	private static WebSystem systemInstance = null;
	private UsersStorage usersStorage = null;
	private CoursesStorage coursesStorage = null;
	private CommandListener listener = null;
	public static Scanner scanner = null;

	private WebSystem() {
		this.usersStorage = UsersStorage.getInstance();
		this.coursesStorage = CoursesStorage.getInstance();
		this.listener = GuestCommandListener.getInstance();
	}

	public static WebSystem getInstance() {
		if (WebSystem.systemInstance == null) {
			WebSystem.systemInstance = new WebSystem();
			WebSystem.scanner = new Scanner(System.in);
		}

		return WebSystem.systemInstance;
	}

	@Override
	public void start() {

		int command;

		do {

			this.listener.showMenu();

			command = scanner.nextInt();

			if (command == WebSystem.EXIT_SYSTEM_COMMAND) {
				return;
			}

			this.listener.execute(command);

		} while (true);

	}

	public void setListener(CommandListener listener) {
		this.listener = listener;
	}

	public User logUser() {

		System.out.println("Entry your username:");
		String username = WebSystem.scanner.next();
		System.out.println("Entry your password:");
		String password = WebSystem.scanner.next();

		if (!Helper.isValid(username)) {
			System.out.println("Invalid username!");
			return null;
		}

		if (!Helper.isValid(password)) {
			System.out.println("Invalid password!");
			return null;
		}

		return this.usersStorage.getUser(username, password);

	}

	public void createNewUser() {

		System.out.println("Fields marked with * are required!");
		System.out.println("To skip optional information write \"skip\"\n");

		System.out.println("* Enter username:");
		String username = scanner.next();
		System.out.println("* Enter password:");
		String password = scanner.next();
		System.out.println("* Enter first name:");
		String firstName = scanner.next();
		System.out.println("* Enter surname: ");
		String surname = scanner.next();
		System.out.println("Enter country:");
		String country = scanner.next();
		System.out.println("Enter city:");
		String city = scanner.next();
		System.out.println("Is this admin profile? Yes/No:");
		String isAdmin = scanner.next();

		if (!this.areRequiredFieldsValid(username, password, firstName, surname)) {
			return;
		}

		if (this.usersStorage.searchUser(username)) {
			System.out.println("There is user with this username");
			return;
		}

		boolean isCountryFieldSkipped = country.equals(COMMAND_SKIP_OPTIONAL_FIELDS_REGISTRATION);
		boolean isCityFieldSkipped = city.equals(COMMAND_SKIP_OPTIONAL_FIELDS_REGISTRATION);

		Address userAddress = this.generateAddress(country, city, isCountryFieldSkipped, isCityFieldSkipped);

		User newUser = UserBuilder.createUser(isAdmin, username, password, firstName, surname, userAddress);

		this.usersStorage.addUser(newUser);
		System.out.println("Successful user registration!");

	}

	public void showCourses() {
		this.coursesStorage.listAllCourses();
	}

	private Address generateAddress(String country, String city, boolean isCountryFieldSkipped,
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

	private boolean areRequiredFieldsValid(String username, String password, String firstName, String surname) {
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

//	public boolean registerUser(User user) {
//		if (user == null) {
//			java.lang.System.out.println("Invalid user argument given!");
//			return false;
//		}
//
//		if (this.usersStorage.add(user)) {
//			java.lang.System.out.println("Successful user registration!");
//			return true;
//		}
//
//		java.lang.System.out.println("The user alredy exists in Database!");
//		return false;
//	}
//
//	public boolean removeUser(User user) {
//		if (user == null) {
//			java.lang.System.out.println("Invalid user argument given!");
//			return false;
//		}
//
//		if (this.usersStorage.remove(user)) {
//			java.lang.System.out.println("User was successfully deleted from Database!");
//			return true;
//		}
//
//		java.lang.System.out.println("Such user does not exist!");
//		return false;
//	}
//
//	public boolean registerCourse(Course course) {
//		if (course == null) {
//			java.lang.System.out.println("Invalid course argument given!");
//			return false;
//		}
//
//		if (this.coursesStorage.add(course)) {
//			java.lang.System.out.println("Successful course registration!");
//			return true;
//		}
//
//		java.lang.System.out.println("The course alredy exists in Database!");
//		return false;
//	}
//
//	public boolean removeCourse(Course course) {
//		if (course == null) {
//			java.lang.System.out.println("Invalid course argument given!");
//			return false;
//		}
//
//		if (this.coursesStorage.remove(course)) {
//			java.lang.System.out.println("Course was successfully deleted from Database!");
//			return true;
//		}
//
//		java.lang.System.out.println("Such course does not exist!");
//		return false;
//	}
//
//	public void listAllUsers() {
//		this.usersStorage.listAll();
//	}
//
//	public void listAllCourses() {
//		this.coursesStorage.listAllCourses();
//	}
//
//}
