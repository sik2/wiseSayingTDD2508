package com.ll.domain.wiseSaying.repository;

import com.ll.domain.wiseSaying.entity.WiseSaying;
import com.ll.standard.util.Util;

import java.util.Map;
import java.util.Optional;

public class WiseSayingFileRepository {
    public void save(WiseSaying wiseSaying) {
        if (wiseSaying.isNew()) {

            int newId = getLastId() + 1;
            wiseSaying.setId(newId);

            setLastId(newId);
        }

        // 객체 정보를 Map으로 바꿔야한다.
        Map<String, Object> wiseSayingMap = wiseSaying.toMap();
        //Map => JSON 형태로 바꾼다
       String wiseSayingJsonStr = Util.json.toString(wiseSayingMap);
        //JSON 파일로 저장
        Util.file.set(getEntityFilePath(wiseSaying.getId()), wiseSayingJsonStr);
    }

     public Optional<WiseSaying> findById(int id) {
        String wiseSayingJsonStr = Util.file.get(getEntityFilePath(id), "");

        if (wiseSayingJsonStr.isBlank()) return Optional.empty();

        Map<String, Object> wiseSayingMap = Util.json.toMap(wiseSayingJsonStr);


         Optional<WiseSaying> opWiseSaying = Optional.of(new WiseSaying(wiseSayingMap));

        return opWiseSaying;
     }

     public boolean delete(WiseSaying wiseSaying) {
        String filePath = getEntityFilePath(wiseSaying.getId());
        return Util.file.delete(filePath);
     }

     private void setLastId(int newId) {
        Util.file.set(getLastIdFilePath(), newId);
     }

     private  int getLastId() {
        return Util.file.getAsInt(getLastIdFilePath(), 0);
     }

     public String getTableDirPath() {
        return "db/wiseSaying";
     }

     public String getEntityFilePath(int id) {
         return getTableDirPath() + "/%d.json".formatted(id);
     }

    public String getLastIdFilePath() {
        return getTableDirPath() +  "/lastId.txt";
    }
}
