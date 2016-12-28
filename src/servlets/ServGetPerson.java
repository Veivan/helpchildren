package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
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

	private Connection connect() throws MalformedURLException, ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		String url = "jdbc:sqlite:" + this.getServletContext().getRealPath("/WEB-INF/test.db");
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
		String sql = "SELECT id, name, summ, link, age, picture, picturelink FROM persons WHERE id=?";
		ResultSet rs = null;
		try (Connection conn = connect();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			// loop through the result set
			while (rs.next()) {
				/*System.out.println(rs.getInt("id") + "\t"
						+ rs.getString("name") + "\t" + rs.getString("summ")
						// + "\t" + rs.getString("picture") + "\t"
						+ rs.getString("age") + rs.getString("link")); */
				person.id = rs.getInt("id");
				person.name = rs.getString("name");
				person.age = rs.getString("age");
				person.summ = rs.getString("summ");
				person.link = rs.getString("link");
				person.picture = rs.getBytes("picture");
				person.pictureLink = rs.getString("picturelink").replace("news-m", "child-badge");
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
		if (paramValue == null) paramValue = "27897";
		Person person = queryPersonByID(Integer.parseInt(paramValue));

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html> <link rel='stylesheet' href='stylesheet.css'><head><title>Фонд 'Подари жизнь'</title></head>" +
"<body>"+ 
"<p><a href='https://podari-zhizn.ru'><img src='https://podari-zhizn.ru/sites/all/themes/giftoflife/logo.png' alt='' title=''></a></p>"+
"<p><a href='https://donate.podari-zhizn.ru/' class='abold'>Благотворительный фонд 'Подари жизнь' примет Вашу помощь.</a></p>"+
"<hr><h1 class='node-title'>");
		out.println(person.name);
		out.println("</h1><hr>");
		out.println("<div  class='intro'><table>"
		+"<tr><th rowspan=3>"
		+ String.format("<a href='%s'>", person.link)  
		+ String.format("<img src='%s' alt='' title=''>", person.pictureLink)  
		+ "</a></th></tr><tr><td>&nbsp</td><th>Возраст:</th><td>");
		out.println(person.age); 
		
		String summ = person.summ.contains("Деньги собраны") ? person.summ + " А Вы можете помочь другим детям!" : person.summ;
		out.println(
		"</td></tr><tr><td>&nbsp</td><th>Необходима сумма:</th><td>"
				+ summ
		+ "</td></tr><tr>&nbsp<td></td><td>&nbsp</td><td>"
		+ "<br><p><a href='https://donate.podari-zhizn.ru/' title='https://donate.podari-zhizn.ru/' class='abutton'>Помочь на сайте фонда</a></p>"
		+ "</td></tr></table></div><hr>"
		+ "<p><a href='https://donate.podari-zhizn.ru/'><img src='https://podari-zhizn.ru/sites/all/themes/giftoflife/podari-zhizn-logo-people.png' alt='' title=''></a></p>"

		+ "<div class='footer-info'>"
		+ "<p>Фонд 'Подари жизнь' не имеет филиалов, отделений, представителей и волонтеров в регионах России.</p>"
		+ "<p>Для обращения за помощью: заполните <a href='http://help.podari-zhizn.ru/'>форму</a>"
		+ "<br>или пишите на <a href='mailto:help@podari-zhizn.ru'>help@podari-zhizn.ru</a></p>"
		+ "<p class='footer-mail'>"
		+ "<a href='mailto:info@podari-zhizn.ru'>info@podari-zhizn.ru</a>, <a href='mailto:info@donors.ru'>info@donors.ru</a></p>"
		+ "<p class='footer-phone'>8-800-250-5222 (звонок из регионов бесплатный)</p>"
		+ "<p class='footer-phone'>+7 (495) 995-31-05, +7 (495) 995-31-06 (факс)"
		+ "<br>Для СМИ: +7 (495) 995-31-08 <a href='mailto:pressa@podari-zhizn.ru'>pressa@podari-zhizn.ru</a></p>"
		+ "<p class='footer-address'>119048, г. Москва, ул. Доватора, д. 13, подъезд 2А</p>"
		+ "<p>Часы работы офиса: по будням с 10:00 до 19:00 часов, без перерыва на обед. Выходные: суббота и воскресенье.</p>"
		+ "<p class='footer-copyright'>© 2007-2016 <a href='http://podari-zhizn.ru'>Фонд «Подари жизнь», Инициативная группа «Доноры - детям»</a></p>"
		+ "</div></body></html>" );
	}

}
