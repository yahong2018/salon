package com.hy.salon.sys.config;

import com.zhxh.core.data.converter.DateConverter;
import com.zhxh.core.data.converter.TimestampConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class SalonApplicationConfig {
    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;

    @PostConstruct
    public void initDateConverter(){
        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer)handlerAdapter.getWebBindingInitializer();
        if(initializer.getConversionService()!=null){
            GenericConversionService genericConversionService = (GenericConversionService)initializer.getConversionService();
            List<String> datePatterns=new ArrayList<>();
            datePatterns.add("yyyy/MM/dd HH:mm:ss");
            datePatterns.add("yyyy/MM/dd");
            datePatterns.add("yyyy-MM-dd HH:mm:ss");
            datePatterns.add("yyyy-MM-dd");

            DateConverter dateConverter = new DateConverter();
            dateConverter.setPatternList(datePatterns);

            genericConversionService.addConverter(dateConverter);

            TimestampConverter timestampConverter = new TimestampConverter();
            timestampConverter.setPatternList(datePatterns);
            genericConversionService.addConverter(timestampConverter);
        }
    }
}
