package com.ll.domain.wiseSaying.service;


import com.ll.domain.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingService {
    private final List<WiseSaying> wiseSayingList = new ArrayList<>();
    private int lastId = 0;

    public WiseSaying write (String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(++lastId, author, content);
        wiseSayingList.add(wiseSaying);

        return wiseSaying;
    }

    public List<WiseSaying> findForList () {
        return wiseSayingList.reversed();
    }
}
