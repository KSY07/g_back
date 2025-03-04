package io.gongson.g_back.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class ConstructionExampleDTO {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        private String title;
        private String contents;
        private long companyPk;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Info {
        private String title;
        private String contents;
        private List<String> imgUrlList;
        private LocalDateTime createdDate;
    }
}
