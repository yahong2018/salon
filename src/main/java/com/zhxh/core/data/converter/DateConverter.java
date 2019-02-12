package com.zhxh.core.data.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter extends BaseDateConverter<Date> {
    @Override
    protected Date doConvert(SimpleDateFormat dateFormat, String source) throws ParseException {
        return dateFormat.parse(source);
    }
}