package system;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import courses.Course;
import exceptions.NullObjectException;

public class CoursesStorage {

	private static CoursesStorage instance = null;
	private Map<String, Course> courses;
	private JSONWriter writer = JSONWriter.getInstance();

	private CoursesStorage() {
		this.courses = new HashMap<String, Course>();
	}

	public static CoursesStorage getInstance() {
		if (CoursesStorage.instance == null) {
			CoursesStorage.instance = new CoursesStorage();
		}

		return CoursesStorage.instance;
	}

	public Course getCourse(String course) {
		if (!this.courses.containsKey(course)) {
			System.out.println("There is no such course in system!");
			return null;
		}

		return this.courses.get(course);

	}

	public void listCategories() {
		this.courses.keySet().stream().forEach(System.out::println);
	}

	public void add(Course course) {
		if (course == null) {
			System.out.println("Invalid course");
			return;
		}

		if (!this.courses.containsKey(course.getTitle())) {
			this.courses.put(course.getTitle(), course);
		} else {
			System.out.println("Course with that name already exists!");
			return;
		}
//			try {
//				this.writer.writeObjectToJSONFile(course);
//			} catch (NullObjectException e) {
//				e.printStackTrace();
//			}

	}

	boolean remove(Course course) {
		if (course == null) {
			return false;
		}

		if (!this.courses.containsKey(course)) {
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

//		for (Course course : courses) {
//			java.lang.System.out.println(course);
//		}

	}

}
