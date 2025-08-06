package com.ll.domain.wiseSaying.controller;

import com.ll.AppContext;
import com.ll.domain.wiseSaying.entity.WiseSaying;
import com.ll.domain.wiseSaying.service.WiseSayingService;
import com.ll.global.rq.Rq;
import com.ll.standard.dto.Pageable;

import java.util.Scanner;

public class WiseSayingController {
    private final Scanner scanner;
    private final WiseSayingService wiseSayingService;

    public WiseSayingController() {
        this.scanner = AppContext.scanner;
        this.wiseSayingService = AppContext.wiseSayingService;
    }

    public void actionWrite() {
        System.out.print("명언 : ");
        String content = scanner.nextLine();

        System.out.print("작가 : ");
        String author = scanner.nextLine();

        WiseSaying wiseSaying = wiseSayingService.write(content, author);

        System.out.println(wiseSaying);
        System.out.printf("%d번 명언이 등록되었습니다.\n", wiseSaying.getId());
    }

    public void actionList(Rq rq) {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        String keywordType = rq.getParam("keywordType", "all");
        String keyword = rq.getParam("keyword", "");

        int pageSize = rq.getParamsAsInt("pageSize", 5);
        int pageNo = rq.getParamsAsInt("page", 1);

        Pageable pageable = new Pageable(pageNo, pageSize);

        for (WiseSaying wiseSaying : wiseSayingService.findForList(keywordType, keyword, pageable)) {
            System.out.printf("%d / %s / %s\n", wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getContent());
        }
    }

    public void actionDelete(Rq rq) {
        int id = rq.getParamsAsInt("id", -1);

        if (id == -1 ) {
            System.out.println("id를 숫자로 입력해주세요.");
            return;
        }

        boolean isDeleted = wiseSayingService.delete(id);

        if (!isDeleted) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }

        System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
    }

    public void actionModify(Rq rq) {
        int id = rq.getParamsAsInt("id", -1);

        if (id == -1 ) {
            System.out.println("id를 숫자로 입력해주세요.");
            return;
        }

        WiseSaying wiseSaying = wiseSayingService.findById(id);

        if (wiseSaying == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }

        System.out.printf("명언 (기존) : %s\n", wiseSaying.getContent());
        System.out.print("명언 : ");
        String modifyContent = scanner.nextLine();

        System.out.printf("작가 (기존) : %s\n", wiseSaying.getAuthor());
        System.out.print("작가 : ");
        String modifyAuthor = scanner.nextLine();

        wiseSayingService.modify(wiseSaying, modifyContent, modifyAuthor);
    }
}
