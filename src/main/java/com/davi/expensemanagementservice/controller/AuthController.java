package com.davi.expensemanagementservice.controller;

import com.davi.expensemanagementservice.config.auth.TokenProvider;
import com.davi.expensemanagementservice.dtos.JwtDTO;
import com.davi.expensemanagementservice.dtos.SignInDTO;
import com.davi.expensemanagementservice.dtos.SignUpDTO;
import com.davi.expensemanagementservice.model.AppUser;
import com.davi.expensemanagementservice.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final TokenProvider tokenProvider;

    public AuthController(AuthenticationManager authenticationManager, AuthService authService, TokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody @Validated SignUpDTO data) {
        authService.signUp(data);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtDTO> signIn(@RequestBody @Validated SignInDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var authUser = authenticationManager.authenticate(usernamePassword);
        var accessToken = tokenProvider.generateAccessToken((AppUser) authUser.getPrincipal());
        return new ResponseEntity<>(new JwtDTO(accessToken), HttpStatus.OK);
    }
}
