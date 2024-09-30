package com.pajakcodes.ecms.controller;

import com.pajakcodes.ecms.dto.AuthResponse;
import com.pajakcodes.ecms.dto.SystemUser;
import com.pajakcodes.ecms.service.SystemUserService;
import io.jsonwebtoken.Jwts;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.util.Date;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final SystemUserService systemUserService;
    private final SecretKey secretKey;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid SystemUser loginRequest) {
        SystemUser user = systemUserService.findByUsername(loginRequest.getUsername());
        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {

            String token = Jwts.builder()
                    .setSubject(user.getUsername())
                    .claim("permissions", user.getPermissions())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 600000))
                    .signWith(secretKey)
                    .compact();

            return ResponseEntity.ok(new AuthResponse(token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
