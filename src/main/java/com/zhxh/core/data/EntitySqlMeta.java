package com.zhxh.core.data;

import com.zhxh.core.data.meta.annotation.*;
import com.zhxh.core.utils.ClassUtils;
import com.zhxh.core.utils.StringUtilsExt;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;

import java.lang.reflect.Field;
import java.util.*;

public abstract class EntitySqlMeta {
    public void initSql() {
        this.initDefaultInsertSql();
        this.initDefaultDeleteSql();
        this.initDefaultUpdateSql();
        this.initDefaultSelectSql();
        this.initCheckUniqueSql();

        this.initIsTreeTable();
        this.initGetTreeRootSql();
    }

    private void initDefaultInsertSql() {
        Object[] columns = this.columns.toArray();
        Object[] properties = this.properties.toArray();
        if(this.dataTableConfigurationConfig!=null && dataTableConfigurationConfig.keyCreateType()==AutoGenerationType.AUTO_INCREMENT) {
            List<String> tmpColumns = new ArrayList<String>();
            tmpColumns.addAll(this.columns);
            tmpColumns.remove(this.keyColumn);
            columns = tmpColumns.toArray();

            List<String> tmpProperties = new ArrayList<>();
            tmpProperties.addAll(this.properties);
            tmpProperties.remove(this.keyProperty);
            properties = tmpProperties.toArray();
        }
        String sql = new StringBuffer("insert into ").append(tableName)
                .append(" (")
                .append(StringUtils.join(columns, ","))
                .append(") ")
                .append("values (")
                .append(StringUtilsExt.joinWrap(properties, ",", "#{", "}"))
                .append(")").toString();
        this.sqlInsert = sql;
    }

    private void initDefaultDeleteSql() {
        StringBuffer buffer = new StringBuffer("delete from ").append(tableName);
        this.sqlDeleteAll = buffer.toString();
        buffer.append(" where ").append(this.keyColumn).append("=").append(this.propertyExprs.get(this.keyProperty));

        this.sqlDeleteById = buffer.toString();
    }

    private void initDefaultUpdateSql() {
        String fieldAssigns = StringUtils.join(fieldsAssigns.values().toArray(), ",");
        StringBuffer buffer = new StringBuffer("update ").append(tableName)
                .append(" set ").append(fieldAssigns)
                .append(" where ").append(this.keyColumn).append("=").append(this.propertyExprs.get(this.keyProperty));

        this.sqlUpdate = buffer.toString();
    }

    private void initDefaultSelectSql() {
        StringBuffer buffer = new StringBuffer("select ")
                .append(StringUtils.join(columns.toArray(), ","))
                .append(" from ")
                .append(tableName);

        this.sqlSelect = buffer.toString();
    }

    public String buildSelectSql(Map listMap) {
        StringBuffer buffer = new StringBuffer(this.sqlSelect);
        if (listMap != null) {
            map2StringBuffer(listMap, buffer);
        }

        return buffer.toString();
    }

    protected void map2StringBuffer(Map listMap, StringBuffer buffer) {
        String where = (String) listMap.get("where");
        if (StringUtils.isNotEmpty(where)) {
            buffer.append(" where ").append(where).append("\n");
        }
        String orderBy = (String) listMap.get("orderBy");
        if (StringUtils.isNotEmpty(orderBy)) {
            buffer.append(" order by ").append(orderBy).append(" ");
        }
        String sortDir = (String) listMap.get("sortDir");
        if (StringUtils.isNotEmpty(sortDir)) {
            buffer.append(sortDir);
        }
    }

    public String buildDeleteByWhereSql(String where) {
        if (StringUtils.isEmpty(where)) {
            return "";
        }

        return new StringBuffer(this.sqlDeleteAll).append(" ").append(where).toString();
    }

    private void initCheckUniqueSql() {
        StringBuffer buffer = new StringBuffer("select count(*) from ").append(tableName)
                .append("  where ")
                .append(this.keyColumn).append("<>").append(this.propertyExprs.get(this.keyProperty))
                .append(" and (");
        int i = 0;
        for (String property : this.uniqueFields) {
            String col = this.propertyColumns.get(property);
            buffer.append(this.fieldsAssigns.get(col));

            if (i > 0) {
                buffer.append(" or ");
            }
            i++;
        }
        buffer.append(")");

        this.checkUniqueSql = buffer.toString();
    }

    private void initGetTreeRootSql() {
        StringBuffer buffer = new StringBuffer(this.getSqlSelect())
                .append("  where ")
                .append(this.keyColumn).append("=").append(this.propertyColumns.get(this.parentKeyField));

        this.getTreeRootSql = buffer.toString();
    }

    public abstract String buildSelectByPageSql(Map listMap, boolean isCount);

    private String tableName;
    private String keyProperty;
    private String keyColumn;
    private String sqlInsert;
    private String sqlDeleteById;
    private String sqlUpdate;
    private String sqlSelect;
    private String sqlDeleteAll;
    private String checkUniqueSql;
    private String getTreeRootSql;

    private ResultMap resultMap;
    private boolean treeTable;
    private String parentKeyField;
    private DataTableConfiguration dataTableConfigurationConfig;

    private List<String> columns = new ArrayList<>();
    private List<String> properties = new ArrayList<>();
    private List<String> uniqueFields = new ArrayList<>();
    private Map<String, String> propertyColumns = new HashMap<>();
    private Map<String, String> fieldsAssigns = new HashMap<>();
    private Map<String, String> propertyExprs = new HashMap<>();

    public DataTableConfiguration getDataTableConfigurationConfig() {
        return dataTableConfigurationConfig;
    }

    public String getSqlDeleteAll() {
        return sqlDeleteAll;
    }

