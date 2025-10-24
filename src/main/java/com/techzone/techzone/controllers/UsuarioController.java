package com.techzone.techzone.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.techzone.techzone.models.Usuario;
import com.techzone.techzone.services.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

	@Autowired
	private UsuarioService servicio;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping
	public ResponseEntity<List<Usuario>> listarUsuarios() {
		List<Usuario> usuarios = servicio.listarUsuarios();
		if (usuarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(usuarios);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> obtenerPorId(@PathVariable int id) {
		Usuario usuario = servicio.obtenerPorId(id);
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuario);
	}

	@PostMapping("/registrar")
	public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario) {
		try {
			Usuario nuevo = servicio.registrarUsuario(usuario);
			return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@DeleteMapping("/{id}")
	public String eliminarUsuario(@PathVariable int id) {
		Usuario usuario = servicio.obtenerPorId(id);
		if (usuario == null) {
			return "Usuario no Existe";
		}
		servicio.eliminarUsuario(id);
		return "Usuario eliminado";
	}

	@PutMapping("/{id}")
	public ResponseEntity<Usuario> actualizarUsuario(@PathVariable int id, @RequestBody Usuario usuario) {
		Usuario actualizar = servicio.obtenerPorId(id);

		if (actualizar == null) {
			return ResponseEntity.notFound().build();
		}

		actualizar.setNomUsu(usuario.getNomUsu());
		actualizar.setApe_usu(usuario.getApe_usu());
		actualizar.setDni_usu(usuario.getDni_usu());
		actualizar.setEmail(usuario.getEmail());
		actualizar.setTelefono(usuario.getTelefono());
		actualizar.setDireccion(usuario.getDireccion());
		actualizar.setUsuario(usuario.getUsuario());
		actualizar.setRol(usuario.getRol());

		if (usuario.getClave() != null && !usuario.getClave().isEmpty()) {
			actualizar.setClave(passwordEncoder.encode(usuario.getClave()));
		}

		try {
			Usuario nuevo = servicio.actualizarUsuario(actualizar);
			return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/login-test")
    public String loginTest() {
        return "Login correcto: " + SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
