package com.ts.batch.repository;

import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.ts.batch.domain.OpenApiVo;

@Mapper
public interface OpenApiMapper {
	int food_save(Map<String, Object> params);

	int food_delete(OpenApiVo openApiVo);

	int attr_save(Map<String, Object> params);

	int attr_delete(OpenApiVo openApiVo);

	int place_save(Map<String, Object> params);
}