    public void setSqlDeleteAll(String sqlDeleteAll) {
        this.sqlDeleteAll = sqlDeleteAll;
    }

    public String getTableName() {
        return tableName;
    }

    public String getKeyColumn() {
        return keyColumn;
    }

    public String getKeyProperty() {
        return keyProperty;
    }

    public String getSqlInsert() {
        return sqlInsert;
    }

    public String getSqlDeleteById() {
        return sqlDeleteById;
    }

    public String getSqlUpdate() {
        return sqlUpdate;
    }

    public String getSqlSelect() {
        return sqlSelect;
    }

    public boolean isTreeTable() {
        return treeTable;
    }

    public String getParentKeyField() {
        return parentKeyField;
    }

    public String getGetTreeRootSql() {
        return getTreeRootSql;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setKeyColumn(String keyColumn) {
        this.keyColumn = keyColumn;
    }

    public void setKeyProperty(String keyProperty) {
        this.keyProperty = keyProperty;
    }

    public void setSqlInsert(String sqlInsert) {
        this.sqlInsert = sqlInsert;
    }

    public void setSqlUpdate(String sqlUpdate) {
        this.sqlUpdate = sqlUpdate;
    }

    public void setSqlDeleteById(String sqlDelete) {
        this.sqlDeleteById = sqlDelete;
    }

    public void setSqlSelect(String sqlSelect) {
        this.sqlSelect = sqlSelect;
    }

    public ResultMap getResultMap() {
        return resultMap;
    }

    public Map<String, String> getFieldsAssigns() {
        return fieldsAssigns;
    }

    public Map<String, String> getPropertyExprs() {
        return propertyExprs;
    }

    public List<String> getColumns() {
        return columns;
    }

    public List<String> getProperties() {
        return properties;
    }

    public List<String> getUniqueFields() {
        return uniqueFields;
    }

    public Map<String, String> getPropertyColumns() {
        return propertyColumns;
    }

    public String getCheckUniqueSql() {
        return checkUniqueSql;
    }

    public void setCheckUniqueSql(String checkUniqueSql) {
        this.checkUniqueSql = checkUniqueSql;
    }


    public void setResultMap(ResultMap resultMap) {
        this.propertyColumns.clear();
        this.columns.clear();
        this.properties.clear();
        this.propertyExprs.clear();
        this.fieldsAssigns.clear();
        this.uniqueFields.clear();

        this.resultMap = resultMap;
        for (ResultMapping mapping : resultMap.getPropertyResultMappings()) {
            String column = mapping.getColumn();
            String property = mapping.getProperty();
            if (this.columns.contains(column)) {
                continue;
            }

            this.propertyColumns.put(property, column);
            this.columns.add(column);
            this.properties.add(property);
            this.propertyExprs.put(property, "#{" + property + "}");

            this.fieldsAssigns.put(column, column + "=#{" + property + "}");
        }

        Class checkUniqueType = CheckUnique.class;
        Class clazz = resultMap.getType();
        Object[] fieldsList = Arrays.stream(ClassUtils.getDeclaredFields(clazz)).filter(x ->
                x.getAnnotation(checkUniqueType) != null
        ).toArray();
        for (int i = 0; i < fieldsList.length; i++) {
            Field field = (Field) fieldsList[i];
            this.uniqueFields.add(field.getName());
        }

        if (StringUtils.isEmpty(this.keyProperty)) {
            if (resultMap.getIdResultMappings().size() > 0) {
                this.keyColumn = resultMap.getIdResultMappings().get(0).getColumn();
                this.keyProperty = resultMap.getIdResultMappings().get(0).getProperty();
                this.dataTableConfigurationConfig = (DataTableConfiguration) clazz.getAnnotation(DataTableConfiguration.class);

//                this.propertyExprs.remove(this.keyProperty);
//                this.fieldsAssigns.remove(this.keyColumn);
            }
        }
    }

    private void initIsTreeTable() {
        Class clazz = resultMap.getType();
        this.treeTable = clazz.getAnnotation(TreeTable.class) != null;

        if (this.treeTable) {
            this.parentKeyField = Arrays.stream(ClassUtils.getDeclaredFields(clazz)).filter(x ->
                    x.getAnnotation(TreeTableParentKey.class) != null
            ).findFirst().get().getName();
        }
    }

    public void copyFrom(EntitySqlMeta meta) {
        this.tableName = meta.tableName;
        this.keyProperty = meta.keyProperty;
        this.keyColumn = meta.keyColumn;
        this.sqlInsert = meta.sqlInsert;
        this.sqlDeleteAll = meta.sqlDeleteAll;
        this.sqlUpdate = meta.sqlUpdate;
        this.sqlSelect = meta.sqlSelect;
        this.sqlDeleteById = meta.sqlDeleteById;
        this.checkUniqueSql = meta.checkUniqueSql;
        this.getTreeRootSql = meta.getTreeRootSql;
        this.resultMap = meta.resultMap;
        this.treeTable = meta.treeTable;
        this.parentKeyField = meta.parentKeyField;
        this.dataTableConfigurationConfig = meta.dataTableConfigurationConfig;
        this.columns.addAll(meta.columns);
        this.properties.addAll(meta.properties);
        this.uniqueFields.addAll(meta.uniqueFields);

        Iterator<Map.Entry<String, String>> iterator = meta.propertyColumns.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> item = iterator.next();
            this.propertyColumns.put(item.getKey(), item.getValue());
        }

        iterator = meta.fieldsAssigns.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> item = iterator.next();
            this.fieldsAssigns.put(item.getKey(), item.getValue());
        }

        iterator = meta.propertyExprs.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> item = iterator.next();
            this.propertyExprs.put(item.getKey(), item.getValue());
        }
    }
}
