package com.zhxh.core.data;

import com.zhxh.core.utils.BeanUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Intercepts(
        @Signature(
                type = Executor.class,
                method = "update",
                args = {MappedStatement.class, Object.class}
        )
)
public class UpdateInterceptor extends MyBatisAbstractInterceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        final Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        if (args[1] instanceof Map) {
            Map parameterObject = (Map) args[1];

            Class itemClass = (Class) parameterObject.get("itemClass");
            if (itemClass != null) {
                if (!this.useGeneratedKeys) {
                    this.useGeneratedKeys = (boolean) parameterObject.get("useGeneratedKeys");
                }
                if (this.useGeneratedKeys) {
                    String keyProperty = parameterObject.get("keyProperty").toString();
                    args[0] = newMappedStatement(ms, itemClass, keyProperty);
                }
            }
        }

        return invocation.proceed();
    }


    public MappedStatement newMappedStatement(MappedStatement ms, Class itemClass, String keyProperty) {
        String id = ms.getId() + "_" + itemClass.getCanonicalName().replace(".", "_") + "_Insert";
        Configuration configuration = new Configuration();
        BeanUtils.copy(ms.getConfiguration(), configuration);
        configuration.setUseGeneratedKeys(true);
        MappedStatement.Builder builder = new MappedStatement.Builder(configuration, id, ms.getSqlSource(), SqlCommandType.INSERT);

        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyProperty(keyProperty);

        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());

        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());
        MappedStatement result = builder.build();

        return result;
    }
}
