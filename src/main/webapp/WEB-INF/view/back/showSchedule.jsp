<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html data-ng-app>
<head>

<script type="text/javascript"	src="js/angular.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>

<h1>유지보수 사이트 리스트</h1>
<body data-ng-init="names=['aaa','bbb','ccc']">

	<table border="1">
		<tr>
			<td>No</td>
			<td>유지보수 사이트</td>
			<td>유지보수 날짜</td>
			<td>고객 이름</td>
			<td>유지보수 여부</td>
			<td>비고</td>
		</tr>
		<tr>
			<td>1</td>
			<td>국민연금공단</td>
			<td>2016-01-00</td>
			<td>손산 주임</td>
			<td>유지보수 여부</td>
			<td>비고</td>
		</tr>
		<tr>
			<td>2</td>
			<td>금융결제원</td>
			<td>2016-01-00</td>
			<td>정상준 계장</td>
			<td>유지보수 여부</td>
			<td>비고</td>
		</tr>
		<tr>
			<td>2</td>
			<td>국방메가(계룡)</td>
			<td>2016-01-00</td>
			<td>김일국 주무관</td>
			<td>유지보수 여부</td>
			<td>비고</td>
		</tr>
		
	</table>
	
	<input type="text" id="maintenanceSite"/>
	<input type="text" id="maintenanceDate"/>
	<input type="text" id="customerName"/>
	
	<input type="button" id="btnDelete" value="저장">
	<input type="button" id="btnDelete" value="수정">	
	<input type="button" id="btnDelete" value="삭제">
	
	<table>
		<tr><td></td></tr>
		<tr><td></td></tr>
	</table>
	
	
	
	<div class="container">
	
		테스트: <input type="text" data-ng-model="name"/> {{ name }}
		<select>
			<option value="1">유지보수 사이트</option>
			<option value="2">유지보수 날짜</option>
			<option value="2">고객 이름</option>
		</select>
	</div>
	
	
	<ul>
		<li data-ng-repeat="personName in names | filter:name">{{ personName }}</li> 
	</ul>
	
</body>



</html>