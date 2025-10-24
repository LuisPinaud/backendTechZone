package com.techzone.techzone.utils;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.techzone.techzone.models.Usuario;
import com.techzone.techzone.repositories.IUsuarioRepository;

@Component
public class EncryptPasswords implements CommandLineRunner {

    private final IUsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public EncryptPasswords(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(String... args) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        for (Usuario u : usuarios) {
            String clave = u.getClave();
            if (!clave.startsWith("$2a$")) {
                u.setClave(encoder.encode(clave));
                usuarioRepository.save(u);
                System.out.println("🔒 Encriptada clave de: " + u.getUsuario());
            }
        }
        System.out.println("✅ Todas las contraseñas fueron encriptadas correctamente.");
    }
}
