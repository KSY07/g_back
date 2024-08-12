package io.gongson.g_back.dto;

import io.gongson.g_back.entity.auth.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

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
        private String companyThumbnail;
        private String companyID;
        private Double rating;
        private boolean isPremium;
    }
}
