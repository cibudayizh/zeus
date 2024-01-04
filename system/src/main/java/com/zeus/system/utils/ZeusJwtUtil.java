package com.zeus.system.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.*;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.zeus.system.dto.common.UserInformationDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * jwt工具类
 */
@Component
public class ZeusJwtUtil {

    /**
     * 过期时间 xx毫秒
     */
    @Value("${token.expired}")
    private long timeOut;

    /**
     * token密钥
     */
    @Value("${token.secret}")
    private String configTokenSecret;


    public static final String AUTH_HEADER_KEY = "Authorization";

    private JWTSigner jwtSigner;

    @PostConstruct
    private void jwtSignerGen(){
      this.jwtSigner = JWTSignerUtil.hs256(configTokenSecret.getBytes());
    }
    /**
     * 生成签名
     * @param userId
     * @param tenantId
     * @return
     */
    public String sign(Long userId,Long tenantId) {
        // 过期时间
        LocalDateTime nowDate = LocalDateTimeUtil.now();
        LocalDateTime offset = LocalDateTimeUtil.offset(nowDate, timeOut, ChronoUnit.MILLIS);
        JWT jwt = JWT.create()
                //设置签发时间
                .setIssuedAt(DateUtil.date(nowDate))
                .setNotBefore(DateUtil.date(nowDate))
                .setExpiresAt(DateUtil.date(offset))
                //设置头信息
                .setHeader(JWTHeader.TYPE, "JWT")
                .setHeader(JWTHeader.ALGORITHM, "HS256")
                //设置载荷信息
                .setPayload("userId",userId)
                .setPayload("tenantId",tenantId)
                .setSigner(jwtSigner);
        return jwt.sign();
    }

    /**
     * 校验token
     * @param token
     * @return
     */
    public boolean verity(String token) {
        if(StrUtil.isEmpty(token)){
            return false;
        }
        return JWTUtil.verify(token,jwtSigner);
    }

    /**
     * 解析token
     * @param token
     */
    public UserInformationDto parseToken(String token){
        JWT jwt = JWTUtil.parseToken(token);

        UserInformationDto userInformationDto=new UserInformationDto();
        userInformationDto.setUserId(Long.valueOf(String.valueOf(jwt.getPayload("userId"))));
        userInformationDto.setTenantId(Long.valueOf(String.valueOf(jwt.getPayload("tenantId"))));
        return userInformationDto;
    }
}


