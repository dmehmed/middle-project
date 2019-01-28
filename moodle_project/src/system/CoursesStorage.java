package system;
import courses.Course;

import courses.Course;
import java.util.HashSet;
import java.util.Set;

public class CoursesStorage {
	private Set<Course> courses;
	
	public CoursesStorage() {
		this.courses = new HashSet<Course>();
	}
		
	void add(Course course) {
		if(course != null) {
			this.courses.add(course);
		}
	}
	
	void remove(Course course) {
		if(course != null) {
			this.courses.remove(courses);
		}
	}
	
}
