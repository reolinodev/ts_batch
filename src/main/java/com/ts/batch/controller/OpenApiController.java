package com.ts.batch.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ts.batch.domain.OpenApiVo;
import com.ts.batch.service.AttrService;
import com.ts.batch.service.FoodService;

@Controller
public class OpenApiController {

	@Autowired
	FoodService foodService;

	@Autowired
	AttrService attrService;

	@RequestMapping(value = "/open_api/proc", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> openApiProc(OpenApiVo openApiVo, Model model) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		int complete = -1;
		String code = openApiVo.getCode();

		if ("food".equals(code)) {
			complete = foodService.callFoodApi();
		} else if ("attr".equals(code)) {
			complete = attrService.callAttrApi();
		}

		if (complete > 0) {
			map.put("code", "success");
			map.put("msg", "데이터 생성에 성공하였습니다");
		} else {
			map.put("code", "fail");
			map.put("msg", "데이터 생성에 실패하였습니다");
		}

		return map;
	}
}
