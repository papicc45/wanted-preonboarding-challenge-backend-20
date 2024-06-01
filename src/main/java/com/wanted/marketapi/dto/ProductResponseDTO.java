package com.wanted.marketapi.dto;

import com.wanted.marketapi.domain.ProductStatus;
import jakarta.persistence.SqlResultSetMapping;
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
