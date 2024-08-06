package io.gongson.g_back.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    @Value("${app.file.saveDir}")
    private String rootDir;

    private final ResourceLoader resourceLoader;

    @PostMapping("/image/upload")
    public ResponseEntity<?> uploadImage(
            @RequestPart("image")MultipartFile file
            ) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Path staticPath = Paths.get(resourceLoader.getResource("classpath:static/images/interiorTip").getURI());
            System.out.println(staticPath.toAbsolutePath());
            if(!Files.exists(staticPath)){
                Files.createDirectories(staticPath);
            }

            String originalFilename = file.getOriginalFilename();
            String filePath = staticPath + originalFilename;
            file.transferTo(new File(filePath));
            String fileUrl = "http://localhost:8080/images/" + originalFilename;
            System.out.println(fileUrl);
            return ResponseEntity.ok(fileUrl);
        } catch(IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).build();
        }
    }
}
