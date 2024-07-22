package io.gongson.g_back.controller;

import io.gongson.g_back.dto.CompanyDTO;
import io.gongson.g_back.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/req")
    public ResponseEntity<?> addCompany(@RequestPart CompanyDTO.AddCompanyRequest dto, @RequestPart("imgFile") MultipartFile imgFile) {
        try {
            if(authService.addCompany(dto, imgFile)) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(null);
            }
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
