package com.uz.justplan.controllers;

import com.uz.justplan.beans.AuthRequest;
import com.uz.justplan.config.security.CustomUserDetails;
import com.uz.justplan.config.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<Map<String, String>> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {
        System.out.println("Authenticating user: " + authRequest.getUsername());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("Incorrect username or password", e);
        }
        System.out.println("User authenticated successfully: " + authRequest.getUsername());

        final CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(authRequest.getUsername());
        System.out.println("Generating JWT..." + userDetails);
        final String jwt = jwtUtil.generateToken(userDetails);
        System.out.println("Generated JWT: " + jwt);
        System.out.println("Name: " + jwtUtil.extractName(jwt));
        return ResponseEntity.ok(Map.of("token", jwt));
    }
}