package com.example.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ecommerce.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

}
