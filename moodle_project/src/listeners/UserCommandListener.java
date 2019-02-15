package listeners;

import system.WebSystem;
import users.User;

public class UserCommandListener extends ActiveProfileListener {

	private static final int VIEW_COURSE_GRADE = 5;
	private static final int LOG_OUT = 6;

	private static ActiveProfileListener instance = null;
	private User user = null;

	public static ActiveProfileListener getInstance() {
		if (UserCommandListener.instance == null) {
			UserCommandListener.instance = new UserCommandListener();
		}
		return UserCommandListener.instance;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void showMenu() {

		System.out.println("Choose option:\n");
		System.out.println("1 - View profile");
		System.out.println("2 - View my courses");
		System.out.println("3 - View course");
		System.out.println("4 - View participants in course");
		System.out.println("5 - View course grade");
		System.out.println("6 - Log out");
		System.out.println();
		System.out.println("0 - Exit");

	}

	@Override
	public void execute(int command) {

		String courseName = null;

		switch (command) {
		case ActiveProfileListener.VIEW_PROFILE:
			this.user.viewProfileInfo();
			return;
		case ActiveProfileListener.VIEW_COURSES:
			this.user.listCourses();
			return;
		case ActiveProfileListener.VIEW_COURSE_INFO:
			System.out.println("Enter course name:");
			courseName = WebSystem.getScanner().nextLine();
			this.user.viewCourseInfo(courseName);
			return;
		case ActiveProfileListener.VIEW_PARTICIPANTS_IN_COURSE:
			System.out.println("Enter course name:");
			courseName = WebSystem.getScanner().nextLine();
			this.user.viewParticipantsInCourse(courseName);
			return;
		case UserCommandListener.VIEW_COURSE_GRADE:
			System.out.println("Enter course name:");
			courseName = WebSystem.getScanner().nextLine();
			this.user.viewCourseGrade(courseName);
			return;
		case UserCommandListener.LOG_OUT:
			this.user.setOffline();
			this.setUser(null);
			WebSystem.getInstance().setListener(GuestCommandListener.getInstance());
			return;
		default:
			System.out.println("Invalid command!");
			return;
		}

	}
}
