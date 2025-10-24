package com.techzone.techzone.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techzone.techzone.models.Categoria;
import com.techzone.techzone.services.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService servicio;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> listarCategorias(){
		List<Categoria> categorias = servicio.listarCategorias();
		if (categorias.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(categorias);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> obtenerPorId(@PathVariable int id) {
		Categoria categoria = servicio.obtenerPorId(id);
		if (categoria == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(categoria);
	}
	
	@PostMapping
	public ResponseEntity<Categoria> agregarCategoria(@RequestBody Categoria categoria) {
		try {
			Categoria nuevaCat = servicio.agregarCategoria(categoria);
			return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCat);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}		
	}
	
	@DeleteMapping("/{id}")
	public String eliminarCategoria(@PathVariable int id) {
		Categoria categoria = servicio.obtenerPorId(id);
		if (categoria == null) {
			return "Categoria no Existe";
		}
		servicio.eliminarCategoria(id);
		return "Categoria eliminada";		
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> actualizarCategoria(@PathVariable int id, @RequestBody Categoria categoria) {
		Categoria actualizar = servicio.obtenerPorId(id);
		actualizar.setNom_cat(categoria.getNom_cat());
		
		if (actualizar == null) {
			return ResponseEntity.notFound().build();
		}
		else {
			try {
				Categoria nuevo = servicio.actualizarCategoria(actualizar);
				return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}	
		}
	}

	
}
