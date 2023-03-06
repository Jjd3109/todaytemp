# 사이드 프로젝트 - 오늘의 날씨

<h2>기획목표</h2>
원하는 지역의 현재온도 및 오늘의 날씨를 알 수 있게 만들기.

<h2>중점사항 </h2>

<p>1.카카오맵 api 사용
<p>2.공공데이터 날씨api사용
<p>3.ajax를 사용해서 실시간으로 연동하기

<hr>
<h2>1.목업</h2>
카카오오븐을 이용한  기초 목업
<img width="528" alt="목업)오늘의날씨" src="https://user-images.githubusercontent.com/100771092/223019902-1476ea52-eb55-4d3b-a937-0b61dfd26936.png">




<h2>2.코드작성</h2>
자바 스프링부트를 이용하여 controller, vo, view 를 생성하여 코드작성.
<p>swiper를 사용하여 이미지 담기
<p>카카오맵api를 이용하여 원하는지역의 좌표를 찍어, ajax를 통해서 비동기로 원하는 지역의 날씨와 온도 표시
<p>
	    
	    $.ajax({
            url:'test',
            type:'post',
            data:{getLat: xy.x, getLng: xy.y},
            dataType: "json",
            success:function(data){ 
            	var message = data.fcstValue;
            	
            	var div = document.getElementById('dhseh');
            	div.innerHTML = message + '';
            	
            	var high = document.getElementById('high');
            	high.innerHTML = data.highValue;
            	
            	var low = document.getElementById('low');
            	low.innerHTML = data.lowValue;
     
            }
			});
      
<p>
<h2>3.실제페이지</h2>
<img width="956" alt="카카오" src="https://user-images.githubusercontent.com/100771092/223019202-414f8568-00a3-405a-855c-eb8ffd0dcb33.png">

<h2>4.수정 및 추가해야할 사항</h2>
+
<p>+

<h2>5.힘들었던 점, 배웠던 점</h2>
<p>공공데이터의 격자위치와 카카오맵 마커를 찍었을때 나오는 위도 경도와 달라서
<p>위치를 동일시 하게 만드는게 힘들었고, 처음으로 스프링부트를 사용하여 만들어봐서
<p>tymeleaf를 사용하는 방식이 조금 어려웠습니다.
<p>api를 받아와서 사용하고, 활용하는 방법에 대해 좀 더 깊게 배웠던 것 같습니다. 
	
<h2>6.제작기간</h2>
2023.03.01 ~ 2023.03.05

