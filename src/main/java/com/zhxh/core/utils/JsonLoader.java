package com.zhxh.core.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.File;
import java.io.IOException;

public class JsonLoader {
    public static Object load(String fileName, Class clazz) {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            Resource jsonResource = resolver.getResource(fileName);
            File file = jsonResource.getFile();
            String jsonString = FileUtils.readFileToString(file, "utf8");

            Object result = JSONObject.parseObject(jsonString, clazz);
            return result;

        } catch (IOException e) {
            Logger.error("解析JSON失败：" + e.getMessage());
        }
        return null;
    }

    public static boolean write(String fileName, Object data) {
        String jsonString = JSONObject.toJSONString(data);
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    return false;
                }
            }
            FileUtils.write(file, jsonString, "utf8");
            return true;
        } catch (IOException e) {
            Logger.error("写入json文件'" + fileName + "'失败:" + e.getMessage());
        }

        return false;
    }
}
