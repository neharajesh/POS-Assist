package com.example.posassist.dto.request;

import com.example.posassist.entities.OrderItem;
import com.example.posassist.enums.OrderType;
import lombok.Data;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class OrderDTO {
    private String orderName;

    private String orderDetails;

    private Date dateOfOrder;

    private Double total;

    private OrderType orderType;

    private Set<OrderItem> orderItems = new HashSet<OrderItem>();
}
