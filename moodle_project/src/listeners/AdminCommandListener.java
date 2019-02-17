package listeners;

import exceptions.NameException;
import exceptions.NullObjectException;
import system.WebSystem2;
import users.Admin;
import users.User;

public class AdminCommandListener extends ActiveProfileListener {

	private static final int CREATE_COURSE = 4;
	private static final int LOG_OUT = 5;

	private static ActiveProfileListener instance = null;

	public static ActiveProfileListener getInstance() {
		if (AdminCommandListener.instance == null) {
			AdminCommandListener.instance = new AdminCommandListener();
		}
		return AdminCommandListener.instance;
	}

	@Override
	public void showMenu() {

		System.out.println("User menu:\n");
		System.out.println("1 - View profile");
		System.out.println("2 - View my courses");
		System.out.println("3 - Choose course");
		System.out.println("4 - Create course");
		System.out.println("5 - Log out");
		System.out.println();
		System.out.println("0 - Exit");

	}

	@Override
	public void execute(int command) throws NullObjectException, NameException{

		switch (command) {
		case AdminCommandListener.CREATE_COURSE:
			((Admin) ActiveProfileListener.getUser()).createCourse();
			return;
		case AdminCommandListener.LOG_OUT:
			ActiveProfileListener.getUser().setOffline();
			ActiveProfileListener.setUser(null);
			WebSystem2.getInstance().setListener(GuestCommandListener.getInstance());
			return;
		default:
			super.execute(command);
		}

	}
//	


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
