<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib	prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>Home | ssbridge 보안사업팀 메인페이지 </title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/main.css" rel="stylesheet">
<!--[if lt IE 9]>
<script src="js/html5shiv.js"></script>
<script src="js/respond.min.js"></script>
<![endif]-->       
<link rel="shortcut icon" href="images/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
    
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.prettyPhoto.js"></script>
<script src="js/main.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/v/bs-3.3.6/dt-1.10.12/datatables.min.js"></script>

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs/dt-1.10.12/datatables.min.css"/>
<script type="text/javascript" src="https://cdn.datatables.net/v/bs/dt-1.10.12/datatables.min.js"></script>

<script type="text/javascript">
$.extend( true, $.fn.dataTable.defaults, {
    "searching": false,
    "paging": false,
    "info": false
} );
$(document).ready(function() {
	console.log("Ready to jquery");
    $('.sort').DataTable();
} );
</script>
    
    
</head><!--/head-->
<body>
    <jsp:include page="./header/mainHeader.jsp" flush="false" />
    <body id="body" class="white">
  		<div class="container">
		  <h2>Table</h2>
		  <p>The .table-responsive class creates a responsive table which will scroll horizontally on small devices (under 768px). When viewing on anything larger than 768px wide, there is no difference:</p>
		  <div class="table-responsive">
		  <table class="table sort">
		    <thead>
		      <tr>
		        <th>#</th>
		        <th>Firstname</th>
		        <th>Lastname</th>
		        <th>Age</th>
		        <th>City</th>
		        <th>Country</th>
		      </tr>
		    </thead>
		    <tbody>
		      <tr>
		        <td>1</td>
		        <td>Anna1</td>
		        <td>가</td>
		        <td>35</td>
		        <td>New York</td>
		        <td>USA</td>
		      </tr>
		      <tr>
		        <td>2</td>
		        <td>Anna2</td>
		        <td>나</td>
		        <td>35</td>
		        <td>New York</td>
		        <td>USA</td>
		      </tr>
		      <tr>
		        <td>3</td>
		        <td>Anna3</td>
		        <td>마</td>
		        <td>35</td>
		        <td>New York</td>
		        <td>USA</td>
		      </tr>
		      <tr>
		        <td>4</td>
		        <td>Anna4</td>
		        <td>나</td>
		        <td>35</td>
		        <td>New York</td>
		        <td>USA</td>
		      </tr>
		    </tbody>
		  </table>
		  </div>
		</div>
    </body>
	<jsp:include page="./footer.jsp" flush="false" />

 </body>
</html>