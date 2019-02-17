package users;

import java.util.Set;
import java.util.TreeSet;

import courses.Course;
import courses.Viewable;
import helper.Helper;
import system.CoursesStorage;

public class User {

	static int MIN_LENGTH_PASSWORD = 6;

	private final String username; // required
	private String password; // required
	private String firstName; // required
	private String surname; // required
	private Address address; // optional
	private boolean isOnline; // automatically generated
	protected Set<Course> courses; // sorted by day of creation from new to old

	User(String username, String password, String firstName, String surname, Address address) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.surname = surname;
		this.address = address;
		this.courses = new TreeSet<Course>();
	}

	public void viewProfileInfo() {

		System.out.println("Username: " + this.username);
		System.out.println("Name: " + this.firstName + " " + this.surname);
		if (this.address != null) {
			System.out.println("Address: " + this.address);
		}
	}

	public void listCourses() {

		System.out.println("My courses: ");

		for (Viewable course : this.courses) {
			System.out.println(course);
		}

	}

	public void viewCourseInfo(String course) {

		if (isThereCourse(course)) {

			Course c = CoursesStorage.getInstance().getCourse(course);

			for (Viewable cs : this.courses) {
				if (cs.equals(c)) {
					cs.view();
					return;
				}
			}
		}

	}

	public void viewParticipantsInCourse(String course) {

		if (isThereCourse(course)) {

			Course c = CoursesStorage.getInstance().getCourse(course);

			for (Viewable cs : this.courses) {
				if (cs.equals(c)) {
					cs.viewParticipants();
					return;
				}
			}
		}
	}

	public void viewCourseGrade(String course) {

		if (isThereCourse(course)) {

			Course c = CoursesStorage.getInstance().getCourse(course);

			for (Viewable cs : this.courses) {
				if (cs.equals(c)) {
					cs.viewGrade(this);
					return;
				}
			}
		}
	}

	@Override
	public String toString() {
		return this.firstName + " " + this.surname;
	}

	@Override
	public int hashCode() {
		return this.username.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return Helper.isValid(obj) && obj instanceof User ? this.username.equals(((User) obj).username) : false;
	}

	public static int getMinPasswordLength() {
		return User.MIN_LENGTH_PASSWORD;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setOnline() {
		this.isOnline = true;
	}

	public void setOffline() {
		this.isOnline = false;
	}

	private boolean isThereCourse(String course) {

		Course c = CoursesStorage.getInstance().getCourse(course);

		if (c == null) {
			return false;
		}

		if (!this.courses.contains(c)) {
			System.out.println("You are not allowed to view this course!");
			return false;
		}

		return true;
	}

}
