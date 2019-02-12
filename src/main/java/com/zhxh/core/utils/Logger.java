package com.zhxh.core.utils;

/**
 * Created by yahong on 14-6-18.
 */
public class Logger {
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Logger.class);

    public static void debug(Object message) {
        logger.debug(message);
    }

    public static void debug(Object message, Throwable t) {
        logger.debug(message, t);
    }

    public static void trace(Object message) {
        logger.trace(message);
    }

    public static void trace(Object message, Throwable t) {
        logger.trace(message, t);
    }

    public static void info(Object message) {
        logger.info(message);
    }

    public static void info(Object message, Throwable t) {
        logger.info(message, t);
    }

    public static void warn(Object message) {
        logger.warn(message);
    }

    public static void warn(Object message, Throwable t) {
        logger.warn(message, t);
    }

    public static void error(Object message) {
        if (message instanceof Exception) {
            Exception e = (Exception) message;
            logger.error(e.getMessage());
            logger.error(e.getStackTrace());
        } else {
            logger.error(message);
        }
    }

    public static void error(Object message, Throwable t) {
        logger.error(message, t);
    }

    public static void fatal(Object message) {
        logger.fatal(message);
    }

    public static void fatal(Object message, Throwable t) {
        logger.fatal(message, t);
    }

}
