package com.wanted.marketapi.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthenticationResponseDTO {
    private final String jwt;
}
