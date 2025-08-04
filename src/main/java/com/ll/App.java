package com.ll;

import java.util.Scanner;

public class App {
    void run () {
        String input = "등록\n 안녕!!";

        Scanner scanner = new Scanner(input);

        String cmd = scanner.nextLine();
        String content = scanner.nextLine();
        System.out.println("cmd : " + cmd);
        System.out.println("content : " + content);
    }
}
