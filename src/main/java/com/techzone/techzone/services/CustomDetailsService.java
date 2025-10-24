package com.techzone.techzone.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.techzone.techzone.models.Usuario;
import com.techzone.techzone.repositories.IUsuarioRepository;
import com.techzone.techzone.security.CustomUserDetails;

@Service
public class CustomDetailsService implements UserDetailsService{

    @Autowired
    private IUsuarioRepository repoUsua;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	Usuario usuario = repoUsua.findByUsuario(username);
        return new CustomUserDetails(usuario);
    }
}
