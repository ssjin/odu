<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery-1.12.4.js"></script>
<script>
  function changeIframeUrl(url)
            {
	  			alert(url);
                document.getElementById("main_frame").src = url;
            }
</script>
<style type="text/css">
#div1{
	background-color: pink;
	height: 100px;
	width: 1900px;
	float: none;
}
#div2{
	background-color: #aff234;
	width: 300px;
	height: 600px;
	float: left;
}
#div3{
	margin-left : 330px;
	margin-top : 10px;
	width: 1300px;
	height:1000px;
	background-color: green;
}
tr{
	height: 100px;
}
td{
	text-align: center;
	font-size: 3em;
}
a{
	text-decoration: none;
	font-family: 궁서체;
}
iframe{
	
}
</style>
</head>
<body>
<div id="div1"></div>
<div id="div2">
<table rules="rows">
<col width="300px">
<tr><td><button value="timeline" onclick = "changeIframeUrl('./timeline.jsp')">timeline</button></td></tr>
<tr><td><button value="일정관리" onclick = "changeIframeUrl('./Calendar.jsp')">일정관리</button></td></tr>
<tr><td><button value="같이해요" onclick = "changeIframeUrl('./together.jsp')">같이해요</button></td></tr>
<tr><td><button value="자유게시판" onclick = "changeIframeUrl('./freebbs.jsp')">자유게시판</button></td></tr>
<tr><td><a href="people.jsp">피플</a></td></tr>
<tr><td><button value="재판" onclick = "changeIframeUrl('./judgepoll.jsp')">재판</button></td></tr>
</table>
</div>
<div id="div3">
<iframe src = "timeline.jsp" width="100%" height="100%" frameborder="0" border="0" scrolling="yes" bgcolor=#EEEEEE bordercolor="#FF000000" marginwidth="0" marginheight="0" name="main_frame" id="main_frame"></iframe> 


</div>
</body>
</html>