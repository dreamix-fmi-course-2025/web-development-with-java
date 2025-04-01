package bg.fmi.uni.inventorysystem.vo;

import lombok.Getter;

@Getter
public enum LoggerLevel {

    INFO(1, "INFO"),
    DEBUG(2, "DEBUG"),
    TRACE(3, "TRACE"),
    ERROR(0, "ERROR");

    private final Integer code;
    public final String label;

    LoggerLevel(Integer code, String label) {
        this.code = code;
        this.label = label;
    }
}
