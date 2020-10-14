package com.example.posassist.dto.request;

import com.example.posassist.entities.Item;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class OrderItemDTO {

    private Long itemId;

    private String instructions;

    private Integer quantity = 1;
}