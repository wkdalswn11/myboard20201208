<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="articleNo" type="java.lang.Integer" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty sessionScope.authUser }">
	<div>
		<form action="${root }/reply/add.do" method="post">
			<input type="number" value="${articleNo }" name="no" hidden>
			<textarea name="body" id="" cols="30" rows="5"></textarea>
			<input type ="submit" value="댓글등록">
		</form>
	</div>
</c:if>

