<%@page import="sist.co.member.memberDAO"%>
<%@page import="java.util.*" %>
<%@page import="sist.co.member.memberDTO" %>
<%request.setCharacterEncoding("utf-8"); %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>pollmake</title>

<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" >
$(document).ready(function(){	// 보기 설정을 보여주는 부분
	for(i=5;i<=10; i++){
		$('#poll'+i).hide();	// 숨김
	}
});

function pollchange(me){	// 보기의 갯수를 수정하는 함수
	
	var num=me.options[me.selectedIndex].value;	// 2번을 선택하면 Index값으로 2가 넘어 온다
	alert('num=' + num);
	
	for(i = 1;i <= 10; i++){
		$('#poll'+i).val("");
		$('#poll'+i).hide(); // 숨김
	}
	for(i = 1;i <= num; i++){
		$('#poll'+i).show()	// 보임	
	}
}
</script>

</head>
<body>

<%
memberDTO user=(memberDTO)session.getAttribute("login");

Calendar cal = Calendar.getInstance();
int tyear = cal.get(Calendar.YEAR);
int tmonth = cal.get(Calendar.MONTH) + 1;
int tday = cal.get(Calendar.DATE);

%>

<h3>투표 문항 만들기</h3>

<form action="pollmakeAf.jsp" method="post">
<table border="1" bgcolor="pink">
<col width="200"/><col width="500"/>

<tr>
	<td>아이디</td>
	<td>
		<%=user.getId() %>
		<input type="hidden" name="id" value='<%=user.getId() %>'/>
	</td>
</tr>
<tr>
	<td>투표기한</td>
	<td>
		<select name='syear'>
			<%
			for(int i = tyear;i < tyear + 6; i++ ){
				%>
				<option <%=(tyear+"").equals(i+"")?"selected='selected'":"" %> value="<%=i%>"><%=i%></option>
				<%
			}%>			
		</select>년
		<select name='smonth'>
			<%
			for(int i = 1;i <= 12; i++ ){
				%>
				<option <%=(tmonth+"").equals(i+"")?"selected='selected'":"" %> value="<%=i%>"><%=i%></option>
				<%
			}%>			
		</select>월
		<select name='sday'>
			<%
			for(int i = 1;
					i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++ ){
				%>
				<option <%=(tday+"").equals(i+"")?"selected='selected'":"" %> value="<%=i%>"><%=i%></option>
				<%
			}%>			
		</select>일
			~
		<select name='eyear'>
			<%
			for(int i = tyear;i < tyear + 6; i++ ){
				%>
				<option <%=(tyear+"").equals(i+"")?"selected='selected'":"" %> value="<%=i%>"><%=i%></option>
				<%
			}%>			
		</select>년
		<select name='emonth'>
			<%
			for(int i = 1;i <= 12; i++ ){
				%>
				<option <%=(tmonth+"").equals(i+"")?"selected='selected'":"" %> value="<%=i%>"><%=i%></option>
				<%
			}%>			
		</select>월
		<select name='eday'>
			<%
			for(int i = 1;
					i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++ ){
				%>
				<option <%=(tday+"").equals(i+"")?"selected='selected'":"" %> value="<%=i%>"><%=i%></option>
				<%
			}%>			
		</select>일	
	</td>
</tr>

<tr>
	<td>투표 내용</td>
	<td>
		<textarea name="question" rows="10" cols="50"></textarea>
	</td>
</tr>


<tr>
	<td>투표 문항수</td>
	<td>
		<select name="itemcount" onchange="pollchange(this)">
			<%
			for(int i = 2; i <= 10; i++){
			%>
				<option <%=(4+"").equals(i+"")?"selected='selected'":"" %> value="<%=i %>" ><%=i %></option>
			
			
			<%	
			}
			%>
		</select>개
		
	</td>
</tr>

<tr>
	<td>투표 상세 문항</td>
	<td>
		<%
		for(int i = 1; i <= 10; i++){
			%>
			<div id='poll<%=i %>'>
				<%=(i+"") %>번:<input type="text" name='poll<%=i%>' size="60"><br>
			</div>
				
		<%
		}
		
		%>
	
	</td>
</tr>
<tr align="center">
	<td colspan="2"><input type="submit" value="투표만들기"></td>
</tr>


</table>
</form>

<a href="polllist.jsp">진행중인 투표 목록</a>

</body>
</html>



