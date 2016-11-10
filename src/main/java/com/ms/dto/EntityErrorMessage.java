package com.ms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ms.dto.helper.MessageType;

/**
 * Created by mshemet on 10/11/2016.
 */
public class EntityErrorMessage extends GeneralMessage{

    private long entityId;
    private Class entityClass;

    public EntityErrorMessage(long entityId, Class entityClass) {
        this.entityId = entityId;
        this.entityClass = entityClass;
        setType(MessageType.ERROR);
    }

  //  @JsonProperty(value = "msg")
    @Override
    public String getDescription() {
        return String.format("Entity %s with id %d not found", entityClass.getSimpleName(), entityId);
    }

    @JsonProperty
    public long getId() {
        return entityId;
    }

    @JsonProperty
    public String getEntity() {
        return entityClass.getSimpleName();
    }
}
