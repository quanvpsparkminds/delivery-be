package net.sparkminds.delivery.exception;

import net.sparkminds.delivery.response.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationError(
            MethodArgumentNotValidException ex
    ) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(
                        "VALIDATION_ERROR",
                        "Invalid request data",
                        errors
                ));
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiResponse<?>> handleBusinessException(
            BaseException ex
    ) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(ApiResponse.error(
                        ex.getCode(),
                        ex.getMessage(),
                        null
                ));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<?>> handleInvalidJson(
            HttpMessageNotReadableException ex
    ) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(
                        "INVALID_JSON",
                        "Request body is invalid JSON format",
                        null
                ));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<?>> handleDataIntegrityViolation(
            DataIntegrityViolationException ex
    ) {
        String message = "Data integrity violation";
        String detail = null;

        // Extract constraint info from root cause
        Throwable rootCause = ex.getRootCause();
        if (rootCause != null && rootCause.getMessage() != null) {
            String rootMsg = rootCause.getMessage();
            if (rootMsg.contains("not-null constraint")) {
                // Extract column name: null value in column "address"
                java.util.regex.Matcher matcher = java.util.regex.Pattern
                        .compile("null value in column \"(\\w+)\"")
                        .matcher(rootMsg);
                if (matcher.find()) {
                    String column = matcher.group(1);
                    message = "Field '" + column + "' must not be null";
                } else {
                    message = "A required field is missing";
                }
            } else if (rootMsg.contains("duplicate key") || rootMsg.contains("unique constraint")) {
                message = "A record with this value already exists";
            } else if (rootMsg.contains("foreign key constraint")) {
                message = "Referenced record does not exist";
            }
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(
                        "DATA_INTEGRITY_ERROR",
                        message,
                        null
                ));
    }
}
