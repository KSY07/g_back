package io.gongson.g_back.controller;

import io.gongson.g_back.dto.InteriorTipDTO;
import io.gongson.g_back.service.BoardService;
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
import java.util.Base64;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    @Value("${app.file.saveDir}")
    private String rootDir;

    private final ResourceLoader resourceLoader;
    private final BoardService boardService;

    @PostMapping("/image/upload")
    public ResponseEntity<?> uploadImage(
            @RequestPart("image")MultipartFile file
            ) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        try {
            String postFolderPath = rootDir + "interiorTip\\";
            File postFolder = new File(postFolderPath);
            if(!postFolder.exists()){
                postFolder.mkdirs();
            }

            String originalFilename = file.getOriginalFilename();
            String filePath = postFolderPath + originalFilename;
            file.transferTo(new File(filePath));
            String fileUrl = "http://localhost:8080/static/interiorTip/" + originalFilename;
            System.out.println(fileUrl);
            return ResponseEntity.ok(fileUrl);
        } catch(IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/tips/save")
    public ResponseEntity<?> saveTips(@RequestPart("dto")InteriorTipDTO.Create dto, @RequestPart("thumbnail") MultipartFile thumbnail) {
        try {
            boardService.saveInteriorTip(dto, thumbnail);
            return ResponseEntity.status(201).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/tips/all")
    public ResponseEntity<?> getAllTips() {
        try {
            return ResponseEntity.ok(boardService.getInteriorTips());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/tips")
    public ResponseEntity<?> getTip(@RequestParam String id)
    {
        try {
            return ResponseEntity.ok(boardService.getInteriorTip(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).build();
        }
    }

}
