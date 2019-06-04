package com.hy.salon.sys.config;

import com.github.miemiedev.mybatis.callable.CallableConvertInterceptor;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import com.zhxh.core.data.*;
import com.zhxh.core.data.meta.MySqlMetaCreator;
import com.zhxh.core.utils.Logger;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Properties;

@Configuration
public class DataConfig {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private ResultTypeInterceptor resultTypeInterceptor;

    @Autowired
    private UpdateInterceptor updateInterceptor;

    @Autowired
    private PageHelper  pageHelper;

    @Autowired
    private CallableConvertInterceptor callableConvertInterceptor;

    @Bean
    public DataSource createDataSource(){
        return new DataSource();
    }

    @Bean
    public ResultTypeInterceptor createResultTypeInterceptor(){
        return new ResultTypeInterceptor();
    }

    @Bean
    public PageHelper getPageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties props = new Properties();
        props.setProperty("reasonable", "true");
        props.setProperty("supportMethodsArguments", "true");
        props.setProperty("returnPageInfo", "check");
        props.setProperty("params", "count=countSql");
        pageHelper.setProperties(props);
        return pageHelper;
    }

    @Bean
    public UpdateInterceptor createUpdateInterceptor(){return new UpdateInterceptor();}

    @Bean
    public CallableConvertInterceptor createCallableConvertInterceptor() {
        return new CallableConvertInterceptor();
    }

    @Bean
    public DataSourceTransactionManager createTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionFactoryBean createSqlSessionFactory() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            String mapperLocation = dataSource.getMyBatisMapperFileLocations();
            Resource[] mapperLocations = resolver.getResources(mapperLocation);
            sqlSessionFactoryBean.setMapperLocations(mapperLocations);
//                       Resource[] mapperLocations = resolver.getResources(mapperLocation);
//            sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:config/mapper/**/*.xml"));


            Resource configLocation = resolver.getResource(dataSource.getMyBatisConfigLocation());

            //Resource configLocation =  resolver.getResource("classpath:config/mybatis.xml");
            sqlSessionFactoryBean.setConfigLocation(configLocation);
        } catch (Exception e) {
            Logger.info("配置 DataSource的 mapperLocation/configLocation出现问题:" + e.getMessage());
        }
        PageInterceptor  pageHelper = new PageInterceptor();
        Properties props = new Properties();
        props.setProperty("reasonable", "true");
        props.setProperty("supportMethodsArguments", "true");
        props.setProperty("returnPageInfo", "check");
        props.setProperty("params", "count=countSql");
        pageHelper.setProperties(props);
        sqlSessionFactoryBean.setDataSource(dataSource);
        Interceptor[] interceptors = new Interceptor[]{pageHelper,this.resultTypeInterceptor, this.callableConvertInterceptor,this.updateInterceptor};
        sqlSessionFactoryBean.setPlugins(interceptors);
        sqlSessionFactoryBean.setTypeAliasesPackage(dataSource.getMyBatisTypeAliasesPackage());
        //sqlSessionFactoryBean.setTypeAliasesPackage("com.zhxh.**.entity");
        return sqlSessionFactoryBean;
    }


    @Bean(name = "sqlSession")
    @Scope("prototype")
    public SqlSessionTemplate createSqlSession(SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception {
        SqlSessionFactory factory = sqlSessionFactoryBean.getObject();
        return new SqlSessionTemplate(factory, ExecutorType.SIMPLE);
    }

    @Bean(name = "validator")
    public LocalValidatorFactoryBean createValidator() {
        LocalValidatorFactoryBean result = new LocalValidatorFactoryBean();
        result.setProviderClass(org.hibernate.validator.HibernateValidator.class);

        return result;
    }

    @Bean(name="sqlMetaCreator")
    SqlMetaCreator createSqlMetaCreator(){
        return new MySqlMetaCreator();
    }
}
