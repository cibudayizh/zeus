package com.zeus.system.listener;

import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.SettingUtil;
import com.zeus.system.annotation.Permission;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Parameter;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zhuzihang
 * @createTime 2023-02-10 010 16:39
 * @description
 */
@Component
public class PermissionListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {


        Map<RequestMappingInfo, HandlerMethod> map = event.getApplicationContext().getBean(RequestMappingHandlerMapping.class).getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : map.entrySet()) {
            RequestMappingInfo info = entry.getKey();
            HandlerMethod method = entry.getValue();
            Permission permissionAnnotation = method.getMethodAnnotation(Permission.class);
            // 判断接口是否是上传文件接口
            int multipartFormData = getMultipartFormData(method);
            // 获取接口的请求类型（post/get/put/delete....）
            String methods = initAllowedHttpMethods(info.getMethodsCondition().getMethods());
            String code = permissionAnnotation != null ? permissionAnnotation.value() : "";


        }
    }

    /**
     * 判断接口是否是上传文件接口
     *
     * @param method
     * @return
     */
    private Integer getMultipartFormData(HandlerMethod method) {
        MethodParameter[] methodParameters = method.getMethodParameters();
        for (MethodParameter methodParameter : methodParameters) {
            Parameter parameter = methodParameter.getParameter();
            if (parameter.getType() == MultipartFile.class) {
                return 1;
            }
        }
        return 0;
    }

    /**
     * 根据controller方法上注解（@GetMapping、@PostMapping...）的所属请求类型（如GET、POST...）获取该方法支持的所有请求类型。
     * 该段逻辑来源于Spring底层的 RequestMappingInfoHandlerMapping.HttpOptionsHandler.initAllowedHttpMethods()。
     * 要点：
     * 1、所有方法都支持OPTIONS请求方式
     * 2、如方法是GET方法，则该方法同时支持HEAD请求方式
     *
     * @param declaredMethods declaredMethods
     * @return String
     */
    private String initAllowedHttpMethods(Set<RequestMethod> declaredMethods) {
        Set<HttpMethod> result = new LinkedHashSet<>(declaredMethods.size());
        if (declaredMethods.isEmpty()) {
            for (HttpMethod method : HttpMethod.values()) {
                if (method != HttpMethod.TRACE) {
                    result.add(method);
                }
            }
        } else {
            for (RequestMethod method : declaredMethods) {
                HttpMethod httpMethod = HttpMethod.valueOf(method.name());
                result.add(httpMethod);
                if (httpMethod == HttpMethod.GET) {
                    result.add(HttpMethod.HEAD);
                }
            }
            result.add(HttpMethod.OPTIONS);
        }
        StringBuffer methods=new StringBuffer();
        result.forEach(e->methods.append(e.name()).append("/"));
        methods.deleteCharAt(methods.length()-1);
        return methods.toString();
    }
}
