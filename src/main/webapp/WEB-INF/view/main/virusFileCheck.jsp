<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Server beta 1.0</title>
<link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
</head>
<h1>백신 업데이트 정보</h1>
<body>
	<table class="table table-bordered table-hover table-striped" border='1'>
		<tr>
			<td>No</td>
			<td>다운로드 정보</td>
			<td>업데이트 정보</td>
		</tr>
		
		<tr>
			<td>1</td>
			<td>
				<a href="<c:url value="/download/eziscan"/>">이지스캔 백신 업데이트 다운로드</a>
			</td>
			<td>	
				바이러스 패턴 수   : ${vo.numOfDetectableVirus } / 
				바이러스 엔진 버전 : ${vo.dateOfVirusData}
			</td>
		</tr>
		
	</table>
</body>
</html>