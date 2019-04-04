package com.hy.salon.basic.common.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class ServletRequestParamsHelper {

    public String getJsonFromString(String[] argNames, Object[] args) {
        JSONObject argJson = new JSONObject();
        if (argNames != null) {
            for (int idx = 0; idx < argNames.length; idx++) {
                Object arg = args[idx];
                String argName = argNames[idx];
                JSONObject res = new JSONObject();
                if (arg instanceof HttpServletRequest) {
                    HttpServletRequest httpServletRequest = ((HttpServletRequest)arg);
                    res.putAll(httpServletRequest.getParameterMap());
                    if (arg instanceof ServletRequestReBox.MyHttpServletRequestWrapper) {
                        res.putAll(ServletRequestReBox.getParams(httpServletRequest));
                    }
                    argJson.put(argName, res);
                } else if (arg instanceof HttpServletResponse) {
                    argJson.put(argName, arg.toString());
                } else if (arg instanceof HttpSession) {
                    argJson.put(argName, arg.toString());
                } else {
                    argJson.put(argName, arg);
                }
            }
        }

        return JSON.toJSONString(argJson, SerializerFeature.IgnoreErrorGetter);
    }

}
