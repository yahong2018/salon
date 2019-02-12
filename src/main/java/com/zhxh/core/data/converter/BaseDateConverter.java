package com.zhxh.core.data.converter;

import com.zhxh.core.utils.Logger;
import lombok.Setter;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Setter
public abstract class BaseDateConverter<T> implements Converter<String, T> {
    private List<String> patternList;

    public T convert(String source) {
        for (String pattern : this.patternList) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            dateFormat.setLenient(false);
            try {
                return doConvert(dateFormat,source);
            } catch (ParseException e) {
                Logger.debug("日期格式解析错误:" + pattern);
            }
        }
        return null;
    }

    protected abstract T doConvert(SimpleDateFormat dateFormat, String source) throws ParseException;
}
