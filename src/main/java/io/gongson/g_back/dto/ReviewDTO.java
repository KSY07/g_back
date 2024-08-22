package io.gongson.g_back.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewDTO {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Create {
        private String title;
        private String contents;
        private double rating;
        private String userId;
        private String companyPk;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class Info {
        private String title;
        private String contents;
        private double rating;
        private String userName;
        private long companyPk;
        private List<String> imageUrlList;
        private LocalDateTime createdDate;
    }
}
