package bg.fmi.uni.inventorysystem.config.logger;

import bg.fmi.uni.inventorysystem.config.AppConfig;
import bg.fmi.uni.inventorysystem.vo.LoggerLevel;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

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
        System.out.println(">>>>>>>>>>>>>>> appConfig.getLogger().getLevel() -> " + appConfig.getLogger().getLevel());
        systemLoggerLevel = LoggerLevel.valueOf(appConfig.getLogger().getLevel());
    }

    @Override
    public void info(Object toLog) {
        System.out.println(toLog);
    }

    @Override
    public void debug(Object toLog) {
        if (isLoggingAllowed(LoggerLevel.DEBUG, systemLoggerLevel)) {
            System.out.println(toLog);
        }
    }

    @Override
    public void trace(Object toLog) {
        if(isLoggingAllowed(LoggerLevel.TRACE, systemLoggerLevel)) {
            System.out.println(toLog);
        }
    }

    @Override
    public void error(Object toLog) {
        System.out.println(toLog);
    }
}
