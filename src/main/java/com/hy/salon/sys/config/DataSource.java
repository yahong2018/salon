package com.hy.salon.sys.config;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@AutoConfigureBefore(SalonApplicationConfig.class)
@ConfigurationProperties(prefix = "datasource")
@PropertySource(value={"classpath:config/settings/data-source.properties"})
public class DataSource extends org.apache.commons.dbcp2.BasicDataSource {
    private String myBatisMapperFileLocations;
    private String myBatisTypeAliasesPackage;
    private String myBatisConfigLocation;

    public String getMyBatisConfigLocation() {
        return myBatisConfigLocation;
    }

    public void setMyBatisConfigLocation(String myBatisConfigLocation) {
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
