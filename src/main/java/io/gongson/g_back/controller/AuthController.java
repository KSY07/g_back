package io.gongson.g_back.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.gongson.g_back.dto.AuthDTO;
import io.gongson.g_back.dto.CompanyDTO;
import io.gongson.g_back.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;
import java.util.Map;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private ObjectMapper mapper = new ObjectMapper();

    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@RequestBody AuthDTO.SignIn dto, HttpServletResponse response) {
        try {
            Map<String, Object> userInfo = authService.signIn(dto);
//            Cookie userCookie = new Cookie("userInfo", URLEncoder.encode(mapper.writeValueAsString(userInfo), "UTF-8"));
//            userCookie.setMaxAge(86400);
//            response.addCookie(userCookie);
            return ResponseEntity.ok(userInfo);
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

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
