# 오늘의 날씨

<h2>기획목표</h2>
원하는 지역의 현재온도 및 오늘의 날씨를 알 수 있게 만들기.

<h2>중점사항 </h2>

<p>1.카카오맵 api 사용
<p>2.공공데이터 날씨api사용
<p>3.ajax를 사용해서 실시간으로 연동하기

<hr>
<h2>1.목업</h2>
카카오오븐을 이용한  기초 목업
![오늘의날씨  Untitled](https://user-images.githubusercontent.com/100771092/223019676-9ac3b30b-b49e-402d-a4d4-5c1ee39874af.png)



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
+
+


