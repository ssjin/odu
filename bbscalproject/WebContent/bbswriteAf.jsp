<%@page import="sist.co.bbs.bbsDTO, sist.co.bbs.bbsDAO" %>
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
String id = request.getParameter("id");
String title = request.getParameter("title");
String content = request.getParameter("content");


bbsDAO dao = bbsDAO.getInstance();
boolean isS = dao.writeBBS(new bbsDTO(id, title, content));


if(isS){
	%>
	<script type="text/javascript">
	alert("성공적으로 글이 등록되었습니다.");
	location.href='bbslist.jsp';
	</script>
	<%	
}else{
	%>
	<script type="text/javascript">
	alert("다시 입력하십시오.");
	location.href='bbswrite.jsp';
	</script> 
	<%
}

%>

</body>
</html>