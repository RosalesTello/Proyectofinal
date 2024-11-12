package com.example.inventario.Service;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.inventario.Entidades.Categoria;
import com.example.inventario.Entidades.Producto;
import com.example.inventario.Entidades.Proveedor;
import com.example.inventario.Interface.CategoriaInterface;
import com.example.inventario.Interface.ProductoInterface;
import com.example.inventario.Interface.ProveedorInterface;

@Service
public class serviceProducto {
	
	@Autowired ProveedorInterface repoProvedor;
	
	@Autowired CategoriaInterface repoCategoria;
	
	@Autowired  ProductoInterface repoProducto;
		
	public ResponseEntity<Object>listado()
	{
		List<Producto>list=repoProducto.findAll();
		
		if (list.isEmpty())
		{
			return new ResponseEntity<>("No ha datos",HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	public ResponseEntity<Object>filtrado(int id)
	
	{
		Producto producto=repoProducto.findById(id).orElse(null);
		
		if (producto==null)
		{
			return new ResponseEntity<>("No ha datos",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(producto,HttpStatus.OK);
		
	}
	
	
	public ResponseEntity<Object>agregar(Producto producto,BindingResult result)
	
	{
		if(result.hasErrors())
		{
			return new ResponseEntity<>(result.getFieldError(),HttpStatus.BAD_REQUEST);
			
		}
		//verificando la en la otra tabla si existe el ide en agregar producto
		Categoria productoCategoria=repoCategoria.findById(producto.getCategoria().getIdCategoria()).orElse(null);
		
		if (productoCategoria==null)
		{
			return new ResponseEntity<>("la categoria que colocaste no existe",HttpStatus.NOT_FOUND);
		}
		//verificando la en la otra tabla si existe el id en agregar producto
		Proveedor productoProveedor=repoProvedor.findById(producto.getProveedor().getIdProveedor()).orElse(null);
		
		if (productoProveedor==null)
		{
			return new ResponseEntity<>("la categoria que colocaste no existe",HttpStatus.NOT_FOUND);
		}
		
		Producto productoCodigo=repoProducto.findByCodigo(producto.getCodigo()).orElse(null);
		if (productoCodigo==null)
		{
			repoProducto.save(producto);
			return new ResponseEntity<>("daro agregado",HttpStatus.CREATED);
		}
		
		return new ResponseEntity<>("Codigo repetido",HttpStatus.CONFLICT);
	}
	
	public ResponseEntity<Object>actualizar(int id,Producto producto,BindingResult result)
	
	{
		if(result.hasErrors())
		{
			return new ResponseEntity<>(result.getFieldError(),HttpStatus.BAD_REQUEST);
			
		}
							// obtiene el objeto Categoria que está asociado con el Producto 
		Categoria productoCategoria=repoCategoria.findById(producto.getCategoria().getIdCategoria()).orElse(null);
		
		if (productoCategoria==null)
		{
			return new ResponseEntity<>("la categoria que colocaste no existe",HttpStatus.NOT_FOUND);
		}
		
		Proveedor productoProveedor=repoProvedor.findById(producto.getProveedor().getIdProveedor()).orElse(null);
		
		if (productoProveedor==null)
		{
			return new ResponseEntity<>("la categoria que colocaste no existe",HttpStatus.NOT_FOUND);
		}
		
		Producto productoActualziar=repoProducto.findById(id).orElse(null);
		if (productoActualziar==null)
		{
			return new ResponseEntity<>("El producto no existe",HttpStatus.NOT_FOUND);
		}
		productoActualziar.setCodigo(producto.getCodigo());
		productoActualziar.setNombre(producto.getNombre());
		productoActualziar.setStock(producto.getStock());
		productoActualziar.setEstado(producto.getEstado());// aca puede ser inactivo o activo
		productoActualziar.setCategoria(producto.getCategoria());
		productoActualziar.setProveedor(producto.getProveedor());
		
		repoProducto.save(productoActualziar);
		return new ResponseEntity<>("dato actualizado",HttpStatus.OK);
		
	}
	
	public ResponseEntity<Object>eliminarLogica(int id)
	
	{
		Producto productorEliminar =repoProducto.findById(id).orElse(null);
		if (productorEliminar!= null && productorEliminar.getEstado().equals("Activo"))
		{
			productorEliminar.setEstado("Inactivo");
			repoProducto.save(productorEliminar);
			return new ResponseEntity<>("Selimino producto de forma logica",HttpStatus.OK);
		}
		
		return new ResponseEntity<>("N se puede eliminar por que esta inactivo el producto",HttpStatus.OK);
		
	}
	
}
