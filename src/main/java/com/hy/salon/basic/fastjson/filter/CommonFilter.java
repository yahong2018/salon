package com.hy.salon.basic.fastjson.filter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.serializer.ValueFilter;

public class CommonFilter implements ValueFilter {

    private DecimalFormat df = new DecimalFormat("#########0.00");

    private SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//	private SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * @param object 对象
     * @param name 对象的字段的名称
     * @param value 对象的字段的值
     */
    @Override
    public Object process(Object object, String name, Object value) {

        if (null != value) {
            if(value instanceof BigDecimal) {

                return df.format(value);
            }

            if(value instanceof Date) {

                return sdfDateTime.format(value);
            }
        }

        return value;
    }

}
