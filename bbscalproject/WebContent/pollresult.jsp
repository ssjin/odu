<%@page import="sist.co.poll.*" %>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>pollresult</title>

<%!
public int barWidth(int acount, int total){
	if(total==0){
		return 0;
	}else{
		return (int)(1.0*acount/total*400);
	}
}




public String barRatio(int acount, int total){
	if(total==0){
		return String.format("0 (%d%%)", 0);
	}else{
		return String.format("%d (%.2f%%)", acount, (1.0*acount*100));
	}
}



public String makeBar(int acount, int total){
	
	String t="<img src='image/rd.gif' width='%dpx' height='21px'/>%s ";
	
	String s=String.format(t, barWidth(acount, total), barRatio(acount, total));
	
	return s;
}


%>


</head>
<body>
<%
String spollid=request.getParameter("pollid");
int pollid=Integer.parseInt(spollid);

pollDAO dao=pollDAO.getInstance();
pollDTO poll=dao.getPoll(pollid);
List<pollsubDTO> psublist=dao.getPollSubList(pollid);

%>

<h3>투표내용</h3>

<table border="1" bgcolor="pink">
<col width="150"/><col width="550"/>
<tr>
	<td>투표번호</td>
	<td><input type="text" name="pollid" value="<%=poll.getPollid() %>" readonly="readonly"></td>	
</tr>
<tr>
	<td>투표기한</td>
	<td><%=poll.getSdate() %>~<%=poll.getEdate() %></td>

</tr>

<tr>
	<td>투표내용</td>
	<td><textarea rows="10" cols="50" name="question" readonly="readonly"><%=poll.getQuestion() %></textarea></td>
</tr>
<tr>
	<td>투표문항수</td>
	<td><%=poll.getItemcount() %>개</td>
</tr>

<tr>
	<td>투표상세문항</td>
	<td>
		<%
		for(int i=0; i<psublist.size(); i++){
			pollsubDTO psub=psublist.get(i);
			%>
			<%=(i+1+"") %>번:<input type="text" size="60" readonly="readonly" value='<%=psub.getAnswer() %>'/><br>
			<%
		}
		%>
	</td>
</tr>

<tr>
	<td>투표결과(전체 투표자:<%=poll.getPolltotal() %>)</td>
	<td>
		<%
		for(int i = 0; i< psublist.size(); i++){
			pollsubDTO psub=psublist.get(i);
			%>
			<table bgcolor="#ffffff">
			<col width="50"/><col width="500"/>
			<tr>
				<td><%=(i+1+"") %>번</td>
				<td><%=makeBar(psub.getAcount(), poll.getPolltotal()) %></td>
			</tr>
			
			</table>
			<%
		}
		
		%>
	
	</td>

</tr>


</table>

<a href='polllist.jsp'>투표목록</a>

</body>
</html>