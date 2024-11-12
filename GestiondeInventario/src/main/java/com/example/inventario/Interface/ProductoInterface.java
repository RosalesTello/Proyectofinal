package com.example.inventario.Interface;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.inventario.Entidades.Producto;

public interface ProductoInterface extends JpaRepository <Producto,Integer>{
	
	Optional <Producto> findByCodigo(String producto); //para que busque en codigo en la columna
	//se espera un dato producto con opcional puede esatr vacio
}
