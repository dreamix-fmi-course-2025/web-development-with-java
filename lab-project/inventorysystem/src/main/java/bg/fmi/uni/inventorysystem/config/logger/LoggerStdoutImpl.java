package bg.fmi.uni.inventorysystem.config.logger;

import bg.fmi.uni.inventorysystem.config.AppConfig;
import bg.fmi.uni.inventorysystem.vo.LoggerLevel;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@Profile("local")
@Component
@Primary
public class LoggerStdoutImpl implements Logger {

    private final AppConfig appConfig;

    @Autowired
    public LoggerStdoutImpl(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    private LoggerLevel systemLoggerLevel;

    /**
     * By using the Bean lifecycle we can set specific values for our business functionalities
     */
    @PostConstruct
    public void setup() {
        System.out.println(">>>>>>>>>>>>>>> [LoggerStdoutImpl] appConfig.getLogger().getLevel() -> " + appConfig.getLogger().getLevel());
        systemLoggerLevel = LoggerLevel.valueOf(appConfig.getLogger().getLevel());
    }

    @Override
    public void info(Object toLog) {
        logInformation(toLog, LoggerLevel.INFO);
    }

    @Override
    public void debug(Object toLog) {
        LoggerLevel currentLogger = LoggerLevel.DEBUG;
        if (isLoggingAllowed(currentLogger, systemLoggerLevel)) {
            logInformation(toLog, currentLogger);
        }
    }

    @Override
    public void trace(Object toLog) {
        LoggerLevel currentLogger = LoggerLevel.TRACE;
        if (isLoggingAllowed(currentLogger, systemLoggerLevel)) {
            logInformation(toLog, currentLogger);
        }
    }

    @Override
    public void error(Object toLog) {
        logInformation(toLog, LoggerLevel.ERROR);
    }

    private void logInformation(Object toLog, LoggerLevel currentLoggerLevel) {
        System.out.println(new Date() + " [" + currentLoggerLevel.getLabel() + "] - " + toLog);
    }
}