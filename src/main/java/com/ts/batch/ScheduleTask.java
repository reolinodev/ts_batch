package com.ts.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ts.batch.service.AttrService;
import com.ts.batch.service.FoodService;

@Component
public class ScheduleTask {

	@Autowired
	FoodService foodService;

	@Autowired
	AttrService attrService;

	// 월요일 새벽 4시에 실행(부산 맛집 호출)
	@Scheduled(cron = "0 0 04 ? * MON")
	public void step1() throws Exception {
		foodService.callFoodApi();
	}

	// 월요일 새벽 4시 30분에 실행(부산 명소 호출)
	@Scheduled(cron = "0 30 04 ? * MON")
	public void step2() throws Exception {
		attrService.callAttrApi();
	}
}
