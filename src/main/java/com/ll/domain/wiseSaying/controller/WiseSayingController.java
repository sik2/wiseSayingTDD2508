package com.ll.domain.wiseSaying.controller;

import com.ll.AppContext;
import com.ll.domain.wiseSaying.entity.WiseSaying;
import com.ll.domain.wiseSaying.service.WiseSayingService;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

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

        System.out.printf("%d번 명언이 등록되었습니다.\n", wiseSaying.getId());
    }

    public void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for (WiseSaying wiseSaying : wiseSayingService.findForList()) {
            System.out.printf("%d / %s / %s\n", wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getContent());
        }
    }

    public void actionDelete(String cmd) {
        String[] cmdBits = cmd.split("\\?", 2);
        String queryString = cmdBits[1];

        Map<String, String> params = Arrays
                .stream(queryString.split("&"))
                .map(e -> e.split("=", 2))
                .filter(e -> e.length == 2 && !e[0].isBlank() && !e[1].isBlank())
                .collect(Collectors.toMap(e -> e[0].trim(), e -> e[1].trim()));

        String idStr = params.getOrDefault("id", "");
        int id = Integer.parseInt(idStr);

        boolean isDeleted = wiseSayingService.delete(id);

        if (!isDeleted) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }

        System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
    }

    public void actionModify(String cmd) {
        String[] cmdBits = cmd.split("\\?", 2);
        String queryString = cmdBits[1];

        Map<String, String> params = Arrays
                .stream(queryString.split("&"))
                .map(e -> e.split("=", 2))
                .filter(e -> e.length == 2 && !e[0].isBlank() && !e[1].isBlank())
                .collect(Collectors.toMap(e -> e[0].trim(), e -> e[1].trim()));

        String idStr = params.getOrDefault("id", "");
        int id = Integer.parseInt(idStr);

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
