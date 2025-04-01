package bg.fmi.uni.inventorysystem.config.logger;

import bg.fmi.uni.inventorysystem.config.AppConfig;
import bg.fmi.uni.inventorysystem.vo.LoggerLevel;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@Profile("dev")
@Component("fileLogger") //  if you need to define named component
@RequiredArgsConstructor
public class LoggerFileScenarioImpl implements Logger {

    private LoggerLevel systemLoggerLevel;

    private final AppConfig appConfig;

    @PostConstruct
    public void setup() {
        System.out.println(">>>>>>>>>>>>>>> [LoggerFileScenarioImpl] appConfig.getLogger().getLevel() -> " + appConfig.getLogger().getLevel());
        systemLoggerLevel = LoggerLevel.valueOf(appConfig.getLogger().getLevel());
    }

    @Override
    public void info(Object toLog) {
        LoggerLevel currentLogger = LoggerLevel.INFO;
        if (isLoggingAllowed(currentLogger, systemLoggerLevel)) { // not needed because info is always printed by the rule
            logInformation(toLog, currentLogger);
        }
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
        File log = new File("log.txt");
        try (PrintWriter out = new PrintWriter(new FileWriter(log, true))) {
            out.println(new Date() + " [" + currentLoggerLevel.getLabel() + "] - " + toLog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
