package com.zhxh.core.data;

import com.zhxh.core.utils.Logger;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public abstract class EntityObject<K> implements Serializable {
    private static long serialVersionUID = 1L;

    private K recordId;
    public K getRecordId() {
        return recordId;
    }

    public void setRecordId(K recordId) {
        this.recordId = recordId;
    }

    private static final Map<Integer, Class> keyMap = new HashMap<>();

    private ParameterizedType getKeyParameterizedType(Class clazz) {
        if (clazz == null) {
            return null;
        }

        Type t = clazz.getGenericSuperclass();
        if (t instanceof ParameterizedType) {
            return (ParameterizedType) t;
        }

        return getKeyParameterizedType(clazz.getSuperclass());
    }

    synchronized Class getKeyClass() {
        int classHasCode = this.getClass().hashCode();
        if (!keyMap.containsKey(classHasCode)) {
            ParameterizedType t = this.getKeyParameterizedType(this.getClass());
            try {
                Class keyClazz = (Class<K>) t.getActualTypeArguments()[0];
                keyMap.put(classHasCode, keyClazz);
            } catch (Exception e) {
                Logger.error(e);
            }
        }
        return keyMap.get(classHasCode);
    }
}
