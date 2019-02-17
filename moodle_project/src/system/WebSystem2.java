package system;

import java.util.Scanner;

import helper.Helper;
import listeners.CommandListener;
import listeners.GuestCommandListener;
import users.User;
import users.UserBuilder;

public class WebSystem2 implements IWebSystem {

	private static final int EXIT_SYSTEM_COMMAND = 0;

	private static WebSystem2 systemInstance = null;
	private UsersStorage usersStorage = null;
	private CoursesStorage coursesStorage = null;
	private CommandListener listener = null;
	public static Scanner scanner = null;

	private WebSystem2() {
		this.usersStorage = UsersStorage.getInstance();
		this.coursesStorage = CoursesStorage.getInstance();
		this.listener = GuestCommandListener.getInstance();
		WebSystem2.scanner = new Scanner(System.in);
	}

	public static WebSystem2 getInstance() {
		if (WebSystem2.systemInstance == null) {
			WebSystem2.systemInstance = new WebSystem2();
		}

		return WebSystem2.systemInstance;
	}

	@Override
	public void start() {

		try {

			this.usersStorage.loadUSersDataFromJSONFile();
			this.coursesStorage.loadCoursesDataFromJSONFile();

			int command;

			do {

				this.listener.showMenu();

				command = scanner.nextInt();

				if (command == WebSystem2.EXIT_SYSTEM_COMMAND) {
					this.usersStorage.saveUsersDataToJSONFile();
					this.coursesStorage.saveCoursesDataToJSONFile();
					return;
				}

				this.listener.execute(command);

			} while (true);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Something's gone wrong...");
		}
	}

	public void setListener(CommandListener listener) {
		this.listener = listener;
	}

	public static Scanner getScanner() {
		return WebSystem2.scanner;
	}

	public User logUser() {

		System.out.println("Entry your username:");
		String username = WebSystem2.scanner.next();
		System.out.println("Entry your password:");
		String password = WebSystem2.scanner.next();

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
		System.out.println("* Enter password (min 6 symbols):");
		String password = scanner.next();
		System.out.println("* Enter first name:");
		String firstName = scanner.next();
		System.out.println("* Enter surname: ");
		String surname = scanner.next();
		scanner.nextLine();
		System.out.println("Enter country:");
		String country = scanner.nextLine();
		System.out.println("Enter city:");
		String city = scanner.nextLine();
		System.out.println("Is this admin profile? Yes/No:");
		String isAdmin = scanner.next();

		User newUser = UserBuilder.createUser(isAdmin, username, password, firstName, surname, country, city);
		this.usersStorage.addUser(newUser);

		System.out.println("Your registration was successful!");
	}

	public void showCourses() {
		this.coursesStorage.listCourses();
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
