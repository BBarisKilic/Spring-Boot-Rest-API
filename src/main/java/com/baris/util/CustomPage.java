package com.baris.util;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

@Data
public class CustomPage<E, D> {
    private final List<D> content;
    private final int pageNumber;
    private final int pageSize;
    private final Sort sort;
    private final int totalPages;
    private final long totalElements;

    public CustomPage(Page<E> page, List<D> list) {
        this.content = list;
        this.pageNumber = page.getNumber();
        this.pageSize = page.getSize();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.sort = page.getSort();
    }
}
