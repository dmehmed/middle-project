package system;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import courses.Course;
import exceptions.NullObjectException;
import helper.Helper;
import users.Admin;
import users.User;

public class JSONWriter {

	private static JSONWriter JSONWriterInstance = null;
	private Gson gson;

	private JSONWriter() {
		this.gson = new GsonBuilder().create();
	}

	public static JSONWriter getInstance() {
		if (JSONWriter.JSONWriterInstance == null) {
			JSONWriter.JSONWriterInstance = new JSONWriter();
		}

		return JSONWriter.JSONWriterInstance;
	}

	public void writeObjectToJSONFile(Object object) throws NullObjectException {

		if (!Helper.isValid(object)) {
			throw new NullObjectException("Invalid object given!");
		}

		File file = null;
		if (object instanceof User) {
			file = new File(".\\users_json_files\\" + ((User) object).getUsername() + ".json");
		} else if (object instanceof Admin) {
			file = new File(".\\admins_json_files\\" + ((Admin) object).getUsername() + ".json");
		} else {
			file = new File(".\\courses_json_files\\" + ((Course) object).getTitle() + ".json");
		}

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try (PrintWriter writer = new PrintWriter(file)) {
			this.gson.toJson(object, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
