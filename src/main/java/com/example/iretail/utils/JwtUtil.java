package com.example.iretail.utils;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.iretail.config.BusinessException;
import com.example.iretail.config.ErrorCode;
import com.example.iretail.dto.UserInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * 更新用户信息(权限,昵称)后需要重新获取jwt
 */
@Component
public class JwtUtil {
    @Value("${jwt.token.secret}")
    public String secret;
    @Value("${jwt.token.expTime}")
    public long expTime;

    /**
     * 根据secret,userId,expTime生成token
     */
    public String generateToken(UserInfo userInfo) {
        Date date = new Date(System.currentTimeMillis() + expTime);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withClaim("userInfo", JSON.toJSONString(userInfo))
                .withExpiresAt(date)
                .sign(algorithm);
    }

    /**
     * 对token进行校验,可在需要拦截的接口进行权限校验
     */
    public UserInfo verify(String authorization) {
        if (StringUtils.isEmpty(authorization)) {
            throw new BusinessException(ErrorCode.PERMISSION_DENY, "Authorization in header require");
        }
        Algorithm algorithm = Algorithm.HMAC256(secret);
        DecodedJWT jwt = JWT.decode(authorization);
        String userInfo = jwt.getClaim("userInfo").asString();
        JWTVerifier verifier = JWT.require(algorithm)
                .withClaim("userInfo", userInfo)
                .build();
        try {
            verifier.verify(authorization);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.PERMISSION_DENY);
        }
        return JSON.parseObject(userInfo, UserInfo.class);
    }
}
