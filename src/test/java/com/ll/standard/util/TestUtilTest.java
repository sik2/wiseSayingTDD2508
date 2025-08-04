package com.ll.standard.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class TestUtilTest {

    @Test
    @DisplayName("TestUtil.genScanner() 테스트")
    public void t1() {
    Scanner scanner =  TestUtil.genScanner("""
                등록
                나의 죽음을 적들에게 알리지 말라!
                이순신
                """);
    }

    String cmd = scanner.nextLine();
    String content = scanner.nextLine();
    String author = scanner.nextLine();

    assertThat(cmd).isEqualTo("등록");
    assertThat(cmd).isEqualTo("나의 죽음을 적들에게 알리지 말라!");
    assertThat(cmd).isEqualTo("이순신");

}
