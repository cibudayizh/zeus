package com.zeus.system.config;

import com.zeus.system.interceptor.AuthorizationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Administrator
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer{

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/*/*")
                        .allowedOrigins("*")
                        .allowedMethods("*");

            }
        };
    }
    @Bean
    public AuthorizationInterceptor authorizationInterceptor(){
        return new AuthorizationInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        将我们上步定义的实现了HandlerInterceptor接口的拦截器实例
        //排除swagger
        String[] excludePathArr = {"/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**"};
        registry.addInterceptor(authorizationInterceptor()).addPathPatterns("/**").excludePathPatterns(excludePathArr);

    }
}
