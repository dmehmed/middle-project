package system;

import courses.Course;
import users.User;

public class System {

	private static System system_instance = null;
	private static UsersStorage usersStorage = new UsersStorage();
	private static CoursesStorage coursesStorage = new CoursesStorage();

	private System() {

	}

	public static System getInstance() {
		if (System.system_instance == null) {
			System.system_instance = new System();
		}

		return System.system_instance;
	}

	public static void addUser(User user) {
		if (user != null) {
			System.usersStorage.add(user);
		}
	}

	public static void removeUser(User user) {
		if (user != null) {
			System.usersStorage.remove(user);
		}
	}

	public static void addCourse(Course course) {
		if (course != null) {
			System.coursesStorage.add(course);
		}
	}

	public static void (Course course) {
		if (course != null) {
			System.coursesStorage.remove(course);
		}
	}

}
