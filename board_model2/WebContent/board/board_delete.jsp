<%@ page language="java" contentType="text/html; charset=utf-8"%>

<html>
<head>
	<title>삭제</title>
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="../js/script.js"></script>
	
</head>
<body>
<form action="<%=request.getContextPath() %>/BoardDelete.do" method="post" >
<%-- <form action="/model2/BoardAddAction.do" method="post"  --%>
	
	<input type="text" name=page value=${param.page }>
	<input type="text" name=num value=${param.num }>
	
<table cellpadding="0" cellspacing="0" align=center border=1>
	<tr align="center" valign="middle">
		<td colspan="5">삭제 페이지</td>
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
	
	<tr bgcolor="cccccc">
		<td colspan="2" style="height:1px;">
		</td>
	</tr>
	<tr><td colspan="2">&nbsp;</td></tr>
	<tr align="center" valign="middle">
		<td colspan="5">			
			<input type=submit value="삭제">
			<input type=reset value="취소">
		</td>
	</tr>
</table>
</form>

</body>
</html>