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

import com.techzone.techzone.models.Proveedor;
import com.techzone.techzone.services.ProveedorService;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

	
	@Autowired
	private ProveedorService servicio;
	
	@GetMapping
	public ResponseEntity<List<Proveedor>> listarProveedores(){
		List<Proveedor> proveedores = servicio.listarProveedores();
		if (proveedores.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(proveedores);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Proveedor> obtenerPorId(@PathVariable int id) {
		Proveedor proveedor = servicio.obtenerPorId(id);
		if (proveedor == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(proveedor);
	}
	
	@PostMapping
	public ResponseEntity<Proveedor> agregarProveedor(@RequestBody Proveedor proveedor) {
		try {
			Proveedor nuevoProv = servicio.agregarProveedores(proveedor);
			return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProv);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}		
	}
	
	@DeleteMapping("/{id}")
	public String eliminarProveedor(@PathVariable int id) {
		Proveedor proveedor = servicio.obtenerPorId(id);
		if (proveedor == null) {
			return "Proveedor no Existe";
		}
		servicio.eliminarProveedor(id);
		return "Proveedor eliminado";		
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<Proveedor> actualizarProveedor(@PathVariable int id, @RequestBody Proveedor proveedor) {
		Proveedor actualizar = servicio.obtenerPorId(id);
		actualizar.setNom_prov(proveedor.getNom_prov());
		
		if (actualizar == null) {
			return ResponseEntity.notFound().build();
		}
		else {
			try {
				Proveedor nuevo = servicio.actualizarProveedor(actualizar);
				return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}	
		}
	}

}
