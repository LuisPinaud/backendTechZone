package com.techzone.techzone.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techzone.techzone.models.Producto;
import com.techzone.techzone.repositories.IProductoRepository;

@Service
public class ProductoService {

	@Autowired
	private IProductoRepository repoProd;

	public List<Producto> listarProductos() {
		return repoProd.findAll();
	}

	public Producto obtenerPorId(int id) {
		return repoProd.findById(id).orElse(null);
	}

	public Producto agregarProducto(Producto producto) {
		return repoProd.save(producto);
	}

	public void eliminarProducto(int id) {
		repoProd.deleteById(id);
	}

	public Producto actualizarProducto(Producto producto) {
		return repoProd.save(producto);
	}
}
