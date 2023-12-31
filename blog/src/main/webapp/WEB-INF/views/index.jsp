<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
</head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body>

	<%@ include file="layout/header.jsp"%>

	<c:forEach var="board" items="${boardList.content}">

		<div class="container">
			<div class="card m-2">
				<div class="card-body">
					<h4 class="card-title">${board.title}</h4>
					<a href="/board/${board.id}" class="btn btn-primary">상세보기</a>
				</div>
			</div>
		</div>

	</c:forEach>

	<!-- 페이징 처리 -->

	<ul class="pagination justify-content-center">

		<c:choose>
			<c:when test="${boardList.first}">
				<li class="page-item disabled"><a class="page-link" href="?page=${boardList.number-1}">Previous</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${boardList.number-1}">Previous</a></li>
			</c:otherwise>
		</c:choose>

		<!-- <li class="page-item"><a class="page-link" href="#">1</a></li> -->

		<c:choose>
			<c:when test="${boardList.last}">
				<li class="page-item disabled"><a class="page-link" href="?page=${boardList.number+1}">Last</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${boardList.number+1}">Last</a></li>
			</c:otherwise>
		</c:choose>

	</ul>

	<%@ include file="layout/footer.jsp"%>

</body>
</html>


