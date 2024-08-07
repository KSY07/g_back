package io.gongson.g_back.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class FileUtils {

    @Value("${app.file.saveDir}")
    private String rootDir;

    private final ApplicationContext ctx;

    public String saveFile(MultipartFile file, FileType type) throws IOException {
        Environment env = ctx.getEnvironment();
        String profile = env.getDefaultProfiles()[0];
        Path path = null;
        switch (type) {
            case CONSTRUCTION_EXAMPLE_IMG -> path = Paths.get(rootDir).resolve("construction_example_img");
            case USER_IMG -> path = Paths.get(rootDir).resolve("user_profile_img");
            case BOARD_IMG -> path = Paths.get(rootDir).resolve("board_img");
            case BOARD_THUMBNAIL -> path = Paths.get(rootDir).resolve("board_thumbnail");
        }

        if(!Files.exists(path)) {
            Files.createDirectories(path);
        }

        String filePath = path.toString();
        switch(profile) {
            case "dev":
                filePath += "\\" +file.getOriginalFilename();
                break;
            case "real":
                filePath += "/" + file.getOriginalFilename();
                break;
        }
        File dest = new File(filePath);
        file.transferTo(dest);

        return filePath;
    }

    public String getImageToBase64(String fileDir) throws IOException {
        Path imagePath = Paths.get(fileDir);
        byte[] bytes = Files.readAllBytes(imagePath);
        return Base64.getEncoder().encodeToString(bytes);
    }

    public String encodeFileToBase64(MultipartFile file) throws IOException {
        try {
            byte[] bytes = file.getBytes();
            return Base64.getEncoder().encodeToString(bytes);
        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
