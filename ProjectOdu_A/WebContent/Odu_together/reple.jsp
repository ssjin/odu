<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="odu_together.*" %>
<%@ page import="odu_member.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
Object sseq = session.getAttribute("seq");
int seq = 0;
seq = (int)sseq;

Object ologin = session.getAttribute("login");
MemberDTO mem = null;
if(ologin == null){
	%>
	<script>
	alert("로그인하십시오.");
	location.href="../index.jsp";
	</script>
	<%
	return;
}
mem =(MemberDTO)ologin;
String reple = request.getParameter("reple");

togetherDAO dao= togetherDAO.getInstance();
boolean isS = dao.writeToreple(new torepleDTO(mem.getId(), seq, reple)); 
/* out.print(mem.getId() + "/" + seq + "/" + reple); */
if(isS){
	%>
	<script type="text/javascript">
	location.href='togetherdetail.jsp?seq=<%=seq%>';	
	</script>
	<%
}else{ 
	%>
	<script type="text/javascript">
	alert('리플 안달림');
	location.href='togetherdetail.jsp';	
	</script>
<%
}
%>
</body>
</html>