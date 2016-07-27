<%@page import="sist.co.poll.*" %>
<% request.setCharacterEncoding("utf-8"); %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Polling</title>
<%!
public boolean isNull(String msg){
	if(msg==null || msg.trim().equals("")){
		return true;
	}
	else return false;
}
%>

</head>
<body>

<%
String id=request.getParameter("id");
String pollid=request.getParameter("pollid");
String pollsubid=request.getParameter("pollsubid");


if(isNull(id) || isNull(pollid) || isNull(pollsubid)){
	%>
	<script type="text/javascript">
	alert("입력된 데이터는 공백일 수 없습니다.");
	location.href="polllist.jsp";
	</script>
	<%
}else{
	pollDAO dao=pollDAO.getInstance();
	boolean isS=dao.polling(new voterDTO(Integer.parseInt(pollid), Integer.parseInt(pollsubid), id));
	if(isS){
	%>
	<script type="text/javascript">
	alert("성공적으로 투표 하셨습니다.");
	location.href="polllist.jsp";
	</script>	
	<%	
	}else{
	%>
	<script type="text/javascript">
	alert("투표하지 못했습니다.");
	location.href="polllist.jsp";
	</script>
	<%	
	}
}
%>




</body>
</html>