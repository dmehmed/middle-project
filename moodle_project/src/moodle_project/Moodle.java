package moodle_project;

import system.IWebSystem;
import system.WebSystem;

public class Moodle {

	public static void main(String[] args) {

		IWebSystem moodle = (IWebSystem) WebSystem.getInstance();
		moodle.showMenu();
		
	}

}
