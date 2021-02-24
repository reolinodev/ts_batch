package com.ts.batch.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.ts.batch.domain.OpenApiVo;

public class OpenApiImpl {

	public static JSONArray callOpenApi(OpenApiVo openApiVo) throws Exception {

		JSONArray jsonArray = new JSONArray();

		try {

			String urlStr = openApiVo.getUrl();
			String code = openApiVo.getCode();

			urlStr += "?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + openApiVo.getServiceKey();
			urlStr += "&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + openApiVo.getNumOfRows();
			urlStr += "&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + openApiVo.getPageNo();
			urlStr += "&" + URLEncoder.encode("resultType", "UTF-8") + "=" + openApiVo.getResultType();

			URL url = new URL(urlStr);

			String line = "";
			String result = "";

			BufferedReader br;
			br = new BufferedReader(new InputStreamReader(url.openStream()));
			while ((line = br.readLine()) != null) {
				result = result.concat(line);
			}

			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(result);

			if ("food".equals(code)) {
				jsonArray = setFood(obj);
			} else if ("attr".equals(code)) {
				jsonArray = setAttraction(obj);
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonArray;
	}

	// 부산 맛집
	public static JSONArray setFood(JSONObject obj) {
		JSONObject obj_temp = (JSONObject) obj.get("getFoodKr");
		JSONArray jsonArray = (JSONArray) obj_temp.get("item");
		return jsonArray;
	}

	// 부산 명소
	public static JSONArray setAttraction(JSONObject obj) {
		JSONObject obj_temp = (JSONObject) obj.get("getAttractionKr");
		JSONArray jsonArray = (JSONArray) obj_temp.get("item");
		return jsonArray;
	}
}
