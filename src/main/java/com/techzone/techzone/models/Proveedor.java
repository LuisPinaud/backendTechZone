package com.techzone.techzone.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="tb_proveedor")
@Data
public class Proveedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cod_prov")
	private int codprov;
	private String nom_prov;
	public int getCodprov() {
		return codprov;
	}
	public void setCodprov(int codprov) {
		this.codprov = codprov;
	}
	public String getNom_prov() {
		return nom_prov;
	}
	public void setNom_prov(String nom_prov) {
		this.nom_prov = nom_prov;
	}
	
}
