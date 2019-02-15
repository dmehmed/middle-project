package listeners;

import helper.Helper;
import system.WebSystem;
import users.User;

public class GuestCommandListener extends CommandListener {

	private static final int LOG_IN_COMMAND = 1;
	private static final int CREATE_NEW_USER_COMMAND = 2;
	private static final int LIST_COURSES_BY_CATEGORIES_COMMAND = 3;

	private static CommandListener instance = null;

	public static CommandListener getInstance() {
		if (GuestCommandListener.instance == null) {
			GuestCommandListener.instance = new GuestCommandListener();
		}
		return GuestCommandListener.instance;
	}

	@Override
	public void showMenu() {

		System.out.println("Choose option:\n");
		System.out.println("1 - Log in");
		System.out.println("2 - Create new account");
		System.out.println("3 - View course categories\n");
		System.out.println("0 - Exit");

	}

	@Override
	public void execute(int command) {

		switch (command) {
		case GuestCommandListener.LOG_IN_COMMAND:
			User user = WebSystem.getInstance().logUser();

			if (Helper.isValid(user)) {
				if (user instanceof User) {
					WebSystem.getInstance().setListener(UserCommandListener.getInstance());
				} else {
					WebSystem.getInstance().setListener(AdminCommandListener.getInstance());
				}
			}

			return;
		case GuestCommandListener.CREATE_NEW_USER_COMMAND:
			WebSystem.getInstance().createNewUser();
			return;
		case GuestCommandListener.LIST_COURSES_BY_CATEGORIES_COMMAND:
			WebSystem.getInstance().showCourses();
			return;
		default:
			System.out.println("Invalid command!");
			return;
		}
	}

}
