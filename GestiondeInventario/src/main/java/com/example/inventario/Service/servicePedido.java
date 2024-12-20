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
	
	@Autowired EmailService emailService;
	
	
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
		
		String destinatario ="rogelio.polaca@gmail.com";
		String asunto = "Confirmación de Pedido";
		String cuerpo = String.format(
		    "Estimado cliente,\n\n" +
		    "Tu pedido ha sido registrado con éxito.\n\n" +
		    "Detalles del Pedido:\n" +
		    "ID del Pedido: %d\n" +
		    "Producto: %s\n" +
		    "Cantidad: %d\n\n" +
		    "Gracias por tu compra.",
		    pedido.getIdPedido(), 
		    producto.getNombre(), 
		    pedido.getCantidad()
		);

		try {
		    emailService.enviarCorreo(destinatario, asunto, cuerpo);
		} catch (Exception e) {
		    System.out.println("Error al enviar email: " + e.getMessage());
		}
		return  new ResponseEntity<>("dato ingresado correctamente y correo enviado ",HttpStatus.CREATED);
		
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
		//busca el pedido si existe 
		Pedido pedidoActualizar=repoPedido.findById(id).orElse(null);
		
		if (pedidoActualizar==null)
		{
			return  new ResponseEntity<>("dato no se encontro",HttpStatus.NOT_FOUND);
			
		}
		

		if(producto.getStock()< pedido.getCantidad()) 
		{
			return  new ResponseEntity<>("stock insuficiente",HttpStatus.CONFLICT);
		}
		//stock esta en producto
		
		producto.setStock((producto.getStock()+pedidoActualizar.getCantidad() )-pedido.getCantidad());    //creqo ue lo suma con la misma cantidad y no con lo anteriror
		repoProducto.save(producto);//se guarda modificado en producto y asi 
		
			
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
