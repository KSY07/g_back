package io.gongson.g_back.service.v1;

import io.gongson.g_back.dto.AuthDTO;
import io.gongson.g_back.dto.CompanyDTO;
import io.gongson.g_back.entity.auth.Company;
import io.gongson.g_back.entity.auth.User;
import io.gongson.g_back.repository.CompanyRepository;
import io.gongson.g_back.repository.UserRepository;
import io.gongson.g_back.service.AuthService;
import io.gongson.g_back.utils.FileType;
import io.gongson.g_back.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceV1 implements AuthService {
    private final FileUtils fileUtils;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

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

    @Override
    public Map<String, Object> signIn(AuthDTO.SignIn dto) {
        String userId = dto.getUserId();
        String password = dto.getPassword();
        boolean isCompany = dto.isCompany();
        Map<String, Object> result = new HashMap<>();
        if(isCompany) {
            Company c = companyRepository.findByCompanyIdAndPassword(userId,password).orElseThrow(
                    () -> new RuntimeException("Company Not Found")
            );

            result.put("companyId", c.getCompanyId());
            result.put("companyName", c.getCompanyName());
            result.put("companyCeoName", c.getCeoName());
            result.put("pk", c.getId());
            result.put("isPremium", c.isPremium());

            return result;
        } else {
            User u = userRepository.findByUserIdAndPassword(userId, password).orElseThrow(
                    () -> new RuntimeException("User Id Not Found")
            );

            result.put("userId", userId);
            result.put("name", u.getName());
            result.put("email", u.getEmail());
            result.put("phone", u.getPhone());
            result.put("providers", u.getProviders());
            result.put("isAdmin", u.isAdmin());

            return result;
        }
    }

    @Override
    public boolean logout(String userId) {
        return false;
    }

    @Override
    public boolean signUp(AuthDTO.SignUp dto) {
        try {
            userRepository.save(User.toEntity(dto));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
