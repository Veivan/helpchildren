package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetRandomID
 */
@WebServlet("/getrandomid")
public class GetRandomID extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetRandomID() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(queryRandomID());
		pw.close();
	}

	private int queryRandomID() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		Random random = new Random();
		String sql = "SELECT id FROM persons";
		ResultSet rs = null;
		try (Connection conn = connect();
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

	private Connection connect() throws MalformedURLException,
			ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		String url = "jdbc:sqlite:"
				+ this.getServletContext().getRealPath("/WEB-INF/test.db");
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

}
