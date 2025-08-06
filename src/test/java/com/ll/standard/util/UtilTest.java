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

    @Test
    @DisplayName("파일의 내용을 수정할 수 있고, 읽을 수 있다.")
    public void t2() {
        // given
        String filePath = "temp/test.txt";

        // when
        Util.file.set(filePath, "내용");

        // then
        assertThat(
                Util.file.get(filePath, "")
        ).isEqualTo("내용");

        Util.file.delete(filePath);
    }

    @Test
    @DisplayName("파일을 삭제할 수 있다.")
    public void t3() {
        // given
        String filePath = "temp/test.txt";

        // when
        Util.file.touch(filePath);
        Util.file.delete(filePath);

        // then
        assertThat(
                Util.file.notExists(filePath)
        ).isTrue();
    }

    @Test
    @DisplayName("파일을 생성할 수 있다, 만약 해당 경로의 폴더가 없다면 만든다.")
    public void t4() {
        // given
        String filePath = "temp/temp/test.txt";

        // when
        Util.file.touch(filePath);

        // then
        assertThat(
                Util.file.exists(filePath)
        ).isTrue();

        Util.file.delete(filePath);
    }
}
