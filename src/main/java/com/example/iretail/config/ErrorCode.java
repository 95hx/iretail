package com.example.iretail.config;

import lombok.Getter;

@Getter
public enum  ErrorCode {
    INVALID_PARAM("000001","invalid parameter"),
    INVALID_PARENT_CODE("000002","invalid parent code"),
    EMPTY_PARAM("000003","request body is empty"),
    SQL_DUPLICATE_ENTRY("000004","sql duplicate entry"),
    SQL_UPDATE_FAIL("000005","sql update fail"),
    DOC_NUM_GENERATE_FAIL("000006","please retry generate doc num"),
    INVALID_ORDER_DETAIL("000007","order detail invalid please check"),
    PERMISSION_DENY("000008","permission deny"),
    INTERVAL_ERROR("999999","internal error");
    private String code;
    private String detail;

    ErrorCode(String code, String detail) {
        this.code = code;
        this.detail = detail;
    }
}
