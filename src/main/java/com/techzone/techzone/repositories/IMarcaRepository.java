package com.techzone.techzone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techzone.techzone.models.Marca;

@Repository
public interface IMarcaRepository extends JpaRepository<Marca, Integer> {

}
