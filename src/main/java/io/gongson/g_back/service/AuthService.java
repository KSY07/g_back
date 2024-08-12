package io.gongson.g_back.service;

import io.gongson.g_back.dto.AuthDTO;
import io.gongson.g_back.dto.CompanyDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface AuthService {
    public boolean addCompany(CompanyDTO.AddCompanyRequest dto, MultipartFile file);
    public Map<String, Object> signIn(AuthDTO.SignIn dto);
    public boolean logout(String userId);
    public boolean signUp(AuthDTO.SignUp dto);
}
