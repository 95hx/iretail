package com.example.iretail.config;

import com.example.iretail.utils.ResponseResult;
import com.example.iretail.utils.ResponseResultUtil;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<ResponseResult> defaultErrorHandler(Exception e) {
        e.printStackTrace();
        ResponseResult responseResult = ResponseResultUtil.fail(e.getMessage());
        return new ResponseEntity<>(responseResult, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = DuplicateKeyException.class)
    @ResponseBody
    public ResponseEntity<ResponseResult> duplicateErrorHandler(Exception e) {
        e.printStackTrace();
        ResponseResult responseResult = ResponseResultUtil.fail(ErrorCode.SQL_DUPLICATE_ENTRY,e.getMessage());
        return new ResponseEntity<>(responseResult, HttpStatus.OK);
    }

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ResponseEntity<ResponseResult> businessErrorHandler(BusinessException e) {
        ResponseResult responseResult = ResponseResultUtil.fail(e);
        responseResult.setMessage(e.getMessage());
        return new ResponseEntity<>(responseResult, HttpStatus.OK);
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ResponseResult> ValidErrorHandler(Exception e) {
        ResponseResult responseResult = ResponseResultUtil.fail(ErrorCode.INVALID_PARAM,e.getMessage());
        return new ResponseEntity<>(responseResult, HttpStatus.OK);
    }

}
