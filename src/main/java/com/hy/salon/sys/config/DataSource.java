package com.hy.salon.sys.config;

import com.hy.salon.sys.commons.DataSourceKey;
import com.hy.salon.sys.commons.DynamicRoutingDataSource;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

@Configuration
@AutoConfigureBefore(SalonApplicationConfig.class)
@ConfigurationProperties(prefix = "datasource")
@PropertySource(value={"classpath:config/settings/data-source.properties"})
public class DataSource extends org.apache.commons.dbcp2.BasicDataSource {
    private String myBatisMapperFileLocations;
    private String myBatisTypeAliasesPackage;
    private String myBatisConfigLocation;

/*
    @Bean
    @ConfigurationProperties(prefix = "datasource")//此处的"multiple.datasource.master"需要你在application.properties中配置，详细信息看下面贴出的application.properties文件。
    public javax.sql.DataSource dbMaster() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "multiple.datasource.other")
    public javax.sql.DataSource dbSlave1() {
        return DataSourceBuilder.create().build();
    }*/



/*    *//**
     * 核心动态数据源
     *
     * @return 数据源实例
     *//*
    @Bean
    public javax.sql.DataSource dynamicDataSource() {
        DynamicRoutingDataSource dataSource = new DynamicRoutingDataSource();
        dataSource.setDefaultTargetDataSource(dbMaster());
        Map<Object, Object> dataSourceMap = new HashMap<>(4);
        dataSourceMap.put(DataSourceKey.DB_MASTER, dbMaster());
        dataSourceMap.put(DataSourceKey.DB_SLAVE1, dbSlave1());
        dataSource.setTargetDataSources(dataSourceMap);
        return dataSource;
    }*/

    public String getMyBatisConfigLocation() {
        return myBatisConfigLocation;
    }

    public  void setMyBatisConfigLocation(String myBatisConfigLocation){
        this.myBatisConfigLocation = myBatisConfigLocation;
    }

    public String getMyBatisTypeAliasesPackage() {
        return myBatisTypeAliasesPackage;
    }

    public void setMyBatisTypeAliasesPackage(String myBatisTypeAliasesPackage) {
        this.myBatisTypeAliasesPackage = myBatisTypeAliasesPackage;
    }

    public String getMyBatisMapperFileLocations() {
        return myBatisMapperFileLocations;
    }

    public void setMyBatisMapperFileLocations(String myBatisMapperFileLocations) {
        this.myBatisMapperFileLocations = myBatisMapperFileLocations;
    }

}
