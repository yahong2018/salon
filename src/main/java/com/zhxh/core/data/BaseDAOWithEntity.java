package com.zhxh.core.data;

import com.zhxh.core.exception.BusinessException;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zhxh.core.exception.ErrorCode.ERROR_DATA_NOT_EXISTS;
import static com.zhxh.core.exception.ExceptionHelper.throwException;

public class BaseDAOWithEntity<T /*extends EntityObject*/> extends BaseDAO implements Generic<T> {

    @Resource(name="entitySqlMetaFactory")
    private EntitySqlMetaFactory entitySqlMetaFactory;

    protected Class clazz;

    public BaseDAOWithEntity() {
        Type t = this.getClass().getGenericSuperclass();
        this.clazz = (Class<T>) ((ParameterizedType) t).getActualTypeArguments()[0];
    }

    public List getAll() {
        return super.getAll(this.clazz);
    }

    public List getList(Map listMap) {
        return this.getList(listMap, null);
    }

    public List getList(Map listMap, Map parameter) {
        return super.getList(clazz,listMap, parameter);
    }

    public int getPageListCount(Map map, Map parameters) {
        return super.getPageListCount(this.clazz,map,parameters);
    }

    public List getPageList(Map map, Map parameters) {
        return super.getPageList(clazz,map, parameters);
    }
    public int getPageListCount(Map listMap) {
        return this.getPageListCount(listMap,null);
    }


    public final T getById(Object id) {
        return (T) this.getById(this.clazz, id);
    }

    public T getOne(String where, Map parameters) {
        EntitySqlMeta meta = this.entitySqlMetaFactory.getEntitySqlMeta(clazz);
        Map listMap = new HashMap();
        listMap.put("where",where);
        String sql = meta.buildSelectSql(listMap);
        T dbItem = sqlHelper.executeScalar(clazz, sql, parameters);
        return dbItem;
    }

    public final String getIdLabel() {
        return super.getIdLabel(this.clazz);
    }

    public final T verifyExistsById(Object id) {
        T dbItem = this.getById(id);
        if (dbItem == null) {
            String idLabel = this.getIdLabel();
            throwException(ERROR_DATA_NOT_EXISTS, idLabel, id);
        }
        return dbItem;
    }
    
    public final int deleteByWhere(String where,Map parameters) {
    	EntitySqlMeta meta = this.entitySqlMetaFactory.getEntitySqlMeta(this.clazz);
    	String deleteSql = meta.buildDeleteByWhereSql(where);
    	
    	return super.executeNoneQuery(deleteSql, parameters);
    }
    
    public final int deleteById(Object id) {
   	    T dbItem = this.verifyExistsById(id);
   	    return this.delete(dbItem);
    }


    @Override
    public final void verify(Object item, int operationCode) throws BusinessException {
        super.verify(item, operationCode);
        this.doVerify((T) item, operationCode);
    }


    protected void doVerify(T item, int operationCode) {
    }

    @Override
    protected final int doInternalInsert(Object item) {
        return this.doInsert((T)item);
    }

    @Override
    protected final int doInternalUpdate(Object item) {
        return this.doUpdate((T) item);
    }

    @Override
    protected final int doInternalDelete(Object item) {
        return this.doDelete((T) item);
    }

    protected int doInsert(T item) {
        return sqlHelper.insert(item);
    }

    protected int doUpdate(T item) {
        return sqlHelper.update(item);
    }

    protected int doDelete(T item) {
        return sqlHelper.delete(item);
    }

}
