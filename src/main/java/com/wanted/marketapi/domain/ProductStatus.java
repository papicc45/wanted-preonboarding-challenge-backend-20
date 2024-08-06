package com.wanted.marketapi.domain;

import lombok.Getter;

import java.security.PrivateKey;
import java.security.PublicKey;

@Getter
public enum ProductStatus {
    FORSALE("판매중"),
    RESERVATION("예약중"),
    COMPLETED("완료");

    private final String status;
    private ProductStatus(String status) {
        this.status = status;
    }
}
