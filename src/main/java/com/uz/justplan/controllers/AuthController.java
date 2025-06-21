package com.uz.justplan.controllers;

import com.uz.justplan.beans.AuthRequest;
import com.uz.justplan.beans.AuthResponse;
import com.uz.justplan.beans.CommonResp;
import com.uz.justplan.config.security.CustomUserDetails;
import com.uz.justplan.config.security.JwtUtil;
import com.uz.justplan.services.CompanyDashboardService;
import com.uz.justplan.utils.Validation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Log log = LogFactory.getLog(AuthController.class);
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private CompanyDashboardService dashboardService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {
        if (!Validation.isEmpty(authRequest.getCompanyCode()) && !authRequest.getUsername().contains(";")) {
            authRequest.setUsername(authRequest.getUsername() + ";" + authRequest.getCompanyCode());
        }
        String pass = authRequest.getPassword().trim();
        {
            if (pass.startsWith("password_0c")) {
                // this is case to login on Company Setup, password passed isalready encrypted
                System.out.println("----------------------------------------------" + pass);
                pass = pass.replaceAll("password_0c", "");
                System.out.println("----------------------------------------------" + pass);
                System.out.println("----------------------------------------------" + pass.indexOf("password_@c"));
                pass = pass.substring(0, pass.indexOf("password_@c"));
            } else {
                //pass = SecurePasswordGenerator.encryptPassword(pass);
            }
        }
        authRequest.setUsername(authRequest.getUsername().trim());
        System.out.println("----------------------------------------------" + pass);
        authRequest.setPassword(pass);

        //try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
/*        } catch (Exception e) {
            log.error(e.getMessage(), e);
            AuthResponse resp = new AuthResponse("");
            resp.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(resp);
            //throw new Exception("Incorrect username or password", e);
        }*/
        System.out.println("User authenticated successfully: " + authRequest.getUsername());

        final CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        System.out.println("Id: " + jwtUtil.extractId(jwt));
        AuthResponse resp = new AuthResponse(jwt);
        resp.setDetails(dashboardService.getLoggedInDetails(jwtUtil.extractId(jwt)));
        //return ResponseEntity.ok(Map.of("token", jwt));
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/metadata")
    public ResponseEntity<Map<String, Object>> getMetadata() throws Exception {
        return ResponseEntity.ok(dashboardService.getMetadata());
    }

    @PostMapping("/reset-password")
    public CommonResp resetPassword(@RequestBody AuthRequest authRequest) throws Exception {
        return dashboardService.resetPassword(authRequest.getUsername());
    }
}