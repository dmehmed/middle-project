package courses;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import exceptions.AddException;
import exceptions.NameException;
import exceptions.ObjectCreationException;
import helper.Helper;

public class Section {

	private String title; // required
	private List<Document> documents; // elements in it are optional

	Section(String title) throws ObjectCreationException {
		try {
			this.setTitle(title);
		} catch (NameException e) {
			e.printStackTrace();
			throw new ObjectCreationException("Cannot create section", e);
		}

		documents = new LinkedList<Document>();
	}

	String getTitle() {
		return title;
	}

	void listAllDocuments() {
		this.documents.stream().forEach(System.out::println);
	}

	@Override
	public String toString() {
		return "Section title: " + this.title;
	}

	void addDocument(Document doc) throws AddException {
		if (!Helper.isValid(doc)) {
			throw new AddException("Invalid document!");
		}

		this.documents.add(doc);
	}

	void removeDocument(Document doc) {
		Document currentDocument = null;
		for (Iterator<Document> it = this.documents.iterator(); it.hasNext();) {
			currentDocument = it.next();

			if (currentDocument.equals(doc)) {
				it.remove();
				return; // delete only first document which is equals to parameter
			}
		}
	}

	private void setTitle(String title) throws NameException {
		if (!Helper.isValid(title)) {
			throw new NameException("Invalid section title!");
		}

		this.title = title;
	}

//	public static void main(String[] args) throws ObjectCreationException, AddException {
//
//		Section sc = new Section("Lecture 1");
//
//		System.out.println(sc);
//		System.out.println();
//
//		for (int i = 0; i < 10; i++) {
//			sc.addDocument(new Document("Project.pdf", "D:Help"));
//		}
//		
//		sc.listAllDocuments();
//
//	}

}
