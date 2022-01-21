<%@ page import="nopecho.servlet.domain.member.MemberRepository" %>
<%@ page import="nopecho.servlet.domain.member.Member" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    MemberRepository memberRepository = MemberRepository.getInstance();
    List<Member> members = memberRepository.findAll();
%>
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
    <%
        for (Member member : members) {
            out.write("    <tr>");
            out.write("       <td>"+ member.getId() + "</td>");
            out.write("       <td>"+member.getUserName()+ "</td>");
            out.write("       <td>"+member.getAge()+"</td>");
            out.write("    </tr>");
        }
    %>
    </tbody>
</table>

</body>
</html>
