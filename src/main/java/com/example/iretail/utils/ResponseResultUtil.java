package com.example.iretail.utils;

import com.example.iretail.config.BusinessException;
import com.example.iretail.config.ErrorCode;

public class ResponseResultUtil {
    public static final String STATUS_FAIL = "fail";
    public static final String STATUS_SUCCESS = "success";
    public static final String CODE_SUCCESS = "000000";


    public static ResponseResult ok() {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(CODE_SUCCESS);
        responseResult.setStatus(STATUS_SUCCESS);
        return responseResult;
    }
    public static ResponseResult ok(Object result) {
        ResponseResult responseResult = ResponseResultUtil.ok();
        responseResult.setResult(result);
        return responseResult;
    }

    public static ResponseResult fail(String message) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setStatus(STATUS_FAIL);
        responseResult.setCode(ErrorCode.INTERVAL_ERROR.getCode());
        responseResult.setDetail(ErrorCode.INTERVAL_ERROR.getDetail());
        responseResult.setMessage(message);
        return responseResult;
    }

    public static ResponseResult fail(String code, String detail) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setStatus(STATUS_FAIL);
        responseResult.setCode(code);
        responseResult.setDetail(detail);
        return responseResult;
    }

    public static ResponseResult fail(ErrorCode errorCode,String message) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setStatus(STATUS_FAIL);
        responseResult.setCode(errorCode.getCode());
        responseResult.setDetail(errorCode.getDetail());
        responseResult.setMessage(message);
        return responseResult;
    }

    public static ResponseResult fail(BusinessException e) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setStatus(STATUS_FAIL);
        responseResult.setCode(e.getErrorCode());
        responseResult.setDetail(e.getDetail());
        return responseResult;
    }
}
