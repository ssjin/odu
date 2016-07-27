<%@page import="sist.co.member.memberDTO" %>
<%@page import="sist.co.poll.*" %>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<% request.setCharacterEncoding("utf-8"); %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>polldetail</title>
</head>
<body>


<%
Object ologin=session.getAttribute("login");
memberDTO mem=null;
mem=(memberDTO)ologin;

String spollid=request.getParameter("pollid");
int pollid=Integer.parseInt(spollid);

pollDAO dao = pollDAO.getInstance();
pollDTO poll = dao.getPoll(pollid);		// 투표할 문제에 대한 정보를 취득
List<pollsubDTO> psublist = dao.getPollSubList(pollid);		// 투표할 항목에 대한 정보를 취득

%>


<h3>투표 하기</h3>

<form action="polling.jsp" method="post">
<table border="1" bgcolor="#4D6BB3">
<col width="200"/><col width="500"/>
<tr>
	<td>투표 번호</td>
	<td><input type="text" name="pollid" value='<%=poll.getPollid() %>' readonly="readonly"/></td>	
</tr>
<tr>
	<td>아이디</td>
	<td><input type="text" name="id" value='<%=mem.getId()%>' readonly="readonly"/></td>
</tr>
<tr>
	<td>투표기한</td>
	<td><%=poll.getSdate() %>~<%=poll.getEdate() %></td>
</tr>
<tr>
	<td>투표내용</td>
	<td><textarea name="question" rows="10" cols="50" readonly="readonly"><%=poll.getQuestion() %></textarea>
</tr>

<tr>
	<td>투표 문항수</td>
	<td><%=poll.getItemcount() %>개</td>
</tr>
<tr>
	<td>투표 상세 문항</td>
	<td>
		<%
		for(int i=0; i < psublist.size(); i++){
			pollsubDTO psub = psublist.get(i);
			%>
			<%=((i+1)+"") %>번:<input type="radio" name="pollsubid" value="<%=psub.getPollsubid() %>"/>
			<input type="text" size="60" readonly="readonly" value="<%=psub.getAnswer() %>"/><br>
			<%
			
		}
		
		
		%>
	
	
	</td>
</tr>
<tr align="center">
	<td colspan="2"><input type="submit" value="투표하기"/></td>
</tr>


</table>
</form>

<a href='bbslist.jsp'>글목록</a>

</body>
</html>