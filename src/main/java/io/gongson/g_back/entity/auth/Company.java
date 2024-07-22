package io.gongson.g_back.entity.auth;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="company")
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private LocalDateTime requestTime;
    private @Setter String profileImgDir;
    private double rating;
    private boolean isEnable;
    private boolean isPremium;
}
