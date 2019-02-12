package com.zhxh.core.data;

import com.zhxh.core.data.meta.annotation.AutoGenerationType;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import com.zhxh.core.env.SysEnv;
import com.zhxh.core.exception.BusinessException;
import com.zhxh.core.utils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;

import static com.zhxh.core.exception.ErrorCode.*;
import static com.zhxh.core.exception.ExceptionHelper.throwException;

public class BaseDAO {
    public static final int DATA_OPERATION_INSERT = 1;
    public static final int DATA_OPERATION_UPDATE = 2;
    public static final int DATA_OPERATION_DELETE = 3;

    @Resource(name = "sqlHelper")
    protected SqlHelper sqlHelper;
    @Resource(name = "validator")
    private Validator validator;

    @Resource(name = "entitySqlMetaFactory")
    private EntitySqlMetaFactory entitySqlMetaFactory;

    public BaseDAO() {
    }

    public List getAll(Class clazz) {
        return this.getList(clazz, null);
    }

    public List getList(Class clazz, Map listMap) {
        return this.getList(clazz, listMap, null);
    }

    public List getList(Class clazz, Map listMap, Map parameter) {
        return sqlHelper.getList(clazz, listMap, parameter);
    }

    public List getPageList(Class clazz, Map listMap, Map parameters) {
        return sqlHelper.getPageList(clazz, listMap, parameters);
    }

    public int getPageListCount(Class clazz, Map listMap, Map parameters) {
        return sqlHelper.getPageListCount(clazz, listMap, parameters);
    }

    public String getIdLabel(Class clazz) {
        return this.getPropertyLabel(this.getPropertyFullName(clazz, this.entitySqlMetaFactory.getEntitySqlMeta(clazz).getKeyProperty()));
    }

    private final static String EMPTY_UUID = new UUID(0, 0).toString();

    public final int insert(Object item) throws BusinessException {
        if (item instanceof EntityObject) {
            EntityObject theItem = (EntityObject) item;
            DataTableConfiguration tableConfiguration = item.getClass().getAnnotation(DataTableConfiguration.class);
            if (tableConfiguration.keyCreateType() == AutoGenerationType.UUID) {
                if (EMPTY_UUID.equals(theItem.getRecordId()) || StringUtils.isEmpty(theItem.getRecordId().toString())) {
                    theItem.setRecordId(UUID.randomUUID().toString());
                }
            }
        }

        if (item instanceof TraceableEntity) {
            TraceableEntity.fillCreateInfo((TraceableEntity) item);
        }

        this.verify(item, DATA_OPERATION_INSERT);

        return this.doInternalInsert(item);
    }

    public final int update(Object item) {
        if (item instanceof TraceableEntity) {
            TraceableEntity.fillUpdateInfo((TraceableEntity) item);
        }
        this.verify(item, DATA_OPERATION_UPDATE);

        return this.doInternalUpdate(item);
    }


    public final int delete(Object item) {
        if (!this.exists(item)) {
            String keyProperty = getKeyProperty(item.getClass());
            String idLabel = this.getIdLabel(item.getClass());
            Object keyValue = BeanUtils.getValue(item, keyProperty);

            throwException(ERROR_DATA_NOT_EXISTS, idLabel, keyValue);
        }
        this.verify(item, DATA_OPERATION_DELETE);

        return this.doInternalDelete(item);
    }

    public int executeNoneQuery(String sql, Map parameters) {
        return this.sqlHelper.executeNoneQuery(sql, parameters);
    }

    public boolean exists(Object item) {
        EntitySqlMeta meta = this.entitySqlMetaFactory.getEntitySqlMeta(item.getClass());
        Object id = BeanUtils.getValue(item, meta.getKeyProperty());
        Object dbItem = this.getById(item.getClass(), id);
        return dbItem != null;
    }

    protected String getKeyProperty(Class clazz) {
        EntitySqlMeta meta = this.entitySqlMetaFactory.getEntitySqlMeta(clazz);
        return meta.getKeyProperty();
    }

    public final Object getById(Class clazz, Object id) {
        EntitySqlMeta meta = this.entitySqlMetaFactory.getEntitySqlMeta(clazz);
        String keyAssign = meta.getFieldsAssigns().get(meta.getKeyColumn());
        Map listMap = new HashMap();
        listMap.put("where", keyAssign);
        String sql = meta.buildSelectSql(listMap);
        Map parameters = new HashMap();
        parameters.put(meta.getKeyProperty(), id);
        Object dbItem = sqlHelper.executeScalar(clazz, sql, parameters);
        return dbItem;
    }

