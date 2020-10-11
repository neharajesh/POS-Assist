package com.example.posassist.repositories;
import java.util.Date;
import java.util.Optional;

import com.example.posassist.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
    Optional<Order> findByDateOfOrder(Date orderDate);

}