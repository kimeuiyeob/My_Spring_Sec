<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
</head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<body>

	<%@ include file="../layout/header.jsp"%>

	<div class="container">

		<div>
			글 번호 : <span id="boardId">${board.id}</span> &nbsp&nbsp 작성자 : <span>${board.user.username}</span>
		</div>
		<br></br>

		<div>
			<h3>${board.title}</h3>
		</div>

		<hr>

		<div>
			<div>${board.content}</div>
		</div>

		<hr>

		<button class="btn btn-secondary" onclick="history.back()">목록</button>

		<c:if test="${board.user.id eq principal.user.id}">
			<a href="/board/${board.id}/updateForm"><button class="btn btn-warning">수정</button></a>
			<button id="btn-delete" class="btn btn-danger">삭제</button>
		</c:if>

		<hr>

		<!-- ======= 댓글 ======= -->

		<div class="card">
			<input type="hidden"  id="userId" value="${principal.user.id}">
			<div class="card-body">
				<textarea id="reply-content" class="form-control" rows="1"></textarea>
			</div>
			<div class="card-footer">
				<button id="btn-reply-save" class="btn btn-primary">등록</button>
			</div>
		</div>

		<br>
		<div class="card">
			<div class="card-header">댓글리스트</div>
			<ul id="reply--box" class="list-group">

				<c:forEach var="reply" items="${board.reply}">
					<li id="reply--1" class="list-group-item d-flex justify-content-between">
						<div>${reply.content}</div>
						<div class="d-flex">
							<div class="font-italic">작성자 : ${reply.user.username} &nbsp;</div>
							<button class="badge">삭제</button>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>

	</div>

	<script src="/js/board.js"></script>

	<%@ include file="../layout/footer.jsp"%>

</body>
</html>


