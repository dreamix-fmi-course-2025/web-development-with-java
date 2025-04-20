package bg.fmi.uni.inventorysystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import bg.fmi.uni.inventorysystem.dto.APIErrorDto;

// Simple exception handler
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    public APIErrorDto handleNotFound(ItemNotFoundException ex) {
        return new APIErrorDto(
            ex.getMessage(),
            HttpStatus.NOT_FOUND.value(),
            LocalDateTime.now()
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public APIErrorDto handleIllegalData(IllegalArgumentException ex) {
        return new APIErrorDto(
            ex.getMessage(),
            HttpStatus.BAD_REQUEST.value(),
            LocalDateTime.now()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public APIErrorDto handleValidation(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult().getFieldErrors().stream()
            .map(err -> err.getField() + ": " + err.getDefaultMessage())
            .reduce((a, b) -> a + "; " + b).orElse(ex.getMessage());
        return new APIErrorDto(
            errors,
            HttpStatus.BAD_REQUEST.value(),
            LocalDateTime.now()
        );
    }

    @ExceptionHandler(Exception.class)
    public APIErrorDto handleGeneric(Exception ex) {
        return new APIErrorDto(
            ex.getMessage(),
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            LocalDateTime.now()
        );
    }
}
