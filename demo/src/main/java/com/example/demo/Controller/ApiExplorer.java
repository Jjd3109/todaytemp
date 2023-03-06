package com.example.demo.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Controller.TransLocalPoint.LatXLngY;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.servlet.http.HttpServletRequest;




@Controller
public class ApiExplorer {
  
	@GetMapping(value="/test")
	public String test(Model model, HttpServletRequest request) throws IOException{
		
	
		
		Date today = new Date();
		//오늘의 날짜
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    	String simToday = simpleDateFormat.format(today);
    	//현재시간구하기
    	SimpleDateFormat DateFormat = new SimpleDateFormat("HH00");
    	String nowTime = DateFormat.format(today);
    	//오늘 날짜랑 시간
       	SimpleDateFormat todayDateFormat = new SimpleDateFormat("E,HH:mm");
    	String todayTime = todayDateFormat.format(today);
    	
    	SimpleDateFormat timeDateFormat = new SimpleDateFormat("HH:mm");
    	String time = timeDateFormat.format(today);
    	
    	
    	
   
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"); /*URL*/
		urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=WmrPWNl1NFnfUvJSuftqqxBX0Gviepbevgj%2BfPo56dKIXpvtaUvjgcV%2B9IhgbHplZxXIkBOFqkWAVwWCzExd2A%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(simToday, "UTF-8")); /*‘21년 6월 28일발표*/
        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0500", "UTF-8")); /*05시 발표*/
        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode("60", "UTF-8")); /*예보지점의 X 좌표값*/
        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode("127", "UTF-8")); /*예보지점의 Y 좌표값*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
       
        //[{"baseDate":"20230226","baseTime":"0500","category":"TMP","fcstDate":"20230226","fcstTime":"0600","fcstValue":"-4","nx":55,"ny":127},
        //이 값만 구하고싶다..
        JsonObject resultObj = JsonParser.parseString(sb.toString()).getAsJsonObject();
		JsonObject responseObj = resultObj.getAsJsonObject("response");
		
		JsonObject bodyObj = responseObj.getAsJsonObject("body");
		JsonObject itemsObj = bodyObj.getAsJsonObject("items");
		JsonArray itemObj = itemsObj.getAsJsonArray("item");

		
		List<Vo> itemVoList = new ArrayList<Vo>();
		for(int i = 0; i < itemObj.size(); i++) {
			//전체를 담는
			JsonObject item = itemObj.get(i).getAsJsonObject();
			
			String category = item.get("category").getAsString(); //온도구별
			String fcstDate = item.get("fcstDate").getAsString(); //오늘의 날짜
			String fcstTime = item.get("fcstTime").getAsString(); //몇시인지 구별
			String fcstValue = item.get("fcstValue").getAsString(); //온도 나타내기.
	
			Vo testVo = new Vo();
			testVo.setCategory(category);
			testVo.setFcstDate(fcstDate);
			testVo.setFcstTime(fcstTime);
			testVo.setFcstValue(fcstValue);
		
			
			itemVoList.add(testVo);
		}

		
		//최고온도랑 최저온도 구하기!!!!!
		


        model.addAttribute("sb", itemVoList);
        model.addAttribute("nowTime", nowTime);
        model.addAttribute("simToday", simToday);
        model.addAttribute("todayTime", todayTime);
        model.addAttribute("time", time);
        return "Test";
       
    }
	
	@PostMapping(value="test")
	@ResponseBody
	public String ajaxTest(Model model, HttpServletRequest request) throws IOException {
		String getLat = request.getParameter("getLat");
		String getLng = request.getParameter("getLng");
		
	
		
		Date today = new Date();
		//오늘의 날짜
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    	String simToday = simpleDateFormat.format(today);
    	//현재시간구하기
    	SimpleDateFormat DateFormat = new SimpleDateFormat("HH00");
    	String nowTime = DateFormat.format(today);
    	//오늘 날짜랑 시간
       	SimpleDateFormat todayDateFormat = new SimpleDateFormat("E,HH:mm");
    	String todayTime = todayDateFormat.format(today);
    	
    	SimpleDateFormat timeDateFormat = new SimpleDateFormat("HH:mm");
    	String time = timeDateFormat.format(today);
    	
    	
    	
   
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"); /*URL*/
		urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=WmrPWNl1NFnfUvJSuftqqxBX0Gviepbevgj%2BfPo56dKIXpvtaUvjgcV%2B9IhgbHplZxXIkBOFqkWAVwWCzExd2A%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(simToday, "UTF-8")); /*‘21년 6월 28일발표*/
        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0500", "UTF-8")); /*05시 발표*/
        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(getLat, "UTF-8")); /*예보지점의 X 좌표값*/
        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(getLng, "UTF-8")); /*예보지점의 Y 좌표값*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
       
        //[{"baseDate":"20230226","baseTime":"0500","category":"TMP","fcstDate":"20230226","fcstTime":"0600","fcstValue":"-4","nx":55,"ny":127},
        //이 값만 구하고싶다..
        JSONObject json = new JSONObject();
        JsonObject resultObj = JsonParser.parseString(sb.toString()).getAsJsonObject();
		JsonObject responseObj = resultObj.getAsJsonObject("response");
		
		JsonObject bodyObj = responseObj.getAsJsonObject("body");
		JsonObject itemsObj = bodyObj.getAsJsonObject("items");
		JsonArray itemObj = itemsObj.getAsJsonArray("item");
		
	
		String value = "";
		String highValue = "";//최고온도
		String lowValue = ""; //최저온도
		
		List<Vo> itemVoList = new ArrayList<Vo>();
		for(int i = 0; i < itemObj.size(); i++) {
			//전체를 담는
			JsonObject item = itemObj.get(i).getAsJsonObject();
			
			String baseDate = item.get("baseDate").getAsString(); // 오늘의 날짜
			String category = item.get("category").getAsString(); //온도구별
			String fcstDate = item.get("fcstDate").getAsString(); //오늘의 날짜
			String fcstTime = item.get("fcstTime").getAsString(); //몇시인지 구별
			String fcstValue = item.get("fcstValue").getAsString(); //온도 나타내기.
			
			//System.out.println("category 값 : " + category + " fcstTime 시간 : " + fcstTime + " fcstDate 날짜 : " + fcstDate );

			//전체를 다담는
			Vo testVo = new Vo();
			testVo.setCategory(category);
			testVo.setFcstDate(fcstDate);
			testVo.setFcstTime(fcstTime);
			testVo.setFcstValue(fcstValue);
	
			itemVoList.add(testVo);
			
			//현재 fcstTime과 nowTime값이 같으면 따로 나오게끔
			if(fcstTime.equals(nowTime) && category.equals("TMP") && fcstDate.equals(baseDate)) {

				value = fcstValue;
			}
		
			if(category.equals("TMX") && fcstDate.equals(baseDate)) {

				highValue = fcstValue; // 최고온도
			}
			
			if(fcstTime.equals("0600") && category.equals("TMP")  && fcstDate.equals(baseDate)) {

				lowValue = fcstValue; //최저온도
			}
		
		}



		json.put("itemVoList", itemVoList);
		json.put("fcstValue", value);
		json.put("code", "success");
		json.put("nowTime", nowTime);
		json.put("todayTime", todayTime);
		
		json.put("lowValue", lowValue);
		json.put("highValue", highValue);
		
		return json.toJSONString();
	}
}