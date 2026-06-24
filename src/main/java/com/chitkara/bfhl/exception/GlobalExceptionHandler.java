package com.chitkara.bfhl.exception;

import com.chitkara.bfhl.dto.BfhlResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<BfhlResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        logger.error("Malformed request received: {}", ex.getMessage());
        BfhlResponse response = new BfhlResponse(
                false,
                null,
                null,
                null,
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                "0",
                ""
        );
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BfhlResponse> handleGeneralException(Exception ex) {
        logger.error("An unexpected error occurred: ", ex);
        BfhlResponse response = new BfhlResponse(
                false,
                null,
                null,
                null,
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                "0",
                ""
        );
        return ResponseEntity.internalServerError().body(response);
    }
}
