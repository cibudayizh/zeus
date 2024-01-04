package com.zeus.system.interceptor;


import cn.hutool.core.util.StrUtil;
import com.zeus.system.annotation.Permission;
import com.zeus.system.dto.common.UserInformationDto;
import com.zeus.system.entity.SysUser;
import com.zeus.system.exception.BusinessException;
import com.zeus.system.exception.ForbiddenException;
import com.zeus.system.mapper.SysUserMapper;
import com.zeus.system.service.UserService;
import com.zeus.system.utils.RedisUtil;
import com.zeus.system.utils.WebContextUtil;
import com.zeus.system.utils.ZeusJwtUtil;
import com.zeus.system.vo.UserVo;
import com.zeus.system.vo.common.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static com.zeus.system.constants.RedisPrefixConstant.USER_TOKEN_PREFIX;

/**
 * @author zhuzihang
 * @createTime 2023-02-02 002 13:45
 * @description
 */
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private ZeusJwtUtil zeusJwtUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${token.checkToken}")
    private boolean checkToken;


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        if(!checkToken){
            return true;
        }
        List<String> ignorePathList=new ArrayList<>();
        ignorePathList.add("/login/userLogin");
        //能够通过的路径
        String servletPath = httpServletRequest.getServletPath();
        if(ignorePathList.contains(servletPath)){
            return true;
        }
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        // 检查有没有Permission的注解 有则不需要校验token
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(Permission.class)) {
            Permission tokenIgnore = method.getAnnotation(Permission.class);
            if (!tokenIgnore.checkPermission()) {
                return true;
            }
        }
        // 从http请求头中取出token
        String token = httpServletRequest.getHeader(ZeusJwtUtil.AUTH_HEADER_KEY);
//        String token   ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NzU5MzMyMTQsIm5iZiI6MTY3NTkzMzIxNCwiZXhwIjoxNjc1OTMzODE0LCJ1c2VySWQiOjE2MjI4OTkwNTkwODAxMjY0NjQsInRlbmFudElkIjowfQ.Hj8pdSbDSA-jKKusoBgypKCLmNDeYPQJomKO_O7SbRI";
        // 执行认证 校验token是否有效
        if (StrUtil.isEmpty(token)&&!zeusJwtUtil.verity(token)) {
            throw new ForbiddenException(ResultVO.FORBIDDEN.getMessage());
        }
        UserInformationDto userInformationDto = zeusJwtUtil.parseToken(token);
        String userTokenKey = USER_TOKEN_PREFIX.concat(String.valueOf(userInformationDto.getUserId()));
        //判断redis中该用户token是否存在
        if(redisUtil.hasKey(userTokenKey)){
            //存在判断redis中token和现有token是否一致 禁止多端登录 后续若允许多端登录可以将key设置为不同
            String userRedisToken = redisUtil.getCacheObject(userTokenKey);
            if(!token.equals(userRedisToken)){
                //两个token 不一致返回无权限
                throw new ForbiddenException(ResultVO.FORBIDDEN.getMessage());
            }
        }else {
            //不存在直接返回无权限
            throw new ForbiddenException(ResultVO.FORBIDDEN.getMessage());
        }
        UserVo userVo = userService.getUser(userInformationDto.getUserId());
        if (userVo == null) {
            throw new BusinessException(ResultVO.FORBIDDEN.getCode(),"用户不存在，请重新登录");
        }
        WebContextUtil.setUserInfo(userVo);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object, Exception e) throws Exception {
        //方法结束后，缓存里移除用户信息
        WebContextUtil.removeUserInfo();
    }
}
