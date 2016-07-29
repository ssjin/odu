<%@page import="odu_bbs.bbsDAO"%>
<%@page import="odu_bbs.bbsDTO"%>
<%@page import="odu_member.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8");%>
<%
int bbsseqq = Integer.parseInt(request.getParameter("bbsseq"));
System.out.println("++bbsseqq = " + bbsseqq);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	function aa(a,b) {
		if(a == 0){
			alert("update");
			//document.frm1.action="repleAf.jsp?type=0&replecontent="+document.frm1.replecontent.value+"";
			document.frm1.action="repleAf.jsp?type=5&bbsseq="+b+"&content="+document.frm1.content.value+"&title="+document.frm1.title.value+"";		
		}
		if(a == 1){
			alert("cancel");
			//document.frm1.action="repleAf.jsp?type=0&replecontent="+document.frm1.replecontent.value+"";
			document.frm1.action="repleAf.jsp?type=4&bbsseq="+b+"";
		}
	}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 수정</title>

<style type="text/css">
.center{
	margin:auto;
	width:60%;
	border:3px solid #8AC007;
	padding:10px;
}
input{
	size:50;
}
</style>

</head>
<body>

<%
Object objlogin=session.getAttribute("login");
MemberDTO mem=(MemberDTO)objlogin;

int bbsseq = Integer.parseInt(request.getParameter("bbsseq"));
String bbsid = request.getParameter("bbsid");
String bbstitle = request.getParameter("bbstitle");
String bbswdate = request.getParameter("bbswdate");
String bbscontent = request.getParameter("bbscontent");

%>    


<a href='logout.jsp'>로그아웃</a>

<h1>글 수정</h1>

<div class='center'>

<form name="frm1" method="post">

<table border="1">
<col width="200"/><col width="500"/>

<tr>
	<td>아이디</td>
	<td>
		<%=bbsid%>
	</td>
</tr>

<tr>
	<td>제목</td>
	<td><input type="text" name="title" size="50" value="<%=bbstitle%>"/></td>
</tr>
<tr>
	<td>작성일</td>
	<td><%=bbswdate%></td>
</tr>
<tr>
	<td>내용</td>
	<td>
		<textarea rows="10" cols="50" name="content"><%=bbscontent%></textarea>
	</td>
</tr>

<tr>
	<td colspan="2">
	<input type="hidden" name="seq" value="<%=bbsseqq %>">
	<input type="submit" onclick="aa(0,<%=bbsseqq %>)" value="수정"/>
	<input type="submit" onclick="aa(1,<%=bbsseqq %>)" value="취소"/></td>
</tr>

</table>
</form>
</div>

<a href='bbslist.jsp'>글목록</a>

</body>
</html>
