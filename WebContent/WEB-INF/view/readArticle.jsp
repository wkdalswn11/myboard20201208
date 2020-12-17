<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
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

<u:navbar/>
	<div class="container">
		<table class="table table-striped table-dark">
  <thead>
    <tr>
      <th scope="col">번호</th>
      <td class="justify-content-center">${articleData.article.number }</td>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">작성자</th>
      <td>${articleData.article.writer.name }</td>
      
    </tr>
    <tr>
      <th scope="row">제목</th>
      <td><c:out value=" ${articleData.article.title }"/></td>
      
    </tr>
    <tr>
      <th scope="row">내용</th>
      <td><u:pre value ="${articleData.content }"/></td>
    </tr>
    <tr>
    	<th colspan="2">
    		<c:set var="pageNo" value="${empty param.pageNo ? '1' : param.pageNo }"/>
				<a href="list.do?pageNo=${pageNo }">[목록]</a>
				<c:if test="${authUser.id == articleData.article.writer.id }">
					<a href="modify.do?no=${articleData.article.number }">[게시글 수정]</a>
					<a href="remove.do?no=${articleData.article.number }">[게시글 삭제]</a>
					
				</c:if>	
    	</th>
    </tr>
  </tbody>
</table>
	
	</div>




<div class="container">
	<%-- <table border="1" width="100%">
			
		<tr>
			<td>번호</td>
			<td>${articleData.article.number }</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${articleData.article.writer.name }</td>
		</tr>
		<tr>
			<td>제목</td>
			<td><c:out value=" ${articleData.article.title }"/></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><u:pre value ="${articleData.content }"/></td>
		</tr>
		<tr>
			<td colspan="2">
				<c:set var="pageNo" value="${empty param.pageNo ? '1' : param.pageNo }"/>
				<a href="list.do?pageNo=${pageNo }">[목록]</a>
				<c:if test="${authUser.id == articleData.article.writer.id }">
					<a href="modify.do?no=${articleData.article.number }">[게시글 수정]</a>
					<a href="remove.do?no=${articleData.article.number }">[게시글 삭제]</a>
					
				</c:if>			
			</td>
		</tr>
	</table> --%>
	
	<%--
	 로그인 한 경우만
	 댓글 폼 출력
	 --%>
	 <u:replyForm articleNo="${articleData.article.number }"/>
	 
	 <u:listReply />
</div>
</body>
</html>