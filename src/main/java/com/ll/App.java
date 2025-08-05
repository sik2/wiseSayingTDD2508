package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private final Scanner scanner;

    public App(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run () {
        int lastId = 0;

        List<WiseSaying> wiseSayingList = new ArrayList<>();

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.println("명령) ");
            String cmd = scanner.nextLine();

            switch (cmd) {
                case "등록" -> {
                    System.out.println("명언 : ");
                    String content = scanner.nextLine();

                    System.out.println("작가 : ");
                    String author = scanner.nextLine();

                    int id = ++lastId;

                    wiseSayingList.add(new WiseSaying(id, author, content));

                    System.out.printf("%d번 명언이 등록되었습니다.\n", id);
                }
                case "목록" -> {
                    System.out.println("번호 / 작가 / 명언");
                    System.out.println("----------------------");
                    for (int i = wiseSayingList.size() - 1; i >= 0; i--) {
                        WiseSaying wiseSaying = wiseSayingList.get(i);
                        System.out.printf("%d / %s / %s\n", wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getContent());
                    }
                }
                case "종료" -> {
                    return;
                }
            }

        }

    }
}
