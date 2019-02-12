package com.zhxh.core.data.meta;

import com.zhxh.core.data.EntitySqlMeta;
import com.zhxh.core.data.SqlMetaCreator;

public class MySqlMetaCreator implements SqlMetaCreator {
    @Override
    public EntitySqlMeta createSqlMeta(Class<?> clazz) {
        return new MySqlMeta();
    }
}
