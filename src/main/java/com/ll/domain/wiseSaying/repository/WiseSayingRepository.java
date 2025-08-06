package com.ll.domain.wiseSaying.repository;

import com.ll.domain.wiseSaying.entity.WiseSaying;
import com.ll.standard.dto.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WiseSayingRepository {
    private final List<WiseSaying> wiseSayingList = new ArrayList<>();
    private int lastId = 0;

    public WiseSaying save (WiseSaying wiseSaying) {
        if (wiseSaying.isNew()) {
            wiseSaying.setId(++lastId);
            wiseSayingList.add(wiseSaying);
        }

        return wiseSaying;
    }

    public List<WiseSaying> findForListByContentContaining(String keyword, Pageable pageable) {
        return wiseSayingList
                .reversed()
                .stream()
                .filter(
                        wiseSaying -> wiseSaying.getContent().contains(keyword)
                )
                .skip((pageable.getSkipCount()))
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
    }

    public List<WiseSaying> findForListByAuthorContaining(String keyword, Pageable pageable) {
        return wiseSayingList
                .reversed()
                .stream()
                .filter(
                        wiseSaying -> wiseSaying.getAuthor().contains(keyword)
                )
                .skip((pageable.getSkipCount()))
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
    }

    public List<WiseSaying> findForListByContentContainingOrAuthorContaining(String keyword, Pageable pageable) {
        return wiseSayingList
                .reversed()
                .stream()
                .filter(
                        wiseSaying -> wiseSaying.getContent().contains(keyword) || wiseSaying.getAuthor().contains(keyword)
                )
                .skip((pageable.getSkipCount()))
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
    }

    public List<WiseSaying> findForList(Pageable pageable) {
        return wiseSayingList
                .reversed()
                .stream()
                .skip((pageable.getSkipCount()))
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
    }

    public void delete(WiseSaying wiseSaying) {
        wiseSayingList.remove(wiseSaying);
    }

    public int findIndexById(int id) {
        return IntStream
                .range(0, wiseSayingList.size())
                .filter(i -> wiseSayingList.get(i).getId() == id)
                .findFirst()
                .orElse(-1);
    }

    public WiseSaying findById(int id) {
        int index = findIndexById(id);

        if (index == -1) return null;

        return wiseSayingList.get(index);
    }
}
