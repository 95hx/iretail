package com.example.iretail.config;

import lombok.Data;

@Data
public class BusinessException extends RuntimeException {
    private final String errorCode;

    private String detail;

    public BusinessException(ErrorCode errorCode) {
        this.errorCode = errorCode.getCode();
        this.detail = errorCode.getDetail();
    }

    public BusinessException(ErrorCode errorCode, String detail) {
        this.errorCode = errorCode.getCode();
        this.detail = detail;
    }
}
