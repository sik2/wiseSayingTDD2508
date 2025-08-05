package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WiseSayingController {
    private final List<WiseSaying> wiseSayingList = new ArrayList<>();
    private final Scanner scanner;
    private int lastId = 0;

    public WiseSayingController(Scanner scanner) {
        this.scanner = scanner;
    }

    public void actionWrite() {
        System.out.println("명언 : ");
        String content = scanner.nextLine();

        System.out.println("작가 : ");
        String author = scanner.nextLine();

        int id = ++lastId;

        wiseSayingList.add(new WiseSaying(id, author, content));

        System.out.printf("%d번 명언이 등록되었습니다.\n", id);
    }

    public void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for (int i = wiseSayingList.size() - 1; i >= 0; i--) {
            WiseSaying wiseSaying = wiseSayingList.get(i);
            System.out.printf("%d / %s / %s\n", wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getContent());
        }
    }
}
