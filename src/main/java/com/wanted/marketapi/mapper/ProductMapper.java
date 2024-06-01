package com.wanted.marketapi.mapper;

import com.wanted.marketapi.domain.Product;
import com.wanted.marketapi.dto.ProductRequestDTO;
import com.wanted.marketapi.dto.ProductResponseDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ProductMapper {

    ProductResponseDTO ProductToDTO(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "productStatus", ignore = true)
    @Mapping(target = "user.id", source = "userId")
    Product DTOToProduct(ProductRequestDTO productRequestDTO);
}
