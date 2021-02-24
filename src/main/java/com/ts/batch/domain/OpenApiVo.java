package com.ts.batch.domain;

import lombok.Data;

@Data
public class OpenApiVo {
    private String url;
    private String serviceKey;
    private String numOfRows;
    private String pageNo;
    private String resultType;
    private String code;

    private String place_id;
    private String area;
    private String gubun;
    private long uc_seq;
    private String init_date;
    private String modify_date;
    private String use_yn;
}
