package com.techzone.techzone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techzone.techzone.models.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer> {

}
