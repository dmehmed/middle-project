package listeners;

public class AdminCommandListener extends CommandListener {

	private static CommandListener instance = null;

	public static CommandListener getInstance() {
		if (AdminCommandListener.instance == null) {
			AdminCommandListener.instance = new GuestCommandListener();
		}
		return AdminCommandListener.instance;
	}

	@Override
	public void showMenu() {

		System.out.println("User menu:");
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
		// TODO Auto-generated method stub

	}

}
