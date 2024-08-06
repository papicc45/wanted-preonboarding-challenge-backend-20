package com.wanted.marketapi.controller;

import com.wanted.marketapi.dto.error.ErrorResponse;
import com.wanted.marketapi.dto.product.ProductRequestDTO;
import com.wanted.marketapi.dto.product.ProductResponseDTO;
import com.wanted.marketapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<ProductResponseDTO> insertProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO productResponseDTO = productService.insertProduct(productRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDTO);
    }

    @GetMapping("/error")
    public ResponseEntity<ErrorResponse> getError() {
        ErrorResponse errorResponse = new ErrorResponse("E999", "Test error message");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
