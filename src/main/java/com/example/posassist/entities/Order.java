package com.example.posassist.entities;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Positive;

import com.example.posassist.enums.OrderStatus;
import com.example.posassist.enums.OrderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @OneToMany
    private Set<OrderItem> orderItems = new HashSet<OrderItem>();

    private Date dateOfOrder;

    @Positive
    private Double total;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}

