<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="odu_together.*" %>
<%@ page import="odu_member.*" %>
<%
String sseq = request.getParameter("seq");
int seq = Integer.parseInt(sseq);
Object ologin = session.getAttribute("login");
MemberDTO mem = null;
if(ologin == null){
	%>
	<script>
	alert("로그인하십시오.");
	location.href="../index.jsp";
	</script>
	<%
	return;
}
mem =(MemberDTO)ologin;
togetherDAO dao= togetherDAO.getInstance();
dao.readCount(seq);
togetherDTO dto = dao.getToDTO(seq);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="../css/w3.css">
 <link rel="stylesheet" href="../css/w3-theme-blue-grey.css">
<title>오늘부터 듀오</title>
<style>
html,body,h1,h2,h3,h4,h5 {font-family: "Open Sans", sans-serif}
</style>
<script type="text/javascript">
state = 0;

function changeimage(){
	if(state == 0){
		state = 1;
		document.getElementById('likeimage').src = "../image/unlike.png";
	}
	else{
		state = 0;
		document.getElementById('likeimage').src = "../image/like.png";
	}
}
</script>
</head>
<body class="w3-theme-l5">

<!-- Navbar -->
<div class="w3-top">
 <ul class="w3-navbar w3-theme-d2 w3-left-align w3-large">
  <li class="w3-hide-medium w3-hide-large w3-opennav w3-right">
    <a class="w3-padding-large w3-hover-white w3-large w3-theme-d2" href="javascript:void(0);" onclick="openNav()"><i class="fa fa-bars"></i></a>
  </li>
  <li><a href="#" class="w3-padding-large w3-theme-d4"><i class="fa fa-home w3-margin-right"></i><img src="../image/logo1.png"></a></li>
  <li class="w3-hide-small"><a href="#" class="w3-padding-large w3-hover-white" title="News"><i class="fa fa-globe"></i><img src="../m1.png"></a></li>
  <li class="w3-hide-small"><a href="#" class="w3-padding-large w3-hover-white" title="Account Settings"><i class="fa fa-user"></i><img src="../chaticon1.png"></a></li>


  <li class="w3-hide-small w3-right"><button type="button" class="w3-btn w3-theme"><i class="fa fa-pencil"></i>로그아웃</button></li>
 </ul>
</div>

<!-- 위에 아이콘 -->
<div id="navDemo" class="w3-hide w3-hide-large w3-hide-medium w3-top" style="margin-top:51px">
  <ul class="w3-navbar w3-left-align w3-large w3-theme">
    <li><a class="w3-padding-large" href="#">321332133131313</a></li>
    <li><a class="w3-padding-large" href="#">Link 2</a></li>
    <li><a class="w3-padding-large" href="#">Link 3</a></li><!--  링크 부분-->
    <li><a class="w3-padding-large" href="#">My Profile</a></li>
  </ul>
</div>

