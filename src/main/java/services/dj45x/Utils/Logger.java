package services.dj45x.Utils;

import org.slf4j.LoggerFactory;

public class Logger {
    private static final org.slf4j.Logger botLogger = LoggerFactory.getLogger(Logger.class);

    public static void info(String message) {
        botLogger.info(message);
    }

    public static void warn(String message) {
        botLogger.warn(message);
    }

    public static void error(String message) {
        botLogger.error(message);
    }
}
