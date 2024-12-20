package com.example.inventario.Entidades;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity

public class Producto {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProducto;

   
    @Size(max = 10)
    @Pattern(regexp = "^[A-Z][0-9]{3}$", message = "El código debe comenzar con una letra mayúscula seguida de tres dígitos (por ejemplo, A001).")
    private String codigo;

    
    @Size(max = 20)
    @Pattern(regexp = "^[A-Z][a-zA-Z\\s]*$", message = "El nombre debe comenzar con una letra mayúscula.")
    private String nombre;


    private int stock;

    @Size(max = 20)
    @Pattern(regexp = "^[A-Z][a-zA-Z\\s]*$", message = "El Estado debe comenzar con una letra mayúscula.")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "idcategoria", referencedColumnName = "idCategoria", nullable = false)
    
    private Categoria categoria;//aca hace referencia ala tabla producto

    @ManyToOne       //nombre de la tabla 
    @JoinColumn(name = "idproveedor", referencedColumnName = "idProveedor", nullable = false)
    private Proveedor proveedor;//el atributo  podria ir igual pero para la siguiete 

	public Producto() {
		super();
	}

	public Producto(int idProducto,
			@Size(max = 10) @Pattern(regexp = "^[A-Z][0-9]{3}$", message = "El código debe comenzar con una letra mayúscula seguida de tres dígitos (por ejemplo, A001).") String codigo,
			@Size(max = 20) @Pattern(regexp = "^[A-Z][a-zA-Z\\s]*$", message = "El nombre debe comenzar con una letra mayúscula.") String nombre,
			int stock,
			@Size(max = 20) @Pattern(regexp = "^[A-Z][a-zA-Z\\s]*$", message = "El Estado debe comenzar con una letra mayúscula.") String estado,
			Categoria categoria, Proveedor proveedor) {
		super();
		this.idProducto = idProducto;
		this.codigo = codigo;
		this.nombre = nombre;
		this.stock = stock;
		this.estado = estado;
		this.categoria = categoria;
		this.proveedor = proveedor;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	
    
    

}
