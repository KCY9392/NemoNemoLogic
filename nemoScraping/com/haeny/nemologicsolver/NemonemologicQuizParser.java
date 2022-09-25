package com.haeny.nemologicsolver;
import java.net.URLDecoder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * http://nem*****c.com 의 퀴즈 페이지 HTML을 파싱하여 <br/>
 * 해당 페이지의 네모네모로직 퍼즐을 푸는 애플리케이션
 *  
 * @author 정태현
 * @수정 진웅휘
 */
public class NemonemologicQuizParser {
	
	
	
	public static void main(String[] args) throws Exception {
		int[] QUIZ_CODE=new int[100];

		String QUIZ_URL = ""
		//랜덤한 페이지에서 문제 가저오기 - 진웅휘
		for (int i = 0; i < QUIZ_CODE.length; i++) {
			QUIZ_CODE[i]=(int)(Math.random()*16746)+1;
		}
		for(int i=0;i<QUIZ_CODE.length;i++){
		String link = QUIZ_URL+QUIZ_CODE[i];
		Document doc = Jsoup.connect(link).get();
		Element input = doc.getElementById("data-holder");		
		String endcodeValue = input.attr("value");
		String value = URLDecoder.decode(endcodeValue, "UTF-8");
		
		JSONParser jsonParser = new JSONParser();
		JSONObject valueObj = (JSONObject) jsonParser.parse(value);		
		JSONArray hhints = (JSONArray) valueObj.get("hhints");
		JSONArray vhints = (JSONArray) valueObj.get("vhints");
		
		int[][] hhintsArray = transformArray(hhints);		
		int[][] vhintsArray = transformArray(vhints);
		if(hhintsArray.length>30||vhintsArray.length>30) {
			continue;
		}

		//제목가저오기 -진웅휘
		String title= doc.title().substring(12);
		if(title.length()>10) {
			continue;
		}
		System.out.println(title);


		NemoLogicSolver solver = new NemoLogicSolver(hhintsArray, vhintsArray,title);
		solver.process();
		}		
	}

	private static int[][] transformArray(JSONArray jsonArray) {
		int[][] transed = new int[jsonArray.size()][];
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONArray row = (JSONArray) jsonArray.get(i);
			transed[i] = new int[row.size()];
			for (int j = 0; j < row.size(); j++) {
				transed[i][j] = ((Long) row.get(j)).intValue();
			}
		}
		return transed;
	}
}
