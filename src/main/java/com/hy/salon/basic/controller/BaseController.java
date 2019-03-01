package com.hy.salon.basic.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.hy.salon.basic.fastjson.filter.CommonFilter;


public abstract class BaseController {

    private static ValueFilter[] jsonValFilter = new ValueFilter[]{new CommonFilter()};

    protected void write(String content, HttpServletResponse resp) {

        try {
            resp.setCharacterEncoding("UTF-8");
            PrintWriter pw = resp.getWriter();
            pw.write(content);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    protected void write(JSONObject json, HttpServletResponse resp) {

        String content = JSONObject.toJSONString(json, jsonValFilter, SerializerFeature.WriteNullStringAsEmpty);
        resp.setContentType("application/json");
        write(content, resp);
    }

    /**
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {

        return null == str || str.isEmpty() ? true : false;
    }




}
