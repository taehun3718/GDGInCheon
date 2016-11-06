<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<header class="navbar navbar-inverse navbar-fixed-top white" role="banner">
	 <div class="container">
	     <div class="navbar-header">
	         <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
	             <span class="sr-only">Toggle navigation</span>
	             <span class="icon-bar"></span>
	             <span class="icon-bar"></span>
	             <span class="icon-bar"></span>
	         </button>
	         <a class="navbar-brand" href="index.html"><img src="images/logo.png" alt="logo"></a>
	         
	          <table>
		         	<c:choose>
						<c:when test="${not empty MemberVO.id }">
							<tr>
								<td>
									<span><br/>${MemberVO.memberInfo }님 환영합니다.</span>
								</td>
							</tr>
						</c:when>
			 		</c:choose>
			 </table>
	     </div>
	     <div class="collapse navbar-collapse">
	         <ul class="nav navbar-nav navbar-right">
	             <li><a href="./ssbizMain">대시보드</a></li>
	             <li class="active"><a href="ssBizInventoryList">재고 관리</a></li>
	             <!--  <li class="active"><a href="#">엔지니어 관리</a></li>
	             <li><a href="#">Contact</a></li>-->
	             <!--   계정에 이미 로그인이 되 있으면 사용자 이름 및 환영 메세지  
	             		계정에 로그인이 되 있지 않으면 로그인 페이지로 이동-->
	             <c:choose>
					<c:when test="${empty MemberVO.id }">
				 		<li><a href="./login">로그인</a></li>
					</c:when>
					<c:when test="${not empty MemberVO.id }">
				 		<li><a href="./doLogout">로그아웃</a></li>
					</c:when>
				 </c:choose>		
	         </ul>
	     </div>
	 </div>
</header><!--/header-->