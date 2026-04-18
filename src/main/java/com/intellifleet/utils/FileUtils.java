package com.intellifleet.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@UtilityClass
public class FileUtils {
    private final String EXTERNAL_RESOURCES_BASE_PATH = "external-resources";

    public void createFile(String strPath, String content) {
        try {
            Path path = Paths.get(strPath); // file will be created in project root
            Files.write(path, content.getBytes());
            log.info("File created: {}", path.toAbsolutePath());
        } catch (IOException e) {
            log.info("Error: {}", e.getMessage());
        }
    }

    public String readFile(Path path) {
        try {
            return Files.readString(path);
        } catch (IOException e) {
            log.info("Error: {}", e.getMessage());
        }
        return "";
    }

    public Path getFilePathByType(String object) {
        Path dirPath = Paths.get(EXTERNAL_RESOURCES_BASE_PATH, object);
        if(Files.exists(dirPath) && Files.isDirectory(dirPath)) {
            return dirPath;
        }
        return null;
    }
}
