package bg.fmi.uni.inventorysystem.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "config")
@Configuration
@Data
public class AppConfig {
    private final LoggerConfig logger = new LoggerConfig();
    private final Inventory inventory = new Inventory();
    private final Transaction transaction = new Transaction();

    @Data
    @ConfigurationProperties(prefix = "logger")
    public static class LoggerConfig {
        private String level;
    }

    @Data
    @ConfigurationProperties(prefix = "inventory")
    public static class Inventory {
        // [week05/Task2] default value for properties can be defined in properties level with expression language
        private Integer lowStockThreshold;
    }

    @Data
    @ConfigurationProperties(prefix = "transaction")
    public static class Transaction {
        // [week05/Task3] default value for properties can be defined in properties level with expression language
        private Integer reminderSafetyWindowDays;
    }
}