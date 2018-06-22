<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset=UTF-8">
<title>게시판 목록</title>
</head>
<body>

<a href="./BoardWrite.do">글쓰기</a>

<table border=1 align=center width=600>
	<caption>게시판 목록</caption>
	
<c:if test="${count > 0}">
	<tr><td colspan=5 align=right>글 갯수:${count}</td></tr>
	<tr><td>번호</td>
		<td>제목</td>
		<td>작성자</td>
		<td>날짜</td>
		<td>조회수</td>
	</tr>
	
	<!-- 화면 출력 번호 -->
	<c:set var="num" value="${ count-(page-1)*10}"></c:set>
	
	<c:forEach var="b" items="${boardList}">
	<tr>
		<td>
			<c:out value="${num}"/>
			<c:set var="num" value="${num-1}"/>
		</td>
		<td>
			<c:if test="${b.board_re_lev > 0 }">
				<c:forEach var="i" begin="0" end="${b.board_re_lev }">
				&nbsp;
				</c:forEach>
			</c:if>
			
			<a href="./BoardDetailAction.do?num=${b.board_num}&page=${page}">
				${b.board_subject}
				
			</a>
		
		</td><!-- 내부적으로 getSubject를 불러와 출력 -->
		<td>${b.board_name}</td>
		<td>
			<fmt:formatDate value="${b.board_date}" type="both" timeStyle="long"/>
		</td>
		<td>${b.board_readcount}</td>
	</tr>
	</c:forEach>
	
	<!-- 페이지 처리 -->
	<tr>
		<td colspan=5 align=center>
			<c:if test="${page<=1 }">
				< &nbsp;			
			</c:if>
			<c:if test="${page>1 }">
				<a href="./BoardListAction.do?page=${page-1}">
					< &nbsp;			
				</a>
			</c:if>
			
			
			<c:forEach var="a" begin="${startPage}" end="${endPage }">
				<c:if test="${a==page }">
					${a}
				</c:if>
				<c:if test="${a!=page }">
					<a href="./BoardListAction.do?page=${a}">
						[${a}]
					</a>
				</c:if>
			
			</c:forEach>
			
			<c:if test="${page>=maxPage }">
				&nbsp; > 			
			</c:if>
			<c:if test="${page<maxPage }">
				<a href="./BoardListAction.do?page=${page+1}">
				&nbsp;	> 			
				</a>
			</c:if>
			
			
		</td>
	</tr>
	
</c:if>

<c:if test="${count == 0}">
	<tr><td>등록된 글이 없습니다.</td></tr>

</c:if>		



</table>
</body>
</html>