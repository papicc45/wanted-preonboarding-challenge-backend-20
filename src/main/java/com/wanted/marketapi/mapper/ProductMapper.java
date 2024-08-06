package com.wanted.marketapi.mapper;

import com.wanted.marketapi.domain.Product;
import com.wanted.marketapi.domain.ProductStatus;
import com.wanted.marketapi.domain.User;
import com.wanted.marketapi.dto.product.ProductRequestDTO;
import com.wanted.marketapi.dto.product.ProductResponseDTO;
import com.wanted.marketapi.dto.user.UserResponseDTO;
import com.wanted.marketapi.exception.ErrorCode;
import com.wanted.marketapi.exception.custom.CustomToProductMapperException;
import com.wanted.marketapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), uses = {UserMapper.class})
public interface ProductMapper {


    ProductResponseDTO ProductToDTO(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "productStatus", ignore = true)
    Product DTOToProduct(ProductRequestDTO productRequestDTO, @Context UserRepository userRepository);

    @AfterMapping
    default void mappingUser(@MappingTarget Product product, ProductRequestDTO productRequestDTO, @Context UserRepository userRepository) {
        User user = userRepository.findById(productRequestDTO.getUserId())
                .orElseThrow(() -> new CustomToProductMapperException(ErrorCode.NOT_FOUND_USERID_FROM_ADD_PRODUCT.getMessage()));
        product.setUser(user);
        System.out.println("asdasd " + user.getId());
    }

}
