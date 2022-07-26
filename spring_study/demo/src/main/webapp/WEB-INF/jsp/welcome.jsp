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
				<thead>
					<tr>
						<th>사원번호</th>
						<th>아이디</th>
						<th>이름</th>
						<th>파트번호</th>
						<th>입사일</th>
						<th>퇴사일</th>
						<th>DEPT_SN</th>
						<th>ROLE_SN</th>
					</tr>
				</thead>
				<tbody id="tbody">
					<!-- <tr>
						<td>2020201010</td>
						<td>geon</td>
						<td>이건</td>
						<td>이건</td>
						<td>이건</td>
						<td>이건</td>
					</tr> -->
				</tbody>
			</table>
		</p>
	<script>
		window.onload = () => {
			console.log('onload')
			postData('/getData.json').then(response => {
				console.log(response);
				const tbody = document.getElementById('tbody');
				let html = '';
				for(const d of response){
					html += '<tr>';
					html += '<td>'+d.MEMBER_CD+'</td>';
					html += '<td>'+d.USER_ID+'</td>';
					html += '<td>'+d.USER_NAME+'</td>';
					html += '<td>'+d.PART+'</td>';
					html += '<td>'+d.ENTRY_DATE+'</td>';
					html += '<td>'+d.LEAVE_DATE+'</td>';
					html += '<td>'+d.DEPT_SN+'</td>';
					html += '<td>'+d.ROLE_SN+'</td>';
					console.log(tbody);

				}
				tbody.innerHTML = html;
			});
		}


		async function postData(url = '', data = {}) {
			// 옵션 기본 값은 *로 강조
			const response = await fetch(url, {
				method: 'POST', // *GET, POST, PUT, DELETE 등
				mode: 'cors', // no-cors, *cors, same-origin
				cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
				credentials: 'same-origin', // include, *same-origin, omit
				headers: {
					'Content-Type': 'application/json',
				// 'Content-Type': 'application/x-www-form-urlencoded',
				},
				redirect: 'follow', // manual, *follow, error
				referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
				body: JSON.stringify(data), // body의 데이터 유형은 반드시 "Content-Type" 헤더와 일치해야 함
			});
			return response.json(); // JSON 응답을 네이티브 JavaScript 객체로 파싱
		}
	</script>

	</body>

	</html>