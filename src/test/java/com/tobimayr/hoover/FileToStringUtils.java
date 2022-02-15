package com.tobimayr.hoover;

import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;

public class FileToStringUtils {

    @SneakyThrows
    public static String getJsonContent(String filename) {
        byte[] bytes = new ClassPathResource("/json/" + filename + ".json").getInputStream().readAllBytes();
        return new String(bytes);
    }
}
