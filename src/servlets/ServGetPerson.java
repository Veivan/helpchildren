package servlets;

import java.io.IOException;
import java.io.PrintWriter;

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

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServGetPerson() {
		super();
		// TODO Auto-generated constructor stub
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
		
		String path2db = this.getServletContext().getRealPath(dbConnector.dbname);
		dbConnector dbConn = new dbConnector(path2db);

		response.setContentType("text/html;charset=utf-8");
		String paramName = "id";
		String paramValue = request.getParameter(paramName);
		if (paramValue == null)
			paramValue = "27897";
		Person person = dbConn.queryPersonByID(Integer.parseInt(paramValue));

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><head>"				
				+ GetCSS() 
				//<link rel='stylesheet' href='stylesheet.css'>
				+"<title>Фонд 'Подари жизнь'</title>"				
				+ "<!-- Yandex.Metrika counter --> <script type='text/javascript'> (function (d, w, c) { (w[c] = w[c] || []).push(function() { try { w.yaCounter41924134 = new Ya.Metrika({ id:41924134, clickmap:true, trackLinks:true, accurateTrackBounce:true }); } catch(e) { } }); var n = d.getElementsByTagName('script')[0], s = d.createElement('script'), f = function () { n.parentNode.insertBefore(s, n); }; s.type = 'text/javascript'; s.async = true; s.src = 'https://mc.yandex.ru/metrika/watch.js'; if (w.opera == '[object Opera]') { d.addEventListener('DOMContentLoaded', f, false); } else { f(); } })(document, window, 'yandex_metrika_callbacks'); </script> <noscript><div><img src='https://mc.yandex.ru/watch/41924134' style='position:absolute; left:-9999px;' alt='' /></div></noscript> <!-- /Yandex.Metrika counter -->"
				+ "</head>"
				+ "<body>"
				+ "<p><a href='https://podari-zhizn.ru'><img src='https://podari-zhizn.ru/sites/all/themes/giftoflife/logo.png' alt='' title=''></a></p>"
				+ "<p><a href='https://donate.podari-zhizn.ru/' class='abold'>Благотворительный фонд 'Подари жизнь' примет Вашу помощь.</a></p>"
				+ "<hr><h1 class='node-title'>");
		out.println(person.name);
		out.println("</h1><hr>");
		out.println("<div  class='intro'><table>"
				+ "<tr><th rowspan=3>"
				+ String.format("<a href='%s'>", person.link)
				+ String.format("<img src='%s' alt='' title=''>",
						person.pictureLink)
				+ "</a></th></tr><tr><td>&nbsp</td><th>Возраст:</th><td>");
		out.println(person.age);

		String summ = person.summ.contains("Деньги собраны") ? person.summ
				+ " А Вы можете помочь другим детям!" : person.summ;
		out.println("</td></tr><tr><td>&nbsp</td><th>Необходима сумма:</th><td>"
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
				+ "</div></body></html>");
	}

	private String GetCSS()
	{
		return "<style type='text/css'>"
		+ "body { margin-top: 0px;	margin-right: 10px; margin-bottom: 5px;margin-left: 30px; }"
		
+ "hr{ border: none;    background-color: #658ca1;    color: #658ca1;    height: 1px;    } "

+ ".node-title{border:none;margin:0;padding-bottom:0;margin-left:30px;padding-top:10px;"
+ "    font-size: 20px;font-family: Century Gothic,sans-serif;color: #658ca1;}"

+ ".intro table {	text-align: left;	font-family: Arial, Helvetica, sans-serif;"
+ "	font-size: 13px;	color: #626262;	padding: 30px;}"
    
+ ".intro th {	    font-weight: normal;}"
    
+ ".intro td {	    font-weight: bold;}"
    
+ "a{	color: #4F5Cff;	text-decoration:none;	font-family: Arial, Helvetica, sans-serif;	font-size: 13px;}"

+ "a:visited {	text-decoration:none;	color: #4F5Cff;}"

+ "a:hover {	text-decoration:underline;}"
   
+ ".abold {	font-family: Helvetica;	font-size: 18px;	font-weight: bold;	font-style: italic;}"

+ ".abutton {	font-family: Helvetica;	font-size: 15px;	font-weight: bold;	font-style: italic;}"

+ ".footer-info{	color: #a1a0a0;    font-size: 12px;}"

+ ".footer-info a{	color: navy;    font-size: 12px;	text-decoration:underline;}"
		+ "</style>";
	}
}
