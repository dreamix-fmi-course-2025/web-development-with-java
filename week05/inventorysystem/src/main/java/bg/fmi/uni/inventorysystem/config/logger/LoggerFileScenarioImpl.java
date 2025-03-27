package bg.fmi.uni.inventorysystem.config.logger;

import bg.fmi.uni.inventorysystem.config.AppConfig;
import bg.fmi.uni.inventorysystem.config.logger.Logger;
import bg.fmi.uni.inventorysystem.vo.LoggerLevel;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@Profile("dev")
@Component("fileLogger")
public class LoggerFileScenarioImpl implements Logger {

    private LoggerLevel loggerLevel;

    @Autowired
    private AppConfig appConfig;

    @PostConstruct
    public void setup() {
        System.out.println(appConfig.getLogger().getLevel());
        loggerLevel = LoggerLevel.valueOf(appConfig.getLogger().getLevel());
    }

    @Override
    public void info(Object toLog) {
       LoggerLevel currentLogger = LoggerLevel.INFO;
        if (isLoggingAllowed(currentLogger)) { // not needed because info is always printed by the rule
            logInformation(toLog, currentLogger);
        }
    }

    @Override
    public void debug(Object toLog) {
        LoggerLevel currentLogger = LoggerLevel.DEBUG;
        if (isLoggingAllowed(currentLogger)) {
            logInformation(toLog, currentLogger);
        }
    }

    @Override
    public void trace(Object toLog) {
        LoggerLevel currentLogger = LoggerLevel.TRACE;
        if (isLoggingAllowed(currentLogger)) {
            logInformation(toLog, currentLogger);
        }
    }

    @Override
    public void error(Object toLog) {
        logInformation(toLog, LoggerLevel.ERROR);
    }

    private boolean isLoggingAllowed(LoggerLevel loggerLevel) {
        return loggerLevel.getCode().compareTo(this.loggerLevel.getCode()) <= 0;
    }

    private void logInformation(Object toLog, LoggerLevel currentLoggerLevel) {
        File log = new File("log.txt");
        try (PrintWriter out = new PrintWriter(new FileWriter(log, true))) {
            out.println(new Date() + " [" + currentLoggerLevel.getLabel() + "] - " + toLog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
