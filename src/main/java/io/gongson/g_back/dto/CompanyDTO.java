package io.gongson.g_back.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CompanyDTO {

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    public static class AddCompanyRequest {
        private String companyId;
        private String companyPassword;
        private String companyName;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    public static class CompanyInfo {
        private String companyName;
        private Double rating;
        private boolean isPremium;

    }
}
