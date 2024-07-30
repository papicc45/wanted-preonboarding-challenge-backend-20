package com.wanted.marketapi.service;

import com.wanted.marketapi.domain.Product;
import com.wanted.marketapi.domain.ProductStatus;
import com.wanted.marketapi.dto.product.ProductRequestDTO;
import com.wanted.marketapi.dto.product.ProductResponseDTO;
import com.wanted.marketapi.mapper.ProductMapper;
import com.wanted.marketapi.repository.ProductRepository;
import com.wanted.marketapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    private final ProductMapper productMapper;


    public ProductResponseDTO insertProduct(ProductRequestDTO productRequestDTO) {
        Product product = productMapper.DTOToProduct(productRequestDTO, userRepository);
        System.out.println("asdasd" + product.getUser().getId());
        product.setProductStatus(ProductStatus.FORSALE);
        Product savedProduct = productRepository.save(product);
        return productMapper.ProductToDTO(savedProduct);
    }
}
