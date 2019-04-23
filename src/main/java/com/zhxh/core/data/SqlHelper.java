package com.zhxh.core.data;

import com.github.pagehelper.Page;
import com.zhxh.core.data.meta.annotation.AutoGenerationType;
import com.zhxh.core.utils.BeanUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("sqlHelper")
public class SqlHelper {
    @Resource(name = "sqlSession")
    private SqlSessionTemplate sqlSession;

    @Resource(name = "entitySqlMetaFactory")
    private EntitySqlMetaFactory entitySqlMetaFactory;

    public SqlSessionTemplate getSqlSession() {
        return sqlSession;
    }

    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List executeList(Class<?> clazz, String sql, Map parameters) {
        Map map = this.WrapParameterMap(sql, parameters, clazz);
        return sqlSession.selectList(SQL_EXECUTE_COMMAND, map);
    }

    public int executeNoneQuery(String sql, Map parameters) {
        Map map = this.WrapParameterMap(sql, parameters, Integer.class);
        int result = sqlSession.update(SQL_EXECUTE_COMMAND, map);
        if (map.containsKey("useGeneratedKeys")) {
            String keyProperty = map.get("keyProperty").toString();
            parameters.put(keyProperty, map.get(keyProperty));
        }
        return result;
    }

    public <T> T executeScalar(Class<?> clazz, String sql, Map parameters) {
        Map map = this.WrapParameterMap(sql, parameters, clazz);
        return sqlSession.selectOne(SQL_EXECUTE_COMMAND, map);
    }

    public int getPageListCount(Class<?> clazz, Map listMap, Map parameters) {
        EntitySqlMeta meta = this.entitySqlMetaFactory.getEntitySqlMeta(clazz);
        String sql = meta.buildSelectByPageSql(listMap, true);
        Map<String, Object> map = fillPageParameters(listMap, parameters);
        map.put("resultType", Integer.class);

        return this.executeScalar(clazz, sql, map);
    }

    public Map<String, Object> fillPageParameters(Map listMap, Map parameters) {
        Map<String, Object> map = new HashMap<>();
        map.put("start", listMap.get("start"));
        map.put("limit", listMap.get("limit"));

        if (parameters != null && parameters.size() > 0) {
            map.putAll(parameters);
        }
        return map;
    }

    public List getPageList(Class<?> clazz, Map listMap, Map parameters) {
        EntitySqlMeta meta = this.entitySqlMetaFactory.getEntitySqlMeta(clazz);
        String sql = meta.buildSelectByPageSql(listMap, false);
        Map<String, Object> map = fillPageParameters(listMap, parameters);

        return this.executeList(clazz, sql, map);
    }




    public List getList(Class<?> clazz, Map listMap, Map parameters) {
        EntitySqlMeta meta = this.entitySqlMetaFactory.getEntitySqlMeta(clazz);
        String sql = meta.buildSelectSql(listMap);
        return this.executeList(clazz, sql, parameters);
    }

    public int insert(Object item) {
        Class itemClass = item.getClass();
        Map<String, Object> itemMap = BeanUtils.getValues(item);
        EntitySqlMeta meta = this.entitySqlMetaFactory.getEntitySqlMeta(itemClass);

        String insertSql = meta.getSqlInsert();
        if (meta.getDataTableConfigurationConfig().keyCreateType() == AutoGenerationType.AUTO_INCREMENT) {
            itemMap.put("itemClass", item.getClass());
            itemMap.put("useGeneratedKeys", true);
            itemMap.put("keyProperty", meta.getKeyProperty());
            Class keyFieldClass = ((EntityObject) item).getKeyClass();
            if (keyFieldClass == Long.class) {
                itemMap.put(meta.getKeyProperty(), -1L);
            } else {
                itemMap.put(meta.getKeyProperty(), -1);
            }
        }
        int result = this.executeNoneQuery(insertSql, itemMap);
        if (meta.getDataTableConfigurationConfig().keyCreateType() == AutoGenerationType.AUTO_INCREMENT) {
            Object keyValue = itemMap.get(meta.getKeyProperty());
            BeanUtils.setValue(item, meta.getKeyProperty(), keyValue);
        }

        return result;
    }


    public int insert(Object item, String sqlId) {
        return this.sqlSession.insert(sqlId, item);
    }

    public int update(Object item) {
        Map<String, Object> itemMap = BeanUtils.getValues(item);
        EntitySqlMeta meta = this.entitySqlMetaFactory.getEntitySqlMeta(item.getClass());
        String updateSql = meta.getSqlUpdate();

        return this.executeNoneQuery(updateSql, itemMap);
    }

    public int update(Object item, String sqlId) {
        return this.sqlSession.update(sqlId, item);
    }

    public int delete(Object item) {
        EntitySqlMeta meta = this.entitySqlMetaFactory.getEntitySqlMeta(item.getClass());
        Object keyValue = BeanUtils.getValue(item, meta.getKeyProperty());
        Map<String, Object> itemMap = new HashMap<>();
        itemMap.put(meta.getKeyProperty(), keyValue);
        String deleteSql = meta.getSqlDeleteById();

        return this.executeNoneQuery(deleteSql, itemMap);
    }

    public int delete(Object item, String sqlId) {
        return this.sqlSession.delete(sqlId, item);
    }

    private Map WrapParameterMap(String sql, Map parameters, Class<?> clazz) {
        Map map = new HashMap();
        map.put("sql", sql);
        map.put("resultType", clazz);
        if (parameters != null && parameters.size() > 0) {
            map.putAll(parameters);
        }
        return map;
    }


    protected final static String SQL_EXECUTE_COMMAND = "com.zhxh.core.data.SqlHelper.executeCommand";
}


