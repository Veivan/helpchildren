package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class TestGetRandomID {

	public static void main(String[] args) throws Exception {
		final String url = "http://localhost:8080/HelpChildren/getrandomid";
		//final String url = "http://helpchildren.online/getrandomid";

		String page = GetPageContent(url);
		System.out.println(page);
		JSONObject json = new JSONObject(page);
		int id = json.getInt("id");
		System.out.println(""+id);
		String picenc = json.getString("picture");
		System.out.println(picenc);
	}

	public static String GetPageContent(String url_string) throws Exception {
		StringBuffer result = new StringBuffer();
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) new URL(url_string).openConnection();

			BufferedReader rd = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			return result.toString();
		} finally {
			if (conn != null)
				conn.disconnect();
		}
	}
}
