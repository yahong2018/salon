package com.hy.salon.basic.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LogCostInterceptor()).addPathPatterns("/**");
//        super.addInterceptors(registry);



        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new LogCostInterceptor());
        interceptorRegistration.excludePathPatterns("/api/login/doLogin");
        interceptorRegistration.excludePathPatterns("/login/doLogin");
        interceptorRegistration.excludePathPatterns("/hy/basic/salon/createStore");
        interceptorRegistration.excludePathPatterns("/hy/basic/pictures/uploadServicePic");
        interceptorRegistration.excludePathPatterns("/hy/basic/salon/sendMessage");
        interceptorRegistration.excludePathPatterns("/static/**");
        interceptorRegistration.addPathPatterns("/hy/basic/attendance/**");
        interceptorRegistration.addPathPatterns("/hy/basic/job/**");
        interceptorRegistration.addPathPatterns("/hy/basic/member/**");
        interceptorRegistration.addPathPatterns("/hy/basic/my/**");
        interceptorRegistration.addPathPatterns("/hy/basic/notice/**");
        interceptorRegistration.addPathPatterns("/hy/basic/nurseLog/**");
        interceptorRegistration.addPathPatterns("/hy/basic/pictures/**");
        interceptorRegistration.addPathPatterns("/hy/basic/product/**");
        interceptorRegistration.addPathPatterns("/hy/basic/productStockMovement/**");
        interceptorRegistration.addPathPatterns("/hy/basic/reservation/**");
        interceptorRegistration.addPathPatterns("/hy/basic/salon/**");
        interceptorRegistration.addPathPatterns("/hy/basic/schedule/**");
        interceptorRegistration.addPathPatterns("/hy/basic/service/**");
        interceptorRegistration.addPathPatterns("/hy/basic/serviceSeries/**");
        interceptorRegistration.addPathPatterns("/hy/basic/serviceSuite/**");
        interceptorRegistration.addPathPatterns("/hy/basic/shift/**");
        interceptorRegistration.addPathPatterns("/hy/basic/schedule/**");
        interceptorRegistration.addPathPatterns("/hy/basic/stockTransferApplication/**");
        interceptorRegistration.addPathPatterns("/hy/basic/storeRoom/**");
        interceptorRegistration.addPathPatterns("/hy/basic/stuff/**");
        interceptorRegistration.addPathPatterns("/hy/basic/stuffIntegralRecord/**");
        interceptorRegistration.addPathPatterns("/hy/basic/timeSheet/**");
        interceptorRegistration.addPathPatterns("/hy/basic/vipSuite/**");
        interceptorRegistration.addPathPatterns("/hy/basic/workSummary/**");




    }
}
