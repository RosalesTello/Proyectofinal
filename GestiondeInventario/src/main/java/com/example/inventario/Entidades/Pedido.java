package com.example.inventario.Entidades;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;

@Entity
public class Pedido {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPedido;


    @ManyToOne
    @JoinColumn(name = "idproducto", referencedColumnName = "idProducto", nullable = false)
    private Producto producto;

    @Min(value = 1, message = "La cantidad debe ser al menos 1.")
    private int cantidad;
    
    private LocalDate fecha;

	public Pedido() {
		super();
	}

	public Pedido(int idPedido, Producto producto,
			@Min(value = 1, message = "La cantidad debe ser al menos 1.") int cantidad, LocalDate fecha) {
		super();
		this.idPedido = idPedido;
		this.producto = producto;
		this.cantidad = cantidad;
		this.fecha = fecha;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
    
    

}
