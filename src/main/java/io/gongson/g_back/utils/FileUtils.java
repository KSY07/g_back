package io.gongson.g_back.utils;

import org.apache.logging.log4j.util.Base64Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Component
public class FileUtils {

    @Value("${app.file.saveDir}")
    private String rootDir;

    public String saveFile(MultipartFile file, FileType type) throws IOException {
        Path path = null;
        switch (type) {
            case CONSTRUCTION_EXAMPLE_IMG -> path = Paths.get(rootDir).resolve("construction_example_img");
            case USER_IMG -> path = Paths.get(rootDir).resolve("user_profile_img");
            case BOARD_IMG -> path = Paths.get(rootDir).resolve("board_img");
        }

        if(!Files.exists(path)) {
            Files.createDirectories(path);
        }

        String filePath = path + file.getOriginalFilename();
        File dest = new File(filePath);
        file.transferTo(dest);

        return filePath;
    }

    public String getImageToBase64(String fileDir) throws IOException {
        Path imagePath = Paths.get(fileDir);
        byte[] bytes = Files.readAllBytes(imagePath);
        return Base64.getEncoder().encodeToString(bytes);
    }
}
