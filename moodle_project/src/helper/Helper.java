package helper;

public abstract class Helper {

	public static boolean isValid(String text) {
		if (text != null && text.length() > 0) {
			return true;
		}

		return false;
	}

}
