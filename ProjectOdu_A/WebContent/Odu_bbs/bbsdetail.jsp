<%@page import="odu_member.MemberDAO"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="odu_bbs.bbsDAO"%>
<%@page import="odu_bbs.bbsDTO"%>
<%@page import="odu_bbs.bbsRepleDTO"%>
<%@page import="odu_member.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	function aa(a) {			
		if(a == 0){
			alert("update");
			//document.frm1.action="repleAf.jsp?type=0&replecontent="+document.frm1.replecontent.value+"";
			
		}
		if(a == 1){
			alert("delete");
			document.frm1.action="repleAf.jsp?type=1";
	<%-- 		<%
				boolean deleteS=dao.deleteReple(repledto.getSeq());
				System.out.println("repledto!! = " + repledto);
				if(deleteS){
					%> document.frm1.action = "bbsdetail.jsp?seq="+<%=seq%>+"";
					<%
				}
		%> --%>
		}
		if(a == 2){
			alert("insert");
			document.frm1.action="repleAf.jsp?type=2&replecontent="+document.frm1.replecontent.value+"";
		}
		if(a == 3){
			alert("답글");
			document.frm1.action="bbsanswer.jsp?";
		}
		
		document.frm1.submit();
}
		/* function bb() {
		alert("함수 호출");
			var idx =  ($(".repleUpdate").index(this) * 2) + 1; 
			 $("#reply_text table tr:eq("+idx+") td:eq(2)").html("<textarea></textarea>"); 
		} */	
		function bb(a) {
		var str = a;
		var viewObj = document.getElementById(a);
		document.frm1.a.INNERHTML = "<textarea>" + viewObj.value + "</textarea>";
		
/* 		txtObj.value = viewObj.innerHTML;

		viewObj.style.display = "none";
		txtObj.style.display = "block"; */
		}
		</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상세 글보기</title>

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
<a href='logout.jsp'>로그아웃</a>

<h1>상세 글보기</h1>

<div class='center'>
<%
String sseq=request.getParameter("seq");
int seq=Integer.parseInt(sseq);	//trim()

System.out.println("seq = " + seq);

MemberDTO login = (MemberDTO)session.getAttribute("login");
String loginId = login.getId();

bbsDAO dao=bbsDAO.getInstance();
dao.readCount(seq);
bbsDTO bbsdto=dao.getBBS(seq);

/* System.out.println(bbs.toString()); */
%>
<form name="frm1" method="post">
<table border="1">

<tr>
	<td>작성자</td>
	<td><%=bbsdto.getId() %></td>
</tr>

<tr>
	<td>제목</td>
	<td><%=bbsdto.getTitle() %></td>
</tr>

<tr>
	<td>작성일</td>
	<td><%=bbsdto.getWdate() %></td>
</tr>

<tr>
	<td>조회수</td>
	<td><%=bbsdto.getReadcount() %></td>
</tr>

<tr>
	<td>정보</td>
	<td><%=bbsdto.getRef()%>-<%=bbsdto.getStep()%>-<%=bbsdto.getDepth()%></td>
</tr>

<tr>
	<td>내용</td>
	<td><textarea rows="10" cols="50" 
	name='content'><%=bbsdto.getContent() %></textarea>
	</td>
</tr>	
</table>

	<input type="hidden" name='seq' value="<%=bbsdto.getSeq() %>"/>
	<input type="submit" onclick="aa(3)" value="답글">

<br>

	<input type="hidden" name='seq' value="<%=bbsdto.getSeq() %>"/>
	<input type="submit" value="삭제">
	
<hr>

<div style=" width: 600px; height: 150px; background-color: #79a099; ">
<table>
<col width="30"/><col width="350"/><col width="10">
<tr>
	<td><%=loginId%> 
	<input type="hidden" name="repid" value="<%=bbsdto.getId()%>" /> 
	<input type="hidden" name="seq_rep" value="<%=bbsdto.getSeq()%>" /> 
	</td>
	<td colspan="2"><textarea name="replecontent" rows="8" cols="50"></textarea> </td>
</tr>
<tr>
	<td></td>
	<td></td>
	<td><input type="submit" onclick="aa(2)" value="입력"></td>
</tr>
</table>
<hr>

<%
MemberDTO loginSession = (MemberDTO)session.getAttribute("login");
String loginid = loginSession.getId();

System.out.println("loginID = " + loginId);

bbsDAO dao1 = bbsDAO.getInstance();
List<bbsRepleDTO> replelist = dao1.getreplelist(2,seq);

for(int i = 0; i < replelist.size(); i++){
	bbsRepleDTO bdto = replelist.get(i);
	System.out.println("bdto = " + bdto.toString());
}

if(replelist.size() >= -1){
%>
	<table border="2">
	<col width="350"/>
	<%
	for(int i = 0; i < replelist.size(); i++){
		bbsRepleDTO repledto = replelist.get(i);
		%>
		<tr>
			<td>
			<%=repledto.getId()%>
			<%
			String str = repledto.getWdate();
			
			String year = str.substring(2, 4);
			String month = str.substring(5, 7);
			String day = str.substring(8, 10);
			String hour = str.substring(11, 13);
			String min = str.substring(14, 16);
			
			String todate = year + "/" + month + "/" + day + " " +  hour + ":" + min;
			%>
			<font size="1px">
			<%=todate %>
			</font>
			<%
			if(repledto.getId().equals(loginId)){
			 	System.out.println("**repleseq = " + repledto.getSeq());
			%>
			<input type="hidden" name="repleseq" value="<%=repledto.getSeq()%>">
			<%
			}
			%>
		</td>
	</tr>
	<tr>
		<td colspan="2">
		<div id="viewTxt<%=i%>">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=repledto.getContent() %>
		<% if(loginId.equals(repledto.getId())){ %>
			<input type="button" class="repleUpdate" size="6px" onclick="bb(viewTxt<%=i%>)" value="수정">
			<input type="button" name="repleDelete" size="6px" onclick="aa(1)" value="삭제">
		</div>
		</td>
	</tr>
	
		<%
	}
	}
}
%>
	</table>
</div>
</form>
</div>
<a href="bbslist.jsp">글 목록</a>

</body>
</html>



