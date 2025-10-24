package com.techzone.techzone.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="tb_producto")
@Data
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cod_prod")
	private int codprod;
	private String nom_prod;
	private String des_prod;
	private int cod_cat;
	private int stock_prod;
	private double precio_compra;
	private int cod_prov;
	private int cod_marca;
	
	public int getCodprod() {
		return codprod;
	}

	public void setCodprod(int codprod) {
		this.codprod = codprod;
	}

	public String getNom_prod() {
		return nom_prod;
	}

	public void setNom_prod(String nom_prod) {
		this.nom_prod = nom_prod;
	}

	public String getDes_prod() {
		return des_prod;
	}

	public void setDes_prod(String des_prod) {
		this.des_prod = des_prod;
	}

	public int getCod_cat() {
		return cod_cat;
	}

	public void setCod_cat(int cod_cat) {
		this.cod_cat = cod_cat;
	}

	public int getStock_prod() {
		return stock_prod;
	}

	public void setStock_prod(int stock_prod) {
		this.stock_prod = stock_prod;
	}

	public double getPrecio_compra() {
		return precio_compra;
	}

	public void setPrecio_compra(double precio_compra) {
		this.precio_compra = precio_compra;
	}

	public int getCod_prov() {
		return cod_prov;
	}

	public void setCod_prov(int cod_prov) {
		this.cod_prov = cod_prov;
	}

	public int getCod_marca() {
		return cod_marca;
	}

	public void setCod_marca(int cod_marca) {
		this.cod_marca = cod_marca;
	}

	public Categoria getObjCategoria() {
		return objCategoria;
	}

	public void setObjCategoria(Categoria objCategoria) {
		this.objCategoria = objCategoria;
	}

	public Proveedor getObjProveedor() {
		return objProveedor;
	}

	public void setObjProveedor(Proveedor objProveedor) {
		this.objProveedor = objProveedor;
	}

	public Marca getObjMarca() {
		return objMarca;
	}

	public void setObjMarca(Marca objMarca) {
		this.objMarca = objMarca;
	}

	@ManyToOne
	@JoinColumn(name = "cod_cat",insertable=false,updatable=false)
	private Categoria objCategoria;
	
	@ManyToOne
	@JoinColumn(name = "cod_prov",insertable=false,updatable=false)
	private Proveedor objProveedor;
	
	@ManyToOne
	@JoinColumn(name = "cod_marca",insertable=false,updatable=false)
	private Marca objMarca;
	
}
