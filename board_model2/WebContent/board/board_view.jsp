<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>상세 페이지</title>
</head>
<body>
<table border=1 align=center width=500>
	<tr><td align=center width=50>이름</td>
		<td width=50>${board.board_name }</td>
		<td align=center width=50>조회수</td>
		<td>${board.board_readcount }</td>
	</tr>
	<tr><td align=center>제목</td>
		<td>${board.board_subject }</td>
		<td align=center>시간</td>
		<td>
		<fmt:formatDate value="${board.board_date }" type="both" timeStyle="long"/>
		</td>
	</tr>
	<tr>
		<td align=center>내용</td>
		<td colspan=3 height=100 >${board.board_content }</td>
	</tr>
	<tr>
		<td align=center>파일</td>
		<td colspan=3><a href="./board/file_down.jsp?file_name=${board.board_file }">${board.board_file }</a></td>
	</tr>
</table>
</body>
</html>