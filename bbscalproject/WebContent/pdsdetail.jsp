<%@page import="sist.co.bbs.bbsDAO, sist.co.bbs.bbsDTO, sist.co.member.*"%>
<%@page import="sist.co.pds.*" %>
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
		f.action="pdslist.jsp";
	}
	if(num==2){
		f.action="delpds.jsp";
	}
	f.submit();	
}



</script>

</head>
<body>
	<a href='logout.jsp'>로그아웃</a>
<h1>상세 자료실보기</h1>

<div class='center'>

<%
Object ologin=session.getAttribute("login");
memberDTO mem=null;
mem=(memberDTO)ologin;

String ppdsid=request.getParameter("pdsid");
int pdsid=Integer.parseInt(ppdsid);



pdsDAO dao=pdsDAO.getInstance();
dao.readCount(pdsid);
pdsDTO pds=dao.getPDS(pdsid);

%>
<table border="1">
<tr>
	<td>작성자</td>
	<td><%=pds.getId() %></td>
</tr>
<tr>
	<td>제목</td>
	<td><%=pds.getTitle() %></td>
</tr>

<tr>
	<td>파일명</td>
	<td>
		<input type="button" name="btnDown" value="<%=pds.getFilename() %>"
		onclick="javascript:document.location.href='./filedown?filename=<%=pds.getFilename()%>&pdsid=<%=pds.getSeq()%>'"/>
	</td>
</tr>

<tr>
	<td>작성일</td>
	<td><%=pds.getRegidate() %></td>
</tr>
<tr>
	<td>다운로드수</td>
	<td><%=pds.getDowncount() %></td>
</tr>
<tr>
	<td>조회수</td>
	<td><%=pds.getReadcount() %></td>
</tr>

<tr>
	<td>내용</td>
	<td><textarea rows="10" cols="50"  
	name='contnet'><%=pds.getContent() %></textarea>
</td>
</table>
 
<form name="fm" method="post">
	<input type="hidden" name="seq" value="<%=pds.getSeq()%>"/>
	<input type="button" onclick="mysubmit(1)"  value="돌아가기"/>
	<% if(mem.getId().equals(pds.getId())){%>
	<input type="button" onclick="mysubmit(2)"  value="삭제"/>
	<%
	}
	%>
</form>

</div>

</body>
</html>