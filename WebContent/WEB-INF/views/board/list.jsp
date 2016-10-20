<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value=""> <input
						type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>

					<c:forEach items="${list }" var="vo" varStatus="status">
						<tr>
							<td>${totalCount - (currentPage - 1)*listSize - status.index }</td>
							<c:choose>
								<c:when test="${vo.depth > 0 }">
									<td class="left" style="padding-left:${20*vo.depth }px"><img
										src="${pageContext.request.contextPath }/assets/images/reply.png">
										<a href="/mysite3/board?a=viewform&no=${vo.no }">${vo.title }</a></td>
								</c:when>
								<c:otherwise>
									<td class="left"><a
										href="/mysite3/board?a=viewform&no=${vo.no }">${vo.title }</a></td>
								</c:otherwise>
							</c:choose>
							<td>${vo.userName }</td>
							<td>${vo.hit }</td>
							<td>${vo.reg_date }</td>
							<td><a href="/mysite3/board?a=deleteform&no=${vo.no }"
								class="del">삭제</a></td>
						</tr>
					</c:forEach>
				</table>

				<div class="pager">
					<ul>
						<c:choose>
							<c:when test="${startPage > 1  }"><li><a href="${pageContext.request.contextPath }/board?p=${startPage-1 }">◀</a></li>
							</c:when>
						</c:choose>
						
						<c:forEach var="i" begin="${startPage }" end="${endPage }">
							<c:choose>
								<c:when test="${i <= totalPage }">
									<li><a href="${pageContext.request.contextPath }/board?p=${i }">${i }</a></li>
								</c:when>
								<c:otherwise>
									<li>${i }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:choose>
							<c:when test="${totalPage > endPage }"><li><a href="${pageContext.request.contextPath }/board?p=${endPage +1}">▶</a></li>
							</c:when>
						</c:choose>
					</ul>
				</div>


				<div class="bottom">
					<c:if test="${not empty authUser }">
						<a href="${pageContext.request.contextPath }/board?a=writeform"
							id="new-book">글쓰기</a>
					</c:if>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
	</div>
</body>
</html>