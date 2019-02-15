package courses;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import exceptions.NameException;
import exceptions.NullObjectException;
import helper.Helper;
import system.UsersStorage;
import users.Admin;
import users.User;

public class Course implements Updatable {

	private String title; // required
	private final LocalDate start; // automatically generation when course is created
	private Admin lecturer; // required!
	private Set<User> students; // elements in it are optional but memory must be allocated //map<username,
								// integer>
	private Map<String, Set<Document>> sections; // elements in it are optional but memory must be allocated

	Course(String title, Admin lecturer) {

		this.title = title;
		this.lecturer = lecturer;
		this.start = LocalDate.now();
		this.students = new HashSet<User>();
		this.sections = new LinkedHashMap<String, Set<Document>>();

	}

	// it is not necessary to have factory method
	public static Course getInstance(String title, Admin lecturer) throws NullObjectException, NameException {
		if (!Helper.isValid(title)) {
			throw new NameException("Invalid course title!");
		}

		if (!Helper.isValid(lecturer)) {
			throw new NullObjectException("Invalid lecturer!");
		}

		return new Course(title, lecturer);
		// izvikai sistemata da go dobavi

	}

	@Override
	public void view() {
		for (Entry<String, Set<Document>> entry : this.sections.entrySet()) {
			System.out.println(entry.getKey() + "\n");

			entry.getValue().forEach(System.out::println);

			System.out.println();
		}
	}

	@Override
	public void addMember(String username) throws NullObjectException {

		User user = UsersStorage.getInstance().getUser(username);

		if (!Helper.isValid(user)) {
			throw new NullObjectException("Invalid user!");
		}

		if (this.students.contains(user)) {
			System.out.println("User " + user.getUsername() + " is already in " + this.getTitle() + " course.");
			return;
		}

		this.students.add(user);
	}

	@Override
	public void removeMember(String username) {

		User user = UsersStorage.getInstance().getUser(username);

		// in method add we have validation for null user but we can return if user is
		// null or collection doesn't contains it

		if (!Helper.isValid(user) || !this.students.contains(user)) {
			return;
		}

		this.students.remove(user);
		System.out.println("You removed user " + user.getUsername() + " from " + this.getTitle() + " course.");
	}

	@Override
	public void addSection(String sectionTitle) throws NameException {

		if (!Helper.isValid(sectionTitle)) {
			throw new NameException("Invalid section title!");
		}

		if (this.sections.containsKey(sectionTitle)) {
			System.out.println("You already have " + sectionTitle + " section");
			return;
		}

		this.sections.put(sectionTitle, new HashSet<Document>());

	}

	@Override
	public void removeSection(String sectionTitle) {

		if (!Helper.isValid(sectionTitle)) {
			return;
		}

		if (!this.sections.containsKey(sectionTitle)) {
			return;
		}

		this.sections.remove(sectionTitle);
		System.out.println("You removed section " + sectionTitle + " from " + this.getTitle() + " course.");
	}

	@Override
	public void addDocument(String sectionTitle, Document document) throws NameException {
		if (!Helper.isValid(sectionTitle)) {
			throw new NameException("Invalid section title!");
		}

		if (!Helper.isValid(document)) {
			throw new NameException("Invalid document!");
		}

		if (!this.sections.containsKey(sectionTitle)) {
			System.out.println("There is no such section in " + this.getTitle() + " course.");
			return;
		}

		this.sections.get(sectionTitle).add(document);
	}

	@Override
	public void removeDocument(String sectionTitle, Document document) {
		if (!Helper.isValid(sectionTitle) || !Helper.isValid(document)) {
			return;
		}

		if (!this.sections.containsKey(sectionTitle)) {
			System.out.println("There is no such section in " + this.getTitle() + " course.");
			return;
		}

		this.sections.get(sectionTitle).remove(document);
	}

	@Override
	public int hashCode() {
		return this.title.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return Helper.isValid(obj) && obj instanceof Course ? this.title.equals(((Course) obj).title) : false;
	}

	// generate getters and setters for everything if we don't need them we will
	// remove them

	public String getTitle() {
		return this.title;
	}

	public LocalDate getStart() {
		return start;
	}

	public Admin getLecturer() {
		return lecturer;
	}

	// try methods

//	public static void main(String[] args) {
//		try {
//			Course c = Course.getInstance("Java EE", new Admin("Petur"));
//
//			UsersStorage.getInstance().add(new User("deniz.mehmed")); za da testvash napravi add public
//			UsersStorage.getInstance().add(new User("ivo.kovachev"));
//			UsersStorage.getInstance().add(new User("nikolay.tomitov"));
//			UsersStorage.getInstance().add(new User("krasimir.stoev"));
//
//			c.addMember("deniz.mehmed");
//			c.addMember("ivo.kovachev");
//			c.addMember("nikolay.tomitov");
//			c.addMember("krasimir.stoev");
//			c.addMember("krasimir.stoev");
//
//			System.out.println(c.students.size());
//
//			for (User user : c.students) {
//				System.out.println(user);
//			}
//
//			System.out.println();
//			c.removeMember(null);
//			System.out.println("---------------------------");
//
//			c.removeMember("deniz.mehmed");
//			System.out.println();
//
//			for (User user : c.students) {
//				System.out.println(user);
//			}
//
//			System.out.println("---------------------------");
//			
//			c.addSection("Lecture 1");
//
//			c.addSection("Lecture 3");
//			c.addSection("Lecture 12");
//			c.addSection("Lecture 13");
//
//			c.addDocument("Lecture 1", new Document("Deni.pdf", "D:\\Help"));
//			c.addDocument("Lecture 1", new Document("Jeni.pdf", "D:\\Help"));
//			c.addDocument("Lecture 3", new Document("Kemi.pdf", "D:\\Help"));
//
//			c.addDocument("Lecture 12", new Document("Deni.pdf", "D:\\Help"));
//			c.addDocument("Lecture 12", new Document("Jeni.pdf", "D:\\Help"));
//			c.addDocument("Lecture 13", new Document("Kemi.pdf", "D:\\Help"));
//
//			c.view();
//		} catch (NameException e) {
//			e.printStackTrace();
//		} catch (NullObjectException e) {
//			e.printStackTrace();
//		} catch (ObjectCreationException e) {
//			e.printStackTrace();
//		}
//
//	}

}
