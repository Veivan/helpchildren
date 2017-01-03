package servlets;

import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

public class dbConnector {

	public static final String dbname = "/WEB-INF/test.db";

	private String path2db;
	
	public dbConnector(String path2db)
	{
		this.path2db = path2db;
	}
	
	public Person queryPersonByID(int id) {
		Person person = new Person();
		String sql = "SELECT id, name, summ, link, age, picture, picturelink FROM persons WHERE id=?";
		ResultSet rs = null;
		try (Connection conn = connect(path2db);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			// loop through the result set
			while (rs.next()) {
				/*
				 * System.out.println(rs.getInt("id") + "\t" +
				 * rs.getString("name") + "\t" + rs.getString("summ") // + "\t"
				 * + rs.getString("picture") + "\t" + rs.getString("age") +
				 * rs.getString("link"));
				 */
				person.id = rs.getInt("id");
				person.name = rs.getString("name");
				person.age = rs.getString("age");
				person.summ = rs.getString("summ");
				person.link = rs.getString("link");
				person.picture = rs.getBytes("picture");
				person.pictureLink = rs.getString("picturelink").replace(
						"news-m", "child-badge");
			}
		} catch (SQLException | MalformedURLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return person;
	}

	public int queryRandomID() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		Random random = new Random();
		String sql = "SELECT id FROM persons";
		ResultSet rs = null;
		try (Connection conn = connect(path2db);
			Statement stmt = conn.createStatement()) {
			rs = stmt.executeQuery(sql);
			// loop through the result set
			while (rs.next()) {
				list.add(rs.getInt("id"));
			}
		} catch (SQLException | MalformedURLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		int index = random.nextInt(list.size());
		return list.get(index);
	}

	private Connection connect(String path) throws MalformedURLException,
			ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		String url = "jdbc:sqlite:" + path;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

}
