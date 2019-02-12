package com.zhxh.core.data.converter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimestampConverter extends BaseDateConverter<Timestamp> {
    @Override
    protected Timestamp doConvert(SimpleDateFormat dateFormat, String source) throws ParseException {
        return new Timestamp(dateFormat.parse(source).getTime());
    }
}
