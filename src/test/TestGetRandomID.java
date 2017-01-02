package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestGetRandomID {

	public static void main(String[] args) throws Exception {
		//final String url = "http://localhost:8080/HelpChildren/getrandomid";
		final String url = "http://helpchildren.online/HelpChildren/getrandomid";

		System.out.println(GetPageContent(url));

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
