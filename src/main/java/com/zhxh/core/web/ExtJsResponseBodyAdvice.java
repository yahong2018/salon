package com.zhxh.core.web;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExtJsResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (!"POST".equals(request.getMethod().toString().toUpperCase())) {
            return body;
        }
        if (!selectedContentType.equals(MediaType.APPLICATION_JSON) && !MediaType.APPLICATION_JSON_UTF8.equals(selectedContentType))
        {
            return body;
        }

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", body);

        return result;
    }
}
