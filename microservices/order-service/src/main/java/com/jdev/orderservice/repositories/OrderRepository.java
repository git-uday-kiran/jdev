package com.jdev.orderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdev.orderservice.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
