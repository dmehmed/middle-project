package courses;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import exceptions.NameException;
import exceptions.NullObjectException;
import exceptions.ObjectCreationException;
import helper.Helper;
import users.Admin;
import users.User;

public class Course {

	private static final int AVERAGE_SECTIONS_PER_COURSE = 20;

	private static int counter = 0;

	private int id; // automatically generation
	private String title; // required
	private LocalDate start; // automatically generation when course is created
	private Admin lecturer; // required!
	private Set<User> students; // elements in it are optional but memory must be allocated
	private List<Section> sections; // elements in it are optional but memory must be allocated

	private Course(String title, Admin lecturer) throws ObjectCreationException {

		try {
			this.setTitle(title);
			this.setLecturer(lecturer);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ObjectCreationException("Cannot create course!", e);
		}

		this.id = ++counter;
		this.start = LocalDate.now();
		this.students = new HashSet<User>();
		this.sections = new ArrayList<Section>(AVERAGE_SECTIONS_PER_COURSE);

	}

	// generate getters and setters for everything if we don't need them we will
	// remove them

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	private void setTitle(String title) throws NameException {
		if (!Helper.isValid(title)) {
			throw new NameException("Invalid course title!");
		}

		this.title = title;
	}

	public LocalDate getStart() {
		return start;
	}

	public Admin getLecturer() {
		return lecturer;
	}

	private void setLecturer(Admin lecturer) throws NullObjectException {
		if (Helper.isValid(lecturer)) {
			throw new NullObjectException("Invalid lecturer!");
		}

		this.lecturer = lecturer;
	}

}
