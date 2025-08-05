package com.ll.domain.wiseSaying.service;


import com.ll.AppContext;
import com.ll.domain.wiseSaying.entity.WiseSaying;
import com.ll.domain.wiseSaying.repository.WiseSayingRepository;

import java.util.List;

public class WiseSayingService {
    private final WiseSayingRepository wiseSayingRepository;

    public WiseSayingService () {
        wiseSayingRepository = AppContext.wiseSayingRepository;
    }

    public WiseSaying write (String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(author, content);
        wiseSayingRepository.save(wiseSaying);

        return wiseSaying;
    }

    public List<WiseSaying> findForList () {
        return wiseSayingRepository.findForList();
    }

    public boolean delete(int id) {
        WiseSaying wiseSaying = wiseSayingRepository.findById(id);

        if (wiseSaying == null) return false;

        wiseSayingRepository.delete(wiseSaying);

        return true;
    }
}
