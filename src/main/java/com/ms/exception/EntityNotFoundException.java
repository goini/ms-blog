package com.ms.exception;

import com.ms.dto.EntityErrorMessage;

/**
 * Created by mshemet on 10/11/2016.
 */
public class EntityNotFoundException extends RuntimeException {

    private EntityErrorMessage error;

    public EntityNotFoundException(EntityErrorMessage error) {
        this.error = error;
    }

    public EntityErrorMessage getError() {
        return error;
    }
    public void setError(EntityErrorMessage error) {
        this.error = error;
    }
}
