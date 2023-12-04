package com.chatapp.server.controller;

import com.chatapp.server.dto.UserDTO;
import com.chatapp.server.dto.request.LoginRequest;
import com.chatapp.server.dto.request.RegistrationRequest;
import com.chatapp.server.service.AuthService;
import com.chatapp.server.service.CloudinaryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final CloudinaryService cloudinaryService;

    @PostMapping("/register")
    public UserDTO registration (@Valid @RequestBody RegistrationRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public UserDTO registration (@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping
    public String testUpload (@RequestParam("image")MultipartFile image)  {
        return cloudinaryService.uploadFile(image);
    }


}
