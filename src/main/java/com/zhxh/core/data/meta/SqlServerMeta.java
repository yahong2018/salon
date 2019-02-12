package com.zhxh.core.data.meta;

import com.zhxh.core.data.EntitySqlMeta;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class SqlServerMeta extends EntitySqlMeta {
    @Override
    public String buildSelectByPageSql(Map listMap, boolean isCount) {
        String fields;
        if(isCount){
            fields="1 as temp_field";
        }else {
            fields= StringUtils.join(this.getColumns().toArray(), ",");
        }
        StringBuffer buffer = new StringBuffer("select ").append(fields).append("\n")
                .append(" from (\n")
                .append("      select row_number()over(order by tempcolumn)temprownumber, ").append(fields).append("\n")
                .append("         from (\n")
                .append("             select top (#{start} + #{limit}) tempcolumn=0, ").append(fields).append("\n")
                .append("               from (").append(this.getTableName()).append("\n")
                .append(this.getSqlSelect()).append("\n")
                .append("                   )t");

        map2StringBuffer(listMap, buffer);

        buffer.append("\n        )tt\n")
                .append("     )ttt\n")
                .append(" where temprownumber>#{start}");

        if(isCount) {
            buffer.insert(0, "select count(*) from (");
            buffer.append(")cnt_table");
        }

        return buffer.toString();
    }
}
