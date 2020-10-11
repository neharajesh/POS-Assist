package com.example.posassist.entities;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

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
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderName;

    private String orderDetails;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @ManyToMany
    private Set<OrderItems> orderItems = new HashSet<OrderItems>();

    private Date dateOfOrder;

    private Double total;
}

