package listeners;

import system.WebSystem;
import users.Admin;
import users.User;

public class AdminCommandListener extends ActiveProfileListener {

	private static final int ADD_PARTICIPANT_IN_COURSE = 5;
	private static final int REMOVE_PARTICIPANT_IN_COURSE = 6;
	private static final int LOG_OUT = 6;

	private static ActiveProfileListener instance = null;
	private Admin admin = null;

	public static ActiveProfileListener getInstance() {
		if (AdminCommandListener.instance == null) {
			AdminCommandListener.instance = new AdminCommandListener();
		}
		return AdminCommandListener.instance;
	}

	@Override
	public void setUser(User user) {
		this.admin = (Admin) user;
	}

	@Override
	public void showMenu() {

		System.out.println("User menu:\n");
		System.out.println("1 - View profile");
		System.out.println("2 - View my courses");
		System.out.println("3 - View course");
		System.out.println("4 - View participants in course");
		System.out.println("5 - Add participant in course");
		System.out.println("6 - Remove participant in course");
		System.out.println("7 - Add section in course");
		System.out.println("8 - Remove section in course");
		System.out.println("9 - Add document in course");
		System.out.println("10 - Remove document in course");
		System.out.println("11 - Log out");
		System.out.println();
		System.out.println("0 - Exit");

	}

	@Override
	public void execute(int command) {

		String courseName = null;

		switch (command) {
		case ActiveProfileListener.VIEW_PROFILE:
			this.admin.viewProfileInfo();
			return;
		case ActiveProfileListener.VIEW_COURSES:
			this.admin.listCourses();
			return;
		case ActiveProfileListener.VIEW_COURSE_INFO:
			System.out.println("Enter course name:");
			courseName = WebSystem.getScanner().nextLine();
			this.admin.viewCourseInfo(courseName);
			return;
		case ActiveProfileListener.VIEW_PARTICIPANTS_IN_COURSE:
			System.out.println("Enter course name:");
			courseName = WebSystem.getScanner().nextLine();
			this.admin.viewParticipantsInCourse(courseName);
			return;
		case AdminCommandListener.LOG_OUT:
			this.admin.setOffline();
			this.setUser(null);
			WebSystem.getInstance().setListener(GuestCommandListener.getInstance());
			return;
		default:
			System.out.println("Invalid command!");
			return;
		}

	}

//
//			this.showAdminMenu();
//			command = scanner.nextInt();
//			if (command == EXIT_SYSTEM_COMMAND) {
//				return;
//			}
//
//			if (command == VIEW_PROFILE_COMMAND) {
//				user.viewProfileInfo();
//			}
//
//			if (command == VIEW_USER_COURSES_COMMAND) {
//				user.listCourses();
//			}
//
//			if (command == VIEW_COURSE_INFO_COMMAND) {
//				System.out.print("Enter course name: ");
//				String course = scanner.nextLine();
//				user.viewCourseInfo(course);
//			}
//
//			if (command == VIEW_PARTICIPANTS_IN_COURSE_COMMAND) {
//				System.out.print("Enter course name: ");
//				String course = scanner.nextLine();
//				user.viewParticipantsInCourse(course);
//			}
////
////			if (command == ADD_PARTICIPANT_IN_COURSE_COMMAND) {
////				user.addParticipantInCourse(course, participant);
////			}
////
////			if(command == REMOVE_PARTICIPANT_IN_COURSE_COMMAND) {
////				user.removeParticipantInCourse(course, participant);
////			}
////			
////			if(command == ) { 
////				
////			}
//
//			if (command == LOG_OUT_COMMAND) {
//				break;
//			}

}
