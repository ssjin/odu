<%@page import="sist.co.member.memberDAO, sist.co.member.memberDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>regiAf</title>
</head>
<body>

<%
String id=request.getParameter("id");
String pwd=request.getParameter("pwd");
String name=request.getParameter("name");
String email=request.getParameter("email");
memberDAO dao=memberDAO.getInstance();

boolean isS=dao.addMember(new memberDTO(id, pwd, name, email, 0));
if(isS){
	%>
	<script type="text/javascript">
		alert("성공적으로 가입되었습니다.");
		location.href='index.jsp';
	</script>
	<%
}else{
	%>
	<script type="text/javascript">
		alert("다시 기입하여 주시기 바랍니다.");
		location.href='regi.jsp';
	</script>
	<%
}
%>

</body>
</html>