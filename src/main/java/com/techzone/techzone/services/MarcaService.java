package com.techzone.techzone.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techzone.techzone.models.Marca;
import com.techzone.techzone.repositories.IMarcaRepository;

@Service
public class MarcaService {

	@Autowired
	private IMarcaRepository repoMarca;
	
	public List<Marca> listarMarcas() {
		return repoMarca.findAll();
	}

	public Marca obtenerPorId(int id) {
		return repoMarca.findById(id).orElse(null);
	}

	public Marca agregarMarca(Marca marca) {
		return repoMarca.save(marca);
	}

	public void eliminarMarca(int id) {
		repoMarca.deleteById(id);
	}

	public Marca actualizarMarca(Marca marca) {
		return repoMarca.save(marca);
	}
}
