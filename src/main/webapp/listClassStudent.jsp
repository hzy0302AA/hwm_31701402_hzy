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
    <td>学号</td>
    <td>学生姓名</td>
    <td>当前状态</td>
    <td>操作</td>
  </tr>

  <c:forEach items="${sList}" var="hw">
    <tr align="center" bgcolor="white" height="30">
      <td>${hw.id}</td>
      <td>${hw.studentId}</td>
      <td>${sMap.get(hw.studentId)}</td>
      <td>${hw.active == 2 ? '通过' : '等待审核'}</td>
      <td>
        <a href="${pageContext.request.contextPath}/app/teacher/applyClassJoin?id=${hw.id}&pass=true">通过</a>
        <a href="${pageContext.request.contextPath}/app/teacher/applyClassJoin?id=${hw.id}&pass=false">拒绝</a>
      </td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
