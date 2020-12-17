<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<head>
<meta charset="UTF-8">
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<title>Insert title here</title>
</head>
<body>
	<u:navbar />
	<!-- .container>.row>.col-3+.col-6>h1+form -->
		<div class="container">
			<div class="row">
				<div class="col-3"></div>
				<div class="col-6">
					<h1>회원 탈퇴</h1>
					<form action="${root }/removeMember.do" method="post">
					<!-- div.form-group*2>label+input.form-control -->
						<div class="form-group">
							<label for="input1-password">암호:</label>
							<input type="password" class="form-control" name="password" id="input1-password"/>
								<c:if test="${errors.password }">
									<small class="form-text text-danger">비밀번호를 입력하세요</small>
								</c:if>
								<c:if test="${errors.notMatchPassword }">
									<small class="form-text text-danger">암호가 일치하지 않습니다.</small>
								</c:if>
						</div>
						<button  class="btn btn-primary">회원 탈퇴</button>
					</form>
				</div>
			</div>
		</div>




<%-- <div class="container">
	<h1>회원 탈퇴</h1>
	<form action="removeMember.do" method="post">
		암호: <br /> 
		<input type="password" name="password" id="" />
		<c:if test="${errors.password }">암호를 적어주십시오.</c:if>
		<c:if test="${errors.notMatchPassword }">암호가 맞지 않습니다.</c:if>
		<br />
		<input type="submit" value="탈퇴" />
	</form>
</div> --%>
</body>
</html>