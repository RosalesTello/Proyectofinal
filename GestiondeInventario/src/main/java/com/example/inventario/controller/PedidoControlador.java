package com.example.inventario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.inventario.Entidades.Pedido;
import com.example.inventario.Service.servicePedido;

import jakarta.validation.Valid;

@RequestMapping("/Pedidos")
@RestController
public class PedidoControlador {
	
	@Autowired servicePedido serviciopedido;
	
	@GetMapping
	public ResponseEntity<Object>listado()
	{
		return serviciopedido.listado();
	}
	
	@GetMapping("/filtrado/{id}")
	public ResponseEntity<Object>filtrado(@PathVariable int id)
	{
		return serviciopedido.filtrado(id);
	}
	
	@PostMapping("/agregar") //el valid siempre para el cuerpo
	public ResponseEntity<Object>agregar(@Valid @RequestBody Pedido pedido,BindingResult result)
	{
		return serviciopedido.agregar(pedido, result);
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<Object>actualizar(@PathVariable int id ,@RequestBody Pedido pedido,BindingResult result)
	{
		return serviciopedido.actualizar(id, pedido, result);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Object>eliminar(@PathVariable int id)
	{
		return serviciopedido.eliminar(id);
	}

}
