<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>Home | ssbridge 보안사업팀 메인페이지</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/main.css" rel="stylesheet">
<!--[if lt IE 9]>
<script src="js/html5shiv.js"></script>
<script src="js/respond.min.js"></script>
<![endif]-->
<link rel="shortcut icon" href="images/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="images/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="images/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="images/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="images/ico/apple-touch-icon-57-precomposed.png">

<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/v/bs/dt-1.10.12/datatables.min.css" />
<script type="text/javascript"
	src="https://cdn.datatables.net/v/bs/dt-1.10.12/datatables.min.js"></script>

<script type="text/javascript">
$.extend(true, $.fn.dataTable.defaults, {
	"searching" : false,
	"paging" : false,
	"info" : false
});

$(document).ready(function() {
	console.log("rdy");
	var isReadyToRegist = false;
	var searchKeyword = "${engineerSearchVO.searchKeyword}";
	$("#keyword").val(searchKeyword);
	
	$("#searchBtn").click(function() {
		
		
		$("#searchForm").attr("action", "<c:url value="/ssBizEngineer"/>");
		$("#searchForm").attr("method", "POST");
		$("#searchForm").submit();
	});
	
	
	
	$("#myBtn").click(function() {
		$("#registModal").modal();
	});
	$('.sort').DataTable();
	
	$("#eng_id").keyup(function() {
		var eng_idLength = $("#eng_id").val().length;
		if(eng_idLength==0) {
			$("#isDuplicatedID").text("");
			return;
		}
		else {
			$.post(	"<c:url value="/ssBizEngineer/checkDuplicateUserIDAjax"/>", {"eng_id" : $("#eng_id").val()}
			, function(data) {
				if (data == "true") {
					$("#isDuplicatedID").text("이미 사용중인 ID 입니다.");
					isReadyToRegist = false;
					return;
				} 
				else{
					$("#isDuplicatedID").text("사용 가능한 ID 입니다.");
					isReadyToRegist = true;
					return;
				}
			});
		}
	});
	
	$(".btn-success").click(function() {
		var isValidData = validationCheck();
		
		if(isValidData && isReadyToRegist==true) {
			if(confirm("해당 엔지니어를 등록하시겠습니까?")==true){
				//등록 로직 후 DB에 넣기
				$("#engineerForm").attr("action",	"<c:url value="/ssBizEngineer/doRegistEngineer"/>");
				$("#engineerForm").attr("method", "POST");
				$("#engineerForm").submit();
			}
		}
		else if(isValidData && isReadyToRegist==false) {
			alert("중복된 사용자 ID가 있습니다. ID를 다른 ID로 변경해주십시오.");
			return;
		}
		
	});
	
	$(".btn-default").click(function() {
		$("#eng_id").val("");
		$("#eng_pswd").val("");
		$("#eng_nm").val("");
		$("#eng_rank").val("");
		
	});
	
	
	
	function validationCheck(){
		
		if($("#eng_id").val() =='' || $("#eng_id").val() ==null) {
			alert("아이디를 입력해주세요.");
			return false;
		}
		
		if($("#eng_pswd").val() =='' || $("#eng_pswd").val() ==null) {
			alert("패스워드를 입력해주세요.");
			return false;
		}
		
		if($("#eng_nm").val() =='' || $("#eng_nm").val() ==null) {
			alert("사용자 이름을 입력해주세요.");
			return false;
		}

		if($("#eng_rank").val() =='' || $("#eng_rank").val() ==null) {
			alert("직급을 입력해주세요.");
			return false;
		}
		return true;
	}	
});



</script>

</head>
<style>
@media all and (-ms-high-contrast: none), (-ms-high-contrast: active) {
/* IE10+ CSS styles go here */
	.css_1_select{
	width:80px;
	height:29px;
	padding: 0px 0px 0px 0px;
	}
	.css_1_input{
	width:300px;
	height:29px;
	padding: 0px 0px 0px 0px;
	margin: 0px 0px 0px 0px;
	}			
	.css_1_btn{
	width:60px;
	height:29px;  
	margin: 0px 0px 0px 0;
	}
}

