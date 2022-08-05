<%@ page language="java" contentType="text/html; charset=euc-kr" pageEncoding="UTF-8" %>
   <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
   <html>

   <head>
      <metahttp-equiv="Content-Type" content="text/html; charset=utf-8">
         <script type="text/javascript"
            src="//dapi.kakao.com/v2/maps/sdk.js?appkey=66b790a5f18d9c86e9ca412d98ec7fb6"></script>
         <title>welcome</title>
   </head>

   <body>
      <p>
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
      <button type="button" id="save" style="width:100px;height:60px;">save</button>
      <div id="map" style="width:100%;height:500px;"></div>
      <div id="result">하하하하하하하하</div>
      </p>
      <script>
         const markerContainer = [];
         let MAP = null;

         window.onload = () => {
            setMap();
            getMember();
            getMarker();
            getTrack();
            //save버튼 눌렀을 때 마커 좌표 저장
            document.getElementById('save').addEventListener('click', (e) => {
               console.log('save');
               console.log(markerContainer);
               let array = [];
               let aa = {};
               for (const m of markerContainer) {
                  aa = {
                     BSTA_ID : m.key,
                     LAT: m.getPosition().getLat(),
                     LNG: m.getPosition().getLng()
                  }
                  array.push(aa);
               }
               postData('/sendAjax.json', array).then(response => {
                  setMarker(response);
                  console.log(response)
            
               });
               aa = {};
               array = [];
               
            });
         }
         //test_member 테이블 띄우기
         const getMember = () => {
            /* postData('/getData.json').then(response => {
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
            }); */
         }

         //지도 그리기
         const setMap = () => {
            const mapContainer = document.getElementById('map'), // 지도를 표시할 div에 접근
               mapOption = {
                  center: new kakao.maps.LatLng(35.5588949320191, 129.35836027268343), // 지도의 중심좌표
                  level: 3 // 지도의 확대 레벨
               };
            // 지도 생성
            MAP = new kakao.maps.Map(mapContainer, mapOption);
         }

         //마커 찍을 좌표 데이터 받아오기
         const getMarker = () => {
            postData('/getList.json').then(response => {
               setMarker(response);
            });
         }
         //선 그릴 좌표 데이터 받아오기
         const getTrack = () => {
            postData('/getTrack.json').then(response => {
               // console.log(response.length);
               trackPosition(response)
            });
         }
         //받아온 데이터로 마커 그리기
         const setMarker = (data) => {
            for (const d of data) {
               const markerPosition = new kakao.maps.LatLng(d.LAT, d.LNG);
               const marker = new kakao.maps.Marker({
                  position: markerPosition,
                  // map: MAP
               });
               marker.setMap(MAP);
               marker.key = d.BSTA_ID //마커의 키를 BSTA_ID로 설정
               markerContainer.push(marker);
               console.log(marker)
               kakao.maps.event.addListener(marker, 'click', function () {
                  var iwContent = '<div style="padding:5px;">큭큭..</div>', // 인포윈도우에 표출될 내용
                      iwRemoveable = true; // removeable 속성을 ture 로 설정 시 인포윈도우를 닫을 수 있는 x버튼이 표시
                  var infowindow = new kakao.maps.InfoWindow({
                      content: iwContent,
                      removable: iwRemoveable
                  });
                  infowindow.open(MAP, marker)//맵에 있는 마커에 인포윈도우 생성
                  //두번 클릭했을 때 드래그 가능
                  kakao.maps.event.addListener(marker, 'click', function () {
                     marker.setDraggable(true);
                  })

                  //마커의 드래그가 끝났을 때 인포윈도우창 닫고, 마커 위치 표시
                  kakao.maps.event.addListener(marker, 'dragend', function (mouseEvent) {
                     infowindow.close();
                     marker.setDraggable(false);
                     const endPosition = marker.getPosition();
                     console.log(endPosition);
                     // 마커 위치를 클릭한 위치로 옮깁니다
                     marker.setPosition(endPosition);
                     var message = '이동한 마커 위치는 ' + endPosition.getLat() + ' 이고, ';
                     message += '경도는 ' + endPosition.getLng() + ' 입니다';
                     var resultDiv = document.getElementById('result');
                     resultDiv.innerHTML = message;
                  });
               })
            }
            // 마커를 생성합니다
            console.log('markerContainer', markerContainer);
         }
         const trackPosition = (arr) => {
            var linePath = []
            for (var i = 0; i < arr.length; i++) {
               var linePosition = new kakao.maps.LatLng(arr[i].LAT, arr[i].LNG);
               linePath.push(linePosition);
            }
            // console.log(linePath);
            setTrack(linePath); //serMarker 함수로 좌표 배열 넘기기

         }
         function setTrack(line) {
            var polyline = new kakao.maps.Polyline({
               path: line,
               strokeWeight: 5, // 선의 두께 입니다
               strokeColor: '#FF0080', // 선의 색깔입니다
               strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
               strokeStyle: 'solid' // 선의 스타일입니다
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