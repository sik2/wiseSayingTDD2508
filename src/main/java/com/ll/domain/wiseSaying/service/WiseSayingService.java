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

    public List<WiseSaying> findForList(String keywordType, String keyword, int pageSize, int pageNo) {
        if (keyword.isBlank()) {
            return wiseSayingRepository.findForList(pageSize, pageNo);
        }

        return switch (keywordType) {
            case "content" -> wiseSayingRepository.findForListByContentContaining(keyword);
            case "author" -> wiseSayingRepository.findForListByAuthorContaining(keyword);
            default -> wiseSayingRepository.findForListByContentContainingOrAuthorContaining(keyword);
        };
    }

    public boolean delete(int id) {
        WiseSaying wiseSaying = wiseSayingRepository.findById(id);

        if (wiseSaying == null) return false;

        wiseSayingRepository.delete(wiseSaying);

        return true;
    }

    public WiseSaying findById(int id) {
        return wiseSayingRepository.findById(id);
    }

    public void modify(WiseSaying wiseSaying, String modifyContent, String modifyAuthor) {
        wiseSaying.setContent(modifyContent);
        wiseSaying.setAuthor(modifyAuthor);

        wiseSayingRepository.save(wiseSaying);
    }
}
