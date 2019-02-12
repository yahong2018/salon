package com.zhxh.core.data;

import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import com.zhxh.core.env.SysEnv;
import org.apache.ibatis.mapping.ResultMap;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component("entitySqlMetaFactory")
public class EntitySqlMetaFactory {
    public void init() {
//        for (Map.Entry<String, String> entry : SysEnv.getEntityTableMappingHolder().entrySet()) {
//            String className = entry.getKey();
//            String tableName = entry.getValue();
//            try {
//                Class clazz = Class.forName(className);
//                EntitySqlMetaFactory.initMeta(clazz, tableName);
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public EntitySqlMeta getEntitySqlMeta(Class<?> clazz) {
        if (clazz == null) {
            return null;
        }

        String key = clazz.getCanonicalName();
        if (!metaMap.containsKey(key)) {
            Class supperClass = clazz.getSuperclass();
            EntitySqlMeta parentMeta = getEntitySqlMeta(supperClass);
            if (parentMeta != null) {
                EntitySqlMeta selfMeta = this.getSqlMetaCreator().createSqlMeta(clazz);
                selfMeta.copyFrom(parentMeta);
                metaMap.put(key, selfMeta);

                return selfMeta;
            }

            DataTableConfiguration dataTableConfiguration = clazz.getAnnotation(DataTableConfiguration.class);
            if (dataTableConfiguration == null) {
                return null;
            }
            String tableName = dataTableConfiguration.value();

            return initMeta(clazz, tableName);
        }
        return metaMap.get(key);
    }

    public synchronized  EntitySqlMeta initMeta(Class<?> clazz, String tableName) {
        String key = clazz.getCanonicalName();
        EntitySqlMeta meta = this.getSqlMetaCreator().createSqlMeta(clazz);
        meta.setTableName(tableName);
        ResultMap resultMap = this.getSqlSession().getConfiguration().getResultMap(key);
        meta.setResultMap(resultMap);
        meta.initSql();

        metaMap.put(key, meta);

        return meta;
    }

    public void registerSqlMeta(Class<?> clazz, EntitySqlMeta meta) {
        String key = clazz.getCanonicalName();
        metaMap.put(key, meta);
    }

    @Resource(name="sqlSession")
    private  SqlSessionTemplate sqlSession;
    @Resource(name="sqlMetaCreator")
    private  SqlMetaCreator sqlMetaCreator;
    protected final static Map<String, EntitySqlMeta> metaMap = new HashMap<>();

    public  SqlMetaCreator getSqlMetaCreator() {
        return sqlMetaCreator;
    }

    public  SqlSessionTemplate getSqlSession() {
        return sqlSession;
    }

}
