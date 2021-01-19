package com.wiley.resultcontext.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProgressMessage implements Serializable {
    private MessageType messageType;
    private Progress progress;
}
