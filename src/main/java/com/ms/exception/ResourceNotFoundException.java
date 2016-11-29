package com.ms.exception;

import com.ms.dto.EntityErrorMessage;

import java.util.Optional;

/**
 * Created by mshemet on 10/11/2016.
 */
public class ResourceNotFoundException extends RuntimeException {

    private EntityErrorMessage error;

    public ResourceNotFoundException(EntityErrorMessage error) {
        super(Optional.ofNullable(error).map(x -> x.getDescription()).orElse(""));
        this.error = error;
    }

    public EntityErrorMessage getError() {
        return error;
    }
    public void setError(EntityErrorMessage error) {
        this.error = error;
    }
}
