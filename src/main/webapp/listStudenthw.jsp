<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>StudentHwList</title>
</head>
<body>

<table align="center" width="960" border="1"
       bgcolor="black" cellpadding="1" cellspacing="1">
    <tr align="center" bgcolor="#7fffd4" height="50">
        <td>学生ID</td>
        <td>作业标题</td>
        <td>回答内容</td>
        <td>附件</td>
        <td>提交时间</td>
        <td>更新时间</td>
        <td>分数</td>
    </tr>

    <c:forEach items="${shList}" var="hw">
        <tr align="center" bgcolor="white" height="30">
            <td>${hw.studentId}</td>
            <td>${hw.homeworkTitle}</td>
            <td>${hw.homeworkContent}</td>
            <td>${hw.attachment}<a href="/app/teacher/download?file=${hw.attachment}">下载</a></td>
            <td>${hw.submitTime}</td>
            <td>${hw.updateTime}</td>
            <td style="width: 110px;">
                <form action="/app/teacher/updateGrade">
                    <input type="hidden" name="shwId" value="${hw.id}">
                    <input type="number" name="grade" style="width:50px" value="${hw.grade}">
                    <input type="submit" value="确认"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
