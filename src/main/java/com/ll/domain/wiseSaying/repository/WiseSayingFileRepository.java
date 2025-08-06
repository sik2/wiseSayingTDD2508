package com.ll.domain.wiseSaying.repository;

import com.ll.domain.wiseSaying.entity.WiseSaying;
import com.ll.standard.util.Util;

import java.util.Map;

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
        Util.file.set("db/wiseSaying/1.json", wiseSayingJsonStr);
    }

     public WiseSaying findById(int id) {
        String wiseSayingJsonStr = Util.file.get("db/wiseSaying/%d.json".formatted(id), "");

        Map<String, Object> wiseSayingMap = Util.json.toMap(wiseSayingJsonStr);


        WiseSaying wiseSaying = new WiseSaying(wiseSayingMap);

        return wiseSaying;
     }

     public void delete(WiseSaying wiseSaying) {

     }

     private void setLastId(int newId) {
        Util.file.set("db/wiseSaying/lastId.json", newId);
     }

     private  int getLastId() {
        return Util.file.getAsInt("db/wiseSaying/lastId.json", 0);
     }
}
