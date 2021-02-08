package com.example.iretail.controller;

import com.example.iretail.service.UserService;
import com.example.iretail.utils.JwtUtil;
import com.example.iretail.utils.ResponseResult;
import com.example.iretail.utils.ResponseResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@Api("用户登录注册")
@Validated
@RestController
@RequestMapping("/")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private JwtUtil jwtUtil;

    @ApiOperation("登录返回jwt")
    @PostMapping("/login")
    public ResponseEntity<ResponseResult> login(@ApiParam("账号密码") @RequestBody Map<String, String> user) {
        return ResponseEntity.ok(ResponseResultUtil.ok(jwtUtil.generateToken(userService.login(user))));
    }

    @ApiOperation("注册")
    @PostMapping("/register")
    public ResponseEntity<ResponseResult> register(@ApiParam("账号密码") @RequestBody Map<String, String> user) {
        return ResponseEntity.ok(ResponseResultUtil.ok(userService.register(user)));
    }

    @ApiOperation("校验jwt并获取userInfo")
    @PostMapping("/verify")
    public ResponseEntity<ResponseResult> verify(@ApiParam("jwt") @RequestBody Map<String, String> requestMap) {
        return ResponseEntity.ok(ResponseResultUtil.ok(jwtUtil.verify(requestMap.get("jwt"))));
    }
}
