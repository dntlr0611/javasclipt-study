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
			</tbody>
		</table>
		<input type='button' id="btnupdate" value='업데이트' style="float: right;">
		<input type='button' id="btnsave" value='저장' style="float: right;">
		<input type="button" id="delbtn" value="삭제" style="float: right">

		<p id="result"></p>

		<div id="map" style="width:800px;height:800px;"></div>

		<script type="text/javascript"
			src="//dapi.kakao.com/v2/maps/sdk.js?appkey=eab4d38681779d686e887e08ab50ea7e"></script>
		<script>
			let MarkerContainer = {};
			let MAP = null;
			let UpdateData = [];
			let cnt = 0;

			window.onload = async () => {
				// 데이터베이스 테이블 load
				postData('/getData.json').then(response => {
					const tbody = document.querySelector("tbody");
					let html = '';
					for (const d of response) {
						html += '<tr>';
						html += '<td>' + d.MEMBER_CD + '</td>';
						html += '<td>' + d.USER_ID + '</td>';
						html += '<td>' + d.USER_NAME + '</td>';
						html += '<td>' + d.PART + '</td>';
						html += '<td>' + d.ENTRY_DATE + '</td>';
						html += '<td>' + d.LEAVE_DATE + '</td>';
						html += '<td>' + d.DEPT_SN + '</td>';
						html += '<td>' + d.ROLE_SN + '</td>';
					}
					tbody.innerHTML = html;
				});
				// 맵 생성.
				SetMap();
				// 마커 생성
				GetMarker();
				// 선 생성
				GetPoly();
				// 마커 종료된 위치 좌표값.
				document.getElementById("btnupdate").addEventListener('click', (e) => {
					// 마커 데이터 컨트롤
					console.log(MarkerContainer);
					console.log(UpdateData);
					postData("/getUpdate.json", UpdateData);
					InitData();
				});
				document.getElementById("btnsave").addEventListener('click', (e) => {
					// 마커 데이터 컨트롤
					console.log(MarkerContainer);
					console.log(UpdateData);
					postData("/setIP.json", UpdateData);
					InitData();
				});
			}

			const GetMarker = () => {
				postData('/getIP.json').then(response => {
					SetMarker(response);
				})
			}
			const GetPoly = () => {
				//데이터 불러오기
				postData('/getLink.json').then(response => {
					console.log(response)
					LinkData(response);
				})
			}

			const SetMap = () => {
				// 맵 생성
				const container = document.getElementById("map");
				option = {
					center: new kakao.maps.LatLng(35.5588949320191, 129.35836027268343),
					level: 8
				}
				MAP = new kakao.maps.Map(container, option);
			}
			const SetMarker = (response) => {
				// 마커 이미지 변경
				for (const d of response) {
					// 맵에 마커 표시.
					const MarkerPosition = new kakao.maps.LatLng(d.LAT, d.LNG),
						marker = new kakao.maps.Marker({
							position: MarkerPosition,
							clickable: true,
						});
					marker.setMap(MAP);
					marker.key = d.BSTA_ID;
					SaveMarkerPos(marker);
					EventController(marker, MarkerPosition);
				}
				// 맵에 우클릭시 마커 생성
				kakao.maps.event.addListener(MAP, 'rightclick', function (mouseEvent) {
					const Insertposition = mouseEvent.latLng;
					Insertmarker = new kakao.maps.Marker({
						position: Insertposition
					});
					Insertmarker.setMap(MAP);
					Insertmarker.key = `1920309216_${cnt}` 
					cnt++;
					SaveMarkerPos(Insertmarker);
					EventController(Insertmarker, Insertposition);
				})
			}
			const EventController = (marker, MarkerPosition) => {
				// 인포윈도우 init
				const iwContent = '<div> ? <div>',
					iwRemoveable = true;
				const infowindow = new kakao.maps.InfoWindow({
					position: MarkerPosition,
					content: iwContent
				});

				// 첫번째 클릭 이벤트
				kakao.maps.event.addListener(marker, 'click', function (mouseEvent) {
					// 드래그 기능
					kakao.maps.event.addListener(marker, 'click', function (mouseEvent) {
						marker.setDraggable(true);
					});
					kakao.maps.event.addListener(marker, 'dragend', function (mouseEvent) {
						marker.setDraggable(false);
						const EndPosition = marker.getPosition();
						const message = '위도 : ' + EndPosition.getLat() + ' 경도 : ' + EndPosition.getLng();
						const resultDiv = document.getElementById('result');
						resultDiv.innerHTML = message;
						SaveMarkerPos(marker);
						InitData();
					});
					// 삭제 버튼 추가
					document.getElementById("delbtn").addEventListener('click', (e) => {
						const del = UpdateData.pop();
						marker.setMap();
						console.log(del)
						postData('/delIP.json', del);
					});
				})
				// 인포 윈도우 표시
				kakao.maps.event.addListener(marker, 'mouseover', MarkOver(MAP, marker, infowindow))
				kakao.maps.event.addListener(marker, 'mouseout', function () {
					infowindow.close();
				})
			}
			// 마커 인포윈도우 함수
			const MarkOver = (map, marker, infowindow) => {
				return function () {
					infowindow.open(MAP, marker);
				}
			}
			// 데이터 로드
			const LinkData = (arr) => {
				let linkarr = [];
				for (x in arr) {
					const linePath = new kakao.maps.LatLng(arr[x].LAT, arr[x].LNG);
					linkarr.push(linePath);
				}
				SetPoly(linkarr);
			}
			const InitData = () => {
				UpdateData = [];
				MarkerContainer = {};
			}
			const SaveMarkerPos = (marker) => {
				MarkerContainer = {
					BSTA_ID: marker.key,
					LAT: marker.getPosition().getLat(),
					LNG: marker.getPosition().getLng()
				}
				UpdateData.push(MarkerContainer);
			}
			// 선그리기.
			const SetPoly = (line) => {
				const polyline = new kakao.maps.Polyline({
					path: line,
					strokeWeight: 3,
					strokeColor: '#FF0000',
					strokeOpacity: 0.7,
					strokeStryle: 'solid'
				});
				polyline.setMap(MAP);
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