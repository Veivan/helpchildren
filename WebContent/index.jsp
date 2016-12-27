<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="servlets.Person"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="stylesheet.css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HelpChildren</title>

</head>
<body>
	<div class="header">Geokot
		<p class="headcomment">
			HelpChildren
		</p>
	</div>

	<div class="rules">
		Задайте нужные Вам фильтры и нажмите кнопку "Запрос".
		Система выберет аккаунты и сформирует файл со списком аккаунтов.
		<br/>
		Пользуйтесь!
	</div>
	<a href="start.jsp">домой</a>

<!-- Передача через html -->
<form action=queryaccounts method=get> 
	<br/>
	<table border = 0>
		<tr>
			<td>Перечень хэштэгов:</td><td></td><td></td>
			<td colspan=3><input class='input' type='text' name='taglist' /> 
		</tr>
		<tr>
			<td>Аватар:</td><td></td><td></td>
			<td colspan=3>
				<select class='input' name='default_profile_image'>
   					<option value="0" selected>Не важно</option>
 					<option value="1">нестандартный</option>
  					<option value="2">стандартный</option>
				</select> 
			</td>
		</tr>
		<tr>
			<td>Учитывать количество твитов:</td> 
			<td><input class='input' type="checkbox" name='use_statuses'></td>
			<td align="right">от</td><td><input class='input' type='text' name='statuses_count_min' /> 
			<td>до</td><td><input class='input' type='text' name='statuses_count_max' /> 
		</tr>
		<tr>
			<td>Учитывать количество читателей:</td> 
			<td><input class='input' type="checkbox" name='use_followers'></td>
			<!-- td colspan=4>(фолловеров)</td-->
			<td align="right">от</td><td><input class='input' type='text' name='followers_count_min' /> 
			<td>до</td><td><input class='input' type='text' name='followers_count_max' /> 
		</tr>
		<tr>
			<td>Учитывать количество читаемых:</td> 
			<td><input class='input' type="checkbox" name='use_friends'></td> 
			<!--td>(фолловингов)</td-->
			<td align="right">от</td><td><input class='input' type='text' name='friends_count_min' /> 
			<td>до</td><td><input class='input' type='text' name='friends_count_max' /> 
		</tr>
		<tr>
			<td>Возраст аккаунта:</td><td></td><td></td>
			<td colspan=3>
				<select name='created_at' class='input' style="width: 300px;">
   					<option value="0" selected>Не важно</option>
 					<option value="1">менее 1 месяца</option>
  					<option value="2">более 1 месяца</option>
  					<option value="3">более 3 месяцев</option>
  					<option value="4">более 6 месяцев</option>
  					<option value="5">более года</option>
				</select> 
			</td>
		</tr>
		<tr>
			<td>Дата последнего твита:</td><td></td><td></td>
			<td colspan=3>
				<select name='lasttweet_at' class='input' style="width: 300px;">
   					<option value="0" selected>Не важно</option>
 					<option value="1">менее двух недель назад</option>
  					<option value="2">от двух недель до месяца назад</option>
  					<option value="3">более месяца назад</option>
				</select> 
			</td>
		</tr>
		<tr>
			<td></td>
		</tr>
		<tr>
			<td>Расположение (из BIO аккаунта):</td><td></td><td></td>
			<td colspan=3><input class='input' type='text' name='location' /> 
		</tr>
		<tr>
			<td>Учитывать расположение из твитов:</td> 
			<td><input class='input' type="checkbox" name='geo_enabled'></td>
		<tr>
			<td>Страна:</td><td></td><td></td>
			<td>
				<select id='countryid' name='id_cn' class='input' style="width: 300px;">
				</select>
			</td>
			<td>Место:</td> 
			<td colspan=2><input class='input' type='text' name='place' /> 
		</tr>
		<tr>
			<td></td>
		</tr>
		<tr>
			<td class=trtwo align="right"><input class='input' type='submit' value="Запрос" /> 
			<td></td>
			<td class=trone colspan=4><input class='input' type='reset' value="Очистить" /> 
		</tr>
	</table>

</form>
	<div class="footer">	
		<table class="copyright">
			<tr>
				<td><a href="mailto:AnivaSoft@yandex.ru" title="Aniva Soft e-mail">Aniva Soft</a> &copy; 2015</td>
				<td><br /></td>
				<td align="right">Помощь проекту __.</td>
			</tr>
		</table>		
	</div>
</body>
</html>