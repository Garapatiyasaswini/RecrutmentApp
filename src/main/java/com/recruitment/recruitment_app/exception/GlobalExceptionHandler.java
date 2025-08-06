package com.recruitment.recruitment_app.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<?> handleInvalidFormat(InvalidFormatException ex) {
        if (ex.getTargetType().isEnum()) {
            String fieldName = ex.getPath().get(0).getFieldName();
            String allowedValues = String.join(", ", 
                ex.getTargetType().getEnumConstants() != null 
                    ? java.util.Arrays.stream(ex.getTargetType().getEnumConstants()).map(Object::toString).toList()
                    : java.util.Collections.emptyList()
            );

            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                    "error", String.format("Invalid value for %s. Allowed values: %s", fieldName, allowedValues)
                ));
        }

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", "Invalid input format."));
    }
}
