package com.zhxh.core.exception;

import com.zhxh.core.env.SysEnv;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionHelper {
    public static String exceptionStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw, true));
        return sw.toString();
    }

    public static void throwException(Enum errorCode, String message) throws BusinessException {
        throw new BusinessException( "[" + errorCode + "]:" + message);
    }

    public static void throwException(Enum errorCode, Object... args) throws BusinessException {
        String message = buildExceptionMessage(errorCode, args);
        throw new BusinessException(message);
    }

    private static String buildExceptionMessage(Enum errorCode, Object... args) {
        String errorCodeName = errorCode.name();

        String template = "[" + errorCode + "]:" + SysEnv.getErrorMessage(errorCodeName);
        return String.format(template, args);
    }
}
