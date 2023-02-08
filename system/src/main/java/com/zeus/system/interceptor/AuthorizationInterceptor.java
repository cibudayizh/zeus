//package com.zeus.system.interceptor;
//
//
//import com.zeus.system.annotation.NotRequired;
//import com.zeus.system.exception.BusinessException;
//import com.zeus.system.mapper.SysUserMapper;
//import com.zeus.system.vo.common.ResultVO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpMethod;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.lang.reflect.Method;
//
///**
// * @author zhuzihang
// * @createTime 2023-02-02 002 13:45
// * @description
// */
//public class AuthorizationInterceptor implements HandlerInterceptor {
//
//    @Autowired
//    private SysUserMapper userMapper;
//
//    @Override
//    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
//        // 从http请求头中取出token
//        String token = httpServletRequest.getHeader(JwtUtils.AUTH_HEADER_KEY);
//        // 如果不是映射到方法直接通过
//        if (!(object instanceof HandlerMethod)) {
//            return true;
//        }
//        // 检查有没有@TokenNotRequired的注解
//        HandlerMethod handlerMethod = (HandlerMethod) object;
//        Method method = handlerMethod.getMethod();
//        if (method.isAnnotationPresent(NotRequired.class)) {
//            NotRequired tokenNotRequired = method.getAnnotation(NotRequired.class);
//            if (tokenNotRequired.required()) {
//                return true;
//            }
//        }
//        // 执行认证
//        if (token == null) {
//            throw new BusinessException(ResultVO.FORBIDDEN.getCode(),"无token，请重新登录");
//        }
//
//        // 获取token中的username
//        String username;
//        try {
//            username = JWT.decode(token).getClaim("username").asString();
//        } catch (JWTDecodeException j) {
//            throw new RuntimeException("401");
//        }
//        User user = userMapper.findUserByName(username);
//        if (user == null) {
//            throw new BusinessException(ResultVO.FORBIDDEN.getStatus(),"用户不存在，请重新登录");
//        }
//        //验证 token
//        try {
//            if (!JwtUtils.verity(token)) {
//                throw new BusinessException(ResultVO.FORBIDDEN.getStatus(),"无效的令牌");
//            }
//        } catch (JWTVerificationException e) {
//            throw new BusinessException(ResultVO.FORBIDDEN.getStatus(),"401");
//        }
//        WebContextUtil.setUserInfo(user);
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object, ModelAndView modelAndView) throws Exception {
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object, Exception e) throws Exception {
//        //方法结束后，缓存里移除用户信息
//        WebContextUtil.removeUserInfo();
//    }
//}
