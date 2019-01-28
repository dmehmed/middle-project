package courses;

import java.util.Iterator;
import java.util.List;

import exceptions.AddException;
import exceptions.NameException;
import exceptions.ObjectCreationException;
import helper.Helper;

public class Section {

	private String title;
	private List<Document> documents;

	public Section(String title) throws ObjectCreationException {
		try {
			this.setTitle(title);
		} catch (NameException e) {
			e.printStackTrace();
			throw new ObjectCreationException("Cannot create section", e);
		}

	}

	public void listAll() {
		System.out.println(this.title);
		System.out.println();

		this.documents.stream().forEach(System.out::println);
	}

	public void addDocument(Document doc) throws AddException {
		if (!Helper.isValid(doc)) {
			throw new AddException("Invalid document!");
		}

		this.documents.add(doc);
	}

	public void removeDocument(Document doc) {

		for (Iterator<Document> it = this.documents.iterator(); it.hasNext();) {
			Document d = it.next();

			if (d.equals(doc)) {
				it.remove();
				return; // ако документа го има няколко пъти ще махне само първото срещане!
			}
		}
	}

	private void setTitle(String title) throws NameException {
		if (!Helper.isValid(title)) {
			throw new NameException("Invalid section title!");
		}
	}

}
