package system;

import courses.Course;
import java.util.HashSet;
import java.util.Set;

public class CoursesStorage {

	private Set<Course> courses;

	CoursesStorage() {
		this.courses = new HashSet<Course>();
	}

	boolean add(Course course) {
		if (course == null) {
			return false;
		}

		if (!this.courses.contains(course)) {
			this.courses.add(course);
			return true;
		} else {
			return false;
		}
	}

	boolean remove(Course course) {
		if (course == null) {
			return false;
		}

		if (!this.courses.contains(course)) {
			return false;
		}

		this.courses.remove(course);
		return true;
	}

	void listAllCourses() {
		if (this.courses.isEmpty()) {
			java.lang.System.out.println("No courses to list!");
			return;
		}

		for (Course course : courses) {
			java.lang.System.out.println(course);
		}

	}

}
