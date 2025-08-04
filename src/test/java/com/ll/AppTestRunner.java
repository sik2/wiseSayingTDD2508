package com.ll;

import com.ll.standard.util.TestUtil;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

public class AppTestRunner {
    public static String run(String input) {
        Scanner scanner = TestUtil.genScanner(input);

        ByteArrayOutputStream output = TestUtil.setOutToByteArray();
        new App(scanner).run();

        return output.toString();
    }
}
