package bg.fmi.uni.inventorysystem.config.logger.scenario;

import bg.fmi.uni.inventorysystem.config.AppConfig;
import bg.fmi.uni.inventorysystem.config.logger.Logger;
import bg.fmi.uni.inventorysystem.vo.LoggerLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("local")
@Component("stdoutLogger")
public class LoggerStdoutImpl implements Logger {
    private final LoggerLevel loggerLevel;

    @Autowired // show that you can go without it
    public LoggerStdoutImpl(final AppConfig appConfig) {
        loggerLevel = LoggerLevel.valueOf(appConfig.getLogger().getLevel());
    }
//
//    @PostConstruct
//    void onConstruct() {
//        loggerLevel = LoggerLevel.valueOf(this.appConfig.getLogger().getLevel());
//    }

    @Override
    public void info(final Object toLog) {
        if(loggerLevel.getCode().compareTo(LoggerLevel.INFO.getCode()) >= 0) {
            System.out.println(toLog);
        }
    }

    @Override
    public void debug(final Object toLog) {
        if(isLoggingAllowed(LoggerLevel.DEBUG, loggerLevel)) {
            System.out.println(toLog);
        }
    }

    @Override
    public void trace(final Object toLog) {
        if(loggerLevel.getCode().compareTo(LoggerLevel.TRACE.getCode()) >= 0) {
            System.out.println(toLog);
        }
    }

    @Override
    public void error(final Object toLog) {
        System.out.println(toLog);
    }
}
