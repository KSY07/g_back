package io.gongson.g_back.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class InteriorTipDTO {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Create {
        private String title;
        private String content;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class Info {
        private String id;
        private String title;
        private String content;
        private LocalDateTime createdAt;
        private int likes;
        private String thumbnailBase64String;
    }
}
