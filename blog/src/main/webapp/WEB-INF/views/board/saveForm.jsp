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
		<form>
			<div class="form-group">
				<label for="Title">Title</label> 
				<input type="text" class="form-control" placeholder="Enter Title" id="Title">
			</div>

			<div class="form-group">

				<label for="content">Content</label>

				<!-- 써머 노트 클래스 추가 -->
				<textarea id="summernote"></textarea>

			</div>
		</form>
		<button id="btn-save" class="btn btn-primary">글쓰기</button>
	</div>

	<!-- 써머 노트 JS -->
	<script>
		$('#summernote').summernote({
			tabsize : 2,
			height : 300
		});
	</script>
	
	<script src="/js/board.js"></script>

	<%@ include file="../layout/footer.jsp"%>

</body>
</html>


