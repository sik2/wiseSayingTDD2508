package com.ll.standard.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UtilTest {
    @Test
    @DisplayName("파일 생성")
    public void t1() {
        // given -> 준비단계
        String filePath = "temp/text.txt";
        // when -> 실행단계
        Util.file.touch(filePath);
        // then -> 검증단계
        assertThat(
                Util.file.exists(filePath)
        ).isTrue();

        Util.file.delete(filePath);
    }
}
