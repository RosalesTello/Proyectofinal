package com.example.inventario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.inventario.Entidades.Producto;
import com.example.inventario.Service.serviceProducto;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("/Productos")
@RestController
public class ProductoControlador {
	
	@Autowired serviceProducto servicioproducto;
	
	@GetMapping
	public ResponseEntity<Object> listadoProductos()
	
	{
		 return servicioproducto.listado();
	}
	
	@GetMapping("/filtrado/{id}")
	public ResponseEntity<Object> filtrado(@PathVariable int id)
	
	{
		 return servicioproducto.filtrado(id);
	}
	
	
	@PostMapping("/agregar")
	public ResponseEntity<Object> agregar(@Valid @RequestBody Producto producto ,BindingResult result) {
		
		return servicioproducto.agregar(producto, result);
	}
	
	
	@PutMapping("/actualizar/{id}")
	
	public ResponseEntity<Object>actualziar(@PathVariable int id,@RequestBody Producto producto,BindingResult result)
	{
		return servicioproducto.actualizar(id, producto, result);
	}
	
	@DeleteMapping("/eliminacionlogica/{id}")
	
	public ResponseEntity<Object>eliminacionlogica(@PathVariable int id)
	
	{
		return servicioproducto.eliminarLogica(id);
	}
}



