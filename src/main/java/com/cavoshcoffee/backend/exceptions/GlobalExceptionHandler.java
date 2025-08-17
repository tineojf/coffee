package com.cavoshcoffee.backend.exceptions;

import com.cavoshcoffee.backend.config.Constant;
import com.cavoshcoffee.backend.dto.GlobalResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<GlobalResponse> handleBadRequestException(Exception e, HttpServletRequest req) {
        GlobalResponse res = createResponse(e, req);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<GlobalResponse> handleEntityAlreadyExistsException(Exception e, HttpServletRequest req) {
        GlobalResponse res = createResponse(e, req);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<GlobalResponse> handleResourceNotFoundException(Exception e, HttpServletRequest req) {
        GlobalResponse res = createResponse(e, req);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    // MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String message = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();

        GlobalResponse errorResponse = createResponse(ex, request);
        errorResponse.setMessage(message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // MethodArgumentMismatchException
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<GlobalResponse> handleTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        String parameterName = ex.getName();
        String expectedType = ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "unknown";
        String message = String.format(Constant.GR_ERROR_PARAMETER_TYPE, parameterName, expectedType);

        GlobalResponse errorResponse = createResponse(ex, request);
        errorResponse.setMessage(message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // NoHandlerFoundException
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<GlobalResponse> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpServletRequest request) {
        GlobalResponse errorResponse = createResponse(ex, request);
        errorResponse.setMessage(Constant.GR_ERROR_NO_HANDLER);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    // private
    private GlobalResponse createResponse(Exception e, HttpServletRequest req) {
        return GlobalResponse.builder()
                .ok(false)
                .message(e.getMessage())
                .details(String.format(Constant.GR_ERROR_DETAILS, req.getMethod(), req.getRequestURI()))
                .build();
    }
}
