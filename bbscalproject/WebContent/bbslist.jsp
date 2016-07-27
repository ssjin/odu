<%@page import="sist.co.member.memberDAO, sist.co.member.memberDTO, sist.co.bbs.bbsDTO, sist.co.bbs.bbsDAO, java.util.List" %>
<% request.setCharacterEncoding("utf-8"); %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>

<style type="text/css">
table{
	border-collapse: collapse;
}
table, th, td{
	border: 1px solid black;
}
th{
	background-color: green;
	color: white;
}
.tda{
	background-color: #abcdef;
	color: black;
}
.tdb{
	background-color: #febdab;
	color: black;
}

</style>

</head>
<body>
<%!
public String arrow(int depth){		// 답변글용
	String rs=" <img src='image/arrow.png' width='20px' height='20px'> ";
	String nbsp="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	String ts="";
	
	for(int i=0; i < depth; i++){
		ts += nbsp;
	}
	
	return depth==0?"":ts + rs;
}

/*
ref 	- 	부모글 번호
step 	- 	답글 번호		횡쪽 번호
depth 	- 	답글의 답글		종쪽 번호
*/

%>



<%
Object ologin=session.getAttribute("login");
memberDTO mem=null;
mem=(memberDTO)ologin;
if(ologin==null){
	%>
	<script type="text/javascript">
	alert('로그인 하십시오.');
	location.href='index.jsp';
	</script>
	<%
	return;
}
%>

<h3>환영합니다. <%=mem.getId() %>님 반갑습니다.</h3>


<a href='calendar.jsp'>일정보기</a>

<a href='pdslist.jsp'>자료실</a>
<%
if(mem.getAuth() == 1){
%>
<a href='pollmake.jsp'>투표 만들기</a>
<%
}else if(mem.getAuth() == 3){
%>
<a href='polllist.jsp'>진행중인 투표 보기</a>
<%
}
%>
<a href='logout.jsp'>로그아웃</a>

<hr>

<%
bbsDAO dao=bbsDAO.getInstance();
List<bbsDTO> bbslist = dao.getBBSList();
%>

<table border="1">
<col width="50"/><col width="500"/><col width="150"/>

<tr>
	<th align="center">순서</th>
	<th>제목</th>
	<th align="center">작성자</th>
	<th align="center">조회수</th>
</tr>
<%
if(bbslist==null || bbslist.size()==0){
	%>
	<tr><td colspan="3">작성된 글이 없습니다.</td></tr>
	<%
}


for(int i = 0; i < bbslist.size(); i++){
	bbsDTO dto = bbslist.get(i);
	%>
	
	<tr <%=i%2==0?"class='tda'":"class='tdb'" %>>
		<%if(dto.getDel() == 1) {%>
		<td colspan="4" align="center"><font color="red">삭제된 게시물입니다.</font></td>
		<%} %>
		<%if(dto.getDel() == 0) {%>	
		<td align="center"><%=i+1 %></td>
		<td>
			<%=arrow(dto.getDepth()) %><%-- 댓글인지 아닌지 분간 --%>
			<a href='bbsdetail.jsp?seq=<%=dto.getSeq() %>&id=<%=dto.getId() %>'><%=dto.getTitle() %></a>
		</td>
		<td align="center"><%=dto.getId() %></td>
		<td align="center"><%=dto.getReadcount() %></td>
		<%
		}
		%>
	</tr>	
	<%
	
}
%>



</table>











<a href='bbswrite.jsp'>글쓰기</a>


</body>
</html>