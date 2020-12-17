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
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<meta charset="UTF-8">

<title>Insert title here</title>
</head>
<body>
	<u:navbar />
<div class="container">
<div class="jumbotron">
  <h1 class="display-4">첫 번째 프로젝트!</h1>
  <p class="lead">첫번째 프로젝트입니다. 사용하시려면 회원가입을 해보세요~!</p>
  <hr class="my-4">
  <p>회원가입으로 바로가시려면 클릭하세요</p>
  <a class="btn btn-primary btn-lg" href="${root }/join.do" role="button">회원가입</a>
</div>
<%-- 
<c:if test="${! empty authUser }">
	${authUser.name }님, 안녕하세요
	<a href="logout.do">[로그아웃 하기]</a>
	<a href="changePwd.do">[암호 변경하기]</a>
</c:if>
<c:if test="${empty authUser }">
	<a href="join.do">[회원가입하기]</a>
	<a href="login.do">[로그인하기]</a>
</c:if>
--%>
	<%-- <u:isLogin>
		${authUser.name }님, 안녕하세요
		<a href="logout.do">[로그아웃 하기]</a>
		<a href="changePwd.do">[암호 변경하기]</a>
		<a href="removeMember.do">[회원 탈퇴]</a>
		<a href="article/write.do">[게시글 쓰기]</a>
		<a href="article/list.do">[게시물 보기]</a>
	</u:isLogin>
	<u:notLogin>
		<a href="join.do">[회원가입하기]</a>
		<a href="login.do">[로그인하기]</a>
	</u:notLogin> --%>
</div>

<br />
</body>
</html>