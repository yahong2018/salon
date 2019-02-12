package com.zhxh.core.data;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.util.Map;
import java.util.Properties;

public abstract class MyBatisAbstractInterceptor implements Interceptor {
    public static final String DEFAULT_KEY = "resultType";
    protected String resultType = DEFAULT_KEY;
    protected boolean useGeneratedKeys = false;

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        String resultType = properties.getProperty("resultType");
        if (resultType != null && resultType.length() > 0) {
            this.resultType = resultType;
        }

        this.useGeneratedKeys = Boolean.valueOf(properties.getProperty("useGeneratedKeys"));
    }

    protected Class getResultType(Object parameterObject) {
        if (parameterObject == null) {
            return null;
        } else if (parameterObject instanceof Class) {
            return (Class) parameterObject;
        } else if (parameterObject instanceof Map) {
            if (((Map) (parameterObject)).containsKey(resultType)) {
                Object result = ((Map) (parameterObject)).get(resultType);
                return objectToClass(result);
            } else {
                return null;
            }
        } else {
            MetaObject metaObject = SystemMetaObject.forObject(parameterObject);
            Object result = metaObject.getValue(resultType);
            return objectToClass(result);
        }
    }

    private Class objectToClass(Object object) {
        if (object == null) {
            return null;
        } else if (object instanceof Class) {
            return (Class) object;
        } else if (object instanceof String) {
            try {
                return Class.forName((String) object);
            } catch (Exception e) {
                throw new RuntimeException("非法的全限定类名字符串:" + object);
            }
        } else {
            throw new RuntimeException("方法参数类型错误，" + resultType + " 对应的参数类型只能为 Class 类型或者为 类的全限定名称字符串");
        }
    }
}
