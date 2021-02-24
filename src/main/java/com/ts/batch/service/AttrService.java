package com.ts.batch.service;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.ts.batch.domain.OpenApiVo;
import com.ts.batch.impl.OpenApiImpl;
import com.ts.batch.repository.OpenApiMapper;

@Service("com.ts.service.AttrService")
@PropertySource(value = { "classpath:openapi.properties" })
public class AttrService {

	@Autowired
	OpenApiMapper openApiMapper;

	@Value("${api.num_of_rows}")
	String numOfRows;

	@Value("${api.result_type}")
	String resultType;

	@Value("${api.page_no}")
	String pageNo;

	@Value("${api2.code}")
	String code;

	@Value("${api2.url}")
	String url;

	@Value("${api2.service_key}")
	String serviceKey;

	@Value("${api2.area}")
	String area;

	public int callAttrApi() throws Exception {
		int complete = -1;

		OpenApiVo openApiVo = new OpenApiVo();
		openApiVo.setUrl(url);
		openApiVo.setServiceKey(serviceKey);
		openApiVo.setCode(code);
		openApiVo.setArea(area);
		openApiVo.setPageNo(pageNo);
		openApiVo.setNumOfRows(numOfRows);
		openApiVo.setResultType(resultType);

		JSONArray jsonArray = OpenApiImpl.callOpenApi(openApiVo);
		complete = attrCreate(jsonArray, openApiVo);

		return complete;
	}

	/**
	 * 부산 명소 데이터를 생성합니다.
	 */
	public int attrCreate(JSONArray jsonArray, OpenApiVo openApiVo) {
		int complete = -1;

		openApiMapper.attr_delete(openApiVo);

		for (int i = 0; i < jsonArray.size(); i++) {
			Map<String, Object> params = new HashMap<String, Object>();
			int result = -1;

			JSONObject data = (JSONObject) jsonArray.get(i);

			Long uc_seq = (Long) data.get("UC_SEQ");
			String main_title = (String) data.get("MAIN_TITLE");
			String gugun_nm = (String) data.get("GUGUN_NM");
			Double lat = (Double) data.get("LAT");
			Double lng = (Double) data.get("LNG");
			String place = (String) data.get("PLACE");
			String title = (String) data.get("TITLE");
			String subtitle = (String) data.get("SUBTITLE");
			String addr1 = (String) data.get("ADDR1");
			String addr2 = (String) data.get("ADDR2");
			String cntct_tel = (String) data.get("CNTCT_TEL");
			String homepage_url = (String) data.get("HOMEPAGE_URL");
			String usage_day_week_and_time = (String) data.get("USAGE_DAY_WEEK_AND_TIME");
			String rprsntv_menu = (String) data.get("RPRSNTV_MENU");
			String main_img_normal = (String) data.get("MAIN_IMG_NORMAL");
			String main_img_thumb = (String) data.get("MAIN_IMG_THUMB");
			String itemcntnts = (String) data.get("ITEMCNTNTS");

			params.put("uc_seq", uc_seq);
			params.put("main_title", main_title);
			params.put("gugun_nm", gugun_nm);
			params.put("lat", lat);
			params.put("lng", lng);
			params.put("place", place);
			params.put("title", title);
			params.put("subtitle", subtitle);
			params.put("addr1", addr1);
			params.put("addr2", addr2);
			params.put("cntct_tel", cntct_tel);
			params.put("homepage_url", homepage_url);
			params.put("usage_day_week_and_time", usage_day_week_and_time);
			params.put("rprsntv_menu", rprsntv_menu);
			params.put("usage_day_week_and_time", usage_day_week_and_time);
			params.put("main_img_normal", main_img_normal);
			params.put("main_img_thumb", main_img_thumb);
			params.put("itemcntnts", itemcntnts);
			params.put("area", openApiVo.getArea());
			params.put("gubun", openApiVo.getCode());

			result = openApiMapper.attr_save(params);
			if (result > -1) {
				complete = openApiMapper.place_save(params);
			}
		}
		return complete;
	}

	/**
	 * 부산 명소 데이터를 전체 삭제합니다.
	 */
	public int attrDelete(OpenApiVo openApiVo) {
		int result = -1;
		result = openApiMapper.attr_delete(openApiVo);
		return result;
	}
}
