package com.techzone.techzone.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techzone.techzone.models.Proveedor;
import com.techzone.techzone.repositories.IProveedorRepository;

@Service
public class ProveedorService {

	@Autowired
	private IProveedorRepository repoProv;
	
	public List<Proveedor> listarProveedores() {
		return repoProv.findAll();
	}

	public Proveedor obtenerPorId(int id) {
		return repoProv.findById(id).orElse(null);
	}

	public Proveedor agregarProveedores(Proveedor proveedor) {
		return repoProv.save(proveedor);
	}

	public void eliminarProveedor(int id) {
		repoProv.deleteById(id);
	}

	public Proveedor actualizarProveedor(Proveedor proveedor) {
		return repoProv.save(proveedor);
	}
	
}
