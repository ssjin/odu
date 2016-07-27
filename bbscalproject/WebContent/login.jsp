<%@page import="sist.co.member.memberDAO, sist.co.member.memberDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
String id=request.getParameter("id");
String pwd=request.getParameter("pwd");

memberDAO dao=memberDAO.getInstance();
memberDTO mem=dao.login(new memberDTO(id, pwd, null, null, 0));

if(mem != null && !mem.getId().equals("")){
	session.setAttribute("login", mem);
	session.setMaxInactiveInterval(30*60);
	%>
	<script type="text/javascript">
		alert("안녕하세요. <%=mem.getId()%>님! 만나서 방갑습니다.");
		location.href='bbslist.jsp';
	</script>
	<%
}else{
	%>
	<script type="text/javascript">
		alert("아이디나 패스워드를 확인해 주십시오.");
		location.href='index.jsp';
	</script>
	<%
}
%>

</body>
</html>