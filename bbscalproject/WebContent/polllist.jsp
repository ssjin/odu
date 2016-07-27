<%@page import="sist.co.poll.voterDTO" %>
<%@page import="sist.co.member.memberDTO" %>
<%@page import="sist.co.poll.pollDTO" %>
<%@page import="sist.co.poll.pollDAO" %>

<%@page import="java.util.*" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="js/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css"/>

<style type="text/css">
#login_wrap {
	margin:10px;
}
#login_wrap th {
	font-weight:bold;
}
#main_wrap { width:800px; margin-left:auto; margin-right:auto; padding:0px; }			
#content_wrap { width: 100%; height: 500px;  background-repeat:no-repeat; background-position:top center;  }
			
.login_title_warp {
	width:500px; color:#FFFFFF; text-align:center; background-color:#3e5fba; border:solid 1px #EFEFEF; font-weight:bold; height:35px;
}
.content_table { width:98%; border-bottom:1px solid #EFEFEF; border-right:1px solid #EFEFEF; border-collapse:collapse; margin-left:auto; margin-right:auto;  clear:both; }
.content_table td, .content_table th { text-align:center; border-top:1px solid #EFEFEF; border-left:1px solid #EFEFEF; padding:0.3em; }
.content_table th { background-color:#4D6BB3; color:#FFFFFF; line-height: 1.7em; font-weight:normal;}
	.content_table td { padding-left:5px; text-align:left; line-height: 1.7em; }
.content_table td.contents { width:100%; height:400px; overflow:auto; }
.content_table th, .content_table td { vertical-align:middle; }

.content_table select { height:19px; border:#CCCCCC solid 1px; vertical-align:middle; line-height: 1.8em; padding-left:0px; }
.content_table select option { margin-right:10px; }
		table{
border : 1px solid black;
}
</style>

</head>
<body>

<%! 

public String two(String msg){		// 2  -> 02
	return msg.trim().length()<2 ? "0"+msg : msg.trim();	
}
public String StringCal(Calendar dd){	// 카렌더 -> 문자열
	String s = dd.get(Calendar.YEAR)+
	two((dd.get(Calendar.MONTH)+1)+"")+""+
	two(dd.get(Calendar.DATE)+"");
	return s;
}
public boolean isEnd(java.util.Date d){	// true이면 기간이 넘었다
	Calendar cal = Calendar.getInstance();	
	cal.setTime(d);
	Calendar t = Calendar.getInstance();
	return Integer.parseInt(StringCal(t))>Integer.parseInt(StringCal(cal));
}

public String pollState(java.util.Date d){
	String s1="<div style='color:RED'>[종료]</div>";
	String s2="<div style='color:BLUE'>[진행중]</div>";
	return isEnd(d)?s1:s2;
}
%>

<%
Object ologin=session.getAttribute("login");
memberDTO mem=null;
if(ologin==null){
		%>
		<script>
		alert("로그인 하십시오");
		location.href='index.jsp';
		</script>
		<%
		return;
}
mem=(memberDTO)ologin;
%>

<%
pollDAO dao = pollDAO.getInstance();
List<pollDTO> pLists = dao.getPollAllList();
%>

<%
if(mem.getAuth() == 1){			// 관리자
	
	
	
}else if(mem.getAuth() == 3){	// user
	%>
<table class="content_table" border="1">
<col width="50"/><col width="50"/><col width="300"/><col width="100"/>
<col width="100"/><col width="100"/><col width="50"/>
<col width="50"/><col width="100"/>

<tr>
	<th>번호</th><th>아이디</th><th>질문</th><th>결과</th>
	<th>시작일</th><th>끝나는날</th><th>질문 항수</th>
	<th>투표수</th><th>등록일</th>
</tr>

<%=mem.getId() %>님 투표하세요.
<%
for(int i=0;i < pLists.size(); i++){
	pollDTO poll=pLists.get(i);
	%>
	<tr bgcolor="#aabbcc">
		<td><%=poll.getPollid() %></td> <%-- 질문 번호 --%>
		<td><%=poll.getId() %></td>		<%-- 만든사람의 id:admin --%>
		
	<% 
	// 질문
	boolean isS = dao.isVote(new voterDTO(poll.getPollid(), -1, mem.getId()));	
	if(isS || isEnd(poll.getEdate())){	// 투표를 한 경우, 기간이 넘어간 경우 
		%>
		<td>[마감]<%=poll.getQuestion() %></td>
		<%
	}else{	// 투표가 가능
		%>
		<td>
			<a href="polldetail.jsp?pollid=<%=poll.getPollid() %>">
				<%=poll.getQuestion() %>
			</a>
		</td>
	<%
	}
	%>	
	<td>	<%-- 결과 --%>
	<%
		if(isS || isEnd(poll.getEdate())){
			%>
			<a href="pollresult.jsp?pollid=<%=poll.getPollid() %>">
				결과
			</a>
			<% 
		}else{	// 투표를 안한 상태
			%>
			<img alt="투표" src="image/pen.gif"/>
			<%			
		}	
	%>	
	</td>	
	<td><%=poll.getSdate() %></td>		<%-- 시작일 --%>
	<%-- <td><%=poll.getEdate() %></td>		<%-- 마감일 -- %>
	 --%>
	<td><%= pollState(poll.getEdate())%><%=poll.getEdate()%> </td>
	<td><%= poll.getItemcount() %></td>
	<td><%= poll.getPolltotal() %></td>
	<td><%= poll.getRegdate() %></td>
	</tr>
	<%	
	}
}
%>
</table>
	
<%
if(mem.getAuth() == 1){
	%>
	<a href='pollmake.jsp'>투표 만들기</a>
	<a href='bbslist.jsp'>HOME</a>
	<%
}else if(mem.getAuth() == 3){
	%>	
	<a href='bbslist.jsp'>HOME</a>
	<%
}
%>
	




</body>
</html>









