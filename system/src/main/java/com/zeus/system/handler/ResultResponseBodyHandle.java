package com.zeus.system.handler;

import cn.hutool.json.JSONUtil;
import com.zeus.system.annotation.ResultVO_Ignore;
import com.zeus.system.vo.common.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller 统一返回包装
 *
 * @author zhuzihang
 */
@ConditionalOnClass(ResponseBodyAdvice.class)
@Slf4j
public class ResultResponseBodyHandle implements ResponseBodyAdvice<Object> {

    private static final String LOCAL_URL = "localhost";
    private static final String LOCAL_IP = "127.0.0.1";

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        // 此处获取到request 为特殊需要的时候处理使用
        // 下面处理统一返回结果（统一code、msg、sign 加密等）
        Object resultBody;
        if (selectedConverterType == MappingJackson2HttpMessageConverter.class
                && (selectedContentType.equals(MediaType.APPLICATION_JSON))) {
            if (body == null) {
                resultBody = ResultVO.NULL;
            } else if (body instanceof ResultVO) {
                resultBody = body;
            } else {
                resultBody = notResultVoBody(body, returnType, (ServletServerHttpRequest) request);
            }
        } else {
            resultBody = body;
        }
        outLog(resultBody);
        return resultBody;
    }

    /**
     * 输出日志
     *
     * @param resultBody
     */
    private void outLog(Object resultBody) {
        if (log.isDebugEnabled()) {
            log.debug("ResponseBody={}", JSONUtil.toJsonStr(resultBody));
        }
    }


    /**
     * 非ResultVO返回对象处理
     *
     * @param body
     * @param returnType
     * @param request
     * @return
     */
    private Object notResultVoBody(Object body, MethodParameter returnType, ServletServerHttpRequest request) {
        Object resultBody;
        //判断url是否应该被忽略，若是则直接返回body
        if (returnType.getExecutable().getDeclaringClass().isAssignableFrom(BasicErrorController.class)) {
            // 异常
            ResultVO<Object> vo = new ResultVO<>(ResultVO.EXCEPTION.getCode(), ResultVO.EXCEPTION.getMessage());
            HttpServletRequest req = request.getServletRequest();
            if (req.getRequestURL().toString().contains(LOCAL_URL) || req.getRequestURL().toString().contains(LOCAL_IP)) {
                vo.setData(body);
            }
            resultBody = vo;
        } else {// 其他对象
            resultBody = new ResultVO<>(body);
        }
        return resultBody;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return !returnType.hasMethodAnnotation(ResultVO_Ignore.class);
    }


}
