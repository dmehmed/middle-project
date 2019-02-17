package listeners;

import exceptions.NameException;
import exceptions.NullObjectException;
import system.WebSystem2;
import users.User;

public class UserCommandListener extends ActiveProfileListener {

	private static final int LOG_OUT = 4;

	private static ActiveProfileListener instance = null;

	public static ActiveProfileListener getInstance() {
		if (UserCommandListener.instance == null) {
			UserCommandListener.instance = new UserCommandListener();
		}
		return UserCommandListener.instance;
	}

	@Override
	public void showMenu() {

		System.out.println("Choose option:\n");
		System.out.println("1 - View profile");
		System.out.println("2 - View my courses");
		System.out.println("3 - Choose course");
		System.out.println("4 - Log out");
		System.out.println();
		System.out.println("0 - Exit");

	}

	@Override
	public void execute(int command) throws NullObjectException, NameException {

		switch (command) {
		case UserCommandListener.LOG_OUT:
			ActiveProfileListener.getUser().setOffline();
			ActiveProfileListener.setUser(null);
			WebSystem2.getInstance().setListener(GuestCommandListener.getInstance());
			return;
		default:
			super.execute(command);
		}

	}
}
