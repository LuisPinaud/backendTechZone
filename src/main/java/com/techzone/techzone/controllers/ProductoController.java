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

import com.techzone.techzone.models.Producto;
import com.techzone.techzone.services.ProductoService;



@RestController
@RequestMapping("/api/productos")
public class ProductoController {
	
	@Autowired
	private ProductoService servicio;
	
	@GetMapping
	public ResponseEntity<List<Producto>> listarProductos(){
		List<Producto> productos = servicio.listarProductos();
		if (productos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(productos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Producto> obtenerPorId(@PathVariable int id) {
		Producto producto = servicio.obtenerPorId(id);
		if (producto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(producto);
	}
	
	@PostMapping
	public ResponseEntity<Producto> agregarProducto(@RequestBody Producto producto) {
		try {
			Producto nuevo = servicio.agregarProducto(producto);
			return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}		
	}
	
	@DeleteMapping("/{id}")
	public String eliminarProducto(@PathVariable int id) {
		Producto producto = servicio.obtenerPorId(id);
		if (producto == null) {
			return "Producto no Existe";
		}
		servicio.eliminarProducto(id);
		return "Producto eliminado";		
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<Producto> actualizarProducto(@PathVariable int id, @RequestBody Producto producto) {
		Producto actualizar = servicio.obtenerPorId(id);
		actualizar.setNom_prod(producto.getNom_prod());
		actualizar.setDes_prod(producto.getDes_prod());
		actualizar.setCod_cat(producto.getCod_cat());
		actualizar.setStock_prod(producto.getStock_prod());
		actualizar.setPrecio_compra(producto.getPrecio_compra());
		actualizar.setCod_prov(producto.getCod_prov());
		actualizar.setCod_marca(producto.getCod_marca());
		
		if (actualizar == null) {
			return ResponseEntity.notFound().build();
		}
		else {
			try {
				Producto nuevo = servicio.actualizarProducto(actualizar);
				return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}	
		}
	}

}
