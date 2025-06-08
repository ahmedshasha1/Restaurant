package com.resturant.config;

import com.resturant.dto.exception.BundleMessage;
import com.resturant.dto.exception.ExceptionDto;
import com.resturant.service.bundlemessage.BundleMessageService;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionConfig {

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<ExceptionDto> runtimeHandler(RuntimeException runtimeException){
        return ResponseEntity.ok(new ExceptionDto(HttpStatus.NOT_ACCEPTABLE, BundleMessageService.getMessages(runtimeException.getMessage())));
    }
    @ExceptionHandler(BadCredentialsException.class)
    ResponseEntity<ExceptionDto> runtimeHandler(BadCredentialsException runtimeException){
        return ResponseEntity.ok(new ExceptionDto(HttpStatus.NOT_ACCEPTABLE, BundleMessageService.getMessages(runtimeException.getMessage())));
    }



//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    ResponseEntity<List<BundleMessage>> handleRuntimeException(MethodArgumentNotValidException methodArgumentNotValidException){
//        List<FieldError> errors =methodArgumentNotValidException.getBindingResult().getFieldErrors();
//
//        List<BundleMessage> bundleMessages =new ArrayList<>();
//        for (FieldError fieldError: errors) {
//            bundleMessages.add(BundleMessageService.getMessages(fieldError.getDefaultMessage()));
//        }
//        return ResponseEntity.internalServerError().body(bundleMessages);
//    }
}