@media screen and (-webkit-min-device-pixel-ratio:0) {
  /* webkit specific styles go here */
  	.css_1_select{
	width:80px;
	height:29px;
	padding: 0px 0px 0px 0px;
	}
	
	.css_1_input{
	width:300px;
	height:29px;
	padding: 2px 0px 0px 0px;
	}			
	
	.css_1_btn{
	width:60px;
	height:29px;  
	margin: 0px 0px 2px 0;
	}
}
</style>
<!--/head-->
<body>
	<jsp:include page="./header/engineerHeader.jsp" flush="false" />
<body id="body" class="white">

	<div class="container">
		<form name="searchForm" id="searchForm" method="POST">
			<select id="type" name="type" class="css_1_select selectpicker">
			  <optgroup label="검색하고자 하는 타입을 선택해주세요.">
			    <option value="1">이름</option>
			    <option value="2">아이디</option>
			  </optgroup>
			</select>
		
		
			<input	type="text" 
					id="keyword" 
					name="keyword" 
					placeholder="검색어"
					class="css_1_input input"
					>
			<button type="button" 
					class="css_1_btn btn btn-sm" 
					id="searchBtn"
					>검색</button>
		</form>
					
					
		<div class="table-responsive">
			
			<table class="table table-hover sort">
				<thead>
					<tr>
						<th>순번</th>
						<th>이름</th>
						<th>직급</th>
						<th>아이디</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="engineerList" items="${engineerList.engineerList }">
					<tr>
						<td>${engineerList.eng_seqNo }</td>
						<td>${engineerList.eng_nm }</td>
						<td>${engineerList.eng_rank }</td>
						<td>${engineerList.eng_id }</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div class="container">
		<ul class="pagination">
		${engineerList.paging.getPagingList("pageNo", "@", "이전", "다음", "")}<br/>
		<!--   <li><a href="#">« Prev</a></li>
		  <li><a href="#">1</a></li>
		  <li><a href="#">2</a></li>
		  <li><a href="#">3</a></li>
		  <li><a href="#">4</a></li>
		  <li><a href="#">5</a></li>
		  <li><a href="#">Next »</a></li> -->
		</ul>
	</div>
	<div class="container">
		<!-- Trigger the modal with a button -->
		<div class="row">
			<div class="col-sm-4"></div>
			<div class="col-sm-4"></div>
			<div class="col-sm-4 text-right">
				<button type="button" class="btn btn-primary btn-lg" id="myBtn">엔지니어
					등록</button>
			</div>
		</div>
		
		<!-- Modal -->
		<div class="modal fade" id="registModal" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">엔지니어 추가</h4>
					</div>
					<div class="modal-body">
						<form id="engineerForm" name="engineerForm">
								<div class="control-group">
									<!-- UserID -->
									<label class="control-label" for="username">ID</label>
									<div class="controls">
										<input type="text" id="eng_id" name="eng_id" placeholder="아이디 입력"
											class="input-xlarge"><label class="control-label" id="isDuplicatedID" for="password"></label>
									</div>
								</div>
								<div class="control-group">
									<!-- Password-->
									<label class="control-label" for="password">Password</label>
									<div class="controls">
										<input type="password" id="eng_pswd" name="eng_pswd"
											placeholder="패스워드 입력" class="input-xlarge">
									</div>
								</div>
								<div class="control-group">
									<!-- E-mail -->
									<label class="control-label" for="username">엔지니어 이름</label>
									<div class="controls">
										<input type="text" id="eng_nm" name="eng_nm" placeholder="홍길동"
											class="input-xlarge">
									</div>
								</div>
		
								<div class="control-group">
									<!-- E-mail -->
									<label class="control-label" for="username">엔지니어 직급</label>
									<div class="controls">
										<input type="text" id="eng_rank" name="eng_rank" placeholder="직급"
											class="input-xlarge">
									</div>
								</div>
							</form>
						</div>
					<div class="modal-footer">
						<button class="btn btn-success">등록</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<jsp:include page="./footer.jsp" flush="false" />

</body>
</html>
<!-- 
파라미터를 get방식으로 넘길 때 한글 깨짐 현상이 발생한다..

 

이것을 해결하기 위해서는  javascript에서는..

 

[Encode]

var mng_dept_en_nm = encodeURIComponent(myForm.mng_dept_nm.value);

 

위와같이 encodeURLComponent(변수)을 사용하여 파라미터를 넘긴다

 

[Decode]

var mng_dept_de_nm = decodeURIComponent(myForm.mng_dept_nm.value);

 

위와같이 decodeURLComponent(변수)을 사용한다.

 -->