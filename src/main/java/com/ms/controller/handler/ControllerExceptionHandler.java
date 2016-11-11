package com.ms.controller.handler;


import com.ms.dto.GeneralMessage;
import com.ms.dto.helper.MessageType;
import com.ms.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mshemet on 10/11/2016.
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> searchEntity(ResourceNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getError());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> updateEntity() {
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity validation(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        List<GeneralMessage> messages = result.getFieldErrors().stream().map(error -> {
            GeneralMessage message = new GeneralMessage();
            message.setType(MessageType.ERROR);
            message.setDescription(error.getDefaultMessage());
            return message;
        }).collect(Collectors.toList());

        return ResponseEntity.badRequest().body(messages);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalHandler(Exception e) {
        return  ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

}
