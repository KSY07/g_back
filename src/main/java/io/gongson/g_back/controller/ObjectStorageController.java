package io.gongson.g_back.controller;

import io.gongson.g_back.service.ObjectStorageService;
import io.gongson.g_back.utils.FileType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/object")
@RequiredArgsConstructor
public class ObjectStorageController {

    private final ObjectStorageService objectStorageService;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestPart("targetFile")MultipartFile file) {
        try {
            String res = objectStorageService.uploadFile(file);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }
}
