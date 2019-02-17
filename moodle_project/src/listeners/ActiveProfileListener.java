package listeners;

import exceptions.NameException;
import exceptions.NullObjectException;
import system.CoursesStorage;
import system.WebSystem2;
import users.Admin;
import users.User;

public abstract class ActiveProfileListener extends CommandListener {

	protected static final int VIEW_PROFILE = 1;
	protected static final int VIEW_COURSES = 2;
	protected static final int CHOOSE_COURSE = 3;
	protected static final int VIEW_PARTICIPANTS_IN_COURSE = 4;

	private static User user;

	public static void setUser(User user) {
		ActiveProfileListener.user = user;
	}

	static User getUser() {
		return ActiveProfileListener.user;
	}

	@Override
	public void execute(int command) throws NullObjectException, NameException {

		String course = null;

		switch (command) {
		case ActiveProfileListener.VIEW_PROFILE:
			ActiveProfileListener.user.viewProfileInfo();
			return;
		case ActiveProfileListener.VIEW_COURSES:
			ActiveProfileListener.user.listCourses();
			return;
		case ActiveProfileListener.CHOOSE_COURSE:

			WebSystem2.getScanner().nextLine();
			System.out.println("Enter course name:");
			course = WebSystem2.getScanner().nextLine();

			if (ActiveProfileListener.user.isThereCourse(course)) {

				CourseListener.setCourse(CoursesStorage.getInstance().getCourse(course));

				if (ActiveProfileListener.user instanceof Admin) {
					WebSystem2.getInstance().setListener(AdminCourseListener.getInstance());
				} else {
					WebSystem2.getInstance().setListener(UserCourseListener.getInstance());
				}
			}

			return;
		default:
			System.out.println("Invalid command!");
			return;
		}
	}

}