<!-- 전체 페이지 시작 -->
<div class="w3-container w3-content" style="max-width:1400px;margin-top:80px;  "  >
  <!-- The Grid -->
  <div class="w3-row">
    <!-- 왼쪽 시작 -->
    <div class="w3-col m3" >
      <!-- 프로필 부분 -->
      <div class="w3-card-2 w3-round w3-white">
        <div class="w3-container">
         <h4 class="w3-center">My Profile</h4>
         <p class="w3-center"><img src="../image/pro.png" class="w3-circle" style="height:106px;width:106px" alt="Avatar"></p>
         <hr>
         <p><i class="fa fa-pencil fa-fw w3-margin-right w3-text-theme"></i> 개발자</p>
         <p><i class="fa fa-home fa-fw w3-margin-right w3-text-theme"></i> 서울</p>
         <p><i class="fa fa-birthday-cake fa-fw w3-margin-right w3-text-theme"></i> 1988년생 2월 2일</p>
        </div>
      </div>
      <br>
      
      <!--왼쪽 메뉴 바 -->
      <div class="w3-card-2 w3-round">
        <div class="w3-accordion w3-white" >
          <button onclick="" class="w3-btn-block w3-theme-l1 w3-left-align"><i class="fa fa-circle-o-notch fa-fw w3-margin-right"></i> 타임라인</button>
          <button onclick="" class="w3-btn-block w3-theme-l1 w3-left-align"><i class="fa fa-calendar-check-o fa-fw w3-margin-right"></i> 일정관리 </button>
           <button onclick="" class="w3-btn-block w3-theme-l1 w3-left-align"><i class="fa fa-calendar-check-o fa-fw w3-margin-right"></i> 같이해요</button>
          <button onclick="" class="w3-btn-block w3-theme-l1 w3-left-align"><i class="fa fa-calendar-check-o fa-fw w3-margin-right"></i> 자유게시판</button>
          <button onclick="" class="w3-btn-block w3-theme-l1 w3-left-align"><i class="fa fa-calendar-check-o fa-fw w3-margin-right"></i> 재판게시판</button>
      
          
          <button onclick="myFunction('Demo3')" class="w3-btn-block w3-theme-l1 w3-left-align"><i class="fa fa-users fa-fw w3-margin-right"></i> 피플</button>
          <div id="Demo3" class="w3-accordion-content w3-container">
         <div class="w3-row-padding">
         <br>
            <div class="w3-quarter">
             <img src="../image/pro1.png" style="width:50px" class="w3-margin-bottom w3-circle w3-hover-opacity">
           </div>
          <div class="w3-quarter">
             <img src="../image/pro2.png" style="width:50px" class="w3-margin-bottom w3-circle w3-hover-opacity">
           </div>
           <div class="w3-quarter ">
              <img src="../image/pro3.png" style="width:50px" class="w3-margin-bottom w3-circle w3-hover-opacity">
           </div>
         
         </div>
          </div>
        </div>
      </div>
      <br>
      
    
    </div>
    <!-- 왼쪽 끝 -->
   
    <!-- 글이 보여지는부분.  -->
    <!-- 중간 시작 -->
    <div class="w3-col m7">
    
      
      
      <div class="w3-container w3-card-2 w3-white w3-round w3-margin"><br>
        <img src="../image/pro1.png" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:60px">
        <span class="w3-right w3-opacity"><%=dto.getM_date().substring(0, 10) %></span>
        <h5><%=dto.getId() %></h5><br>
        <h4>제목 : <%=dto.getTitle() %></h4>
        <hr class="w3-clear" style="border-color: #1ab188;">
        <p><%if(dto.getF_name() != null){
        	%>
        	<img style="width:400px; height: 200px;" src="../upload/<%=dto.getF_name() %>" alt="사진없음">
        	<%
        	}
        	%>
        	</p>
        	<p><%=dto.getContent() %>	</p>
        <div class="w3-row-padding" style="margin:0 -16px">
            <div class="w3-half">
            	 <i><b>모집:</b> <%=dto.getT_num() %></i><br>
       			 <i><b>같이해요!:</b> <%=dto.getParent() %></i>
            </div>
        </div>
        <%if(dto.getId().equals(mem.getId()) && dto.getDead() != 1){
        	%>
        	 <a href="togetherMod.jsp?seq=<%=dto.getSeq()%>"><button type="button"class="w3-btn w3-theme-d1 w3-margin-bottom">수정</button></a>
        	 <a href="togetherEnd.jsp?seq=<%=dto.getSeq()%>"><button type="button"class="w3-btn w3-theme-d1 w3-margin-bottom">마감</button></a>
        	 <a href="togetherDEL.jsp?seq=<%=dto.getSeq()%>"><button type="button"class="w3-btn w3-theme-d1 w3-margin-bottom">삭제</button></a>
        	<%
         	}
         %>
        <%if(dto.getDead() == 1){
        	%>
        	<p class="w3-btn w3-theme-d1 w3-margin-bottom">마감됨!</p>
        	<%
        }
        %>
        <!-- <a href="togetherlike.jsp"> --><img src="../image/like.png" id="likeimage"  onclick="changeimage()" ><!-- </a> -->
        <button type="button" class="w3-btn w3-theme-d2 w3-margin-bottom" onclick="myFunction('Demo4')" ><i class="fa fa-comment"></i>댓글</button>
         <div id="Demo4" class="w3-accordion-content w3-container">
         	<div>
            	<form action="reple.jsp" method="get">
            	<table>
            	<tr>
            		<td><%=mem.getId() %>:</td> 
            		<td><input type="text" placeholder="댓글" name="reple" size="70px;"></td>
            		<td><input type="submit" value="입력"></td>
            	</tr>
            	</table>
            	</form>
            	<%
            	session.setAttribute("seq", dto.getSeq());
				session.setMaxInactiveInterval(10*10);
				%>
         	<table>	<!--for문 돌려주면 된다.  -->
         		<tr>
         			<td>댓글리스트</td>
         		</tr>
         		<tr>
         			<td>댓글리스트</td>
         		</tr>
         	</table>
         	</div>
          </div>
      </div>
      &nbsp;&nbsp;<a href="together.jsp"><button type="button"class="w3-btn w3-theme-d1 w3-margin-bottom">글 목록</button></a>
      
    중간 끝
    </div>
    <!-- 오른쪽 시작 -->
    <div class="w3-col m2" >
      <div class="w3-card-2 w3-round w3-white w3-center">
        <div class="w3-container">
          <p>오늘의 일정</p>
        
          <p><strong>원빈님이랑 밥먹기</strong></p>
          <p>금요일 15:00</p>
          <p><button class="w3-btn w3-btn-block w3-theme-l4">일정보기</button></p>
        </div>
      </div>
      <br>
      
    
      
    <!-- 오른 쪽끝  -->
    </div>
    
  <!-- End Grid -->
  </div>
  
<!-- End Page Container -->
</div>
<br>


 
<script>
// Accordion
function myFunction(id) {
    var x = document.getElementById(id);
    if (x.className.indexOf("w3-show") == -1) {
        x.className += " w3-show";
        x.previousElementSibling.className += " w3-theme-d1";
    } else {
        x.className = x.className.replace("w3-show", "");
        x.previousElementSibling.className =
        x.previousElementSibling.className.replace(" w3-theme-d1", "");
    }
}

// Used to toggle the menu on smaller screens when clicking on the menu button
function openNav() {
    var x = document.getElementById("navDemo");
    if (x.className.indexOf("w3-show") == -1) {
        x.className += " w3-show";
    } else {
        x.className = x.className.replace(" w3-show", "");
    }
}
</script>

</body>
</html>