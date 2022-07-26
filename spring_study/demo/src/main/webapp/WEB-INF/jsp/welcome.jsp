<%@ page language="java" contentType="text/html; charset=euc-kr" pageEncoding="UTF-8" %>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
		<title>welcome</title>
	</head>

	<body>
		<p>
			안녕
			<a href="test01.html" target="_blank">Test01</a>
			<a href="test02.html" target="_blank">Test02</a>
			<a href="test03.html" target="_blank">Test03</a>
			<a href="test04.html" target="_blank">Test04</a>
			<h1>
				DB SELECT EXAMPLE
			</h1>
			<table border="1">
				<tr>
					<th>사원번호</th>
					<th>아이디</th>
					<th>이름</th>
					<th>파트번호</th>
					<th>입사일</th>
					<th>퇴사일</th>
				</tr>
				<c:forEach items="${list}" var = "m">
					<tr>
						<td>
							${m.MEMBER_CD}
						</td>
						<td>
							${m.USER_ID}
						</td>
						<td>
							${m.USER_NAME} 님
						</td>
						<td>
							${m.PART}
						</td>
						<td>
							${m.ENTRY_DATE}
						</td>
						<td>
							${m.LEAVE_DATE}
						</td>
					</tr>
				</c:forEach>
			</table>
		</p>
	</body>

	</html>