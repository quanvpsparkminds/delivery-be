package net.sparkminds.delivery.exception;

import net.sparkminds.delivery.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.*;

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
}
