package com.wanted.marketapi.exception;

import com.wanted.marketapi.dto.error.ErrorResponse;
import com.wanted.marketapi.exception.custom.CustomToProductMapperException;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.swing.border.EtchedBorder;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder errorMessage = new StringBuilder("유효성 검사 실패한 필드 : ");
        for(FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errorMessage.append(fieldError.getField()).append(" ( ").append(fieldError.getDefaultMessage()).append("), ");
        }
        errorMessage.setLength(errorMessage.length() - 2);
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.METHOD_ARGUMENT_NOT_VALID.getCode(), errorMessage.toString());
        return new ResponseEntity<>(errorResponse, ErrorCode.METHOD_ARGUMENT_NOT_VALID.getHttpStatus());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.DUPLICATE_LOGIN_ID.getCode(), ErrorCode.DUPLICATE_LOGIN_ID.getMessage());
        return new ResponseEntity<>(errorResponse, ErrorCode.DUPLICATE_LOGIN_ID.getHttpStatus());
    }

    @ExceptionHandler(SQLGrammarException.class)
    public ResponseEntity<ErrorResponse> handleSQLGrammarException(SQLGrammarException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.WRONG_SQL_GRAMMER.getCode(), ErrorCode.WRONG_SQL_GRAMMER.getMessage());
        return new ResponseEntity<>(errorResponse, ErrorCode.WRONG_SQL_GRAMMER.getHttpStatus());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.NOT_SUPPORTED_HTTP_REQUEST.getCode(), ErrorCode.NOT_SUPPORTED_HTTP_REQUEST.getMessage());
        return new ResponseEntity<>(errorResponse, ErrorCode.NOT_SUPPORTED_HTTP_REQUEST.getHttpStatus());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleNullPointerException(NullPointerException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.NULL_POINTER.getCode(), ErrorCode.NULL_POINTER.getMessage());
        return new ResponseEntity<>(errorResponse, ErrorCode.NULL_POINTER.getHttpStatus());
    }

    @ExceptionHandler(CustomToProductMapperException.class)
    public ResponseEntity<ErrorResponse> handleCustomToProductMapperException(CustomToProductMapperException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.NOT_FOUND_USERID_FROM_ADD_PRODUCT.getCode(), ErrorCode.NOT_FOUND_USERID_FROM_ADD_PRODUCT.getMessage());
        return new ResponseEntity<>(errorResponse, ErrorCode.NOT_FOUND_USERID_FROM_ADD_PRODUCT.getHttpStatus());
    }
}
