package users;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import courses.Course;
import courses.Updatable;
import courses.Viewable;
import exceptions.ObjectCreationException;
import helper.Helper;

public class User {

	static int MIN_LENGTH_PASSWORD = 6;

	private final String username; // required
	private String password; // required
	private String firstName; // required
	private String surname; // required
	private Address address; // optional
	private boolean isOnline; // automatically generated
	Set<Updatable> courses = new TreeSet<Updatable>(); // sorted by day of creation from new to old

	User(String username, String password, String firstName, String surname, Address address) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.surname = surname;
		this.address = address;
	}

//	public static void main(String[] args) {
//		User user = new User(" =sjacgbjd", "fbgwf", "defwe", "dwqdwe");
//		user.courses.stream().forEach(course -> course.);
//	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public void showMenu() {
		System.out.println("User menu");

		System.out.println(this.username);
		System.out.println(this.password);
		System.out.println(this.firstName);
		System.out.println(this.surname);
		System.out.println(this.address);
		System.out.println(this.isOnline);
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline() {
		this.isOnline = true;
	}

	public void setOffline() {
		this.isOnline = false;
	}

	@Override
	public String toString() {
		return this.username;
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

	public void viewProfileInfo() {
		// TODO Auto-generated method stub
		
	}

	public void listCourses() {
		// TODO Auto-generated method stub
		
	}

	public void viewCourseInfo(String course) {
		// TODO Auto-generated method stub
		
	}

	public void viewParticipantsInCourse(String course) {
		// TODO Auto-generated method stub
		
	}

	public void viewCourseGrade() {
		// TODO Auto-generated method stub
		
	}

//	/*
//	 * public void register() { if(this.hasRegistration) {
//	 * java.lang.System.out.println("You have registration!"); return; }
//	 * 
//	 * Scanner input = new Scanner(java.lang.System.in);
//	 * java.lang.System.out.print("Username: "); String username = input.nextLine();
//	 * java.lang.System.out.print("Password: "); String password = input.nextLine();
//	 * java.lang.System.out.print("Name: "); String name = input.nextLine();
//	 * java.lang.System.out.println("Address: ");
//	 * java.lang.System.out.print("Country: "); String country = input.nextLine();
//	 * java.lang.System.out.print("City: "); String city = input.nextLine();
//	 * java.lang.System.out.print("Street: "); String street = input.nextLine();
//	 * 
//	 * try { Address address = new Address(country, city, street); } catch
//	 * (ObjectCreationException e) { e.printStackTrace(); }
//	 * 
//	 * this.username = username; this.password = password; this.name = name;
//	 * this.address = address;
//	 * 
//	 * if(this.system.registerUser(this)) { this.hasRegistraion = true; return; }
//	 * 
//	 * }
//	 */

//	public void logIn() {
//		if (!this.hasRegistration) {
//			java.lang.System.out.println("You must have a registration to log in!");
//			return;
//		}
//
//		if (this.isOnline) {
//			java.lang.System.out.println("You are already logged in!");
//			return;
//		}
//
//		Scanner input = new Scanner(java.lang.System.in);
//		java.lang.System.out.print("Username: ");
//		String username = input.nextLine();
//		java.lang.System.out.print("Password: ");
//		String password = input.nextLine();
//
//		if (username.equals(this.username) && password.equals(this.password)) {
//			java.lang.System.out.println("You successfully logged in!");
//			input.close();
//			return;
//		}
//
//		java.lang.System.out.println("Invalid username or password! Please try again!");
//		input.close();
//	}
//
//	public void listAllCourses() { // List all courses in the System
//		this.system.listAllCourses();
//	}
//
//	public void listMyCourses() { // List all user`s courses
//		if (!this.hasRegistration) { // Must have registration to use this function
//			this.register();
//			return;
//		}
//
//		if (!this.isOnline) { // Must be logged in to list my courses
//			this.logIn();
//		}
//
//		if (!this.courses.isEmpty()) {
//			java.lang.System.out.println("No courses to list!");
//			return;
//		}
//
//		for (Course course : this.courses) {
//			java.lang.System.out.println(course);
//		}
//
//	}

}
