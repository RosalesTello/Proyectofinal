package com.example.inventario.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Categoria {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategoria;

    @Size(max = 100)
    @Pattern(regexp = "^[A-Z\\s]+$", message = "El nombre debe estar en mayúsculas y solo contener letras y espacios.")
    private String nombre;
    
    
    
	public Categoria(int idCategoria,
			@Size(max = 100) @Pattern(regexp = "^[A-Z\\s]+$", message = "El nombre debe estar en mayúsculas y solo contener letras y espacios.") String nombre) {
		super();
		this.idCategoria = idCategoria;
		this.nombre = nombre;
	}


	public Categoria() {
		super();
	}


	public int getIdCategoria() {
		return idCategoria;
	}


	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	

}
