package com.example.inventario.Interface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.inventario.Entidades.Proveedor;

@Repository
public interface ProveedorInterface extends JpaRepository<Proveedor,Integer> {
	
	

}
