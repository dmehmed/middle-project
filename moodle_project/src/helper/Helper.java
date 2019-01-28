package helper;

import courses.Document;

public abstract class Helper {

	public static boolean isValid(String text) {
		if (text != null && text.length() > 0) {
			return true;
		}

		return false;
	}

	public static boolean isValid(Document doc) {
		if (doc != null) {
			return true;
		}

		return false;
	}

}
