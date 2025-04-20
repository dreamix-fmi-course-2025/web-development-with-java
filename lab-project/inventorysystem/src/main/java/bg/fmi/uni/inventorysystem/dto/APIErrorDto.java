package bg.fmi.uni.inventorysystem.dto;

import java.time.LocalDateTime;

public record APIErrorDto(
    String message,
    int status,
    LocalDateTime timestamp
) {}
