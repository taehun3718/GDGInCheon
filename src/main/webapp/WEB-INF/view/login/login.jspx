<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 페이지입니다.</title>
</head>
<body>
	<form 	id="loginForm" 
			name="loginForm"
			method="post"
			action="<c:url value="/doLogin"/>">
		<table>
			<tr>
				<td>로그인</td>
			</tr>
			<tr>
				<th>ID:</th>
				<td>
					<input	type="text"
							name="id"
							maxlength="20"/>
					
				</td>
			</tr>
			
			<tr>
				<th>Password:</th>
				<td>
					<input	type="password"
							name="pwd"
							maxlength="10"/>
				</td>
			</tr>
		</table>
		<input type="submit" value="로그인"/>
	</form>
</body>
</html>