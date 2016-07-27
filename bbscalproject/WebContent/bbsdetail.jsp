<%@page import="sist.co.bbs.bbsDAO, sist.co.bbs.bbsDTO, sist.co.member.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.center{
	margin: auto;
	width: 60%;
	border: 3px solid #8ac007;
	padding: 10px;
}
input{
	size: 50;
}
</style>

<script type="text/javascript">

function mysubmit(num){
	var f=document.fm;
	if(num==1){
		f.action="answer.jsp";
	}
	if(num==2){
		f.action="delete.jsp";
	}
	f.submit();	
}



</script>

</head>
<body>
	<a href='logout.jsp'>로그아웃</a>
<h1>상세 글보기</h1>

<div class='center'>

<%
Object ologin=session.getAttribute("login");
memberDTO mem=null;
mem=(memberDTO)ologin;
String sseq=request.getParameter("seq");
int seq=Integer.parseInt(sseq);	//trim()

bbsDAO dao=bbsDAO.getInstance();
dao.readCount(seq);
bbsDTO bbs=dao.getBBS(seq);
%>

<table border="1">
<tr>
	<td>작성자</td>
	<td><%=bbs.getId() %></td>
</tr>
<tr>
	<td>제목</td>
	<td><%=bbs.getTitle() %></td>
</tr>
<tr>
	<td>작성일</td>
	<td><%=bbs.getWdate() %></td>
</tr>
<tr>
	<td>조회수</td>
	<td><%=bbs.getReadcount() %></td>
</tr>
<tr>
	<td>정보</td>
	<td><%=bbs.getRef()%>-<%=bbs.getStep()%>-<%=bbs.getDepth()%></td>
</tr>

<tr>
	<td>내용</td>
	<td><textarea rows="10" cols="50"  
	name='contnet'><%=bbs.getContent() %></textarea>
</td>
</table>

<form name="fm" method="post">
	<input type="hidden" name="seq" value="<%=bbs.getSeq()%>"/>
	<input type="button" onclick="mysubmit(1)"  value="답글"/>
	<% if(mem.getId().equals(bbs.getId())){%>
	<input type="button" onclick="mysubmit(2)"  value="삭제"/>
	<%
	}
	%>
</form>

</div>

<a href="bbslist.jsp">글목록</a>

</body>
</html>