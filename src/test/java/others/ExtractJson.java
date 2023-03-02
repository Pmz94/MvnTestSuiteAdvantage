package others;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class ExtractJson {

	final static String basepath = System.getProperty("user.dir");
	final static String jsonfiles = basepath + "\\src\\test\\resources\\jsonfiles";

	public static void main(String[] args) throws IOException {
		ObjectMapper o = new ObjectMapper();
		String file = jsonfiles + "\\customerInfo0.json";
		CustomerDetails c = o.readValue(new File(file), CustomerDetails.class);
		System.out.println(c.getCourseName());
	}
}