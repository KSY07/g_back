package io.gongson.g_back.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class InteriorTipDTO {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Create {
        private String title;
        private String content;
    }
}
