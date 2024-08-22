package io.gongson.g_back.controller;

import io.gongson.g_back.dto.CompanyDTO;
import io.gongson.g_back.dto.ConstructionExampleDTO;
import io.gongson.g_back.dto.InteriorTipDTO;
import io.gongson.g_back.dto.ReviewDTO;
import io.gongson.g_back.service.BoardService;
import io.gongson.g_back.service.CompanyService;
import io.gongson.g_back.service.ConstructionExampleService;
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
import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final ConstructionExampleService constructionExampleService;
    @Value("${app.file.saveDir}")
    private String rootDir;

    private final ResourceLoader resourceLoader;
    private final BoardService boardService;
    private final CompanyService companyService;

    @PostMapping("/image/upload")
    public ResponseEntity<?> uploadImage(
            @RequestPart("image")MultipartFile file
            ) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        try {
            String postFolderPath = rootDir + "interiorTip/";
            File postFolder = new File(postFolderPath);
            if(!postFolder.exists()){
                postFolder.mkdirs();
            }

            String originalFilename = file.getOriginalFilename();
            String filePath = postFolderPath + originalFilename;
            file.transferTo(new File(filePath));
            String fileUrl = "http://10.79.9.79:8080/static/interiorTip/" + originalFilename;
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

    @PostMapping("/reviews")
    public ResponseEntity<?> createReview(@RequestPart("dto")ReviewDTO.Create dto, @RequestPart("images") List<MultipartFile> images)
    {
        try {
            boardService.createReview(dto, images);
            return ResponseEntity.status(HttpServletResponse.SC_CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/reviews")
    public ResponseEntity<?> getReviews(@RequestParam long companyPk) {
        try {
            return ResponseEntity.ok(boardService.getReviews(companyPk));
        } catch(Exception e) {
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/constructionExample")
    public ResponseEntity<?> createConstructionExample(@RequestPart("dto")ConstructionExampleDTO.Create dto, @RequestPart("images") List<MultipartFile> images) {
        try {
            boardService.createConstructionExample(dto, images);
            return ResponseEntity.status(HttpServletResponse.SC_CREATED).build();
        } catch(Exception e) {
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/constructionExample")
    public ResponseEntity<?> getConstructionExample(@RequestParam long companyPk) {
        try {
            return ResponseEntity.ok(boardService.getConstructionExamples(companyPk));
        } catch(Exception e) {
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/companyInfo")
    public ResponseEntity<?> getCompanyPageInfos(@RequestParam int companyPk) {
        try {
            CompanyDTO.CompanyInfo cInfo = companyService.getCompanyById(companyPk);
            CompanyDTO.InfoPage infos = CompanyDTO.InfoPage.builder()
                    .companyID(cInfo.getCompanyID())
                    .companyName(cInfo.getCompanyName())
                    .companyThumbnail(cInfo.getCompanyThumbnail())
                    .isPremium(cInfo.isPremium())
                    .rating(cInfo.getRating())
                    .constructionExampleList(boardService.getConstructionExamples(companyPk))
                    .reviewList(boardService.getReviews(companyPk))
                    .build();
            return ResponseEntity.ok(infos);
        } catch(Exception e) {
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).build();
        }
    }

}
