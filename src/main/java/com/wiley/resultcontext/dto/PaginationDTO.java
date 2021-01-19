package com.wiley.resultcontext.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaginationDTO implements Serializable {
    private int page;
    private int size;
    private String[] orderByList;
}
