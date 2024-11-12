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

import com.example.inventario.Entidades.Proveedor;
import com.example.inventario.Service.serviceProveedor;

import jakarta.validation.Valid;

@RequestMapping("/Proveedores")
@RestController
public class ProveedorControlador {
	
	@Autowired serviceProveedor servicioproveedor;
	
	@GetMapping 
	public ResponseEntity<Object>lista()
	{
		return  servicioproveedor.lista() ;
	}
	
	
	@GetMapping("/filtrado/{id}")
	public ResponseEntity<Object>filtrado(@PathVariable int id)
	{
		return servicioproveedor.filtrado(id);
		
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<Object> agregar(@Valid @RequestBody Proveedor provedor,BindingResult result)
	{
		return servicioproveedor.agregar(provedor, result);
		
	}
	
	@PutMapping("/actualizar/{id}")          					//solo para validar     		     // para ver los errores 
	public ResponseEntity<Object>actualizar(@PathVariable int id,@Valid @RequestBody Proveedor provedor,BindingResult result)
	
	{
		return servicioproveedor.actaulizar(id, provedor, result);
		
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Object>eliminar(@PathVariable int id)
	{
		return servicioproveedor.eliminar(id);
	}

}
