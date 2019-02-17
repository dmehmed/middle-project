package system;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

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

	
	
	public void writeObjectToJSONFile(Map<String, User> users) throws NullObjectException {
		
		if(!Helper.isValid(users)) {
			throw new NullObjectException("Invalid object given!");
		}

		File file = null;

			file = new File(".\\users_json_files\\users.json");
 
		
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			



		try (PrintWriter writer = new PrintWriter(new FileOutputStream(file), true)) {
		    this.gson.toJson(users, writer);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
