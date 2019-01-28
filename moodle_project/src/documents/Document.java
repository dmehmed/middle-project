package documents;

import exceptions.NameException;
import exceptions.ObjectCreationException;
import exceptions.PathException;
import helper.Helper;

public class Document {

	private String title;
	private String path;

	public Document(String title, String path) {
		try {
			this.setTitle(title);
			this.setPath(path);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ObjectCreationException("Cannot create this object", e);
		}

	}

	private void setTitle(String title) throws NameException {
		if (!Helper.isValid(title)) {
			throw new NameException("Title for this document is not valid!");
		}

		this.title = title;
	}

	private void setPath(String path) throws PathException {
		if (!Helper.isValid(path)) {
			throw new PathException("Path for this document is not valid!");
		}

		this.path = path;
	}

}
