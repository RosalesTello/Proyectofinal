package com.example.inventario.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity

public class Proveedor {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProveedor;

    @Size(max = 20)
    @Pattern(regexp = "^[A-Z\\s]+$", message = "El nombre debe estar en mayúsculas y solo contener letras y espacios.")
    private String nombre;   //patron 

    @Size(max = 20)
    private String contacto;
    
    

	public Proveedor(int idProveedor,
			@Size(max = 20) @Pattern(regexp = "^[A-Z\\s]+$", message = "El nombre debe estar en mayúsculas y solo contener letras y espacios.") String nombre,
			@Size(max = 20) String contacto) {
		super();
		this.idProveedor = idProveedor;
		this.nombre = nombre;
		this.contacto = contacto;
	}



	public Proveedor() {
		super();
	}



	public int getIdProveedor() {
		return idProveedor;
	}



	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getContacto() {
		return contacto;
	}



	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
    
	
    
    
    

}
