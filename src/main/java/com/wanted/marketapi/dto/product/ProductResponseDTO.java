package com.wanted.marketapi.dto.product;

import com.wanted.marketapi.domain.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductResponseDTO {
    private long id;
    private String name;
    private int price;
    private ProductStatus productStatus;
}
