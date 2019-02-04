package system;

import java.util.Scanner;

import helper.Helper;
import users.Address;
import users.User;
import users.UserBuilder;

public class WebSystem implements IWebSystem {

	private static final int REMOVE_PARTICIPANT_IN_COURSE_COMMAND = 6;

	private static final int ADD_PARTICIPANT_IN_COURSE_COMMAND = 5;

	private static final int LOG_OUT_COMMAND = 6;

	private static final int VIEW_COURSE_GRADE_COMMAND = 5;

	private static final int VIEW_PARTICIPANTS_IN_COURSE_COMMAND = 4;

	private static final int VIEW_COURSE_INFO_COMMAND = 3;

	private static final int VIEW_USER_COURSES_COMMAND = 2;

	private static final int VIEW_PROFILE_COMMAND = 1;

	public static Scanner scanner = null;

	private static final int LOG_IN_COMMAND = 1;
	private static final int CREATE_NEW_USER_COMMAND = 2;
	private static final int LIST_COURSES_BY_CATEGORIES_COMMAND = 3;
	private static final int EXIT_SYSTEM_COMMAND = 0;
	private static final String COMMAND_SKIP_OPTIONAL_FIELDS_REGISTRATION = "skip";

	private static WebSystem systemInstance = null;
	private UsersStorage usersStorage = null;
	private CoursesStorage coursesStorage = null;

	private WebSystem() {
		usersStorage = UsersStorage.getInstance();
		coursesStorage = CoursesStorage.getInstance();
	}

	public static WebSystem getInstance() {
		if (WebSystem.systemInstance == null) {
			WebSystem.systemInstance = new WebSystem();
			scanner = new Scanner(System.in);
		}

		return WebSystem.systemInstance;
	}

	@Override
	public void showMenu() {

		int command;

		do {
			System.out.println("Choose option:");
			System.out.println("1 - Log in");
			System.out.println("2 - Create new account");
			System.out.println("3 - View course categories\n");
			System.out.println("0 - Exit");

			command = scanner.nextInt();

			switch (command) {
			case LOG_IN_COMMAND:
				User user = this.logUser();

				if (Helper.isValid(user)) {
					if (user instanceof User) {

						do {
							this.showUserMenu();
							command = scanner.nextInt();
							if (command == EXIT_SYSTEM_COMMAND) {
								return;
							}

							if (command == VIEW_PROFILE_COMMAND) {
								user.viewProfileInfo();
							}

							if (command == VIEW_USER_COURSES_COMMAND) {
								user.listCourses();
							}

							if (command == VIEW_COURSE_INFO_COMMAND) {
								System.out.print("Enter course name: ");
								String course = scanner.nextLine();
								user.viewCourseInfo(course);
							}

							if (command == VIEW_PARTICIPANTS_IN_COURSE_COMMAND) {
								System.out.print("Enter course name: ");
								String course = scanner.nextLine();
								user.viewParticipantsInCourse(course);
							}

							if (command == VIEW_COURSE_GRADE_COMMAND) {
								System.out.print("Enter course name: ");
								String course = scanner.nextLine();
								user.viewCourseGrade();
							}

							if (command == LOG_OUT_COMMAND) {
								break;
							}

						} while (true);
					} else {
						
						do {
		
							this.showAdminMenu();
							command = scanner.nextInt();
							if (command == EXIT_SYSTEM_COMMAND) {
								return;
							}
							
							if (command == VIEW_PROFILE_COMMAND) {
								user.viewProfileInfo();
							}

							if (command == VIEW_USER_COURSES_COMMAND) {
								user.listCourses();
							}

							if (command == VIEW_COURSE_INFO_COMMAND) {
								System.out.print("Enter course name: ");
								String course = scanner.nextLine();
								user.viewCourseInfo(course);
							}

							if (command == VIEW_PARTICIPANTS_IN_COURSE_COMMAND) {
								System.out.print("Enter course name: ");
								String course = scanner.nextLine();
								user.viewParticipantsInCourse(course);
							}
//
//							if (command == ADD_PARTICIPANT_IN_COURSE_COMMAND) {
//								user.addParticipantInCourse(course, participant);
//							}
//
//							if(command == REMOVE_PARTICIPANT_IN_COURSE_COMMAND) {
//								user.removeParticipantInCourse(course, participant);
//							}
//							
//							if(command == ) { 
//								
//							}
							
							if (command == LOG_OUT_COMMAND) {
								break;
							}

						} while (true);
					}
				}

				break;
			case CREATE_NEW_USER_COMMAND:
				this.createNewUser();
				break;
			case LIST_COURSES_BY_CATEGORIES_COMMAND:
				this.coursesStorage.listCategories();
				break;
			case EXIT_SYSTEM_COMMAND:
				return;
			default:
				System.out.println("Invalid command!");
				break;
			}

			System.out.println();

		} while (true);

	}

	private void showUserMenu() {
		System.out.println("User menu:");
		System.out.println("1 - View profile");
		System.out.println("2 - View my courses");
		System.out.println("3 - View course");
		System.out.println("4 - View participants in course");
		System.out.println("5 - View course grade");
		System.out.println("6 - Log out");
		System.out.println();
		System.out.println("0 - Exit");
	}

	private void showAdminMenu() {
		System.out.println("User menu:");
		System.out.println("1 - View profile");
		System.out.println("2 - View my courses");
		System.out.println("3 - View course");
		System.out.println("4 - View participants in course");
		System.out.println("5 - Add participant in course");
		System.out.println("6 - Remove participant in course");
		System.out.println("7 - Add section in course");
		System.out.println("8 - Remove section in course");
		System.out.println("9 - Add document in course");
		System.out.println("10 - Remove document in course");
		System.out.println("11 - Log out");
		System.out.println();
		System.out.println("0 - Exit");
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
