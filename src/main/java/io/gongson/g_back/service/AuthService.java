package io.gongson.g_back.service;

import io.gongson.g_back.dto.CompanyDTO;
import org.springframework.web.multipart.MultipartFile;

public interface AuthService {

    public boolean addCompany(CompanyDTO.AddCompanyRequest dto, MultipartFile file);
}
