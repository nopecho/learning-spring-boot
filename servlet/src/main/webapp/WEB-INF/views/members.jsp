<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--JSP제공하는 제공--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/"> main </a>
<table>
    <thead>
    <td>id</td>
    <td>userName</td>
    <td>age</td>
    </thead>
    <tbody>
        <c:forEach var="item" items="${members}">
            <tr>
                <td>${item.id}</td>
                <td>${item.userName}</td>
                <td>${item.age}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>