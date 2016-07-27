<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="java.util.*" %>       
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link rel="stylesheet" href="css/reset.css">

        <link rel="stylesheet" href="css/style.css">

<title>회원가입</title>

<style type="text/css">
.center{
	margin: auto;
	width: 60%;
	border: 3px solid #8AC007;
	padding: 10px;

}

</style>
</head>
<body>

<h3>회원 등록</h3>
<p>안녕하세요~</p>

<div class="center">
	<form action="regAf.jsp" method="post">
	<table border="1">
		<tr>
			<td>아이디</td>
			<td><input type="text" name="id" size="20"></td>
		</tr>
		
	
		
		<tr>
			<td>패스워드</td>
			<td><input type="text" name="pw" size="20"></td>
		</tr>
		
		<tr>
			<td>이름</td>
			<td><input type="text" name="name" size="20"></td>
		</tr>
		
		
		<tr>
			<td>이메일</td>
			<td><input type="text" name="email" size="20"></td>
		</tr>
		
		<tr>
			<td>이메일</td>
			<td><input type="text" name="email" size="20"></td>
		</tr>
		
		
		
		
		<tr>
			<td>이메일</td>
			<td><input type="text" name="email" size="20"></td>
		</tr>
		
		<tr>
			<td colspan="2"><input type="submit" value="회원가입" class="btn btn--right"> </td>
		</tr>
	
	
	
	
	
	
	</table>
	
	
	
	
	
	
	</form>






</div>
<a href="Index.jsp">돌아가기</a>

</body>
</html>