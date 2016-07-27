<%@page import="sist.co.pds.pdsDAO"%>
<%@page import="sist.co.pds.pdsDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.io.File" %>

<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% request.setCharacterEncoding("utf-8"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>자료실</title>
</head>
<body>
<%
pdsDAO dao=pdsDAO.getInstance();
List<pdsDTO> pdsList = dao.getPDSList();

// 톰켓에 배포
String fupload = application.getRealPath("/upload");
//String fupload = "c:\\upload";

System.out.println("fupload:" + fupload);
String filePath = "";
%>

<table border="1">
<col width="50"/><col width="100"/><col width="400"/>
<col width="70"/><col width="60"/><col width="60"/>
<col width="100"/>

<tr bgcolor="#09bbaa" align="center">
	<td>번호</td>
	<td>작성자</td>
	<td>제목</td>
	<td>다운로드</td>
	<td>조회수</td>
	<td>다운수</td>
	<td>작성일</td>
</tr>

<%

for(int i = 0;i < pdsList.size(); i++){
	pdsDTO pds=pdsList.get(i);	
	String bgcolor="";
	if(i%2 == 0){
		bgcolor="#ddeebb";
	}else{
		bgcolor="#ddddcc";
	}
%>

<tr bgcolor="<%=bgcolor %>" align="center" height="5">

	<%if(pds.getDel() == 1){%>
			<td colspan="7"><font color="red">삭제된 자료입니다.</font></td>
	<%
	}
	%>
	<%if(pds.getDel() == 0){
		%>
		<td><%=i+1 %></td>				<%-- seq --%>
	
			<td><%= pds.getId() %></td>		<%-- id --%>
	
		<td align="left">
			<a href='pdsdetail.jsp?pdsid=<%=pds.getSeq() %>'>
				<%=pds.getTitle() %>
			</a>	
		</td>
		
		<td>
			<%
			filePath=fupload+"\\"+pds.getFilename();
			File f = new File(filePath);
			if(!f.exists()){
					%>
					No File!
					<%
			}else{
			%>
			<input type="button" name="btnDown" value="파일"
			onclick="javascript:document.location.href='./filedown?filename=<%=pds.getFilename()%>&pdsid=<%=pds.getSeq()%>'"/>
			<%
			}
			%>		
		</td>
		<td><%=pds.getReadcount() %></td>
		<td><%=pds.getDowncount() %></td>
		<td><%=pds.getRegidate() %></td>
		<%
	}
	%>
</tr>
<%}%>
</table>

<a href='pdswrite.jsp'>올리기</a>

<a href='bbslist.jsp'>HOME</a>


</body>
</html>








