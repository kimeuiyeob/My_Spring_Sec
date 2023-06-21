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
		<form action="/auth/loginProc" method="post">

			<div class="form-group">
				<label for="username">Username:</label> <input type="text" class="form-control" placeholder="Enter Username" name="username" id="username">
			</div>

			<div class="form-group">
				<label for="password">Password:</label> <input type="password" class="form-control" placeholder="Enter password" name="password" id="password">
			</div>

			<button id="btn-login" class="btn btn-primary">로그인</button>

		</form>

	</div>

	<%-- <script src="/js/user.js"></script> --%>
	<%@ include file="../layout/footer.jsp"%>

</body>
</html>