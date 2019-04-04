package com.hy.salon.basic.common;

import com.alibaba.fastjson.JSON;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Function;

public class DataFormat {

    private static Set<Function<String, Object>> functions = new HashSet<>();

    static {
        init();
    }

    private static void init() {
        functions.add(JSON::parse);
    }

    public static Object format(String data) {
        if (data == null) {
            return null;
        }
        Iterator<Function<String, Object>> functionsIterator = functions.iterator();
        while (functionsIterator.hasNext()) {
            try {
                return functionsIterator.next().apply(data);
            }catch (Exception e) {

            }
        }
        return data;
    }
}
