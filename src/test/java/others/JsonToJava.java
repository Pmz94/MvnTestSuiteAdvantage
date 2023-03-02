package others;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.commons.text.StringEscapeUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class JsonToJava {

	final static String guias = "--------------------";
	final static String basepath = System.getProperty("user.dir");
	final static String jsonfiles = basepath + "\\src\\test\\resources\\jsonfiles";

	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");

		try {
			System.out.println("Conectandose a BD");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/business", "root", "");

			Statement st = conn.createStatement();
			String query = "SELECT * FROM customerinfo WHERE PurchasedDate <= CURDATE() AND Location = 'Asia';";
			System.out.println("Ejecutando query:");
			System.out.println(query);
			ResultSet rs = st.executeQuery(query);
			ArrayList<CustomerDetails> a = new ArrayList<>();
			JSONArray js = new JSONArray();

			while(rs.next()) {
				CustomerDetails c = new CustomerDetails();
				c.setCourseName(rs.getString(1));
				c.setPurchaseDate(rs.getString(2));
				c.setAmount(rs.getInt(3));
				c.setLocation(rs.getString(4));
				a.add(c);

				if(rs.isFirst()) System.out.println(guias);
				System.out.println(c.getCourseName());
				System.out.println(c.getPurchaseDate());
				System.out.println(c.getAmount());
				System.out.println(c.getLocation());
				System.out.println(guias);
			}

			for(int i = 0; i < a.size(); i++) {
				ObjectMapper oj = new ObjectMapper();
				String file = jsonfiles + "\\customerInfo" + i + ".json";
				oj.writeValue(new File(file), a.get(i));
				Gson g = new Gson();
				String jsonString = g.toJson(a.get(i));
				js.add(jsonString);
			}

			JSONObject jo = new JSONObject();
			jo.put("data", js);
			String unescapeString = StringEscapeUtils.unescapeJava(jo.toJSONString());
			String finalString = unescapeString.replace("\"{", "{").replace("}\"", "}");
			System.out.println(finalString);

			try(FileWriter file = new FileWriter(jsonfiles + "\\singleJson.json")) {
				file.write(finalString);
			}

			conn.close();
		} catch(SQLException ex) {
			System.out.println("No se pudo conectar a la BD");
		} catch(IOException ex) {
			System.out.println(ex.getMessage());
		}
	}
}