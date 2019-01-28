package courses;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import users.User;

public class Course {

	private static int counter = 0;

	private int id;
	private String title;
	private LocalDate start;
	private User lecturer; // for now it stay user
	private Set<User> students; // here too
	private List<Section> sections;

}
