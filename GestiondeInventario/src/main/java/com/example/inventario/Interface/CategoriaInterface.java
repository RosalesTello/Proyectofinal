package com.example.inventario.Interface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.inventario.Entidades.Categoria;

@Repository

public interface CategoriaInterface  extends JpaRepository<Categoria,Integer>{

}
