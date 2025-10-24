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

import com.techzone.techzone.models.Marca;
import com.techzone.techzone.services.MarcaService;

@RestController
@RequestMapping("/api/marcas")
public class MarcaController {

	@Autowired
	private MarcaService servicio;
	
	@GetMapping
	public ResponseEntity<List<Marca>> listarMarcas(){
		List<Marca> marcas = servicio.listarMarcas();
		if (marcas.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(marcas);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Marca> obtenerPorId(@PathVariable int id) {
		Marca marca = servicio.obtenerPorId(id);
		if (marca == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(marca);
	}
	
	@PostMapping
	public ResponseEntity<Marca> agregarMarca(@RequestBody Marca marca) {
		try {
			Marca nuevaMarca = servicio.agregarMarca(marca);
			return ResponseEntity.status(HttpStatus.CREATED).body(nuevaMarca);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}		
	}
	
	@DeleteMapping("/{id}")
	public String eliminarMarca(@PathVariable int id) {
		Marca marca = servicio.obtenerPorId(id);
		if (marca == null) {
			return "Marca no Existe";
		}
		servicio.eliminarMarca(id);
		return "Marca eliminada";		
	}


	@PutMapping("/{id}")
	public ResponseEntity<Marca> actualizarMarca(@PathVariable int id, @RequestBody Marca marca) {
		Marca actualizar = servicio.obtenerPorId(id);
		actualizar.setNom_marca(marca.getNom_marca());
		actualizar.setPai_marca(marca.getPai_marca());
		
		if (actualizar == null) {
			return ResponseEntity.notFound().build();
		}
		else {
			try {
				Marca nuevo = servicio.actualizarMarca(actualizar);
				return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}	
		}
	}

}
