package com.hy.salon.sys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan("com.zhxh.*")
@ComponentScan("com.hy.*")
@EnableAutoConfiguration
@EnableScheduling
public class SalonApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SalonApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SalonApplication.class, args);
    }

}
