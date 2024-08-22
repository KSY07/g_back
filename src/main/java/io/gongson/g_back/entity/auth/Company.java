package io.gongson.g_back.entity.auth;

import io.gongson.g_back.dto.CompanyDTO;
import jakarta.persistence.*;
import lombok.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;

@Entity
@Table(name="company")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Company {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String companyId;
    private String password;
    private String companyName;
    private String companyRegistrationNumber; // 사업자 등록 번호
    private String ceoName;
    private String registrationImgDir;
    private String idCardImgDir;
    private String licenseImgDir;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String companyDescription;
    private LocalDateTime requestTime;
    private @Setter String profileImgDir;
    private double rating;
    private boolean isEnable;
    private boolean isPremium;

    public static CompanyDTO.CompanyInfo toDto(Company c) {
        Path imagePaths = Paths.get(c.getProfileImgDir());
        String ext = c.getProfileImgDir().substring(c.getProfileImgDir().lastIndexOf("."));
        String base64String = String.format("data:image/%s;base64,", ext);
        try {
            byte[] bytes = Files.readAllBytes(imagePaths);
            base64String += Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return CompanyDTO.CompanyInfo.builder()
                .id(c.getId())
                .companyName(c.getCompanyName())
                .companyThumbnail(base64String)
                .companyID(c.getCompanyId())
                .rating(c.getRating())
                .build();
    }
}
