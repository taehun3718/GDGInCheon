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
<title>재고 관리 등록</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/main.css" rel="stylesheet">
<!--[if lt IE 9]>
<script src="js/html5shiv.js"></script>
<script src="js/respond.min.js"></script>
<![endif]-->
<link rel="shortcut icon" href="images/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"   href="images/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"                 href="images/ico/apple-touch-icon-57-precomposed.png">

<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css"	href="https://cdn.datatables.net/v/bs/dt-1.10.12/datatables.min.css" />
<script type="text/javascript" 	src="https://cdn.datatables.net/v/bs/dt-1.10.12/datatables.min.js"></script>

<script type="text/javascript">
$.extend(true, $.fn.dataTable.defaults, {
	"searching" : false,
	"paging" : false,
	"info" : false,
	"order": [[ 0, 'desc'], [ 1, 'desc' ]]
});

$(document).ready(function() {
	console.log("rdy");
	/*숫자만 입력받는 스크립트*/
	$(".inventNumCls").keyup(function(){$(this).val( $(this).val().replace(/[^0-9]/g,"") );} );
	
	var isReadyToRegist = false;
	var searchKeyword = "${inventorySearchVO.searchKeyword}";
	$("#keyword").val(searchKeyword);
	
	$("#searchBtn").click(function() {
		$("#searchForm").attr("action", "<c:url value="/ssBizInventoryList"/>");
		$("#searchForm").attr("method", "POST");
		$("#searchForm").submit();
	});
	
	
	$("#btnRegist").click(function() {
		$("#registModal").modal();
	});

	$("#btnDelete").click(function() {
		
		var isChekedData = validationCheckedData();
		console.log("isChekedData=" + isChekedData);
		if(isChekedData) {
			if(confirm("선택된 재고정보를 삭제하시겠습니까?")==true){
				$("input[name=chk_info]:checked").each(function() {
					var checkboxValue = $(this).val();
					console.log("checkboxValue=" + checkboxValue);
					
						$.post("<c:url value="/ssBizInventoryList/doDeleteInventory"/>"
								, { "inventSeqno" : checkboxValue} , function(data){		
						});
				});
				window.location.reload(true);
			}
		}
		
	});
	/*http://stackoverflow.com/questions/3545341/jquery-get-the-id-value-of-li-after-click-function  */
	$(".inventoryList td").on("click", function() {
		var str=$(this).attr("class");
		isSeq = str.indexOf("inventSeqno");
		//console.log("isSeq:" + isSeq);
		if(isSeq>-1){
			return;
		}
		//console.log("iElement:" + $(this).html());
		//console.log("iElement:" + $(this).parent().html());
		
		var inventSeqno		= $(this).parent().find(".inventSeqno").text();
		var inventName		= $(this).parent().find(".inventName").text();
		var inventCategory	= $(this).parent().find(".inventCategory").text();
		var inventNum		= $(this).parent().find(".inventNum").text();
		var inventMemo		= $(this).parent().find(".inventMemo").text();
		
		//console.log("inventSeqno:" + inventSeqno);
		//console.log("inventName:" + inventName);
		//console.log("inventCategory:" + inventCategory);
		//console.log("inventNum:" + inventNum);
		//console.log("inventMemo:" + inventMemo);
		$("#inventoryUpdateForm #inventSeqno").val(inventSeqno);
		$("#inventoryUpdateForm #inventName").val(inventName);
		$("#inventoryUpdateForm #inventCategory").val(inventCategory);
		$("#inventoryUpdateForm #inventNum").val(inventNum);
		$("#inventoryUpdateForm #inventMemo").val(inventMemo);
		
		console.log("t:" + $("#inventoryUpdateForm #inventName").val());
		//$(".updateModal .inventName").val("AAA");
		
		$("#updateModal").modal();
	});
	
	
	$('.sort').DataTable();
	
	$(".btn-add").click(function() {
		var isValidData = validationCheckAdd();
		
		if(isValidData) {
			if(confirm("해당하는 재고정보를 등록하시겠습니까?")==true){
				//등록 로직 후 DB에 넣기
				$("#inventoryForm").attr("action",	"<c:url value="/ssBizInventoryList/doRegistInventory"/>");
				$("#inventoryForm").attr("method", "POST");
				$("#inventoryForm").submit();
				
				
			}
		}
		
	});
	
	$(".btn-modify").click(function() {
		var isValidData = validationCheckUpdate();
		
		if(isValidData) {
			if(confirm("해당하는 재고정보를 수정하시겠습니까?")==true){
 				$("#inventoryUpdateForm").attr("action", "<c:url value="/ssBizInventoryList/doUpdateInventory"/>");
				$("#inventoryUpdateForm").attr("method", "POST");
				$("#inventoryUpdateForm").submit();
				
			}
		}
		
	});
	
	function validationCheckAdd(){
		
		if($("#inventName").val() =='' || $("#inventName").val() ==null) {
			alert("재고 이름을 입력해주세요.");
			return false;
		}
		
		if($("#inventNum").val() =='' || $("#inventNum").val() ==null) {
			alert("재고 수량은 반드시 입력해주십시오.");
			return false;
		}
		
		return true;
	}	
	
	function validationCheckUpdate(){
		
		if($("#inventoryUpdateForm #inventName").val() =='' || $("#inventoryUpdateForm #inventName").val() ==null) {
			alert("재고 이름을 입력해주세요.");
			return false;
		}
		
		if($("#inventoryUpdateForm #inventNum").val() =='' || $("#inventoryUpdateForm #inventNum").val() ==null) {
			alert("재고 수량은 반드시 입력해주십시오.");
			return false;
		}
		
		return true;
	}	
	
	function validationCheckedData() {
		var checkboxValue="";
		$("input[name=chk_info]:checked").each(function() {
			checkboxValue = $(this).val();
		});
		
		if(checkboxValue=="")
			return false;
		else
			return true;
	}
	
});

