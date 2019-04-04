package com.hy.salon.basic.common.request;

import com.alibaba.fastjson.JSONObject;
import com.hy.salon.basic.common.ArraysUtil;
import com.hy.salon.basic.common.DataFormat;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;

public class ServletRequestReBox {
    public static String getRealIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }if (ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static class MyHttpServletRequestWrapper extends HttpServletRequestWrapper{

        private byte[] inputStream;

        private Map paramMap;

        /**
         * Constructs a request object wrapping the given request.
         *
         * @param request The request sessionId wrap
         * @throws IllegalArgumentException if the request is null
         */
        public MyHttpServletRequestWrapper(HttpServletRequest request) {
            super(request);
            this.paramMap = request.getParameterMap();
            request.setAttribute("X-Real-Addr", getRealIpAddr(request));
            try {
                InputStream stream = request.getInputStream();
                inputStream = ArraysUtil.channel(stream) ;
                stream.close();
            } catch (IOException e) {
                inputStream = new byte[0];
            }
        }

        /*public String getParameter(String name) {
            String urlParam = this.request.getParameter(name);
            if (urlParam == null) {

            }
        }*/


        public ServletInputStream getInputStream() {
            ByteArrayInputStream stream = new ByteArrayInputStream(inputStream);
            return new ServletInputStream() {
                @Override
                public int read() throws IOException {
                    return stream.read();
                }

				@Override
				public boolean isFinished() {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public boolean isReady() {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public void setReadListener(ReadListener listener) {
					// TODO Auto-generated method stub
					
				}
            };
        }
    }

    public static JSONObject getParams(HttpServletRequest request) {
        String data = null;
        try {
            InputStream body = request.getInputStream();
            byte[] elements = ArraysUtil.channel(body);
            body.close();
            data =new String(elements, request.getCharacterEncoding());
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject finalData = new JSONObject();

        Object formatData = DataFormat.format(data);

        finalData.put("body", formatData);
        request.getParameterMap().forEach((key, val) -> {
            Object[] vals = (Object[]) val;
            finalData.put(key.toString(), vals.length != 0 ? vals.length == 1 ? vals[0] : Arrays.asList(val) : "");
        });
        return finalData;
    }


}
