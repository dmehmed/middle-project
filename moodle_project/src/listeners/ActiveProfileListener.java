package listeners;

import users.User;

public abstract class ActiveProfileListener extends CommandListener {

	protected static final int VIEW_PROFILE = 1;
	protected static final int VIEW_COURSES = 2;
	protected static final int VIEW_COURSE_INFO = 3;
	protected static final int VIEW_PARTICIPANTS_IN_COURSE = 4;

	public abstract void setUser(User user);

}
