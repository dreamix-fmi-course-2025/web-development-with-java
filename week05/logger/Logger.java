package bg.fmi.uni.inventorysystem.config.logger;

import bg.fmi.uni.inventorysystem.vo.LoggerLevel;

public interface Logger {

    void info(Object toLog);

    void debug(Object toLog);

    void trace(Object toLog);

    void error(Object toLog);

    default boolean isLoggingAllowed(LoggerLevel loggerLevel, LoggerLevel systemLoggerLevel) {
        return loggerLevel.getCode().compareTo(systemLoggerLevel.getCode()) <= 0;
    }
}