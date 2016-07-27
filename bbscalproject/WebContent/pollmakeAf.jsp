<%@page import="sist.co.poll.pollDAO" %>

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

<jsp:useBean id="pbean" class="sist.co.poll.PollBean"/>
<jsp:setProperty property="*" name="pbean"/>

<%
pollDAO dao = pollDAO.getInstance();
System.out.println("pbean:" + pbean.toString());

boolean isS = dao.makePoll(pbean);
String url = "polllist.jsp";

if(isS){
	%>
	<script type="text/javascript">
		alert("성공적으로 작성했습니다.");
		location.href = "<%=url%>";
	</script>
	<%
}else{
	%>
	<script type="text/javascript">
		alert("작성하지 못했습니다.");
		location.href = "<%=url %>";
	</script>
	<%
}
%>


</body>
</html>