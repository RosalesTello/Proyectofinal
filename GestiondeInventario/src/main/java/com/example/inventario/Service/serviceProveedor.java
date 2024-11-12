package com.example.inventario.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.inventario.Entidades.Proveedor;
import com.example.inventario.Interface.ProveedorInterface;

@Service
public class serviceProveedor {
	
	@Autowired ProveedorInterface repoProveedor;
	
	public ResponseEntity<Object>lista()
	{
		List<Proveedor> list=repoProveedor.findAll();
		
		if (list.isEmpty())
		{
			return new ResponseEntity<>("sin datos",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	
	public ResponseEntity<Object> filtrado (int id)
	{
		Proveedor provedor =repoProveedor.findById(id).orElse(null);
		if (provedor==null)
		{
			return new ResponseEntity<>("dato no existe",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(provedor,HttpStatus.OK);
	}
	
	
	public ResponseEntity<Object>agregar(Proveedor provedor,BindingResult result)
	{
		if(result.hasErrors())
		{
			return new ResponseEntity<>(result.getFieldError(),HttpStatus.BAD_REQUEST);
		}
		
		Proveedor Provedor=repoProveedor.findById(provedor.getIdProveedor()).orElse(null);
		if(Provedor==null)
		{
			repoProveedor.save(provedor);
			return new ResponseEntity<>("nuevo insercion",HttpStatus.CREATED);
		}
		return new ResponseEntity<>("no se agrego el dato ",HttpStatus.CONFLICT);
	}
	
	public ResponseEntity<Object>actaulizar(int id ,Proveedor provedor,BindingResult result)
	{
		if(result.hasErrors())
		{
			return new ResponseEntity<>(result.getFieldError(),HttpStatus.BAD_REQUEST);
		}
		
		Proveedor provedorActualizar=repoProveedor.findById(id).orElse(null);
		if(provedorActualizar==null)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		provedorActualizar.setNombre(provedor.getNombre());
		provedorActualizar.setContacto(provedor.getContacto());
		repoProveedor.save(provedorActualizar);
		return new ResponseEntity<>("dato guardado" ,HttpStatus.OK);
	}
	
	public ResponseEntity<Object> eliminar (int id)
	{
		Proveedor provedorEliminar =repoProveedor.findById(id).orElse(null);
		if (provedorEliminar==null)
		{
			return new ResponseEntity<>("proveedor no existe",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(provedorEliminar,HttpStatus.OK);
	}
	

}
