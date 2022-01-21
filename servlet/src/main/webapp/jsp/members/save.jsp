<%@ page import="nopecho.servlet.domain.member.MemberRepository" %>
<%@ page import="nopecho.servlet.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //request, response는 사용가능 -> JSP파일도 서블릿으로 변환되서 실행되기 때문
    MemberRepository memberRepository = MemberRepository.getInstance();
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username,age);
    memberRepository.save(member);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id = <%=member.getId()%></li>
    <li>username = <%=member.getUserName()%></li>
    <li>age = <%=member.getAge()%></li>

</ul>
<a href="/">메인으로</a>
</body>
</html>
