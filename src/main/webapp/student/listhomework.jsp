<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HomeworkList</title>
</head>
<body>
<h4 style="margin-left: 15%">欢迎回来，${sId} <a href="/app/student/sLoginPage">退出</a></h4>
<h1 style="margin-left: 15%">班级列表</h1>
<h3 style="margin-left: 15%; color: red;">${msg}</h3>
<table align="center" width="960" border="1"
       bgcolor="black" cellpadding="1" cellspacing="1">
    <tr align="center" bgcolor="#7fffd4" height="50">
        <td>编号</td>
        <td>班级名称</td>
        <td>班级描述</td>
        <td>查看学生</td>
    </tr>

    <c:forEach items="${cList}" var="cls">
        <tr align="center" bgcolor="white" height="30">
            <td>${cls.id}</td>
            <td>${cls.name}</td>
            <td>${cls.description}</td>
            <td><a href="${pageContext.request.contextPath}/app/student/applyToJoinTheClass?id=${cls.id}">申请加入</a></td>
        </tr>
    </c:forEach>
</table>

<h1 style="margin-left: 15%">作业列表</h1>
<table align="center" width="960" border="1"
       bgcolor="black" cellpadding="1" cellspacing="1">
    <tr align="center" bgcolor="#7fffd4" height="50">
        <td>编号</td>
        <td>班级编号</td>
        <td>作业标题</td>
        <td>作业要求</td>
        <td>附件</td>
        <td>创建时间</td>
        <td>截止日期</td>
        <td>提交</td>
        <td>分数</td>
        <td>操作</td>
    </tr>

    <c:forEach items="${hList}" var="hw">
        <tr align="center" bgcolor="white" height="30">
            <td>${hw.id}</td>
            <td>${hw.classId}</td>
            <td>${hw.title}</td>
            <td>${hw.content}</td>
            <td>${hw.attachment}<c:if test="${null != hw.attachment}"><a
                    href="/app/teacher/download?file=${hw.attachment}">下载</a></c:if></td>
            <td>${hw.createTime}</td>
            <td>${hw.deadline}</td>
            <td>未提交</td>
            <td>${gradeMap.get(hw.id)}</td>
            <td>
                <a href="${pageContext.request.contextPath}/app/student/submitPage?hwID=${hw.id}&&sid=${student.id}">提交</a>
            </td>
        </tr>
    </c:forEach>

    <c:forEach items="${subList}" var="hw">
        <tr align="center" bgcolor="white" height="30">
            <td>${hw.id}</td>
            <td>${hw.classId}</td>
            <td>${hw.title}</td>
            <td>${hw.content}</td>
            <td>${hw.attachment}<c:if test="${null != hw.attachment}"><a
                    href="/app/teacher/download?file=${hw.attachment}">下载</a></c:if></td>
            <td>${hw.createTime}</td>
            <td>${hw.deadline}</td>
            <td>已提交</td>
            <td>${gradeMap.get(hw.id)}</td>
            <td>
                <a href="${pageContext.request.contextPath}/app/student/updatePage?hwID=${hw.id}&&sid=${student.id}">编辑</a>
            </td>
        </tr>
    </c:forEach>


</table>
</body>
</html>
