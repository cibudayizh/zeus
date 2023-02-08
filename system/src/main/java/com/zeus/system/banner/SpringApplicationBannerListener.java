package com.zeus.system.banner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.SpringBootConfiguration;


/**
 * @author zhuzihang
 * @create 2021/12/29
 */
 class SpringApplicationBannerListener implements SpringApplicationRunListener {

     SpringApplicationBannerListener(SpringApplication application, String[] args) {
        application.setBanner(new ZeusBanner());
    }
}
