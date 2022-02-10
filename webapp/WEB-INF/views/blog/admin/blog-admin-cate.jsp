<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 메타태그 -->
<!--  
<meta name="viewport" content="width=device-width, initial-scale=1">
-->

<!-- css -->
<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

<!-- 자바스크립트 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>


<title>JBlog</title>

</head>

<body>
	<div id="wrap">

		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>

		<div id="content">
			<ul id="admin-menu" class="clearfix">
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${sessionScope.authUser.id}/admin/basic">기본설정</a></li>
				<li class="tabbtn selected"><a href="${pageContext.request.contextPath}/${sessionScope.authUser.id}/admin/category">카테고리</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${sessionScope.authUser.id}/admin/writeForm">글작성</a></li>
			</ul>
			<!-- //admin-menu -->

			<div id="admin-content">

				<table id="admin-cate-list">
					<colgroup>
						<col style="width: 50px;">
						<col style="width: 200px;">
						<col style="width: 100px;">
						<col>
						<col style="width: 50px;">
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>카테고리명</th>
							<th>포스트 수</th>
							<th>설명</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody id="cateList">
						<!-- 리스트 영역 -->
						
						<!-- 리스트 영역 -->
					</tbody>
				</table>

				<table id="admin-cate-add">
					<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
					<tr>
						<td class="t">카테고리명</td>
						<td><input id="cateName" type="text" name="name" value=""></td>
					</tr>
					<tr>
						<td class="t">설명</td>
						<td><input id="cateDesc" type="text" name="desc" value=""></td>
					</tr>
				</table>
					
				<div id="btnArea">
					<button id="btnAddCate" class="btn_l" type="submit">카테고리추가</button>
					<input id="cateId" type="hidden" value="${sessionScope.authUser.id}">
				</div>

			</div>
			<!-- //admin-content -->
		</div>
		<!-- //content -->


		<!-- 개인블로그 푸터 -->
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>


	</div>
	<!-- //wrap -->
</body>
<script type="text/javascript">
	//페이지가 뿌려지기 전 리스트 출력
	$(document).ready(function(){
		$.ajax({
			url : "${pageContext.request.contextPath}/${sessionScope.authUser.id}/categoryList", 
			type : "post",
			success : function(categoryList) {
				console.log(categoryList);

				for (var i = 0; i < categoryList.length; i++) {
					render(categoryList[i], 'down'); // 방명록리스트 그리기
				}

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});
	
	function render(categoryVo, updown) {
		var str = "";
		str += '<tr>';
		str += '	<td>'+categoryVo.cateNo+'</td>';
		str += '	<td>'+categoryVo.cateName+'</td>';
		str += '	<td>'+categoryVo.count+'</td>';
		str += '	<td>'+categoryVo.description+'</td>';
		str += '	<td class="text-center"><img class="btnCateDel" src="${pageContext.request.contextPath}/assets/images/delete.jpg"></td>';
		str += '</tr>';

		if (updown == 'down') {
			$("#cateList").append(str);
		} else if (updown == 'up') {
			$("#cateList").prepend(str);
		} else {
			console.log("방향오류");
		}
	};
	
	//카테고리 추가 버튼 클릭시
	$("#btnAddCate").on("click", function(){
		console.log("카테고리 추가 클릭");
		var cateName = $("#cateName").val();
		var description = $("#cateDesc").val();
		var id = $("#cateId").val();
		
		var categoryVo = {
			id : id,
			cateName : cateName,
			description : description
		};
		console.log(categoryVo);
		
		$.ajax({
			url : "${pageContext.request.contextPath}/addCategory",   
			type : "post",
			data : categoryVo,
			
			success : function(categoryVo) {
				render(categoryVo, 'up');
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	})
	
	//삭제버튼 클릭시
</script>
</html>