<%@page import="odu_bbs.bbsDAO"%>
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
String typee = request.getParameter("type");			// bbsdetail에서의 함수호출 버튼 순서 		0: update	1: delete
int type = Integer.parseInt(typee);

String seqq = request.getParameter("seq");
int seq = Integer.parseInt(seqq);
//--------------------------------------------------------------------------------

bbsDAO dao = bbsDAO.getInstance();

if(type == 0){			// update
	
}
if(type == 1){			// delete
	
	String repleseqq = request.getParameter("repleseq");	//댓글의 시퀀스
	int repleseq = Integer.parseInt(repleseqq);
	
	boolean deleteS = dao.deleteReple(repleseq);
	if(deleteS){
		%>
		<script>
		alert("삭제 성공");
		location.href="bbsdetail.jsp?seq="+<%=seq%>+"";
		</script>
		repleseq : <%=repleseq %>
		<%
	}
}
if(type == 2){			// insert
	String wid = request.getParameter("repid");	//댓글 쓴 사람 id
	String replecontent = request.getParameter("replecontent");
	boolean insertS = dao.repleInsert(2, seq, wid, replecontent);
	if(insertS){
	%>
	<script>
	alert("추가");
	location.href="bbsdetail.jsp?seq="+<%=seq%>+"";
	</script>
	작성자 아이디  : <%=wid %>
	작성자 댓글 : <%=replecontent %>
	<%
	}
}
%>




</body>
</html>