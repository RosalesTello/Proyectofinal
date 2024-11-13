package com.example.inventario.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.inventario.Entidades.Pedido;
import com.example.inventario.Entidades.Producto;
import com.example.inventario.Interface.PedidoInterface;
import com.example.inventario.Interface.ProductoInterface;

@Service
public class servicePedido {
	
	@Autowired  PedidoInterface repoPedido;
	@Autowired ProductoInterface repoProducto;
	
	public ResponseEntity<Object>listado()
	{
		List<Pedido>lista=repoPedido.findAll();//list no es del lenguaje
		if(lista.isEmpty())
		{	
			return new ResponseEntity<>("no hay datos",HttpStatus.NO_CONTENT);
			
		}
		return new ResponseEntity<>(lista,HttpStatus.OK);
	}
	
	public ResponseEntity<Object>filtrado(int id)
	{
		Pedido pedido=repoPedido.findById(id).orElse(null);//list no es del lenguaje
		if(pedido==null)
		{	
			return new ResponseEntity<>("no hay datos",HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<>(pedido,HttpStatus.OK);
	}
	
	public ResponseEntity<Object>agregar(Pedido pedido,BindingResult result)
	{
		if (result.hasFieldErrors())
		{
			return new ResponseEntity<>(result.getFieldErrors(),HttpStatus.CONFLICT);
		}
		
		//producto encontrado con  lo puesto en pedido el id
		Producto producto =repoProducto.findById(pedido.getProducto().getIdProducto()).orElse(null);
		
		
		if (producto==null)
		{
			return new ResponseEntity<>("el id de producto no coincide o no existe ",HttpStatus.NOT_FOUND);
		}
		
		if (producto.getStock()<pedido.getCantidad())
			
		{
			return new ResponseEntity<>("El stock no es sufficeinte con la cantidad ingresada ",HttpStatus.CONFLICT);
		}
		
		producto.setStock(producto.getStock()-pedido.getCantidad());
		repoProducto.save(producto);
		
		repoPedido.save(pedido);
		return  new ResponseEntity<>("dato ingresado correctamente ",HttpStatus.CREATED);
		
	}
	
	public ResponseEntity<Object>actualizar(int id,Pedido pedido,BindingResult result)
	{
		if (result.hasFieldErrors())
		{
			return new ResponseEntity<>(result.getFieldErrors(),HttpStatus.CONFLICT);
		}
		
		Producto producto =repoProducto.findById(pedido.getProducto().getIdProducto()).orElse(null);
		if (producto==null)
		{
			return new ResponseEntity<>("el id de producto no coincide o no existe ",HttpStatus.NOT_FOUND);
		}
		
		Pedido pedidoActualizar=repoPedido.findById(id).orElse(null);
		if (pedidoActualizar==null)
		{
			return  new ResponseEntity<>("dato no se encontro",HttpStatus.NOT_FOUND);
			
		}
		//pedidoActualizar.setIdPedido(pedido.getIdPedido()); veremos si es por el id pedido le paso un dato qu eno va en el cuerpo
		pedidoActualizar.setCantidad(pedido.getCantidad());
		pedidoActualizar.setFecha(pedido.getFecha());
		pedidoActualizar.setProducto(pedido.getProducto());
		repoPedido.save(pedidoActualizar);
		return  new ResponseEntity<>("dato actualizado  correctamente",HttpStatus.OK);
		
	}
	
	public ResponseEntity<Object>eliminar(int id)
	{
		Pedido pedido=repoPedido.findById(id).orElse(null);
		if(pedido==null)
		{
			return new ResponseEntity<>("el id no existe del pedido",HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<>(pedido,HttpStatus.OK);
	}
	
	
	

}
