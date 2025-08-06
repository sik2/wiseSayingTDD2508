package com.ll.wiseSaying.Repository;

import com.ll.domain.wiseSaying.entity.WiseSaying;
import com.ll.domain.wiseSaying.repository.WiseSayingFileRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class WiseSayingFileRepositoryTest {
    private final WiseSayingFileRepository wiseSayingFileRepository;

    WiseSayingFileRepositoryTest() {
        wiseSayingFileRepository = new WiseSayingFileRepository();
    }

    @Test
    @DisplayName("저장")
    void t1 () {
        WiseSaying wiseSaying = new WiseSaying("니체",  "나를 죽이지 못하는 고통은 나를 성장시킬 뿐이다.");

        wiseSayingFileRepository.save(wiseSaying);

        WiseSaying foundWiseSaying =  wiseSayingFileRepository.findById(1);

        assertThat(
                foundWiseSaying
        ).isEqualTo(wiseSaying);
    }

}
