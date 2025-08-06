package com.ll.standard.util;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Util {
    public static class file {
        // 유틸리티 메서드들
        private static Path getPath(String filePath) {
            return Paths.get(filePath);
        }

        // 파일생성
        public static void touch(String filePath) {
            set(filePath, "");
        }

        // 파일 존재유무 체크
        public static boolean exists(String filePath) {
            return Files.exists(getPath(filePath));
        }

        public static boolean notExists(String filePath) {
            return !exists(filePath);
        }

        public static String get(String filePath, String defaultValue) {
            try {
                return Files.readString(getPath(filePath));
            } catch (IOException e) {
               return defaultValue;
            }
        }

        public static int getAsInt(String filePath, int defaultValue) {
            String value = get(filePath, String.valueOf(defaultValue));

            if (value.isBlank()) {
                return defaultValue;
            }

            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }

        public static void set(String filePath, int content) {
            set(filePath, String.valueOf(content));
        }

        public static void set(String filePath, String content) {
            Path path = getPath(filePath);
            try {
                writeFile(path, content);
            } catch (IOException e) {
                handleFileWriteError(path, content, e);
            }
        }

        private static void writeFile(Path path, String content) throws IOException {
            Files.writeString(path, content,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        }

        private static void handleFileWriteError(Path path, String content, IOException e) {
            Path parentDir = path.getParent();
            if (parentDir != null && Files.notExists(parentDir)) {
                try {
                    Files.createDirectories(parentDir);
                    writeFile(path, content);
                } catch (IOException ex) {
                    throw new RuntimeException("파일 쓰기 실패: " + path, ex);
                }
            } else {
                throw new RuntimeException("파일 접근 실패: " + path, e);
            }
        }

        public static void rmdir(String dirPath) {
            delete(dirPath);
        }

        private static class FileDeleteVisitor extends SimpleFileVisitor<Path> {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        }

        // 파일삭제
        public static boolean delete(String filePath) {
            try {
                Files.walkFileTree(getPath(filePath), new FileDeleteVisitor());
                return true;
            } catch (IOException e) {
                return false;
            }
        }
    }

    public static class json {
        public static String toString(List<Map<String, Object>> mapList) {
            StringBuilder sb = new StringBuilder();

            sb.append("[");
            sb.append("\n");

            String indent = "    ";

            mapList.forEach(map -> {
                sb.append(indent);
                sb.append(toString(map).replaceAll("\n", "\n" + indent));
                sb.append(",\n");
            });

            if (!mapList.isEmpty()) {
                sb.delete(sb.length() - 2, sb.length());
            }

            sb.append("\n");
            sb.append("]");

            return sb.toString();
        }

        public static String toString(Map<String, Object> map) {
            StringBuilder sb = new StringBuilder();

            sb.append("{");
            sb.append("\n");

            map.forEach((key, value) -> {
                sb.append("    ");
                key = "\"" + key + "\"";

                if (value instanceof String) {
                    value = "\"" + value + "\"";
                }

                sb.append("%s: %s,\n".formatted(key, value));
            });

            if (!map.isEmpty()) {
                sb.delete(sb.length() - 2, sb.length());
            }

            sb.append("\n");
            sb.append("}");

            return sb.toString();
        }

        public static Map<String, Object> toMap(String jsonStr) {
            Map<String, Object> map = new LinkedHashMap<>();

            jsonStr = jsonStr.substring(1, jsonStr.length() - 1);

            String[] jsonStrBits = jsonStr.split(",\n    \"");

            for (String jsonStrBit : jsonStrBits) {
                jsonStrBit = jsonStrBit.trim();

                if (jsonStrBit.endsWith(",")) jsonStrBit = jsonStrBit.substring(0, jsonStrBit.length() - 1);

                String[] jsonField = jsonStrBit.split("\": ");

                String key = jsonField[0];
                if (key.startsWith("\"")) key = key.substring(1);

                boolean valueIsString = jsonField[1].startsWith("\"") && jsonField[1].endsWith("\"");
                String value = jsonField[1];

                if (valueIsString) value = value.substring(1, value.length() - 1);

                if (valueIsString) {
                    map.put(key, value);
                } else if (value.equals("true") || value.equals("false")) {
                    map.put(key, value.equals("true"));
                } else if (value.contains(".")) {
                    map.put(key, Double.parseDouble(value));
                } else {
                    map.put(key, Integer.parseInt(value));
                }
            }

            return map;
        }
    }
}