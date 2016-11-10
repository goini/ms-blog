package com.ms.controller.handler;


import com.ms.dto.GeneralMessage;
import com.ms.dto.helper.MessageType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mshemet on 10/11/2016.
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> searchEntity(EntityNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                //.body(e.getError());
                .build();
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
