package com.ll;

import java.util.Scanner;

public class App {
    private final Scanner scanner;

    public App(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run () {
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
                }
                case "종료" -> {
                    return;
                }
            }

        }

    }
}
