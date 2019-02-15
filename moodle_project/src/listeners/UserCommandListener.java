package listeners;

public class UserCommandListener extends CommandListener {

	private static CommandListener instance = null;

	public static CommandListener getInstance() {
		if (UserCommandListener.instance == null) {
			UserCommandListener.instance = new GuestCommandListener();
		}
		return UserCommandListener.instance;
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
		// TODO Auto-generated method stub

	}

}
