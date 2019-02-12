package com.zhxh.core.data.meta;

import com.zhxh.core.data.EntitySqlMeta;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class OracleMeta extends EntitySqlMeta {
    @Override
    public String buildSelectByPageSql(Map listMap, boolean isCount) {
        if(isCount){
            return this.getSelectBytePageCountString(listMap);
        }

        return this.getSelectByPageSql(listMap);

        /*
        String fields;
        if(isCount){
            fields="1 as temp_field";
        }else {
            fields= StringUtils.join(this.getColumns().toArray(), ",");
        }
        StringBuffer buffer = new StringBuffer("select ").append(fields).append("\n")
                .append(" from (\n")
                .append("      select ROWNUM  RN,t.* ").append("\n")
                .append("         from (\n")
                .append("             select ").append(fields).append("\n")
                .append("               from ").append(this.getTableName()).append("\n");
        map2StringBuffer(listMap, buffer);

        buffer.append("\n        )t\n")
                .append("     )tt\n")
                .append(" where RN > #{start} and RN <= (#{start} + #{limit})");

        if(isCount) {
            buffer.insert(0, "select count(*) from (");
            buffer.append(")cnt_table");
        }

        return buffer.toString();
        */
    }

    private String getSelectByPageSql(Map listMap){
        String fields= StringUtils.join(this.getColumns().toArray(), ",");
        StringBuffer buffer = new StringBuffer("select ").append(fields).append("\n")
                .append(" from (\n")
                .append("      select ROWNUM  RN,t.* ").append("\n")
                .append("         from (\n")
                .append("             select ").append(fields).append("\n")
                .append("               from ").append(this.getTableName()).append("\n");
        map2StringBuffer(listMap, buffer);

        buffer.append("\n        )t\n")
                .append("     )tt\n")
                .append(" where RN > #{start} and RN <= (#{start} + #{limit})");
        return buffer.toString();
    }

    private String getSelectBytePageCountString(Map listMap){
        StringBuffer buffer=new StringBuffer("select count(*) from ").append(this.getTableName()).append("\n");
        map2StringBuffer(listMap,buffer);

        return buffer.toString();
    }


}
