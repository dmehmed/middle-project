package courses;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import documents.Section;
import users.User;

public class Course {

	private static int counter = 0;

	private int id;
	private String title;
	private LocalDate start;
	private User lecturer; // засега го оставям user
	private Set<User> students;
	private List<Section> sections;

	private Course(String title) {

	}

}
