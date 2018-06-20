<%@ page language="java" contentType="text/html; charset=utf-8"%>

<html>
<head>
	<title>정보 수정</title>
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="../js/script.js"></script>
	
</head>
<body>

<form action="<%=request.getContextPath() %>/BoardModifyView.do" method="post" 
<%-- <form action="/model2/BoardAddAction.do" method="post"  --%>
	enctype="multipart/form-data">
	<input type="hidden" name=page value=${page }>
	<input type="hidden" name=num value=${board.board_num }>
	
<table cellpadding="0" cellspacing="0" align=center border=1>
	<tr align="center" valign="middle">
		<td colspan="5">수정 페이지</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12" height="16">
			<div align="center">글쓴이</div>
		</td>
		<td>
			<input name="board_name" id="board_name" type="text" size="10" maxlength="10" 
				value="${board.board_name }"/>
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12" height="16">
			<div align="center">비밀번호</div>
		</td>
		<td>
			<input name="board_pass" id="board_pass" type="password" size="10" maxlength="10" 
				value=""/>
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12" height="16">
			<div align="center">제 목</div>
		</td>
		<td>
			<input name="board_subject" id="board_subject" type="text" size="50" maxlength="100" 
				value="${board.board_subject }"/>
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12">
			<div align="center">내 용</div>
		</td>
		<td>
			<textarea name="board_content" id="board_content" cols="67" rows="15">${board.board_content }</textarea>
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12">
			<div align="center">파일 첨부</div>
		</td>
		<td>
			<input name="board_file" type="file" />
		</td>
	</tr>
	<tr bgcolor="cccccc">
		<td colspan="2" style="height:1px;">
		</td>
	</tr>
	<tr><td colspan="2">&nbsp;</td></tr>
	<tr align="center" valign="middle">
		<td colspan="5">			
			<input type=submit value="완료">
			<input type=reset value="취소">
		</td>
	</tr>
</table>
</form>

</body>
</html>