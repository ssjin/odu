<%@page import="odu_timeline.timelineDAO"%>
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
String id = request.getParameter("id");

String content = request.getParameter("content");
String f_name = request.getParameter("f_name");

timelineDAO dao = timelineDAO.getInstance();
boolean isS = dao.updateTimeline(seq, id, content, f_name);
if(isS){
%>
   <script type="text/javascript">
      alert("수정 성공");
      location.href="timeline.jsp";
   </script>
   <%
}else{
   %>
   <script>
      alert("수정 실패");
      location.href='timeline.jsp?<%=seq%>';
   </script>
   <%
}
%>
</body>
</html>