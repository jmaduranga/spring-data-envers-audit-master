package com.wiley.resultcontext.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.io.Serializable;

public enum MessageType implements Serializable {
    ASSESSMENT("assessment", "assessment message type "),
    QUESTION("question", "question message type"),
    ANSWER("answer", "description message type");

    private String name;
    private String description;
    private MessageType(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public String getName(){
        return name;
    }

    @JsonCreator
    public MessageType getMessageType(String name){
        for(MessageType type : values()){
            if(type.getName().equals(name)){
                return type;
            }
        }
        return null;
    }
}
