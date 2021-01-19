package com.wiley.resultcontext.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> implements Serializable {
    private List<T> items;
    private int currentPage;
    private int totalPages;
    private long totalElements;
}