</script>

</head>
<style>
@media all and (-ms-high-contrast: none), (-ms-high-contrast: active) {
/* IE10+ CSS styles go here */
	.css_1_select{
	width:200px;
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
	width:200px;
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
	<jsp:include page="./header/inventoryHeader.jsp" flush="false" />
<body id="body" class="white">

	<div class="container">
		<form name="searchForm" id="searchForm" method="POST">
			<select id="type" name="type" class="css_1_select selectpicker">
			  <optgroup label="검색하고자 하는 타입을 선택해주세요.">
			    <option value="1">재고 이름</option>
			    <option value="2">재고 카테고리</option>
			    <option value="3">재고 메모</option>
			  </optgroup>
			</select>
		
		
			<input	type="text" 
					id="keyword" 
					name="keyword" 
					placeholder="검색어"
					class="css_1_input input">
			<button type="button" 
					class="css_1_btn btn btn-sm" 
					id="searchBtn">검색
			</button>
		</form>
					
					
		<div class="table-responsive">
			
			<table class="table table-hover sort">
				<thead>
					<tr>
						<th>순번</th>
						<th>재고 이름</th>
						<th>재고 카테고리</th>
						<th>재고 수량</th>
						<th>재고 메모</th>
						<th>최근 업데이트 일자</th>
					</tr>
				</thead>
				<tbody class="inventoryList">
					<c:forEach var="inventoryList" items="${inventoryList.inventoryList }">
					<tr>
						<td class="inventSeqno"><input type="checkbox" name="chk_info" value="${inventoryList.inventSeqno }">${inventoryList.inventSeqno }</td>
						<td class="inventName">${inventoryList.inventName}</td>
						<td class="inventCategory">${inventoryList.inventCategory }</td>
						<td class="inventNum">${inventoryList.inventNum }</td>
						<td class="inventMemo" >${inventoryList.inventMemo }</td>
						<td class="inventUpdt">${inventoryList.inventUpdt }</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div class="container">
		<ul class="pagination">
		${inventoryList.paging.getPagingList("pageNo", "@", "이전", "다음", "")}<br/>
	</div>
	<div class="container">
		<!-- Trigger the modal with a button -->
		<div class="row">
			<div class="col-sm-4"></div>
			<div class="col-sm-4"></div>
			<div class="col-sm-4 text-right">
				<button type="button" class="btn btn-primary btn-lg" id="btnRegist">재고 등록</button>
				<button type="button" class="btn btn-primary btn-lg" id="btnDelete">재고 삭제</button>
			</div>
		</div>
		
		<!-- Modal -->
		<div class="modal fade" id="registModal" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">재고 추가</h4>
					</div>
					<div class="modal-body">
						<form id="inventoryForm" name="inventoryForm">
								<div class="control-group">
									<!-- 재고 이름 -->
									<label class="control-label" for="inventName">재고 이름</label>
									<div class="controls">
									<input	type="text" 
											id="inventName" 
											name="inventName" 
											placeholder="재고 이름 입력"
											class="input-xlarge">
									</div>
								</div>
								<div class="control-group">
									<!-- Password-->
									<label class="control-label" for="inventCategory">재고 카테고리</label>
									<div class="controls">
										<select id="inventCategory" name="inventCategory" class="css_1_select selectpicker">
										  <optgroup label="재고 카테고리 선택">
											  <c:forEach var="inventList" items="${inventoryCatList }">
											  		<option value="${inventList.inventCatSeqno }">${inventList.inventCatName }</option>
											  </c:forEach>
										  </optgroup>
										</select>
									</div>
								</div>
								<div class="control-group">
									<!-- E-mail -->
									<label class="control-label" for="inventNum">재고 수량</label>
									<div class="controls">
										<input 	type="text" 
												id="inventNum" 
												name="inventNum" 
												placeholder="재고 수  ex)1"
												class="inventNumCls input-xlarge"
												onkeydown="showKeyCode(event)">
									</div>
								</div>
		
								<div class="control-group">
									<!-- E-mail -->
									<label class="control-label" for="inventMemo">메모</label>
									<div class="controls">
										<input 	type="text" 
												id="inventMemo" 
												name="inventMemo" 
												placeholder="서버 BMT 납품용"
												class="input-xlarge">
									</div>
								</div>
							</form>
						</div>
					<div class="modal-footer">
						<button class="btn btn-add">등록</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					</div>
				</div>
			</div>
		</div>
		
		<!-- Modal -->
		<div class="modal fade" id="updateModal" role="dialog" class="updateModal">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">재고 수정</h4>
					</div>
					<div class="modal-body">
						<form id="inventoryUpdateForm" name="inventoryUpdateForm">
								<div class="control-group">
									<!-- 재고 이름 -->
									<label class="control-label" for="inventName">재고 이름</label>
									<div class="controls">
									<input	type="hidden" 
											id="inventSeqno" 
											name="inventSeqno" >
									<input	type="text" 
											id="inventName" 
											name="inventName" 
											placeholder="재고 이름 입력"
											class="input-xlarge">
									</div>
								</div>
								<div class="control-group">
									<!-- Password-->
									<label class="control-label" for="inventCategory">재고 카테고리</label>
									<div class="controls">
										<input 	type="text" id="inventCategory" name="inventCategory" readonly="readonly"/>
									</div>
								</div>
								<div class="control-group">
									<!-- E-mail -->
									<label class="control-label" for="inventNum">재고 수량</label>
									<div class="controls">
										<input 	type="text" 
												id="inventNum" 
												name="inventNum" 
												placeholder="재고 수  ex)1"
												class="inventNumCls input-xlarge"
												onkeydown="showKeyCode(event)">
									</div>
								</div>
		
								<div class="control-group">
									<!-- E-mail -->
									<label class="control-label" for="inventMemo">메모</label>
									<div class="controls">
										<input 	type="text" 
												id="inventMemo" 
												name="inventMemo" 
												placeholder="서버 BMT 납품용"
												class="input-xlarge">
									</div>
								</div>
							</form>
						</div>
					<div class="modal-footer">
						<button class="btn btn-modify">수정</button>
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