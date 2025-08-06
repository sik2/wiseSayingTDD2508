package com.ll.domain.wiseSaying.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class WiseSaying {
    private int id;
    private String author;
    private String content;

    public WiseSaying (String author, String content) {
        this.author = author;
        this.content = content;
    }

    public WiseSaying(Map<String, Object> wiseSayingMap) {
        this.id = (int) wiseSayingMap.get("id");
        this.author = (String) wiseSayingMap.get("author");
        this.content = (String) wiseSayingMap.get("content");
    }

    public boolean isNew() {
        return id == 0;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new LinkedHashMap<>();

        map.put("id", id);
        map.put("author", author);
        map.put("content", content);

        return map;
    }
}
