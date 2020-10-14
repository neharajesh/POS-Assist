package com.example.posassist.dto.request;

import com.example.posassist.enums.ItemType;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ItemDTO {
    @NotBlank
    private String itemName;

    @NotBlank
    private ItemType itemType;

    @NotBlank
    private Double price;


}
