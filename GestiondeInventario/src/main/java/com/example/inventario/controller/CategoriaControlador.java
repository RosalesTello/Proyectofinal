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

import com.example.inventario.Entidades.Categoria;
import com.example.inventario.Service.serviceCategoria;

import jakarta.validation.Valid;


@RequestMapping("/Categorias")
@RestController

public class CategoriaControlador {
	
	@Autowired  serviceCategoria sevicio;
	
	@GetMapping
	public ResponseEntity<Object>todo()
	{
		return sevicio.listaCategoria();
		
	}
	
	
	@GetMapping("/filtrado/{id}")
	public ResponseEntity<Object>filtrado(@PathVariable int id)
	{
		return sevicio.buscarporid(id);
		
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<Object>agregarObjeto(@Valid @RequestBody Categoria categoria,BindingResult result)
	{
		return sevicio.agregar(categoria,result);
	}
	
	
	@DeleteMapping("/eliminar/{id}")//no es necesario bilding solo cuando mandas como post y put
	public ResponseEntity<Object>eliminarObjeto(@PathVariable int id )
	{
		return sevicio.eliminar(id);
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<Object>agregarObjeto(@PathVariable int id,@Valid @RequestBody Categoria categoria,BindingResult result)
	{
		return sevicio.actualizar(id,categoria,result);
	}
	

}
