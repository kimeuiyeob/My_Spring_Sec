<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
</head>
<body>

	<%@ include file="../layout/header.jsp"%>

	<div class="container">
		<form>
			<div class="form-group">
				<label for="username">Username:</label> <input type="text" class="form-control" placeholder="Enter Username" id="username">
			</div>

			<div class="form-group">
				<label for="password">Password:</label> <input type="password" class="form-control" placeholder="Enter password" id="password">
			</div>
			
			<div class="form-group">
				<label for="email:">Email:</label> <input type="email:" class="form-control" placeholder="Enter email" id="email">
			</div>

		</form>
		
		<button  id="btn-save" class="btn btn-primary">회원 가입</button>
		
	</div>
	
	<script src="/js/user.js"></script>

	<%@ include file="../layout/footer.jsp"%>

</body>
</html>