package com.wanted.marketapi.dto.product;

import com.wanted.marketapi.domain.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductRequestDTO {
    private String name;
    private int price;
    private long userId;
    private ProductStatus productStatus;
}
