package users;

import java.util.Set;
import java.util.TreeSet;

import courses.Course;
import courses.Updatable;
import courses.Viewable;
import system.CoursesStorage;

public class Admin extends User {

	private Set<Updatable> courses;

	Admin(String username, String password, String firstName, String surname, Address address) {
		super(username, password, firstName, surname, address);
		this.courses = new TreeSet<Updatable>();
	}

	public void viewProfileInfo() {
		super.viewProfileInfo();
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
