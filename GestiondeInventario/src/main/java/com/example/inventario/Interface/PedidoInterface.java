package com.example.inventario.Interface;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.inventario.Entidades.Pedido;

public interface PedidoInterface  extends JpaRepository<Pedido, Integer>{
	
	

}
