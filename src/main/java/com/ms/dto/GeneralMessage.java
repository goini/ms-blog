package com.ms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ms.dto.helper.MessageType;

/**
 * Created by mshemet on 10/11/2016.
 */
public class GeneralMessage {

    private MessageType type;
    private String description;

    @JsonProperty
    public MessageType getType() {
        return type;
    }
    public void setType(MessageType type) {
        this.type = type;
    }

    @JsonProperty
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
