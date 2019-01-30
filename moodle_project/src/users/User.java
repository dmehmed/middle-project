package users;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import system.System;
import courses.Course;
import exceptions.ObjectCreationException; 


public abstract class User {

	private final String username;
	private String password;
	private String name;
	private Address address;
	private boolean isOnline;
	private boolean hasRegistration;
	private System system = System.getInstance();
	private Set<Course> courses = new TreeSet<Course>();
	
	public User() {
		
	}
	
	
	/*public void register() {
		if(this.hasRegistration) {
			java.lang.System.out.println("You have registration!");
			return;
		}
		
		Scanner input = new Scanner(java.lang.System.in);
		java.lang.System.out.print("Username: ");
		String username = input.nextLine();
		java.lang.System.out.print("Password: ");
		String password = input.nextLine();
		java.lang.System.out.print("Name: ");
		String name = input.nextLine();
		java.lang.System.out.println("Address: ");
		java.lang.System.out.print("Country: ");
		String country = input.nextLine();
		java.lang.System.out.print("City: ");
		String city = input.nextLine();
		java.lang.System.out.print("Street: ");
		String street = input.nextLine();
		
		try {
			Address address = new Address(country, city, street);
		} catch (ObjectCreationException e) {
			e.printStackTrace();
		}
		
		this.username = username;
		this.password = password;
		this.name = name;
		this.address = address;
		
		if(this.system.registerUser(this)) {
			this.hasRegistraion = true;
			return;
		}
		
	}*/
	
	
	public void logIn() {
		if(!this.hasRegistration) {
			java.lang.System.out.println("You must have a registration to log in!");
			return;
		}
		
		if(this.isOnline) {
			java.lang.System.out.println("You are already logged in!");
			return;
		}
		
		Scanner input = new Scanner(java.lang.System.in);
		java.lang.System.out.print("Username: ");
		String username = input.nextLine();
		java.lang.System.out.print("Password: ");
		String password = input.nextLine();
		
		if(username.equals(this.username) && password.equals(this.password)) {
			java.lang.System.out.println("You successfully logged in!");
			input.close();
			return;
		}
		
		java.lang.System.out.println("Invalid username or password! Please try again!");
		input.close();
	}
	
	public void listAllCourses() {	// List all courses in the System
		this.system.listAllCourses();
	}
	
	public void listMyCourses() { 	// List all user`s courses
		if(!this.hasRegistration) {	// Must have registration to use this function 
			this.register();		
			return;
		}
		
		if(!this.isOnline) {	// Must be logged in to list my courses
			this.logIn();
		}
		
		if(!this.courses.isEmpty()) {
			java.lang.System.out.println("No courses to list!");
			return;
		}
		
		for(Course course : this.courses) {
			java.lang.System.out.println(course);
		}
		
	}
	
	
}
