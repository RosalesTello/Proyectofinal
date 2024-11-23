package com.example.inventario.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.inventario.Entidades.Categoria;
import com.example.inventario.Interface.CategoriaInterface;



@Service
public class serviceCategoria {
	
	@Autowired
	private CategoriaInterface repoCategoria ;  //de la interface 
	
	
	public ResponseEntity<Object> listaCategoria()
	
	{
		List<Categoria>listado=repoCategoria.findAll();
		
		if (listado.isEmpty())  //listado=[]
		{
			return new ResponseEntity<>("sin datos",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(listado,HttpStatus.OK);
		
	}
	
	
	public ResponseEntity<Object> buscarporid(int id)
	
	{
		Categoria datoUnico=repoCategoria.findById(id).orElse(null);
		
		if (datoUnico==null)
		{
			return new ResponseEntity<>("sin datos",HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(datoUnico,HttpStatus.OK);
		
	}
	
	
	
	
	public ResponseEntity<Object> agregar(Categoria categoria, BindingResult result)
	
	{
		if (result.hasErrors())
		{
			return new ResponseEntity<>(result.getFieldErrors(),HttpStatus.BAD_REQUEST);
		}
		
		Categoria categoriarepetida=repoCategoria.findByNombre(categoria.getNombre()).orElse(null);
		
		if (categoriarepetida!=null)
			
		{
			return new ResponseEntity<>("No se puede repetir nombre de la categoria",HttpStatus.BAD_REQUEST);
		}
		
		
		
		Categoria nuevoProducto =repoCategoria.findById(categoria.getIdCategoria()).orElse(null);
		
		if (nuevoProducto==null)//aca no devuelve nada  TARE TODO DE ESO OSEA NADA 
		{
			repoCategoria.save(categoria);//aca guarda el producto POR QUE ESE ID NO EXISTE
			return new ResponseEntity<>("Dato nuevo",HttpStatus.CREATED);
			
		}
		
		
		return new ResponseEntity<>("dato no creado",HttpStatus.CONFLICT);
		
	}
	
	
	public ResponseEntity<Object> actualizar (int id,Categoria categoria, BindingResult result)
	
	{
		if (result.hasErrors())
		{
			return new ResponseEntity<>(result.getFieldErrors(),HttpStatus.BAD_REQUEST);
		}
		
		
		Categoria productoActualizar =repoCategoria.findById(id).orElse(null);
		
		if (productoActualizar==null)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
		
		
		productoActualizar.setNombre(categoria.getNombre());  
		repoCategoria.save(productoActualizar);
		
		
		return new ResponseEntity<>("dato Actualizado",HttpStatus.OK);
		
	}
	
	
	public ResponseEntity<Object> eliminar (int id)
	
	{//sin necesidad bilding 
	
		Categoria productoEliminar =repoCategoria.findById(id).orElse(null);
		
		if (productoEliminar==null)
		{
			return new ResponseEntity<>("la categoria con ese id no existe",HttpStatus.NOT_FOUND);
			
		}
		
		repoCategoria.delete(productoEliminar);
		return new ResponseEntity<>("dato eliminado",HttpStatus.OK);
		
	}
	
	
	
	
	
	

}
