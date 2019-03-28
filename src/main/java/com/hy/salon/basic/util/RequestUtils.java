package com.hy.salon.basic.util;

import com.hy.salon.basic.vo.RequestResult;
import com.github.kevinsawicki.http.HttpRequest;

public class RequestUtils {

    private static final String DEFAULT_CHARSET = "utf-8";
    private static final int CONNECT_TIMEOUT_IN_MIS_SECOND = 10000;
    private static final int READ_TIMEOUT_IN_MIS_SECOND = 30000;

    public static RequestResult getJsonText(String url, String charset) {
        if (charset == null) charset = DEFAULT_CHARSET;
        HttpRequest request = HttpRequest.get(url).connectTimeout(CONNECT_TIMEOUT_IN_MIS_SECOND).readTimeout(READ_TIMEOUT_IN_MIS_SECOND);
        String body = request.body(charset);
        int code = request.code();
        request.disconnect();
        return new RequestResult(code, body);
    }
}
