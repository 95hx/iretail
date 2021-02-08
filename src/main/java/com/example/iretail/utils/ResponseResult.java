package com.example.iretail.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * ResponseResult
 */
@Validated
@Data
public class ResponseResult {
    @JsonProperty("status")
    private String status = null;

    @JsonProperty("code")
    private String code = null;

    @JsonProperty("detail")
    private String detail = null;

    @JsonProperty("message")
    private String message = null;

    @JsonProperty("result")
    private Object result = null;
}

