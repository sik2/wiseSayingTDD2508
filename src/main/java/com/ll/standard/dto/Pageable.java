package com.ll.standard.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class Pageable {
    private final int PageNo;
    private final int PageSize;

    public long getSkipCount() {
        return (PageNo - 1) * PageSize;
    }
}
