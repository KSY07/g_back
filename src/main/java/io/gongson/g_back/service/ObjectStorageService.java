package io.gongson.g_back.service;

import io.gongson.g_back.utils.FileType;
import org.springframework.web.multipart.MultipartFile;

public interface ObjectStorageService {
    String uploadFile(MultipartFile file);
}
