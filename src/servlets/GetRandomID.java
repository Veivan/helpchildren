package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetRandomID
 * Получает из БД случайный ID и соответствующую ему картинку в виде json
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
		
		String path2db = this.getServletContext().getRealPath(dbConnector.dbname);
		dbConnector dbConn = new dbConnector(path2db);
		int id = dbConn.queryRandomID();
		
		Person person = dbConn.queryPersonByID(id);
		String json = String.format("{\"id\":\"%d\"}", person.id);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		//{"title":"Конференция","date":"2014-11-30T12:00:00.000Z"}
		pw.print(json);
		pw.close();
	}

}
