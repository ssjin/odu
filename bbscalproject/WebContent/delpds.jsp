<%@page import="sist.co.bbs.bbsDAO, sist.co.bbs.bbsDTO"%>
<%@page import="sist.co.pds.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
String sseq = request.getParameter("seq");
int seq = Integer.parseInt(sseq.trim());

pdsDAO dao = pdsDAO.getInstance();
boolean isS=dao.deletePDS(seq);

if(isS){
%>
   <script type="text/javascript">
   alert("삭제완료");
   location.href='pdslist.jsp';
   </script>
<%
}else {
   %>
   <script type="text/javascript">
   alert("삭제안됨");
   location.href='pdsdetail.jsp';
   </script>
   <%
}
%>
</body>
</html>