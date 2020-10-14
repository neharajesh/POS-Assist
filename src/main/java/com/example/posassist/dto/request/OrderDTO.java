package com.example.posassist.dto.request;

import com.example.posassist.enums.OrderType;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Data
public class OrderDTO {
    private String orderName;

    private Set<OrderItemDTO> orderItems = new HashSet<OrderItemDTO>();

    private String orderType;
}
