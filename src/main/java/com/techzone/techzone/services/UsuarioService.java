package com.techzone.techzone.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techzone.techzone.models.Usuario;
import com.techzone.techzone.repositories.IUsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private IUsuarioRepository repoUsua;
	
	public List<Usuario> listarUsuarios() {
		return repoUsua.findAll();
	}

	public Usuario obtenerPorId(int id) {
		return repoUsua.findById(id).orElse(null);
	}

	@Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario registrarUsuario(Usuario usuario) {
        String claveCodificada = passwordEncoder.encode(usuario.getClave());
        usuario.setClave(claveCodificada);
        return repoUsua.save(usuario);
    }

	public void eliminarUsuario(int id) {
		repoUsua.deleteById(id);
	}

	public Usuario actualizarUsuario(Usuario usuario) {
		return repoUsua.save(usuario);
	}
}
