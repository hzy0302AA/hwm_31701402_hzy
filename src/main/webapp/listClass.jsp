<%--<%@ page import="com.javaee.code.class2.jdbc.HomeworkJDBC" %>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>HomeworkList</title>
</head>
<body>

<table align="center" width="960" border="1"
       bgcolor="black" cellpadding="1" cellspacing="1">
  <tr align="center" bgcolor="#7fffd4" height="50">
    <td>编号</td>
    <td>班级名称</td>
    <td>班级描述</td>
    <td>查看学生</td>
  </tr>

  <c:forEach items="${hList}" var="hw">
    <tr align="center" bgcolor="white" height="30">
      <td>${hw.id}</td>
      <td>${hw.name}</td>
      <td>${hw.description}</td>
      <td><a href="${pageContext.request.contextPath}/app/teacher/listStudentWithClass?clsId=${hw.id}">查看学生情况</a></td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
