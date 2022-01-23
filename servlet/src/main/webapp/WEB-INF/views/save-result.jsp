<%@ page import="nopecho.servlet.domain.member.Member" %><%--
  Created by IntelliJ IDEA.
  User: nopecho
  Date: 2022/01/23
  Time: 12:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id = <%=((Member)request.getAttribute("member")).getId()%></li>
    <li>user name = <%=((Member)request.getAttribute("member")).getUserName()%></li>
    <li>age = <%=((Member)request.getAttribute("member")).getAge()%></li>

<%-- JSP el표현식! JS 백틱이랑 비슷하다고 생각하면됨(프로퍼티 접근법) request의 getAttribute값을 꺼내와서 거기 모델의 데이터에 접근 가능   --%>
    <li>id = ${member.id}</li>
    <li>name = ${member.userName}</li>
    <li>age = ${member.age}</li>
</ul>
<a href="/">메인으로</a>
</body>
</html>