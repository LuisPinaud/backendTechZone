package com.techzone.techzone.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techzone.techzone.models.Categoria;
import com.techzone.techzone.repositories.ICategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private ICategoriaRepository repoCat;
	
	public List<Categoria> listarCategorias() {
		return repoCat.findAll();
	}

	public Categoria obtenerPorId(int id) {
		return repoCat.findById(id).orElse(null);
	}

	public Categoria agregarCategoria(Categoria categoria) {
		return repoCat.save(categoria);
	}

	public void eliminarCategoria(int id) {
		repoCat.deleteById(id);
	}

	public Categoria actualizarCategoria(Categoria categoria) {
		return repoCat.save(categoria);
	}
}
