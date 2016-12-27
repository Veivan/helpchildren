package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServGetPerson
 */
@WebServlet(urlPatterns = { "/ServGetPerson" }, initParams = { @WebInitParam(name = "id", value = "") })
public class ServGetPerson extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String dbname = "D:/Work/Projects_Java/HelpChildren/test.db";
	// SQLite connection string
	private static final String url = "jdbc:sqlite:" + dbname;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServGetPerson() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static Connection connect() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	private Person queryPersonByID(int id) {
		Person person = new Person();
		String sql = "SELECT id, name, summ, link, age, picture FROM persons WHERE id=?";
		ResultSet rs = null;
		try (Connection conn = connect();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			// loop through the result set
			while (rs.next()) {
				System.out.println(rs.getInt("id") + "\t"
						+ rs.getString("name") + "\t" + rs.getString("summ")
						// + "\t" + rs.getString("picture") + "\t"
						+ rs.getString("age") + rs.getString("link"));
				person.id = rs.getInt("id");
				person.name = rs.getString("name");
				person.age = rs.getString("age");
				person.summ = rs.getString("summ");
				person.link = rs.getString("link");
				person.picture = rs.getBytes("picture");
			}
		} catch (SQLException e) {
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 handleRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		String paramName = "id";
		String paramValue = request.getParameter(paramName);
		String name = queryPersonByID(27897).name; 
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Hello World!</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Hello World!</h1>");
		out.println(paramValue + " : " +  name);
		out.println("</body>");
		out.println("</html>");

	}

}