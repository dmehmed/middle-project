package system;

import courses.Course;
import users.User;

public class System {

	private static System system_instance = null;
	private UsersStorage usersStorage = new UsersStorage();
	private CoursesStorage coursesStorage = new CoursesStorage();

	private System() {}

	
	public static System getInstance() {
		if (System.system_instance == null) {
			System.system_instance = new System();
		}

		return System.system_instance;
	}


	public boolean registerUser(User user) {
		if(user == null) {
			java.lang.System.out.println("Invalid user argument given!");
			return false;
		}

		if (this.usersStorage.add(user)) {
			java.lang.System.out.println("Successful user registration!");
			return true;
		}
		
		java.lang.System.out.println("The user alredy exists in Database!");
		return false;
	}
	
	public boolean removeUser(User user) {
		if (user == null) {
			java.lang.System.out.println("Invalid user argument given!");
			return false;
		}
		
		if(this.usersStorage.remove(user)) {
			java.lang.System.out.println("User was successfully deleted from Database!");
			return true;
		}
		
		java.lang.System.out.println("Such user does not exist!");
		return false;
	}
	
	
	public boolean registerCourse(Course course) {
		if(course == null) {
			java.lang.System.out.println("Invalid course argument given!");
			return false;
		}
		
		if(this.coursesStorage.add(course)) {
			java.lang.System.out.println("Successful course registration!");
			return true;
		}
		
		java.lang.System.out.println("The course alredy exists in Database!");
		return false;
	}

	public boolean removeCourse(Course course) {
		if (course == null) {
			java.lang.System.out.println("Invalid course argument given!");
			return false;
		}
		
		if(this.coursesStorage.remove(course)) {
			java.lang.System.out.println("Course was successfully deleted from Database!");
			return true;
		}
		
		java.lang.System.out.println("Such course does not exist!");
		return false;
	}
	
	
	public void listAllUsers() {
		this.usersStorage.listAll();
	}
	
}
