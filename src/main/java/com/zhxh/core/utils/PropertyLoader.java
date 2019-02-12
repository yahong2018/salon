package com.zhxh.core.utils;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyLoader {
    public static Map<String, String> getPropertyMap(String file) {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Map<String, String> propertyMap = new HashMap();
        try {
            Resource[] dataTableResources = resolver.getResources(file);
            fillPropertyMap(propertyMap, dataTableResources);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertyMap;
    }

    private static void fillPropertyMap(Map<String, String> propertyMap, Resource[] dataTableResources) throws IOException {
        for (Resource resource : dataTableResources) {
            Properties mapping = PropertiesLoaderUtils.loadProperties(resource);
            for (Object key : mapping.keySet()) {
                String keyStr = key.toString();
                String value = mapping.getProperty(keyStr);
                propertyMap.put(keyStr, value);
            }
        }
    }
}
