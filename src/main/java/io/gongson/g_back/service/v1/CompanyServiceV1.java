package io.gongson.g_back.service.v1;

import io.gongson.g_back.dto.CompanyDTO;
import io.gongson.g_back.entity.auth.Company;
import io.gongson.g_back.repository.CompanyRepository;
import io.gongson.g_back.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceV1 implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public List<CompanyDTO.CompanyInfo> getCompanyList() {
        return companyRepository.findAll().stream().map(Company::toDto).toList();
    }

    @Override
    public CompanyDTO.CompanyInfo getCompanyById(int id) {
        long lid = id;
        return Company.toDto(companyRepository.findById(lid).orElseThrow(
                () -> new RuntimeException("Not Valid Company Pk")
        ));
    }
}
