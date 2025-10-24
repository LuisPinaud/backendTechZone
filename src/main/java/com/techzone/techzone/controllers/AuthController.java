package com.techzone.techzone.controllers;


import com.techzone.techzone.security.JwtUtil;
import com.techzone.techzone.security.CustomUserDetails;
import com.techzone.techzone.services.CustomDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> user) {
        String username = user.get("usuario"); // antes era "correo"
        String password = user.get("clave");

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String rol = userDetails.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");

        String token = jwtUtil.generarToken(username, rol);

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("rol", rol);
        response.put("usuario", username); // antes era "correo"

        return ResponseEntity.ok(response);
    }
}
