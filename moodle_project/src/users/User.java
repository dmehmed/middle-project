package users;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import courses.Course;
import courses.Viewable;
import helper.Helper;
import system.CoursesStorage;

public class User {

	static int MIN_LENGTH_PASSWORD = 6;

	private boolean isAdmin;
	private final String username; // required
	private String password; // required
	private String firstName; // required
	private String surname; // required
	private Address address; // optional
	private boolean isOnline; // automatically generated
	protected Set<Course> courses; // sorted by day of creation from new to old

	User(String username, String password, String firstName, String surname, Address address) {
		this.isAdmin = false;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.surname = surname;
		this.address = address;
		this.courses = new TreeSet<Course>((c1, c2) -> {
			if (c1.getStart().equals(c2.getStart())) {
				return c1.getTitle().compareTo(c2.getTitle());
			}
			return c1.getStart().compareTo(c2.getStart());
		});
	}

	public void viewProfileInfo() {

		System.out.println("\nProfile Info:\n");

		System.out.println("Username: " + this.username);
		System.out.println("Name: " + this.firstName + " " + this.surname);
		if (this.address != null) {
			System.out.println("Address: " + this.address);
		}

		System.out.println();
	}

	public void listCourses() {

		System.out.println("\nMy courses: ");

		for (Viewable course : this.courses) {
			System.out.println(course);
		}

		System.out.println();
	}

	public void viewCourseInfo(String course) {

		System.out.println("Course");

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

	public boolean isThereCourse(String course) {

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
	
	public void removeCourse(Course course) {

		for (Iterator<Course> it = this.courses.iterator(); it.hasNext();) {
			Course currentCourse = it.next();
			if (currentCourse.equals(course)) {
				it.remove();
				return;
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

	public void addCourse(Course course) {
		this.courses.add(course);
	}

	public boolean isAdmin() {
		return this.isAdmin;
	}

	protected void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