    public <T> void verifyBean(T item, int operationCode) throws BusinessException {
        Set<ConstraintViolation<T>> errorSet = validator.validate(item);
        StringBuffer msgBuffer = new StringBuffer();
        for (ConstraintViolation<T> error : errorSet) {
            String fieldName = this.getPropertyFullName(item.getClass(), error.getPropertyPath().toString());
            String fieldLabel = this.getPropertyLabel(fieldName);

            msgBuffer.append(fieldLabel).append(error.getMessage()).append("\n");
        }
        if (errorSet.size() > 0) {
            throw new BusinessException(msgBuffer.toString());
        }
    }

    protected String getPropertyFullName(Class clazz, String shortPropertyName) {
        if (TraceableEntity.class.isAssignableFrom(clazz)) {
            StringBuffer traceablePropertyShortName = new StringBuffer(shortPropertyName.substring(clazz.getName().length()));
            traceablePropertyShortName.replace(0, 1, traceablePropertyShortName.substring(0, 1).toUpperCase());
            String theShortPropertyName = traceablePropertyShortName.toString();
            if (TraceableEntity.internal_fields.contains(theShortPropertyName)) {
                return TraceableEntity.class.getCanonicalName() + "." + theShortPropertyName;
            }
        }
        return clazz.getCanonicalName() + "." + shortPropertyName;
    }

    public final String getPropertyLabel(String property) {
        String fieldLabel = SysEnv.getFieldLabel(property);
        return fieldLabel;
    }

    public void verify(Object item, int operationCode) throws BusinessException {
        Class clazz = item.getClass();

        if (operationCode == DATA_OPERATION_INSERT || operationCode == DATA_OPERATION_UPDATE) {
            this.verifyBean(item, operationCode);

            EntitySqlMeta meta = this.entitySqlMetaFactory.getEntitySqlMeta(clazz);
            if (meta.getUniqueFields().size() > 0) {
                if (!this.checkUnique(item)) {
                    StringBuffer buffer = new StringBuffer();
                    for (String field : meta.getUniqueFields()) {
                        String fieldFullName = this.getPropertyFullName(clazz, field);
                        String fieldLabel = this.getPropertyLabel(fieldFullName);
                        buffer.append("\n ").append(fieldLabel).append("=").append(BeanUtils.getValue(item, field)).append(" ").append("\n 或");
                    }
                    buffer.deleteCharAt(buffer.length() - 1);
                    buffer.append(" 的数据已存在！");

                    throwException(ERROR_DATA_ALREADY_EXISTS, buffer.toString());
                }
            }
        }

        if (operationCode == DATA_OPERATION_INSERT) {
            if (this.exists(item)) {
                String keyProperty = getKeyProperty(clazz);
                Object keyValue = BeanUtils.getValue(item, keyProperty);
                String idLabel = this.getIdLabel(clazz);

                throwException(ERROR_DATA_ALREADY_EXISTS, idLabel, keyValue);
            }
        } else if (operationCode == DATA_OPERATION_UPDATE) {
            if (!this.exists(item)) {
                String keyProperty = getKeyProperty(item.getClass());
                Object keyValue = BeanUtils.getValue(item, keyProperty);
                String idLabel = this.getIdLabel(item.getClass());

                throwException(ERROR_DATA_NOT_EXISTS, idLabel, keyValue);
            }
        }

        if (operationCode == DATA_OPERATION_DELETE) {
            if (this.isTreeRoot(item)) {
                throwException(ERROR_ROOT_CANNOT_DELETE);
            }
        }
    }

    private boolean isTreeRoot(Object item) {
        Class clazz = item.getClass();
        EntitySqlMeta meta = this.entitySqlMetaFactory.getEntitySqlMeta(clazz);
        if (!meta.isTreeTable()) {
            return false;
        }
        Object dbItem = sqlHelper.executeScalar(clazz, meta.getGetTreeRootSql(), null);
        Object key1 = BeanUtils.getValue(item, meta.getKeyProperty());
        Object key2 = BeanUtils.getValue(dbItem, meta.getKeyProperty());

        return key1.equals(key2);
    }


    public boolean checkUnique(Object item) {
        Class clazz = item.getClass();
        EntitySqlMeta meta = this.entitySqlMetaFactory.getEntitySqlMeta(clazz);
        String sql = meta.getCheckUniqueSql();
        Map parameters = new HashMap();
        String key = meta.getKeyProperty();
        parameters.put(key, BeanUtils.getValue(item, key));
        for (String field : meta.getUniqueFields()) {
            parameters.put(field, BeanUtils.getValue(item, field));
        }
        parameters.put("resultType", Integer.class);
        int count = sqlHelper.executeScalar(clazz, sql, parameters);
        return count == 0;
    }

    protected int doInternalInsert(Object item) {
        return sqlHelper.insert(item);
    }

    protected int doInternalUpdate(Object item) {
        return sqlHelper.update(item);
    }

    protected int doInternalDelete(Object item) {
        return sqlHelper.delete(item);
    }

    public SqlHelper getSqlHelper() {
        return sqlHelper;
    }
}
