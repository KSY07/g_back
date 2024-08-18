package io.gongson.g_back.controller;

import io.gongson.g_back.service.AuthService;
import io.gongson.g_back.service.CompanyService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final AuthService authService;
    private final CompanyService companyService;

    @GetMapping("/list")
    public ResponseEntity<?> getList() {
        try {
            return ResponseEntity.ok(companyService.getCompanyList());
        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> get(@RequestParam int companyId) {
        try {
            return ResponseEntity.ok(companyService.getCompanyById(companyId));
        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).build();
        }
    }
}
