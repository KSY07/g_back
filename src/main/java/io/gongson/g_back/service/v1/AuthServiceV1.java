package io.gongson.g_back.service.v1;

import io.gongson.g_back.dto.CompanyDTO;
import io.gongson.g_back.entity.auth.Company;
import io.gongson.g_back.repository.CompanyRepository;
import io.gongson.g_back.service.AuthService;
import io.gongson.g_back.utils.FileType;
import io.gongson.g_back.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthServiceV1 implements AuthService {
    private final FileUtils fileUtils;
    private final CompanyRepository companyRepository;

    @Override
    public boolean addCompany(CompanyDTO.AddCompanyRequest dto, MultipartFile file) {
        Company company = Company.builder()
                .companyId(dto.getCompanyId())
                .password(dto.getCompanyPassword())
                .companyName(dto.getCompanyName())
                .build();

        try {
            company.setProfileImgDir(fileUtils.saveFile(file, FileType.USER_IMG));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        companyRepository.save(company);
        return true;
    }
}
