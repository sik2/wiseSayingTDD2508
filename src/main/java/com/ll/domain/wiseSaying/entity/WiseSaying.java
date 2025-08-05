package com.ll.domain.wiseSaying.entity;

import lombok.*;

@Getter
@Setter
@ToString
public class WiseSaying {
    private int id;
    private String author;
    private String content;

    public WiseSaying (String author, String content) {
        this.author = author;
        this.content = content;
    }

    public boolean isNew() {
        return id == 0;
    }
}
