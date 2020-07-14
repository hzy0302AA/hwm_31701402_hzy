<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
</head>
<body style="background-image: url(${pageContext.request.contextPath}/pic/body.jpg)">

<form enctype="multipart/form-data"
	  action="${pageContext.request.contextPath}/app/teacher/addClass?tid=${tid}"
	  method="post">
	<table align="center">

		<tr>
			<td>班级名称：</td>
			<td><input name="title" style="width:500px"></td>
		</tr>
		<tr>
			<td>班级描述：</td>
			<td><textarea name="desc" rows="20" cols="80" style="width: 500px"></textarea></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="添加"/>
			</td>
		</tr>
		<tr>
			<td></td>
			<td><font color="red">${requestScope.message}</font></td>
		</tr>
	</table>
</form>
</body>
</html>
