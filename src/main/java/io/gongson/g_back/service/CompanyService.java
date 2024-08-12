package io.gongson.g_back.service;

import io.gongson.g_back.dto.CompanyDTO;
import io.gongson.g_back.entity.auth.Company;

import java.util.List;

public interface CompanyService {
    List<CompanyDTO.CompanyInfo> getCompanyList();
}
