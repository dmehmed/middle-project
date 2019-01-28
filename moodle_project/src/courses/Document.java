package courses;

import exceptions.NameException;
import exceptions.ObjectCreationException;
import exceptions.PathException;
import helper.Helper;

public class Document {

	private String title;
	private String path;

	public Document(String title, String path) throws ObjectCreationException {
		try {
			this.setTitle(title);
			this.setPath(path);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ObjectCreationException("Cannot create documet", e);
		}

	}

	@Override
	public String toString() {
		return "Title: " + this.title;
	}

	private void setTitle(String title) throws NameException {
		if (!Helper.isValid(title)) {
			throw new NameException("Invalid document title!");
		}

		this.title = title;
	}

	private void setPath(String path) throws PathException {
		if (!Helper.isValid(path)) {
			throw new PathException("Invalid document path!");
		}

		this.path = path;
	}

}
